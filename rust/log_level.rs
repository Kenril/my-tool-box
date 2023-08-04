use std::char::ParseCharError;
use std::env;
use std::ffi::OsStr;
use std::fmt::{Display, Formatter};
use std::str::FromStr;

use time::OffsetDateTime;

pub const SYS_LOG_LEVEL: &str = "SYS_LOG_LEVEL";

#[derive(Debug, PartialOrd, PartialEq, Clone, Eq)]
pub enum LogLevel {
  ERROR,
  WARN,
  INFO,
  DEBUG,
}

impl LogLevel {
  fn to_str(&self) -> &str {
    match self {
      LogLevel::ERROR => { "ERROR" }
      LogLevel::WARN => { "WARN" }
      LogLevel::INFO => { "INFO" }
      LogLevel::DEBUG => { "DEBUG" }
    }
  }

  fn as_os_str(&self) -> &OsStr {
    OsStr::new(self.to_str())
  }
}

impl Display for LogLevel {
  fn fmt(&self, f: &mut Formatter<'_>) -> std::fmt::Result {
    write!(f, "{}", self.to_str())
  }
}

impl AsRef<OsStr> for LogLevel {
  fn as_ref(&self) -> &OsStr {
    OsStr::new(self.as_os_str())
  }
}

impl FromStr for LogLevel {
  type Err = ParseCharError;

  fn from_str(s: &str) -> Result<Self, Self::Err> {
    let log_level = match s.to_lowercase().as_str() {
      "error" => LogLevel::ERROR,
      "warn" => LogLevel::WARN,
      "info" => LogLevel::INFO,
      "debug" | _ => LogLevel::DEBUG,
    };
    Ok(log_level)
  }
}

#[derive(Debug)]
pub struct LogMessage {
  /// Logging level of the application, runtime
  sys_log_level: LogLevel,
  /// Log level of the message
  log_level: LogLevel,
  /// The message to be logged
  msg: String,
}

impl LogMessage {
  pub fn error<S: Into<String>>(msg: S) -> Self {
    Self::new(LogLevel::ERROR, msg)
  }

  pub fn warn<S: Into<String>>(msg: S) -> Self {
    Self::new(LogLevel::WARN, msg)
  }

  pub fn info<S: Into<String>>(msg: S) -> Self {
    Self::new(LogLevel::INFO, msg)
  }

  pub fn debug<S: Into<String>>(msg: S) -> Self {
    Self::new(LogLevel::DEBUG, msg)
  }

  fn new<S: Into<String>>(log_level: LogLevel, msg: S) -> Self {
    Self { sys_log_level: Self::get_sys_log_level(), log_level, msg: msg.into() }
  }

  fn get_sys_log_level() -> LogLevel {
    return match env::var(SYS_LOG_LEVEL) {
      Ok(v) => {
        match LogLevel::from_str(v.as_str()) {
          Ok(log_level) => {
            log_level
          }
          Err(_) => {
            println!("This shouldn't happen...");
            LogLevel::WARN
          }
        }
      }
      Err(e) => {
        println!("Value {} not found in env vars : {}", SYS_LOG_LEVEL, e);
        LogLevel::WARN
      }
    };
  }

  pub fn sys_log_level(&self) -> &LogLevel {
    &self.sys_log_level
  }

  pub fn log_level(&self) -> &LogLevel {
    &self.log_level
  }
}

impl Display for LogMessage {
  fn fmt(&self, f: &mut Formatter<'_>) -> std::fmt::Result {
    let t = OffsetDateTime::now_utc();
    let (h, m, s) = t.time().as_hms();
    let timestamp: String = format!("{}T{}:{}:{}", t.date(), h, m, s);
    write!(f, "{} | {:?} | {}", timestamp, self.log_level, self.msg)
  }
}

pub trait Print {
  fn print(&self);
}

impl Print for LogMessage {
  fn print(&self) {
    if self.log_level() <= self.sys_log_level() {
      println!("{}", self);
    }
  }
}