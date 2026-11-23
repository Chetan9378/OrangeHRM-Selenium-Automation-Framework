# OrangeHRM-Selenium-Automation-Framework

A Selenium-based automation framework built using **Java, Selenium WebDriver, TestNG, Maven, Page Object Model, Extent Reports, and Jenkins**.

The framework automates an end-to-end OrangeHRM employee workflow and supports:

- Cross-browser testing
- Parallel browser execution
- Page Object Model
- Dynamic test data generation
- Explicit waits
- Soft assertions
- Screenshots on failures
- Extent HTML reporting
- Maven execution
- Jenkins CI execution
- Headless browser execution in Jenkins

---

# 1. Project Objective

The objective of this project is to build a maintainable and reusable Selenium automation framework that follows commonly used automation testing practices.

The framework validates an end-to-end OrangeHRM employee workflow:

1. Login to OrangeHRM
2. Navigate to the PIM module
3. Add a new employee
4. Generate a unique Employee ID dynamically
5. Validate successful employee creation
6. Search for the employee
7. Verify the employee exists
8. Edit employee details
9. Validate successful update
10. Search for the employee again
11. Delete the employee
12. Validate successful deletion
13. Verify that the deleted employee no longer exists
14. Logout
15. Verify that the login page is displayed

---

# 2. Technology Stack

| Technology | Purpose |
|---|---|
| Java | Programming language |
| Selenium WebDriver | Browser automation |
| TestNG | Test execution and assertions |
| Maven | Dependency and build management |
| Page Object Model | Framework design pattern |
| ThreadLocal WebDriver | Parallel browser execution |
| Extent Reports | HTML test reporting |
| Jenkins | Continuous Integration |
| Git | Version control |
| GitHub | Source code repository |

---

# 3. Framework Features

## 3.1 Page Object Model

Each application page has a separate Page Object class.

This keeps:

- Locators
- Page actions
- Test logic

separated from each other.

Examples:

```text
LoginPage.java
DashboardPage.java
AddEmployeePage.java
EmployeeListPage.java
EditEmployeePage.java
LogoutPage.java
```

This makes the framework easier to maintain when application elements change.

---

## 3.2 Cross-Browser Testing

The framework supports:

1. Google Chrome
2. Microsoft Edge
3. Mozilla Firefox

Browser selection is controlled through TestNG parameters.

Example:

```xml
<parameter name="browser" value="chrome"/>
```

---

## 3.3 Parallel Execution

The local TestNG suite is configured to execute browsers in parallel.

Example:

```xml
<suite name="OrangeHRM Cross Browser Suite"
       parallel="tests"
       thread-count="3">
```

This allows:

```text
Chrome
Edge
Firefox
```

to execute simultaneously.

---

# 4. ThreadLocal WebDriver

The framework uses `ThreadLocal<WebDriver>` to maintain a separate WebDriver instance for each parallel TestNG thread.

Example:

```java
private ThreadLocal<WebDriver> driver =
        new ThreadLocal<>();
```

Each browser receives its own driver instance.

This helps prevent one parallel test from interfering with another browser session.

---

# 5. Project Structure

```text
OrangeHRMAutomationFramework
│
├── src/main/java
│   │
│   ├── base
│   │   └── BaseTest.java
│   │
│   ├── listeners
│   │   └── TestListener.java
│   │
│   ├── pages
│   │   ├── AddEmployeePage.java
│   │   ├── DashboardPage.java
│   │   ├── EditEmployeePage.java
│   │   ├── EmployeeListPage.java
│   │   ├── LoginPage.java
│   │   └── LogoutPage.java
│   │
│   └── utils
│       ├── AssertionUtils.java
│       ├── ConfigReader.java
│       ├── ExtentManager.java
│       ├── ScreenshotUtils.java
│       ├── TestDataGenerator.java
│       └── WaitUtils.java
│
├── src/test/java
│   └── tests
│       ├── AddEmployeeTest.java
│       └── LoginTest.java
│
├── src/test/resources
│   └── config.properties
│
├── pom.xml
├── testng.xml
├── testng-jenkins.xml
├── .gitignore
└── README.md
```

Generated folders such as the following are excluded from Git:

```text
target/
test-output/
reports/
screenshots/
```

---

# 6. Important Framework Components

