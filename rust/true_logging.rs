use std::str::FromStr;
use log4rs::append::file::FileAppender;
use log4rs::config::{Config, Appender, Root};
use log4rs::encode::pattern::PatternEncoder;

use log::LevelFilter;

pub fn log_setup(log_level: &str) {
  let log_filter: LevelFilter = LevelFilter::from_str(log_level).unwrap_or(LevelFilter::Warn);
  let logfile = FileAppender::builder()
      .encoder(Box::new(PatternEncoder::new("{l} - {m}\n")))
      .build("log/output.log").unwrap();

  let config = Config::builder()
      .appender(Appender::builder().build("logfile", Box::new(logfile)))
      .build(Root::builder()
          .appender("logfile")
          .build(log_filter)).unwrap();

  log4rs::init_config(config).expect("TODO: panic message");

  log::info!("Hello, world!");
  log
}