# My Personal Developer Toolbox

A curated collection of code snippets, scripts, utilities, and reference materials to streamline daily development tasks across multiple programming languages and technologies.

## 📁 Contents Overview

### 🔧 [Git Tools](./git/)
- **git_global_cmds.sh** - Batch operations across multiple git repositories
- **git_prune_local.sh** - Clean up local branches that don't exist on remote
- Useful git commands and workflows

### ☕ [Java Utilities](./java/)
Complete Maven project with various utility classes and examples:
- **Database Access** - JPA/DAO patterns and database utilities
- **Excel Processing** - Apache POI utilities for spreadsheet manipulation
- **Validation** - Bean validation examples with custom groups
- **File Operations** - File handling utilities
- **JSON Processing** - JSON serialization/deserialization helpers
- **Design Patterns** - Examples of mapping and architectural patterns

### 🌐 [JavaScript Snippets](./javascript/)
- **Touch Scroll** - Mobile-friendly horizontal carousel implementation
- DOM manipulation utilities

### 🦀 [Rust Code](./rust/)
- **Logging System** - Custom log level implementation with environment configuration
- **True Logging** - Advanced logging utilities
- Rust commands and best practices

### 🐚 [Shell Scripts](./shell/)
- **System Configuration** - Bash profile and aliases
- **Certificate Management** - Keystore utilities
- Shell scripting utilities and configurations

### 🏢 [Jahia CMS Tools](./jahia/)
- **Module Provisioning** - Scripts for Jahia module management
- Development workflow automation

## 🚀 Quick Start

1. **Clone the repository:**
   ```bash
   git clone git@github.com:Kenril/my-tool-box.git
   cd my-tool-box
   ```

2. **Browse by technology:**
   - Each folder contains its own README with specific usage instructions
   - Look for executable scripts with `.sh` extension
   - Java utilities are in a complete Maven project structure

3. **Set up shell environment:**
   ```bash
   # Copy shell configurations
   cp shell/.bash_profile ~/.bash_profile
   source ~/.bash_profile
   ```

## 📖 Usage Guidelines

### For Scripts
- Make scripts executable: `chmod +x script_name.sh`
- Run from appropriate directory as indicated in individual READMEs
- Check script headers for author information and usage instructions

### For Code Snippets
- Copy and adapt to your specific use case
- Review dependencies and imports
- Test in your environment before production use

### For Java Project
- Navigate to `java/` directory
- Use Maven commands: `mvn compile`, `mvn test`, `mvn package`
- Import individual classes as needed

## 🔍 Finding What You Need

- **Git Operations**: Check `git/` folder
- **File Processing**: Look in `java/src/main/java/erik/munk/utils/`
- **Web Development**: Browse `javascript/` folder
- **System Administration**: Explore `shell/` folder
- **Logging Solutions**: See `rust/` folder
- **CMS Development**: Check `jahia/` folder

## 🤝 Contributing

This is a personal toolbox, but feel free to:
- Suggest improvements via issues
- Share similar utilities
- Report bugs or outdated information

## 📝 Notes

- **Java Version**: Project uses JDK 17+
- **Dependencies**: Check individual folder READMEs for specific requirements
- **Testing**: Most utilities include test examples
- **Documentation**: Each major component has its own README

---

*Last updated: January 2025*
*Maintained by: Erik Munk*
