# 🧰 Toolbox Quick Reference Index

Fast lookup guide for finding specific tools, utilities, and code snippets in your personal developer toolbox.

## 🔍 Quick Search by Category

### 📊 Data Processing
| Tool | Location | Description |
|------|----------|-------------|
| Excel Processing | `java/src/main/java/erik/munk/excel/` | Apache POI utilities for spreadsheet manipulation |
| JSON Processing | `java/src/main/java/erik/munk/utils/JsonUtils.java` | JSON serialization/deserialization helpers |
| File Processing | `java/src/main/java/erik/munk/cmdapps/BigFileLineReplacer.java` | Large file line-by-line processing |
| File Utils | `java/src/main/java/erik/munk/utils/FileUtils.java` | General file manipulation utilities |

### 🗄️ Database & Persistence
| Tool | Location | Description |
|------|----------|-------------|
| DAO Pattern | `java/src/main/java/erik/munk/database/dao/` | Data Access Object implementations |
| JPA Entities | `java/src/main/java/erik/munk/database/model/` | Database entity models |
| Service Layer | `java/src/main/java/erik/munk/service/` | Business logic services |

### 🌐 Web Development
| Tool | Location | Description |
|------|----------|-------------|
| Touch Carousel | `javascript/touchScroll.js` | Mobile-friendly horizontal scrolling |
| REST Controllers | `java/src/main/java/erik/munk/mappingDesign/MyController.java` | Spring Boot REST API examples |
| Validation | `java/src/main/java/erik/munk/validation/` | Bean validation with custom groups |

### 🔧 System Administration
| Tool | Location | Description |
|------|----------|-------------|
| Git Batch Operations | `git/git_global_cmds.sh` | Bulk git operations across repositories |
| Git Branch Cleanup | `git/git_prune_local.sh` | Clean up local branches |
| Certificate Management | `shell/add_cert_keystore.sh` | Java keystore certificate utilities |
| Shell Configuration | `shell/.bash_profile` | Personal bash profile setup |

### 📝 Logging & Debugging
| Tool | Location | Description |
|------|----------|-------------|
| Rust Logging | `rust/log_level.rs` | Custom logging system with environment config |
| Advanced Logging | `rust/true_logging.rs` | Production-ready logging patterns |

### 🏢 CMS & Enterprise
| Tool | Location | Description |
|------|----------|-------------|
| Jahia Module List | `jahia/provisioning-available-module-list.sh` | Generate available Jahia modules |
| Jahia Provisioning | `jahia/provisioning-generate-script.sh` | Automate module deployment |

## 🎯 Quick Search by Technology

### ☕ Java
- **Location**: `java/`
- **Key Features**: Maven project, Spring Boot, JPA, Apache POI, Bean Validation
- **Main Classes**: App.java, ParamService.java, WorkbookService.java
- **Patterns**: DAO, Hexagonal Architecture, Service Layer

### 🌐 JavaScript
- **Location**: `javascript/`
- **Key Features**: Touch gestures, DOM manipulation, jQuery utilities
- **Main Files**: touchScroll.js
- **Use Cases**: Mobile carousels, touch interfaces

### 🦀 Rust
- **Location**: `rust/`
- **Key Features**: Environment-based logging, custom traits, performance optimization
- **Main Files**: log_level.rs, true_logging.rs
- **Patterns**: Trait implementations, error handling

### 🐚 Shell/Bash
- **Location**: `shell/`
- **Key Features**: System configuration, aliases, certificate management
- **Main Files**: .bash_profile, aliases.sh, add_cert_keystore.sh
- **Use Cases**: Environment setup, productivity shortcuts

### 🔧 Git
- **Location**: `git/`
- **Key Features**: Batch operations, branch management, repository maintenance
- **Main Files**: git_global_cmds.sh, git_prune_local.sh
- **Use Cases**: Multi-repo workflows, cleanup operations