## 6.1 BaseTest

`BaseTest.java` is responsible for:

1. Browser initialization
2. ThreadLocal WebDriver management
3. Browser selection
4. Jenkins detection
5. Headless browser execution
6. Window configuration
7. Application URL loading
8. WebDriverWait initialization
9. Browser cleanup after test execution

---

## 6.2 ConfigReader

`ConfigReader.java` reads values from:

```text
src/test/resources/config.properties
```

Configuration values can include:

```text
baseUrl
username
password
```

This avoids hardcoding environment information directly inside test classes.

---

## 6.3 WaitUtils

`WaitUtils.java` contains reusable explicit wait methods.

It helps handle dynamic web elements using conditions such as:

- Element visibility
- Element clickability
- Loader invisibility
- Refreshed elements

This reduces unnecessary use of static waits.

---

## 6.4 TestDataGenerator

`TestDataGenerator.java` generates dynamic test data such as:

- First name
- Last name
- Unique Employee ID

Dynamic Employee IDs are useful during parallel execution because each browser requires independent test data.

---

## 6.5 AssertionUtils

`AssertionUtils.java` provides reusable assertion handling.

The framework uses TestNG `SoftAssert` so that multiple validations can be performed during the same end-to-end test before calling:

```java
softAssert.assertAll();
```

---

## 6.6 ScreenshotUtils

When an important validation fails, the framework can capture a screenshot.

Screenshots are useful for:

1. Failure analysis
2. Debugging
3. Reporting
4. Understanding the application state at the time of failure

Generated screenshots are excluded from GitHub.

---

## 6.7 TestListener

The TestNG listener monitors test execution events such as:

- Test start
- Test success
- Test failure
- Suite completion

It is also integrated with reporting and failure screenshot functionality.

---

# 7. Extent Reports

The framework uses Extent Reports to generate an HTML test report.

The generated report can contain:

- Test name
- Browser name
- Test execution status
- Passed tests
- Failed tests
- Failure details
- Screenshots where applicable

Generated reports are stored locally and excluded from GitHub through `.gitignore`.

---

# 8. Prerequisites

Before running this project, install the following.

## 8.1 Java

Install a compatible Java JDK.

Verify:

```bash
java --version
```

---

## 8.2 Maven

Install Apache Maven.

Verify:

```bash
mvn --version
```

---

## 8.3 Git

Install Git.

Verify:

```bash
git --version
```

---

## 8.4 Browsers

Install:

1. Google Chrome
2. Microsoft Edge
3. Mozilla Firefox

Ensure the required browser versions are compatible with the Selenium/WebDriver configuration.

---

# 9. Clone the Project

Open Command Prompt, PowerShell, Git Bash, or a terminal.

Run:

```bash
git clone https://github.com/Chetan9378/OrangeHRM-Selenium-Automation-Framework.git
```

Move into the project directory:

```bash
cd OrangeHRM-Selenium-Automation-Framework
```

---

# 10. Import Project into Eclipse

1. Open Eclipse.
2. Select:

```text
File
→ Import
→ Maven
→ Existing Maven Projects
```

3. Browse to the cloned project directory.
4. Select the project.
5. Click **Finish**.
6. Allow Maven to download the dependencies.

If required:

```text
Right-click project
→ Maven
→ Update Project
```

---

# 11. Configure the Application

Configuration is stored inside:

```text
src/test/resources/config.properties
```

Configure the required values such as:

```properties
baseUrl=<application-url>
username=<username>
password=<password>
```

Do not store real production passwords, API keys, tokens, or sensitive credentials in a public GitHub repository.

---

# 12. Microsoft Edge Configuration

The framework supports Microsoft Edge as part of the local cross-browser execution.

For the current local setup, Edge uses a manually configured `msedgedriver.exe` path.

Example:

```java
EdgeDriverService service =
        new EdgeDriverService.Builder()
                .usingDriverExecutable(
                        new File(
                                "C:\\WebDrivers\\Edge\\msedgedriver.exe"
                        )
                )
                .build();

driver.set(
        new EdgeDriver(service, options)
);
```

## 12.1 Why a Manual EdgeDriver Path Is Used

During framework development, Selenium Manager was not consistently able to download the required EdgeDriver version automatically.

Because of this, a compatible Microsoft EdgeDriver was downloaded manually and configured in the framework.

