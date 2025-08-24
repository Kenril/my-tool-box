# Shell Scripts & Configuration

Collection of shell scripts, configuration files, and system administration utilities.

## 📁 Contents

### .bash_profile
Personal bash profile configuration with custom settings and environment variables.

**Features:**
- Custom aliases and functions
- Environment variable configurations
- PATH modifications
- Shell prompt customizations

### aliases.sh
Collection of useful command aliases for improved productivity.

**Features:**
- Git shortcuts
- Directory navigation helpers
- System administration shortcuts
- Development workflow aliases

### add_cert_keystore.sh
Utility script for adding certificates to Java keystores.

**Features:**
- Automated certificate installation
- Keystore management
- SSL/TLS certificate handling
- Java application certificate setup

## 🚀 Quick Start

### Initial Setup on New Machine
```bash
# Navigate to shell directory
cd shell/

# Copy bash profile to home directory
cp .bash_profile ~/.bash_profile

# Source the profile to apply changes
source ~/.bash_profile

# Make scripts executable
chmod +x *.sh
```

### Alternative Setup Methods
```bash
# For systems using .bashrc
cp .bash_profile ~/.bashrc

# For systems using .zshrc (adapt as needed)
cp .bash_profile ~/.zshrc

# Create backup of existing configuration
cp ~/.bash_profile ~/.bash_profile.backup
```

## 🔧 Configuration Files

### .bash_profile
Personal shell configuration that typically includes:

**Common Sections:**
- **Environment Variables**: PATH, JAVA_HOME, etc.
- **Aliases**: Command shortcuts
- **Functions**: Custom shell functions
- **Prompt**: Custom PS1 configuration
- **History**: History settings and behavior

**Usage:**
```bash
# Edit the profile
nano ~/.bash_profile

# Reload configuration
source ~/.bash_profile

# Or restart terminal session
```

### aliases.sh
Standalone alias definitions that can be sourced separately.

**Usage:**
```bash
# Source aliases in your profile
source /path/to/aliases.sh

# Or add to .bash_profile
echo "source /path/to/aliases.sh" >> ~/.bash_profile
```

## 🛠️ Utility Scripts

### add_cert_keystore.sh
Certificate management utility for Java applications.

**Prerequisites:**
- Java Development Kit (JDK) installed
- `keytool` command available
- Administrative privileges (may be required)

**Usage:**
```bash
# Make script executable
chmod +x add_cert_keystore.sh

# Run the script (check script for specific parameters)
./add_cert_keystore.sh [certificate_file] [keystore_path] [alias]
```

**Common Use Cases:**
- Adding SSL certificates for HTTPS connections
- Installing CA certificates for enterprise environments
- Setting up certificates for Java web applications
- Configuring certificates for development environments

## 📚 Common Shell Patterns

### Environment Variables
```bash
# Set environment variables
export JAVA_HOME="/usr/lib/jvm/java-17-openjdk"
export PATH="$JAVA_HOME/bin:$PATH"
export EDITOR="nano"

# Conditional environment setup
if [ -d "/opt/maven" ]; then
    export MAVEN_HOME="/opt/maven"
    export PATH="$MAVEN_HOME/bin:$PATH"
fi
```

### Useful Aliases
```bash
# Navigation shortcuts
alias ll='ls -alF'
alias la='ls -A'
alias l='ls -CF'
alias ..='cd ..'
alias ...='cd ../..'

# Git shortcuts
alias gs='git status'
alias ga='git add'
alias gc='git commit'
alias gp='git push'
alias gl='git log --oneline'

# System shortcuts
alias grep='grep --color=auto'
alias fgrep='fgrep --color=auto'
alias egrep='egrep --color=auto'
```

### Custom Functions
```bash
# Create directory and navigate to it
mkcd() {
    mkdir -p "$1" && cd "$1"
}

# Find and kill process by name
killbyname() {
    ps aux | grep "$1" | grep -v grep | awk '{print $2}' | xargs kill -9
}

# Extract various archive formats
extract() {
    if [ -f "$1" ]; then
        case "$1" in
            *.tar.bz2)   tar xjf "$1"     ;;
            *.tar.gz)    tar xzf "$1"     ;;
            *.bz2)       bunzip2 "$1"     ;;
            *.rar)       unrar x "$1"     ;;
            *.gz)        gunzip "$1"      ;;
            *.tar)       tar xf "$1"      ;;
            *.tbz2)      tar xjf "$1"     ;;
            *.tgz)       tar xzf "$1"     ;;
            *.zip)       unzip "$1"       ;;
            *.Z)         uncompress "$1"  ;;
            *.7z)        7z x "$1"        ;;
            *)           echo "'$1' cannot be extracted via extract()" ;;
        esac
    else
        echo "'$1' is not a valid file"
    fi
}
```

## 🔐 Security Considerations

### Certificate Management
- **Verify certificates** before adding to keystores
- **Use strong passwords** for keystores
- **Backup keystores** before modifications
- **Limit access** to certificate files and keystores

### Script Security
- **Review scripts** before execution
- **Use absolute paths** when possible
- **Validate input parameters**
- **Set appropriate file permissions**

```bash
# Secure script permissions
chmod 750 script.sh  # Owner: read/write/execute, Group: read/execute, Others: none
chmod 644 config.sh  # Owner: read/write, Group/Others: read only
```

## 🧪 Testing Shell Scripts

### Basic Testing
```bash
# Check syntax without execution
bash -n script.sh

# Run with debugging
bash -x script.sh

# Test with different shells
sh script.sh
bash script.sh
zsh script.sh
```

### Script Validation
```bash
# Use shellcheck for static analysis
shellcheck script.sh

# Test with different inputs
./script.sh test_input_1
./script.sh test_input_2
./script.sh ""  # Test empty input
```

## 🔄 Maintenance

### Regular Tasks
- **Update aliases** as workflows change
- **Review environment variables** for relevance
- **Clean up unused functions**
- **Update certificate stores** as needed
- **Backup configurations** before major changes

### Version Control
```bash
# Track configuration changes
git add .bash_profile aliases.sh
git commit -m "Update shell configuration"

# Create configuration repository
git init ~/dotfiles
cp ~/.bash_profile ~/dotfiles/
cd ~/dotfiles && git add . && git commit -m "Initial dotfiles"
```

## 📖 Learning Resources

- [Bash Manual](https://www.gnu.org/software/bash/manual/)
- [Advanced Bash-Scripting Guide](https://tldp.org/LDP/abs/html/)
- [ShellCheck](https://www.shellcheck.net/) - Shell script analysis tool
- [Bash Pitfalls](https://mywiki.wooledge.org/BashPitfalls)
- [Google Shell Style Guide](https://google.github.io/styleguide/shellguide.html)

## 🔄 Future Improvements

- [ ] Add more comprehensive aliases
- [ ] Create modular configuration system
- [ ] Add cross-platform compatibility
- [ ] Implement configuration management
- [ ] Add automated backup scripts
- [ ] Create installation automation

## 📝 Notes

- **Compatibility**: Scripts tested on Linux and macOS
- **Shell**: Primarily bash-focused, some zsh compatibility
- **Permissions**: Some scripts may require sudo access
- **Backup**: Always backup existing configurations before applying changes