### 🏢 Jahia CMS
- **Location**: `jahia/`
- **Key Features**: Module management, provisioning automation
- **Main Files**: provisioning-available-module-list.sh, provisioning-generate-script.sh
- **Use Cases**: CMS deployment, module tracking

## 🚀 Common Use Cases

### "I need to..."

#### Process Excel Files
→ `java/src/main/java/erik/munk/excel/WorkbookService.java`

#### Set up Git workflows for multiple repositories
→ `git/git_global_cmds.sh`

#### Create a mobile-friendly carousel
→ `javascript/touchScroll.js`

#### Implement logging in Rust
→ `rust/log_level.rs`

#### Configure a new development machine
→ `shell/.bash_profile` and `shell/aliases.sh`

#### Validate user input with groups
→ `java/src/main/java/erik/munk/validation/UserDto.java`

#### Process large files efficiently
→ `java/src/main/java/erik/munk/cmdapps/BigFileLineReplacer.java`

#### Manage Java certificates
→ `shell/add_cert_keystore.sh`

#### Clean up git branches
→ `git/git_prune_local.sh`

#### Deploy Jahia modules
→ `jahia/provisioning-generate-script.sh`

## 📋 Checklists

### New Machine Setup
- [ ] Copy shell configuration: `cp shell/.bash_profile ~/.bash_profile`
- [ ] Source profile: `source ~/.bash_profile`
- [ ] Make scripts executable: `chmod +x **/*.sh`
- [ ] Install Java 17+ for Java utilities
- [ ] Install Rust for Rust utilities
- [ ] Configure git with global settings

### Project Integration
- [ ] Identify needed utilities from index
- [ ] Copy relevant files to project
- [ ] Update dependencies (Maven, Cargo, npm)
- [ ] Adapt configuration for project needs
- [ ] Test utilities in project context
- [ ] Document integration in project README

### Maintenance Tasks
- [ ] Review and update aliases quarterly
- [ ] Update Java dependencies in pom.xml
- [ ] Check for Rust crate updates
- [ ] Backup shell configurations
- [ ] Clean up unused git branches
- [ ] Update documentation for new utilities

## 🔗 Cross-References

### Architecture Patterns
- **Hexagonal Architecture**: `java/src/main/java/erik/munk/mappingDesign/`
- **DAO Pattern**: `java/src/main/java/erik/munk/database/dao/`
- **Service Layer**: `java/src/main/java/erik/munk/service/`

### Configuration Management
- **Environment Variables**: `rust/log_level.rs`, `shell/.bash_profile`
- **Application Properties**: `java/src/main/resources/`
- **Shell Aliases**: `shell/aliases.sh`

### Testing Examples
- **JUnit 5**: `java/src/test/java/erik/munk/validation/`
- **Parameterized Tests**: `java/src/test/java/erik/munk/validation/UserDTOValidationTest.java`
- **Validation Testing**: `java/src/test/java/erik/munk/validation/ValidationTestRestController.java`

## 📚 Learning Paths

### Java Enterprise Development
1. Start with `java/README.md`
2. Explore DAO pattern in `database/dao/`
3. Study validation in `validation/`
4. Review service layer in `service/`
5. Examine REST controllers in `mappingDesign/`

### System Administration
1. Begin with `shell/README.md`
2. Set up bash profile from `shell/.bash_profile`
3. Learn git workflows from `git/README.md`
4. Practice certificate management with `shell/add_cert_keystore.sh`

### Web Development
1. Start with `javascript/README.md`
2. Implement touch carousel from `javascript/touchScroll.js`
3. Integrate with Java backend from `java/mappingDesign/`

### Rust Development
1. Begin with `rust/README.md`
2. Study logging implementation in `rust/log_level.rs`
3. Explore advanced patterns in `rust/true_logging.rs`

---

*This index is automatically maintained. Last updated: January 2025*

**💡 Tip**: Use Ctrl+F (Cmd+F on Mac) to quickly search for specific tools or technologies in this index.
