## CommandLineRunner 
1. Execute when application context is ready and our spring-boot project is ready to run.
2.  implements CommandLineRunner and override its method
3. `@Override
   public void run(String... args) throws Exception {

   }`
4. Also, this method is not static. so we can use non-static method/fields.

## Beans
1. Beans are the java object that managed by the Spring container.
- @Component → Generic Spring-managed bean. Used when no specific role (service/repo/controller) fits.
- @Service → Business logic layer. Improves readability and supports service-level concerns (transactions, AOP).
- @Repository → Data access layer (DAO). Automatically translates DB exceptions into Spring’s DataAccessException.
- @Controller → Web MVC controller. Handles HTTP requests and returns views (JSP/HTML).
- @RestController → REST API controller. Combines @Controller + @ResponseBody → returns JSON/XML directly.

## Dependency Injection
- @AutoWired → Field Injection, can not make variable as final.
- Constructor Dependency Injection → It is most reliable way to use Dependency Injection, because it Loosely coupled. make final.

## Conditional Bean Property
- @ConditionalOnProperty → Conditional beans are created only when specific conditions are satisfied.

---

### Conditional Bean Feature Comparison

| Feature              | Spring Boot                    | Quarkus            | Short Explanation                              |
| -------------------- | ------------------------------ | ------------------ | ---------------------------------------------- |
| **Property-based**   | `@ConditionalOnProperty`       | `@IfBuildProperty` | Enable/disable beans via config values         |
| **Missing bean**     | `@ConditionalOnMissingBean`    | `@DefaultBean`     | Provide default implementation, allow override |
| **Bean presence**    | `@ConditionalOnBean`           | ❌                  | Spring checks bean graph at runtime            |
| **Classpath check**  | `@ConditionalOnClass`          | Build-time         | Optional dependencies resolved during build    |
| **Profile-based**    | `@Profile`                     | `@IfBuildProfile`  | Environment-specific beans (dev/prod)          |
| **Expression logic** | `@ConditionalOnExpression`     | ❌                  | SpEL-based runtime decisions                   |
| **Web context**      | `@ConditionalOnWebApplication` | Implicit           | Determined by installed extensions             |
| **Java version**     | `@ConditionalOnJava`           | ❌                  | Build-time Java target selection               |
| **Resource check**   | `@ConditionalOnResource`       | ❌                  | Runtime resource availability check            |
| **Custom condition** | `@Conditional`                 | Extension logic    | Advanced customization                         |
| **Decision time**    | Runtime                        | Build-time         | When the condition is evaluated                |
| **Flexibility**      | High                           | Limited            | Dynamic vs static behavior                     |
| **Startup time**     | Slower                         | Very fast          | Runtime checks vs pre-built graph              |
| **Memory usage**     | Higher                         | Lower              | Reflection vs ahead-of-time                    |
| **Native image fit** | Medium                         | Excellent          | GraalVM-first design                           |

---

### Key Takeaway

> **Spring Boot prioritizes runtime flexibility using conditional annotations, while Quarkus shifts decisions to build-time to achieve faster startup, lower memory usage, and better native-image performance.**

---

### When to Use What

* Choose **Spring Boot** when:

   * You need runtime feature toggles
   * Applications change behavior dynamically
   * You rely heavily on autoconfiguration

* Choose **Quarkus** when:

   * Startup time and memory are critical
   * You target containers or serverless
   * You plan to use GraalVM native images

---

## IOC (Inversion of Control) Working 
- You don’t create objects — the framework creates and manages them.
- IoC is a design principle where the framework controls object creation and dependency wiring instead of the application code.

---
### How IoC Works in Spring
This document explains the internal working of Spring’s Inversion of Control (IoC) container in a simple format.

1️⃣ Application Starts
- Spring Boot application starts using `SpringApplication.run()`.
- Spring bootstraps the IoC Container, also known as `ApplicationContext`.
- This container is responsible for managing beans and their lifecycle.

2️⃣ Class Scanning
. Spring scans the classpath to find components using annotations such as:

