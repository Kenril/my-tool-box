# Java Utilities & Examples

Complete Maven project containing various Java utilities, design patterns, and code examples for enterprise development.

## 📁 Project Structure

### Core Packages

#### `erik.munk.cmdapps`
- **BigFileLineReplacer.java** - Utility for processing large files line by line

#### `erik.munk.database`
- **ParamDao.java** - Data Access Object implementation
- **ParamDaoPort.java** - Port interface for DAO pattern
- **Param.java** - Entity model for parameters

#### `erik.munk.excel`
- **MyWorkbookFactory.java** - Apache POI workbook creation utilities
- **WorkbookService.java** - Excel processing service
- **ParamKey.java** - Excel parameter key definitions

#### `erik.munk.mappingDesign`
- **Person.java** - Domain model example
- **PersonDeserializer.java** - Custom JSON deserializer
- **PersonPort.java** - Port interface for hexagonal architecture
- **PersonPortConnected.java** - Connected implementation
- **PersonPortDisconnected.java** - Disconnected implementation
- **MyClient.java** - Client implementation
- **MyController.java** - REST controller example

#### `erik.munk.service`
- **ParamService.java** - Business logic service layer

#### `erik.munk.utils`
- **FileUtils.java** - File manipulation utilities
- **JsonUtils.java** - JSON processing helpers

#### `erik.munk.validation`
- **UserDto.java** - Data Transfer Object with validation
- **BasicInfo.java** - Validation group for basic information
- **AdvancedInfo.java** - Validation group for advanced information

#### `erik.munk.exception`
- **NoUpdateException.java** - Custom exception for update operations

## 🚀 Quick Start

### Prerequisites
- **Java 17+** (JVM level changed from 1.8 to 17)
- **Maven 3.6+**
- **IDE** with Maven support (IntelliJ IDEA, Eclipse, VS Code)

### Build & Run
```bash
# Navigate to java directory
cd java/

# Compile the project
mvn compile

# Run tests
mvn test

# Package the application
mvn package

# Run the main application
mvn exec:java -Dexec.mainClass="erik.munk.App"
```

## 🔧 Configuration

### JVM Arguments
For SSL debugging and troubleshooting:
```bash
-Djavax.net.debug="ssl,handshake"
```

### Maven Dependencies
The project includes:
- **Spring Boot** - Web framework and dependency injection
- **Spring Data JPA** - Database access layer
- **Apache POI** - Excel file processing
- **Jackson** - JSON processing
- **Bean Validation** - Input validation
- **JUnit 5** - Testing framework

## 📚 Key Features & Examples

### Database Access Pattern
```java
// Using the DAO pattern
ParamDao paramDao = new ParamDao();
Param param = paramDao.findById(1L);

// Using the Port pattern (Hexagonal Architecture)
PersonPort personPort = new PersonPortConnected();
Person person = personPort.findPerson(id);
```

### Excel Processing
```java
// Create and manipulate Excel files
MyWorkbookFactory factory = new MyWorkbookFactory();
Workbook workbook = factory.createWorkbook();
WorkbookService service = new WorkbookService();
service.processWorkbook(workbook);
```

### Validation Groups
```java
// Validate with different groups
@Valid
public class UserDto {
    @NotNull(groups = BasicInfo.class)
    private String name;
    
    @Email(groups = AdvancedInfo.class)
    private String email;
}
```

### File Processing
```java
// Process large files efficiently
BigFileLineReplacer replacer = new BigFileLineReplacer();
replacer.processFile("input.txt", "output.txt", oldText, newText);
```

## 🧪 Testing

### Run All Tests
```bash
mvn test
```

### Parameterized Tests
The project includes examples of JUnit 5 parameterized tests. See:
- `UserDTOValidationTest.java`
- `ValidationTestRestController.java`

### Mutation Testing
Set up mutation testing with PIT:
```bash
mvn org.pitest:pitest-maven:mutationCoverage
```

## 📖 Learning Resources

### Tutorial Links
- [Parameterized Tests with JUnit 5](https://www.baeldung.com/parameterized-tests-junit-5)
- [Mutation Testing Setup](https://pitest.org/quickstart/maven/)

### Spring Boot References
- [Official Apache Maven Documentation](https://maven.apache.org/guides/index.html)
- [Spring Boot Maven Plugin Reference](https://docs.spring.io/spring-boot/docs/3.1.2/maven-plugin/reference/html/)
- [Spring Web Documentation](https://docs.spring.io/spring-boot/docs/3.1.2/reference/htmlsingle/index.html#web)
- [Spring Data JPA Documentation](https://docs.spring.io/spring-boot/docs/3.1.2/reference/htmlsingle/index.html#data.sql.jpa-and-spring-data)

### Practical Guides
- [Accessing Data with MySQL](https://spring.io/guides/gs/accessing-data-mysql/)
- [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
- [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
- [Building REST Services with Spring](https://spring.io/guides/tutorials/rest/)
- [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)

## 🏗️ Architecture Patterns

### Hexagonal Architecture
The `mappingDesign` package demonstrates hexagonal architecture with:
- **Ports**: Interfaces defining business operations
- **Adapters**: Implementations for different contexts (connected/disconnected)
- **Domain Models**: Core business entities

### Repository Pattern
Database access follows repository/DAO patterns with:
- Clear separation of concerns
- Interface-based design
- Testable implementations

## 🔄 Future Improvements

- [ ] Add more comprehensive integration tests
- [ ] Implement caching strategies
- [ ] Add API documentation with OpenAPI/Swagger
- [ ] Enhance error handling and logging
- [ ] Add performance monitoring
- [ ] Implement security features

## 📝 Notes

- **Java Version**: Upgraded from Java 8 to Java 17
- **Spring Boot**: Version 3.1.2
- **Testing**: JUnit 5 with parameterized test examples
- **Build Tool**: Maven with standard directory structure
- **Code Quality**: Includes mutation testing setup
