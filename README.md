# PetStoreAutomation

This project is an API automation framework built using Maven. It is designed to automate and validate RESTful API endpoints for a Pet Store application.

## Features
- API test automation using Java
- Maven for build and dependency management
- TestNG for test execution and reporting
- Logging and reporting of test results
- Test data management with Excel files

## Project Structure
- `src/main/` - Main source code (if any)
- `src/test/java/` - Test automation code
- `src/test/resources/` - Test resources
- `testData/` - Test data files (e.g., Excel)
- `logs/` - Automation logs
- `reports/` - HTML test reports
- `test-output/` - TestNG output and reports
- `pom.xml` - Maven project configuration
- `testng.xml` - TestNG suite configuration

## Prerequisites
- Java JDK 8 or above
- Maven 3.6+
- (Optional) Eclipse or VS Code for development

## How to Run Tests
1. Clone the repository:
   ```
   git clone <repo-url>
   ```
2. Navigate to the project directory:
   ```
   cd PetStoreAutomation
   ```
3. Run tests using Maven:
   ```
   mvn clean test
   ```
4. View reports in the `reports/` or `test-output/` directory.

## Customization
- Update `testng.xml` to configure test suites and groups.
- Place test data in `testData/Userdata.xlsx`.
- Check logs in `logs/automation.log`.

## Dependencies
All dependencies are managed via `pom.xml`. Maven will automatically download required libraries.

## Reporting
- HTML reports are generated in the `reports/` and `test-output/` folders after test execution.
- Logs are available in the `logs/` folder.