- `@Component`
- `@Service`
- `@Repository`
- `@Controller`

This process is known as component scanning.

3️⃣ Bean Definition Creation
For every discovered component, Spring creates a Bean Definition containing:

- Bean class name
- Scope (singleton, prototype, etc.)
- Constructor arguments
- Dependency metadata

At this stage, objects are NOT yet created.

4️⃣ Bean Instantiation
Spring creates bean instances when required (or eagerly for singletons).

Preferred creation mechanisms:
- Constructor Injection (recommended)
- Setter Injection
- Field Injection

5️⃣ Dependency Injection (DI)
  Spring automatically injects dependencies between beans.

Example:

@Service
class OrderService {
private final PaymentService paymentService;

    public OrderService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
}

Spring resolves and injects `PaymentService` at runtime.

6️⃣ Lifecycle Management
Spring manages the complete lifecycle of beans, including:

- Initialization callbacks (`@PostConstruct`)
- Destruction callbacks (`@PreDestroy`)
- Bean scopes:
   - Singleton (default)
   - Prototype
   - Request / Session (web applications)
     ✅ Summary
     Spring IoC promotes loose coupling by:

- Creating and managing objects
- Injecting dependencies automatically
- Handling lifecycle and scope

This leads to cleaner, testable, and maintainable applications.
---

## @AutoConfiguration
---
## What is Auto-Configuration?

**Auto-configuration** is a Spring Boot feature that:

> Automatically configures application beans based on **classpath dependencies**, **application properties**, and **existing user-defined beans**.

The goal is to **reduce boilerplate configuration** and follow **convention over configuration**.

---

## How Auto-Configuration Works (Step-by-Step)

### 1. Application Startup

When a Spring Boot application starts, the annotation below is processed:

```java
@SpringBootApplication
```

This internally includes:

* `@Configuration`
* `@ComponentScan`
* `@EnableAutoConfiguration`

---

### 2. Enable Auto-Configuration

```java
@EnableAutoConfiguration
```

This annotation tells Spring Boot to:

* Load predefined auto-configuration classes
* Apply them conditionally

---

### 3. Load Auto-Configuration Classes

Spring Boot loads auto-configuration metadata from:

```
META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports
```

(Older versions used `spring.factories`)

Each entry points to an auto-configuration class such as:

* `DataSourceAutoConfiguration`
* `JacksonAutoConfiguration`
* `WebMvcAutoConfiguration`

---

### 4. Conditional Evaluation

Each auto-configuration class is guarded by conditional annotations, for example:

* `@ConditionalOnClass`
* `@ConditionalOnProperty`
* `@ConditionalOnMissingBean`

Only when all conditions match will the configuration be applied.

---

### 5. Bean Registration

If conditions are satisfied, Spring registers default beans such as:

* `DataSource`
* `JdbcTemplate`
* `ObjectMapper`

These beans become part of the **ApplicationContext**.

---

### 6. User Overrides Take Priority

If the application defines its own bean of the same type, auto-configuration backs off:

```java
@Bean
DataSource customDataSource() {}
```

This works because auto-configurations usually use:

```java
@ConditionalOnMissingBean
```

---

## Example Flow

**Classpath contains:**

* `spring-boot-starter-jdbc`
* H2 Database driver

**Result:**

* Spring Boot auto-configures `DataSource`
* Creates `JdbcTemplate`
* No manual configuration required

---

## Key Annotations Used in Auto-Configuration

* `@Configuration`
* `@AutoConfiguration`
* `@EnableAutoConfiguration`
* `@ConditionalOnClass`
* `@ConditionalOnProperty`
* `@ConditionalOnMissingBean`

---

## Disabling Auto-Configuration

### Disable Specific Auto-Configuration

```java
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
```

### Disable Using Properties

```properties
spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
```

---

## Why Auto-Configuration is Important

* Eliminates repetitive XML/Java config
* Provides sensible defaults
* Allows easy customization
* Improves developer productivity

---

## Spring Boot vs Quarkus (High-Level)

