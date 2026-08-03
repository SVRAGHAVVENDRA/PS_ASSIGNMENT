# Task B2: Maven Lifecycle Understanding

## B2.1 Maven Lifecycle Phases for `mvn package`
When you execute `mvn package`, Maven runs the default lifecycle phases in the following order:

1. validate
2. compile
3. test-compile
4. test
5. package

## B2.2 Difference Between `mvn package` and `mvn install`
- **`mvn package`**: Compiles your code, runs unit tests, and packages the compiled code into a JAR or WAR file in the local project's `target/` directory.
- **`mvn install`**: Executes all phases up to `package`, and then installs the packaged file into your local Maven repository (`~/.m2/repository`). This allows other Maven projects on your machine to use it as a dependency.

## B2.3 Why JUnit Uses `test` Scope
JUnit is a testing library needed only during development for writing and executing unit tests. Using `<scope>test</scope>` ensures JUnit is included during test compilation and execution, but excluded from the final production application JAR. This keeps the production deployment file smaller and prevents unnecessary test dependencies in production.