This provided more stable Edge execution during local cross-browser testing.

## 12.2 Important for Anyone Cloning the Project

The path below is specific to the original development machine:

```text
C:\WebDrivers\Edge\msedgedriver.exe
```

If you clone this project on another computer, update the EdgeDriver path according to your own machine.

For example:

```java
new File(
    "D:\\Drivers\\Edge\\msedgedriver.exe"
)
```

## 12.3 Edge Browser and Driver Compatibility

The installed Microsoft Edge browser version and EdgeDriver version should be compatible.

If Edge updates automatically and the driver becomes outdated, errors may occur such as:

```text
SessionNotCreatedException
```

or browser startup failures.

In that case:

1. Check the installed Microsoft Edge version.
2. Download the compatible Microsoft EdgeDriver version.
3. Replace the old `msedgedriver.exe`.
4. Run the tests again.

## 12.4 Future Improvement

The current framework uses a fixed local path for EdgeDriver because it was the most stable solution during development.

A future enhancement would be to remove the machine-specific path and use one of the following approaches:

1. Selenium Manager
2. WebDriverManager
3. Environment-based driver configuration
4. Selenium Grid
5. Docker-based browser execution

This would make the framework more portable across machines and CI environments.

## 12.5 Current Execution Strategy

The framework currently uses:

```text
Local execution:
Chrome + Edge + Firefox
        ↓
Parallel cross-browser execution

Jenkins execution:
Chrome headless
        ↓
Stable CI execution
```

Edge and Firefox are validated locally as part of the cross-browser suite, while Jenkins currently uses Chrome headless for stable Windows CI execution.

The framework can later be extended with Selenium Grid for distributed Chrome, Edge, and Firefox execution in CI.

---

# 13. Run Tests Using Maven

From the project root, execute:

```bash
mvn test
```

Maven Surefire reads the TestNG suite configuration and executes the tests.

Successful execution should end with:

```text
BUILD SUCCESS
```

If a test fails, Maven reports:

```text
BUILD FAILURE
```

---

# 14. Local Cross-Browser Parallel Execution

The main TestNG suite is:

```text
testng.xml
```

It is used for local cross-browser execution.

The suite runs:

```text
Chrome
Edge
Firefox
```

in parallel.

Run:

```bash
mvn test
```

Expected architecture:

```text
Maven
   ↓
TestNG
   ↓
ThreadLocal WebDriver
   ↓
--------------------------------
| Chrome | Edge | Firefox |
--------------------------------
        Parallel
```

---

# 15. Jenkins CI Execution

A separate TestNG suite is available for Jenkins:

```text
testng-jenkins.xml
```

The current Jenkins configuration uses **Chrome in headless mode** for stable CI execution.

Jenkins runs:

```bash
mvn test -Dsurefire.suiteXmlFiles=testng-jenkins.xml
```

Execution flow:

```text
Jenkins
   ↓
Maven
   ↓
TestNG
   ↓
Chrome Headless
   ↓
OrangeHRM Test
   ↓
BUILD SUCCESS / BUILD FAILURE
```

---

# 16. Why Headless Chrome Is Used in Jenkins

Jenkins runs as a Windows service in the current setup.

GUI browsers can behave differently when launched from a background Windows service.

Therefore, Chrome headless execution is used for stable CI execution.

Local cross-browser testing still validates:

```text
Chrome + Edge + Firefox
```

in parallel.

For larger cross-browser CI environments, the framework can later be extended using:

1. Selenium Grid
2. Docker
3. BrowserStack
4. Sauce Labs
5. Other cloud browser platforms

---

# 17. Jenkins Setup

A Jenkins Freestyle project can be configured with a Windows batch command.

Example:

```bat
cd /d C:\path\to\OrangeHRM-Selenium-Automation-Framework

mvn test -Dsurefire.suiteXmlFiles=testng-jenkins.xml
```

Jenkins will:

1. Navigate to the project
2. Execute Maven
3. Start the TestNG suite
4. Run Chrome in headless mode
5. Execute the OrangeHRM automation
6. Mark the Jenkins job based on the test result

Successful test:

```text
BUILD SUCCESS
```

Failed test:

```text
BUILD FAILURE
```

---

# 18. End-to-End Test Scenario