| Aspect             | Spring Boot | Quarkus    |
| ------------------ | ----------- | ---------- |
| Configuration time | Runtime     | Build-time |
| Flexibility        | High        | Limited    |
| Startup time       | Slower      | Very fast  |
| Native image       | Moderate    | Excellent  |

---

## Key Takeaway

> **Spring Boot Auto-Configuration dynamically configures the application at runtime using conditional logic, while still allowing full override by the developer.**

---

## What Happen when Spring-boot Application start/run ?

---
## 1️⃣ JVM Starts

- JVM is launched by the OS.
- The main class is loaded.
- The entry method is executed:

```java
public static void main(String[] args)
```

---

## 2️⃣ `SpringApplication.run()` Is Called

This is the **real entry point of Spring Boot**.

```java
SpringApplication.run(MyApp.class, args);
```

Responsibilities:
- Bootstraps Spring
- Prepares the application context
- Starts the lifecycle

---

## 3️⃣ `@SpringBootApplication` Is Detected

Spring detects `@SpringBootApplication` on `MyApp.class`.

It is a **meta-annotation** composed of:

### `@SpringBootConfiguration`
- Marks the class as a configuration class
- Internally equivalent to `@Configuration`

### `@ComponentScan`
- Scans the current package and sub-packages
- Detects:
   - `@Component`
   - `@Service`
   - `@Repository`
   - `@Controller`

### `@EnableAutoConfiguration`
- Enables Spring Boot auto-configuration
- Loads auto-configurations from:

```
META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports
```

> Older Spring Boot versions used `spring.factories`

✅ Image explanation is correct here

---

## 4️⃣ `SpringApplication` Object Is Created

Spring Boot prepares the bootstrap process:

### Determines application type:
- **Servlet** → Tomcat
- **Reactive** → Netty
- **Non-web**

### Initializes:
- `ApplicationContext`
- `Environment`

### Registers:
- `ApplicationListeners`
- `ApplicationContextInitializers`

---

## 5️⃣ `ApplicationContext` Is Created

Based on application type:

- `AnnotationConfigServletWebServerApplicationContext`
- `AnnotationConfigReactiveWebServerApplicationContext`
- `AnnotationConfigApplicationContext`

This container will manage:
- Beans
- Dependency Injection
- Lifecycle

---

## 6️⃣ Environment Is Prepared

Spring loads configuration from:

- `application.properties` / `application.yml`
- Command-line arguments
- Environment variables
- Active profiles (`spring.profiles.active`)

### Property precedence is applied automatically.

---

## 7️⃣ Bean Definitions Are Loaded

### a) Component Scanning

Beans detected using:
- `@Component`
- `@Service`
- `@Repository`
- `@Controller`

### b) Auto-Configuration

Conditional beans are registered, such as:
- `DataSource`
- `EntityManager`
- `WebServerFactory`

Controlled using:
- `@ConditionalOnClass`
- `@ConditionalOnProperty`
- `@ConditionalOnMissingBean`

✅ Image explanation is accurate

---

## 8️⃣ `ApplicationContext` Is Refreshed (Core Phase)

This is the **most important phase**:

- Beans are instantiated
- Dependencies are injected (`@Autowired`)
- `Aware` interfaces are invoked
- `BeanPostProcessor`s are executed
- Lifecycle callbacks run:
   - `@PostConstruct`
   - `InitializingBean`

---

## 9️⃣ Embedded Web Server Starts

For web applications:

- Embedded server starts:
   - Tomcat / Jetty / Undertow
- Ports are bound (default `8080`)
- `DispatcherServlet` is initialized

---

## 🔟 `CommandLineRunner` / `ApplicationRunner` Executes

These beans run **after context startup**:

```java
@Component
class StartupRunner implements CommandLineRunner {
    @Override
    public void run(String... args) {
        // executes after startup
    }
}
```

---

## 1️⃣1️⃣ Application Is Fully Started

- Application is ready to handle requests
- Health & actuator endpoints are available
- Startup log appears:

```
Started MyApplication in X seconds
```

---
