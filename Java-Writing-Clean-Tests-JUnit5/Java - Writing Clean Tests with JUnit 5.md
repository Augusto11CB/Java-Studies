# Java - Writing Clean Tests with JUnit 5

- [Modern Best Practices for Testing in Java](https://phauer.com/2019/modern-best-practices-testing-java/)
- [Writing clean tests ](https://www.petrikainulainen.net/writing-clean-tests/)
- [JUnit 5 -  the ultimate resource](https://www.petrikainulainen.net/junit-5-the-ultimate-resource/)
- [JUnit 5 - tutorial](https://www.petrikainulainen.net/junit-5-tutorial/)
- [Testing RESTful Services in Java](https://phauer.com/2016/testing-restful-services-java-best-practices/)
- [Junit5 Architecture Jupiter](https://blog.codefx.org/design/architecture/junit-5-architecture-jupiter/)


## Unit Test

**Benefitis** 
- quick feedback
- automated regression checking
- design aid - If is hard to write some test for a code, it maybe an indicative of bad design
- Improves confidence
- acts as a documentation 

## JUnit 5

### Test Lifecycle

#### **@Test Annotation**
The `@Test` marks methods that are to be run as tests.

####  @Before and @After Annotations
-   `@BeforeAll`: Annotated a method with this annotation makes it run once before any test method.
Also, the annotated method must be  `static`. 
-   `@BeforeEach`: Annotated a method with this annotation makes it **be invoked before each test method.**
-   `@AfterEach`:   Annotated a method with this annotation makes it **be invoked after each test method.**
-   The method that is annotated with the  `@AfterAll`  annotation must be  `static`, and it’s run once after all test methods have been run.

#### @DisplayName - 

### Assert methods

- `assert(Not)Equals`
- `assert(Not)Same` 
- `assertFalse/assertTrue`
- `assertIterableEquals`: compares two collections 
- `assertThrows`: explicitly verifies that when a certain code is called within a context an expection is thrown
```java
//todo
```
- `assertAll`: 
```java
//todo
```

### Running Groups of Tests -

#### Executing Tests by Tag Expressions
```java
@Tag("dateTime")
@DisplayName("Date time converter should")
class DateTimeConverterShould {
	....
}
```

#### TestsSuits

