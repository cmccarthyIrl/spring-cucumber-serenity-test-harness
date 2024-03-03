# Spring Cucumber Serenity Test Harness

# Index

<table> 
<tr>
  <th>Start</th>
  <td>
    | <a href="#maven">Maven</a> 
    | <a href="#quickstart">Quickstart</a> | 
  </td>
</tr>
<tr>
  <th>Run</th>
  <td>
    | <a href="#command-line">Command Line</a>
    | <a href="#ide-support">IDE Support</a>    
    | <a href="#java-jdk">Java JDK</a>    
    | <a href="#troubleshooting">Troubleshooting</a>    |
  </td>
</tr>
<tr>
  <th>Report</th> 
  <td>
     | <a href="#configuration">Configuration</a> 
    | <a href="#environment-switching">Environment Switching</a>
    | <a href="#extent-reports">Serenity Reports</a>
    | <a href="#logging">Logging</a> |
  </td>
</tr>
<tr>
  <th>Advanced</th>
  <td>
    | <a href="#hooks">Before / After Hooks</a>
    | <a href="#json-transforms">JSON Transforms</a>
    | <a href="#contributing">Contributing</a> |
    </td>
</tr>
</table>

# Maven

The Framework uses [Spring Boot Test](https://spring.io/guides/gs/testing-web/)
, [Cucumber](https://cucumber.io/) and [Serenity](http://www.thucydides.info/#/) client implementations.

Spring `<dependencies>`:

```xml

<dependecies>
    ...
    <dependency>
        <groupId>org.springframework.amqp</groupId>
        <artifactId>spring-rabbit</artifactId>
        <version>${spring-rabbit.version}</version>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-test</artifactId>
    </dependency>
    ...
</dependecies>
```

Serenity `<dependencies>`:

```xml

<dependecies>
    ...
    <dependency>
      <groupId>net.serenity-bdd</groupId>
      <artifactId>serenity-core</artifactId>
      <version>${serenity.version}</version>
      <scope>test</scope>
    </dependency>
    <dependency>
      <groupId>net.serenity-bdd</groupId>
      <artifactId>serenity-junit</artifactId>
      <version>${serenity.version}</version>
      <scope>test</scope>
    </dependency>
    <dependency>
      <groupId>net.serenity-bdd</groupId>
      <artifactId>serenity-screenplay</artifactId>
      <version>${serenity.version}</version>
      <scope>test</scope>
    </dependency>
    <dependency>
      <groupId>net.serenity-bdd</groupId>
      <artifactId>serenity-screenplay-webdriver</artifactId>
      <version>${serenity.version}</version>
      <scope>test</scope>
    </dependency>
    <dependency>
      <groupId>net.serenity-bdd</groupId>
      <artifactId>serenity-ensure</artifactId>
      <version>${serenity.version}</version>
      <scope>test</scope>
    </dependency>
    <dependency>
      <groupId>net.serenity-bdd</groupId>
      <artifactId>serenity-cucumber6</artifactId>
      <version>${serenity.cucumber.version}</version>
      <scope>test</scope>
    </dependency>
    <dependency>
      <groupId>net.serenity-bdd</groupId>
      <artifactId>serenity-rest-assured</artifactId>
      <version>2.2.12</version>
    </dependency>
    <dependency>
      <groupId>net.serenity-bdd</groupId>
      <artifactId>serenity-spring</artifactId>
      <version>2.2.12</version>
    </dependency>
    ...
</dependecies>
```

# Quickstart

- [Intellij IDE](https://www.jetbrains.com/idea/) - `Recommended`
- [Java JDK 11](https://www.oracle.com/ie/java/technologies/javase-jdk11-downloads.html)
- [Apache Maven 3.6.3](https://maven.apache.org/docs/3.6.3/release-notes.html)

# JUnit

By using the [JUnit Framework](https://junit.org/junit4/) `@RunWith` Annotation Type we can utilize [Serenity](http://www.thucydides.info/#/) and [Cucumber](https://cucumber.io/) `@CucumberOptions` Annotation Type to execute the `*.feature`
file tests

> Right click the `WeatherTest` class and select `Run`

```java
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        plugin = {"pretty", "json:target/cucumber/report.json"},
        features = "src/test/resources/features/WeatherTest.feature",
        glue = {"com/cmccarthy/api", "com/cmccarthy/common"}
)
public class WeatherRunnerTest {}
```

# Command Line

Normally you will use your IDE to run a `*.feature` file directly or via the `*Test.java` class. With the `Test` class,
we can run tests from the command-line as well.

Note that the `mvn test` command only runs test classes that follow the `*Test.java` naming convention.

You can run a single test or a suite or tests like so :

```
mvn test -Dtest=WeatherTest
```

```
mvn test -Dtest=JunitSuiteTest
```

Note that the `mvn clean install` command runs all test Classes that follow the `*Test.java` naming convention

```
mvn clean install
```

# IDE Support

To minimize the discrepancies between IDE versions and Locales the `<sourceEncoding>` is set to `UTF-8`

```xml

<properties>
    ...
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
    ...
</properties>
```

# Java JDK

The Java version to use is defined in the `maven-compiler-plugin`

```xml

<build>
    ...
    <pluginManagement>
        <plugins>
            ...
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <configuration>
                    <source>11</source>
                    <target>11</target>
                </configuration>
            </plugin>
            ...
        </plugins>
    </pluginManagement>
    ...
</build>
```

# Configuration

The `AbstractTestDefinition` class is responsible for specifying each Step class as `@SpringBootTest` and
its `@ContextConfiguration`

> All the `Step Classes` in the Framework should `extend` the `AbstractTestDefinition` class

```java

@ContextConfiguration(classes = {FrameworkContextConfiguration.class})
@SpringBootTest
public class AbstractTestDefinition {
}
```

The `FrameworkContextConfiguration` class is responsible for specifying the Spring `@Configuration`, modules to scan,
properties to use etc

```java

@EnableRetry
@Configuration
@ComponentScan({
        "com.cmccarthy"
})
@PropertySource("application.properties")
public class FrameworkContextConfiguration {
}
```

# Environment Switching

There is only one thing you need to do to switch the environment - which is to set `<activeByDefault>` property in the
Master POM.

> By default, the value of `spring.profiles.active` is defined in the `application.properties` file which inherits its value from the Master POM property `<activeByDefault>`

```xml

<profiles>
    ...
    <profile>
        <id>prod</id>
        <activation>
            <activeByDefault>true</activeByDefault>
        </activation>
        <properties>
            <activatedProperties>prod</activatedProperties>
        </properties>
    </profile>
    ...
</profiles>
```

You can then specify the profile to use when running Maven from the command line like so:

```
mvn clean install -P dev
```

Below is an example of the `application.properties` file.

```properties
spring.profiles.active=@activatedProperties@
```

# Serenity Reports

The Framework uses [Serenity Reports](https://serenity-bdd.github.io/theserenitybook/latest/extended-reports.html) to
generate the HTML Test Reports


> To generate the Serenity Reports execute the following from the command line
```
mvn serenity:aggregate
```
The example below is a report generated by Serenity Reports open-source library.

<img src="https://github.com/cmccarthyIrl/spring-cucumber-serenity-test-harness/blob/master/common/src/main/resources/demo/demo.png" height="400px"/>

# Logging

The Framework uses [Log4j2](https://logging.apache.org/log4j/2.x/) You can instantiate the logging service in any Class
like so

```java
        private final Logger logger=LoggerFactory.getLogger(WikipediaPageSteps.class);
```

you can then use the logger like so :

```java
        logger.info("This is a info message");
        logger.warn("This is a warning message");
        logger.debug("This is a info message");
        logger.error("This is a error message");
```

# Before / After Hooks

The [Log4j2](https://logging.apache.org/log4j/2.x/) logging service is initialized from the `Hooks.class`

```java
public class Hooks {

    @Autowired
    private HookUtils hookUtil;

    @After
    public void afterScenario(Scenario scenario) {
        hookUtil.endOfTest(scenario);
    }
}
```

# JSON Transforms

[Rest Assured IO](https://rest-assured.io/) is used to map the `Response` Objects to their respective `POJO` Classes

```xml

<dependency>
    <groupId>io.rest-assured</groupId>
    <artifactId>rest-assured</artifactId>
    <version>3.0.0</version>
</dependency>
```

# Troubleshooting

- Execute the following commands to resolve any dependency issues
    1. `cd ~/spring-cucumber-serenity-test-harness`
    2. `mvn clean install -DskipTests`

# Contributing

Spotted a mistake? Questions? Suggestions?

[Open an Issue](https://github.com/cmccarthyIrl/spring-cucumber-serenity-test-harness/issues)


