# Java - Writing Clean Tests with JUnit 5

- [Modern Best Practices for Testing in Java](https://phauer.com/2019/modern-best-practices-testing-java/)
- [Writing clean tests ](https://www.petrikainulainen.net/writing-clean-tests/)
- [JUnit 5 -  the ultimate resource](https://www.petrikainulainen.net/junit-5-the-ultimate-resource/)
- [JUnit 5 - tutorial](https://www.petrikainulainen.net/junit-5-tutorial/)
- [Testing RESTful Services in Java](https://phauer.com/2016/testing-restful-services-java-best-practices/)
- [Junit5 Architecture Jupiter](https://blog.codefx.org/design/architecture/junit-5-architecture-jupiter/)


## Unit Test
**Definition:** A unittest test only a piece of code by invoking it and checking the correcteness of some assumptions.
 
**Benefitis** 
- quick feedback
- automated regression checking
- design aid - If is hard to write some test for a code, it maybe an indicative of bad design
- improves confidence
- acts as a documentation 

## JUnit 5

### JUnit 5 Architecture
JUnit 5 is the sum of three projects:
1. JUnit Platform - It provides an API to launch tests from either the console, IDEs, or build tools (gradle)
2. JUnit Jupiter - Writing tests and extensions
3. JUnit Vintage - It Provides an engine for running JUnit 3 and JUnit 4 base test on the platform

### Four Phases of a test - A.A.A.A.
- Arrange: Prepare the scenario that will allow to perform the test (also called test **fixture**);
- Act: The function or action that will be tested is called;
- Assert: Check the output;
- *Annihilation*: Put the system in the state that was before the test.

### Test Lifecycle

#### Per method/class
By default JUnit create a new instance of a test class before execute each test method. This is done to avoid unexpected side effects due to mutable instances estate -> **Per method fase**

This behavior can be changed to execute all the test methods on the same instance -> **Per Class fase**. It can be done by **annotating** the test class with:
- `@TestInstance(TestInstance.Lifecycle.PER_METHOD)`
- `@TestInstance(TestInstance.Lifecycle.PER_CLASS)`

####  @Before and @After Annotations
-   `@BeforeAll`: Annotated a method with this annotation makes it run once before any test method.
Also, the annotated method must be  `static`. 
-   `@BeforeEach`: Annotated a method with this annotation makes it **be invoked before each test method.**
-   `@AfterEach`:   Annotated a method with this annotation makes it **be invoked after each test method.**
-   The method that is annotated with the  `@AfterAll`  annotation must be  `static`, and it’s run once after all test methods have been run.

**Why methods annotated with `@BeforeAll` and `@AfterAll` must be static ?**
Both annotations are static methods, because in the scenario where a test class is using **per method fase**, these methods are called before the instantiation of the test class.


### The Annotation @Test -
The `@Test` marks methods that are to be run as tests.

### The Annotation @DisplayName - 

### Assert methods

- assert(Not)Equals
	```java
	@Test
	fun dontsaveCandidatePreRegistrationListWhenSomethingHappen() {
			
		val exception = assertThrows<InvalidStateException> {
			this.registrationService.save(candidatePreRegistre)
		}	
		assertEquals("Error with saving CandidatePreRegistre", exception.message)
	}
	```

- assert(Not)Null
	```java
	@Test  
	void valueIsNotNull() {  
	    MySystem mySystem = new MySystem();
	    String value = mySystem.getValue();
	    assertNotNull(value);
	}
	```
	
- assert(Not)Same
- assertFalse/assertTrue
- assertIterableEquals: compares two collections 
- assertThrows: explicitly verifies that when a certain code is called within a context an expection is thrown
	```java
	//todo
	```
- `assertAll`: 
	```java
	//todo
	```

### Running Groups of Tests

#### Executing Tests by Tag Expressions
```java
@Tag("dateTime")
@DisplayName("Date time converter should")
class DateTimeConverterShould {
	....
}
```

#### TestsSuits
```java
//todo
```

### Test Hierarchies - Nested Test Classes
-  The nested test classes must have to be annotated with the @Nested annotation. This annotation ensures that JUnit 5 recognizes our nested test classes.
- Only non-static inner classes
- @BeforeAll and @AfterAll do not work by default (Only with `Lifecycle.PER_CLASS`
- There is no limit for the depth of the class hierarchy.