The main automation scenario performs the following:

1. Launch browser
2. Open OrangeHRM
3. Login
4. Navigate to PIM
5. Click Add Employee
6. Generate dynamic first name
7. Generate dynamic last name
8. Generate unique Employee ID
9. Save employee
10. Validate creation success
11. Open Employee List
12. Search using Employee ID
13. Validate employee exists
14. Edit employee
15. Generate updated employee details
16. Save changes
17. Validate successful update
18. Search employee again
19. Delete employee
20. Confirm deletion
21. Validate successful deletion
22. Search deleted Employee ID
23. Verify "No Records Found"
24. Logout
25. Validate login page

---

# 19. Parallel Execution Design

The framework uses both:

```text
TestNG parallel="tests"
```

and:

```java
ThreadLocal<WebDriver>
```

This is important because each parallel browser requires its own WebDriver instance.

Conceptually:

```text
Thread 1 → ChromeDriver
Thread 2 → EdgeDriver
Thread 3 → FirefoxDriver
```

Without isolated driver instances, parallel tests can interfere with each other.

---

# 20. Failure Handling

The framework handles failures using:

1. Explicit waits
2. Soft assertions
3. TestNG listeners
4. Screenshots
5. Extent Reports
6. Maven build status
7. Jenkins build status

This provides multiple levels of visibility when a test fails.

---

# 21. Git Ignore Configuration

Generated and IDE-specific files are excluded from the repository using `.gitignore`.

```gitignore
target/
test-output/
reports/
screenshots/

.classpath
.project
.settings/

*.log
```

This keeps the repository clean and prevents generated execution artifacts from being committed.

---

# 22. Future Enhancements

The framework can be extended with:

1. Selenium Grid for distributed cross-browser execution
2. Docker-based browser execution
3. BrowserStack or Sauce Labs integration
4. REST Assured for API automation
5. Data-driven testing
6. Excel/JSON test data support
7. Cucumber BDD
8. Retry mechanism for selected transient failures
9. Jenkins scheduled regression execution
10. GitHub-triggered Jenkins builds
11. Improved CI reporting
12. Additional OrangeHRM business workflows

---

# 23. Skills Demonstrated

This project demonstrates practical knowledge of:

- Selenium WebDriver
- Java
- TestNG
- Maven
- Page Object Model
- Cross-browser testing
- Parallel testing
- ThreadLocal WebDriver
- Dynamic test data
- Explicit waits
- Soft assertions
- TestNG listeners
- Screenshots
- Extent Reports
- Git
- GitHub
- Jenkins
- Headless browser execution
- CI automation
- End-to-end functional testing

---

# 24. Interview Explanation

A short explanation of the framework:

> I developed a Selenium automation framework for OrangeHRM using Java, TestNG, Maven and the Page Object Model. The framework supports cross-browser parallel execution for Chrome, Edge and Firefox using TestNG and ThreadLocal WebDriver. I implemented reusable wait utilities, dynamic test data generation, soft assertions, screenshots on failures and Extent Reports. I also integrated the framework with Jenkins, where Maven triggers a separate TestNG suite and Chrome runs in headless mode for CI execution.

---

# 25. Repository

GitHub:

```text
https://github.com/Chetan9378/OrangeHRM-Selenium-Automation-Framework
```

GitHub Profile:

```text
https://github.com/Chetan9378
```

---

# 26. Author

**Chetan Kalpande**

QA / Test Engineer

Manual Testing | Selenium Java | API Testing | SQL | TestNG | Maven | Jenkins

GitHub:

```text
https://github.com/Chetan9378
```

---

# 27. Project Status

- ✅ Selenium WebDriver
- ✅ Java
- ✅ Page Object Model
- ✅ End-to-End Employee Workflow
- ✅ Dynamic Test Data
- ✅ Explicit Wait Utilities
- ✅ Soft Assertions
- ✅ Extent Reporting
- ✅ Screenshots
- ✅ Chrome Testing
- ✅ Edge Testing
- ✅ Firefox Testing
- ✅ TestNG Parallel Execution
- ✅ ThreadLocal WebDriver
- ✅ Maven Integration
- ✅ Jenkins Integration
- ✅ Chrome Headless CI Execution

The project can be further extended with Selenium Grid and additional automated test scenarios.
