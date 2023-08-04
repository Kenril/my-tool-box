# Rust commands

`cargo fmt`
Automatically formats your code. Can be customised with a .rustfmt.toml file

`cargo clippy`
Detects code idiomatic "errors" and warns about them.

`cargo doc --no-deps --open`
Let's you create documentation from source code comments without including dependencies documentation. It also opens the documentation.
Comment types :
- /// for single line Outer documentation
- //! for single line Inner documentation
- /** for multi-line Outer documentation
- /*! for multi-line Inner documentation

The difference between Inner and Outer documentation is that Inner documentation is not attached to code.


# Rust coding
Closure syntax : ||[] <br>
exemple : |x y| [x + y] 