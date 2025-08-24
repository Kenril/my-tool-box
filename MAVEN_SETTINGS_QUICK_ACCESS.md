# Maven Settings Quick Access

## File Location
**Global Settings:** `%M2_HOME%\conf\settings.xml`
**User Settings:** `%USERPROFILE%\.m2\settings.xml`

## Quick Open Commands

### Windows PowerShell
```powershell
# Open with default editor
notepad "%M2_HOME%\conf\settings.xml"

# Open with VS Code
code "%M2_HOME%\conf\settings.xml"

# Open with Notepad++
notepad++ "%M2_HOME%\conf\settings.xml"
```

### Windows Command Prompt
```cmd
# Open with default editor
start "%M2_HOME%\conf\settings.xml"

# Open with notepad
notepad "%M2_HOME%\conf\settings.xml"
```

## Common Configuration Sections

### Local Repository
```xml
<localRepository>%USERPROFILE%\.m2\repository</localRepository>
```

### Mirrors (for faster downloads)
```xml
<mirrors>
  <mirror>
    <id>central-mirror</id>
    <mirrorOf>central</mirrorOf>
    <name>Central Repository Mirror</name>
    <url>https://repo1.maven.org/maven2</url>
  </mirror>
</mirrors>
```

### Servers (for authentication)
```xml
<servers>
  <server>
    <id>your-repo-id</id>
    <username>your-username</username>
    <password>your-password</password>
  </server>
</servers>
```

### Profiles
```xml
<profiles>
  <profile>
    <id>jdk-11</id>
    <activation>
      <jdk>11</jdk>
    </activation>
    <properties>
      <maven.compiler.source>11</maven.compiler.source>
      <maven.compiler.target>11</maven.compiler.target>
    </properties>
  </profile>
</profiles>
```

## Environment Variables
- `MAVEN_OPTS`: Set JVM options for Maven
- `M2_HOME`: Maven installation directory
- `PATH`: Add Maven bin directory

## Quick Tips
- User-level settings: `%USERPROFILE%\.m2\settings.xml`
- Global settings: `%M2_HOME%\conf\settings.xml`
- User settings override global settings
