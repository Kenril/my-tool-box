# Rust Code & Utilities

Collection of Rust code examples, utilities, and development reference materials.

## 📁 Contents

### log_level.rs
Custom logging system implementation with environment-based configuration.

**Features:**
- Environment-configurable log levels (ERROR, WARN, INFO, DEBUG)
- Structured log messages with timestamps
- Conditional logging based on system log level
- Custom traits for extensible logging

**Usage:**
```rust
use crate::log_level::{LogMessage, Print};

// Create log messages
let error_msg = LogMessage::error("Something went wrong");
let info_msg = LogMessage::info("Process completed");

// Print messages (respects SYS_LOG_LEVEL environment variable)
error_msg.print();
info_msg.print();
```

**Environment Configuration:**
```bash
export SYS_LOG_LEVEL=DEBUG  # Shows all messages
export SYS_LOG_LEVEL=ERROR  # Shows only errors
```

### true_logging.rs
Advanced logging utilities and patterns.

**Features:**
- Extended logging functionality
- Production-ready logging patterns
- Performance-optimized implementations

## 🚀 Quick Start

### Prerequisites
- **Rust 1.70+** (latest stable recommended)
- **Cargo** (comes with Rust installation)

### Using the Code
1. **Copy relevant files** to your Rust project
2. **Add dependencies** as needed (check individual files)
3. **Configure environment** variables for logging
4. **Import and use** the utilities in your code

### Example Project Setup
```bash
# Create new Rust project
cargo new my-project
cd my-project

# Copy utilities
cp /path/to/log_level.rs src/
cp /path/to/true_logging.rs src/

# Add to main.rs or lib.rs
mod log_level;
mod true_logging;
```

## 🛠️ Rust Development Commands

### Code Formatting
```bash
cargo fmt
```
Automatically formats your code. Can be customized with a `.rustfmt.toml` file.

**Example .rustfmt.toml:**
```toml
max_width = 100
hard_tabs = false
tab_spaces = 4
```

### Code Linting
```bash
cargo clippy
```
Detects code idiomatic "errors" and warns about them. Essential for writing idiomatic Rust.

**Advanced usage:**
```bash
cargo clippy -- -D warnings  # Treat warnings as errors
cargo clippy --all-targets   # Check all targets including tests
```

### Documentation Generation
```bash
cargo doc --no-deps --open
```
Creates documentation from source code comments without including dependencies documentation. Opens the documentation in your browser.

### Testing
```bash
cargo test              # Run all tests
cargo test --release    # Run tests in release mode
cargo test -- --nocapture  # Show println! output in tests
```

### Building
```bash
cargo build             # Debug build
cargo build --release   # Optimized release build
cargo run               # Build and run
```

## 📚 Rust Documentation Patterns

### Comment Types
- `///` - **Single line Outer documentation** (documents the item that follows)
- `//!` - **Single line Inner documentation** (documents the enclosing item)
- `/** */` - **Multi-line Outer documentation**
- `/*! */` - **Multi-line Inner documentation**

### Documentation Examples
```rust
//! This module provides logging utilities
//! 
//! # Examples
//! ```
//! use crate::log_level::LogMessage;
//! let msg = LogMessage::info("Hello");
//! ```

/// Creates a new log message with ERROR level
/// 
/// # Arguments
/// * `msg` - The message to log
/// 
/// # Examples
/// ```
/// let error = LogMessage::error("Failed to connect");
/// ```
pub fn error<S: Into<String>>(msg: S) -> Self {
    // implementation
}
```

## 🦀 Rust Language Features & Patterns

### Closure Syntax
```rust
// Basic closure syntax: |parameters| expression
let add = |x, y| x + y;
let result = add(5, 3); // 8

// Multi-line closures
let complex_operation = |x: i32| {
    let doubled = x * 2;
    let squared = doubled * doubled;
    squared + 1
};

// Closures with move semantics
let data = vec![1, 2, 3];
let closure = move |x| data.iter().sum::<i32>() + x;
```

### Error Handling Patterns
```rust
// Result type usage
fn might_fail() -> Result<String, &'static str> {
    if some_condition {
        Ok("Success".to_string())
    } else {
        Err("Something went wrong")
    }
}

// Using ? operator
fn chain_operations() -> Result<i32, Box<dyn std::error::Error>> {
    let value = might_fail()?;
    let number: i32 = value.parse()?;
    Ok(number * 2)
}
```

### Trait Implementation Examples
```rust
// Custom trait definition
trait Print {
    fn print(&self);
}

// Implementation for custom type
impl Print for LogMessage {
    fn print(&self) {
        if self.log_level() <= self.sys_log_level() {
            println!("{}", self);
        }
    }
}
```

## 🔧 Dependencies

### Common Crates Used
```toml
[dependencies]
time = "0.3"           # Time handling (used in log_level.rs)
serde = "1.0"          # Serialization (if needed)
tokio = "1.0"          # Async runtime (if needed)
```

### Development Dependencies
```toml
[dev-dependencies]
criterion = "0.5"      # Benchmarking
proptest = "1.0"       # Property-based testing
```

## 🧪 Testing Patterns

### Unit Tests
```rust
#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_log_level_ordering() {
        assert!(LogLevel::ERROR < LogLevel::WARN);
        assert!(LogLevel::WARN < LogLevel::INFO);
        assert!(LogLevel::INFO < LogLevel::DEBUG);
    }
}
```

### Integration Tests
```rust
// tests/integration_test.rs
use my_crate::LogMessage;

#[test]
fn test_logging_integration() {
    std::env::set_var("SYS_LOG_LEVEL", "DEBUG");
    let msg = LogMessage::debug("Test message");
    // Test logging behavior
}
```

## 🚀 Performance Tips

### Optimization Flags
```toml
[profile.release]
opt-level = 3
lto = true
codegen-units = 1
panic = 'abort'
```

### Benchmarking
```rust
use criterion::{black_box, criterion_group, criterion_main, Criterion};

fn benchmark_logging(c: &mut Criterion) {
    c.bench_function("log_message_creation", |b| {
        b.iter(|| LogMessage::info(black_box("test message")))
    });
}

criterion_group!(benches, benchmark_logging);
criterion_main!(benches);
```

## 🔄 Future Improvements

- [ ] Add async logging support
- [ ] Implement structured logging (JSON output)
- [ ] Add log rotation capabilities
- [ ] Create macro-based logging interface
- [ ] Add configuration file support
- [ ] Implement custom formatters

## 📖 Learning Resources

- [The Rust Programming Language Book](https://doc.rust-lang.org/book/)
- [Rust by Example](https://doc.rust-lang.org/rust-by-example/)
- [The Cargo Book](https://doc.rust-lang.org/cargo/)
- [Rust API Guidelines](https://rust-lang.github.io/api-guidelines/)
- [Effective Rust](https://www.lurklurk.org/effective-rust/)

## 📝 Notes

- **Edition**: Code uses Rust 2021 edition features
- **Safety**: All code follows Rust's memory safety principles
- **Performance**: Optimized for zero-cost abstractions
- **Compatibility**: Tested with stable Rust releases
