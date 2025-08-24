# Jahia CMS Tools

Collection of shell scripts and utilities for Jahia CMS development and module management.

## 📁 Contents

### provisioning-available-module-list.sh
Generates a list of available Jahia modules from your local Maven repository.

**Purpose:**
- Scans your local Maven repository (`~/.m2/repository`)
- Finds all Jahia modules (org.jahia group)
- Creates a formatted list for provisioning scripts

**Usage:**
```bash
./provisioning-available-module-list.sh
```

**Output:**
- Creates `~/.m2/repository/available-jahia-modules.list`
- Displays the list to stdout
- Format: `org.jahia.module-name/version`

**Example Output:**
```
org.jahia.modules.article/2.0.0
org.jahia.modules.news/1.5.0
org.jahia.modules.blog/3.1.0
```

### provisioning-generate-script.sh
Generates provisioning scripts for Jahia module deployment.

**Features:**
- Automates module provisioning script creation
- Uses the available modules list
- Streamlines deployment workflows

**Usage:**
```bash
./provisioning-generate-script.sh
```

## 🚀 Quick Start

1. **Make scripts executable:**
   ```bash
   chmod +x *.sh
   ```

2. **Generate module list:**
   ```bash
   ./provisioning-available-module-list.sh
   ```

3. **Generate provisioning script:**
   ```bash
   ./provisioning-generate-script.sh
   ```

## 📋 Prerequisites

- **Jahia Development Environment**: Working Jahia installation
- **Maven**: Local Maven repository with Jahia modules
- **Bash Shell**: Unix-like environment (Linux, macOS, WSL)
- **Jahia Modules**: Installed Jahia modules in Maven repository

## 🔧 Configuration

### Maven Repository Location
The scripts assume the standard Maven repository location:
```bash
M2_DIR="${HOME}/.m2/repository"
```

If your Maven repository is in a different location, modify the `M2_DIR` variable in the scripts.

### Jahia Group ID
Scripts look for modules under the Jahia group:
```bash
JAHIA_GROUP='org/jahia'
```

## 📝 Script Details

### provisioning-available-module-list.sh
**Author:** Julien CHARLES  
**Contributor:** Erik MUNK

**Process:**
1. Searches `~/.m2/repository/org/jahia/` for module directories
2. Converts file paths to Maven coordinates format
3. Outputs formatted list for use in provisioning

**Output Format:**
- Input: `/home/user/.m2/repository/org/jahia/modules/article/2.0.0`
- Output: `org.jahia.modules.article/2.0.0`

## 🐛 Troubleshooting

### Common Issues

**No modules found:**
- Verify Jahia modules are installed in Maven repository
- Check Maven repository path in script
- Ensure modules follow standard Jahia naming conventions

**Permission denied:**
- Make scripts executable: `chmod +x *.sh`
- Check file permissions on Maven repository

**Empty output:**
- Verify `$HOME/.m2/repository/org/jahia/` exists
- Check if modules are properly installed

## 🔄 Integration

These scripts are typically used in:
- **CI/CD Pipelines**: Automated deployment workflows
- **Development Setup**: Local environment provisioning
- **Module Management**: Tracking available modules
- **Deployment Scripts**: Production environment setup

## 📚 Related Documentation

- [Jahia Documentation](https://academy.jahia.com/)
- [Maven Repository Management](https://maven.apache.org/repository-management.html)
- [Jahia Module Development](https://academy.jahia.com/documentation/developer/jahia/8/developing-modules)

## 🤝 Contributing

**Authors:**
- Julien CHARLES (Original author)
- Erik MUNK (Contributor)

For improvements or bug fixes, consider:
- Adding error handling
- Supporting custom repository locations
- Adding verbose/debug modes
- Supporting different output formats
