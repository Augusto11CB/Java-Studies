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
- assertArrayEquals
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
	@Test
	@DisplayName("Exception is thrown when invalid product ID")
	void exceptionThrownWhenInvalidProducID() {
		long productID = 0;
		assertThrows(RuntimeException.class, () -> {
			reward.setGiftProductID(productID);
		});	
	}
	```
- assertAll: 
	```java
	@Test
	@DisplayName("Reward applied with enough points")
	void rewardApplied() {
		RewardInformation info = reward.applyReward(buildSampleOrder(109), 200);
		
		assertAll("Reward info errors", 
				() -> assertNotNull(info),
				() -> assertEquals(2, info.getDiscount()),
				() -> assertEquals(10, info.getPointsRedeemed())
		);

	}
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

#### Example of Nested Test Class
``` java
public class NestedTestSimpleExample {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.out.println("@BeforeAll - Outer Class");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		System.out.println("@AfterAll - Outer Class");
	}

	@BeforeEach
	void setUp() throws Exception {
		System.out.println("@BeforeEach - Outer Class");
	}

	@AfterEach
	void tearDown() throws Exception {
		System.out.println("@AfterEach - Outer Class");
	}
	
	@Test
	void test() {
		System.out.println("Outer Class Test");
	}
	
	@Nested
	class InnerClass {
		@BeforeEach
		void setUp() throws Exception {
			System.out.println("@BeforeEach - Inner Class");
		}

		@AfterEach
		void tearDown() throws Exception {
			System.out.println("@AfterEach - Inner Class");
		}
		
		@Test
		void test() {
			System.out.println("Inner Class Test");
		}
	}
}
```

#### Nested Test Class  - Lifecycle method order
```java
@BeforeAll - Outer Class 
@BeforeEach - Outer Class 
- - Outer Class Test 
@AfterEach - Outer Class 
@BeforeEach - Outer Class 
@BeforeEach - Inner Class 
- - Inner Class Test 
@AfterEach - Inner Class 
@AfterEach - Outer Class 
@AfterAll - Outer Class
```

### Assumptions
- Stoping the execution of a test at some point. 
- Using assumptions is one way to conditionally stop the execution of a test.  
- **failed assumptions do not result in test failure like assertions. A failer assumption just abort the test.**

1. assumeTrue - It validates if the given assumption evaluates to true to allow the execution of the test.
2. assumeFalse - It validates if the given assumption evaluates to false to allow the execution of the test
3. assumingThat() - executes the supplied lambda passed 

### Test Interfaces and Default Methods

### Repeatable Tests

### Dynamic	Tests
The standard tests annotated with _@Test_ annotation are static tests which are fully specified at the compile time. **A  _DynamicTest_  is a test generated during runtime**.

> It is composed of a *display name* and an  `Executable`.  `Executable`  is a functional interface which means that the implementations of dynamic tests can be provided as lambda expressions or method references.
> \- [sormuras](https://sormuras.github.io/blog/2018-05-14-junit5-scatter-assertions.html)

Dynamic tests are generated by a factory method annotated with `@TestFactory`. A test factory must return either Stream, Collection, Iterable, or Iterator of `DynamicTest` instances. Morerver, test factories cannot be `static` or `private`

**WARNING**: Lifecycle callbacks are not supported

```java
@TestFactory
Stream<DynamicTest> test() {
  var object = new Object();
  return Stream.of(
      dynamicTest("constructor", () -> assertNotNull(object)),
      dynamicTest("equality", () -> assertNotEquals(new Object(), object)),
      dynamicTest("waitWithoutMonitorFails", () -> assertThrows(Exception.class, object::wait)));
}
```

```java
@TestFactory

Stream<DynamicTest> dynamicTestsFromStreamInJava8() {

    DomainNameResolver resolver = new DomainNameResolver();

    List<String> inputList = Arrays.asList("www.somedomain.com", "www.anotherdomain.com", "www.yetanotherdomain.com");

    List<String> outputList = Arrays.asList("154.174.10.56", "211.152.104.132", "178.144.120.156");

    return inputList.stream()

        .map(dom -> DynamicTest.dynamicTest("Resolving: " + dom, () -> {

               int id = inputList.indexOf(dom);

               assertEquals(outputList.get(id), resolver.resolveDomain(dom));

         }));

}
```

### Parameterized Tests 
//TODO

### Dynamic Tests vs Parameterized Tests
Dynamic tests differ from the parameterized tests as they support full test lifecycle, while parametrized tests don't.

### Argument Sources
In Parameterized tests, arguments are provided by sources via annotations. 

#### Argument  Sources - Rules
- At least one source
- Each source must provide values for all parameters in the test method
- One execution for each group of argument

#### JUnit Default Sources
- **@ValueSource** 
	- it defines an array of strings, ints, doubles, longs.
	- It can be used only for test methods that has single parameter
- **@EnumSource**
	- It runs a test with the values of an enum 
	- Optional parameters {names (this one specify the name of the enums that must be injected in the test), mode(this one allows include or exclude the values informed in the formerly parameter )}
	- One execution for each group of argument
	```java
	@ParameterizedTest  
	@EnumSource(SpecialProductsEnum.class)  
	@EnumSource(value = SpecialProductsEnum.class, names = { "BIG_LATTE", "BIG_TEA" })  
	@EnumSource(value = SpecialProductsEnum.class, names = { "BIG_LATTE", "BIG_TEA" }, mode = EnumSource.Mode.EXCLUDE)  
	@EnumSource(value = SpecialProductsEnum.class, names = "^BIG.*", mode = EnumSource.Mode.MATCH_ALL)  
	void discountShouldBeAppliedEnumSource(SpecialProductsEnum product) {  
	    reward.setGiftProductId(product.getProductId());  
	  RewardInformation info = reward.applyReward(  
	            getSampleOrder(), 200);  
	  
	  assertTrue(info.getDiscount() > 0);  
	}
	```
- **@MethodSource**
	- With this annotation is possible to specify the name of one or more methods that will provide the arguments for the test
	- For tests with a single parameter
		- returns a stream of parameter type
		- returns a stream of primitive types
	- For tests with multiple parameters
		- Return a Stream, iterable, Iterator or array of types arguments
	- The methods used by `MethodSource` must be a static unless `PER_CLASS` lifecycle is been used

	```java
	@ParameterizedTest  
	@MethodSource("productIdsCustomerPoints")  
	void discountShouldBeAppliedMethodSourceMultipleParams(long productId, long customerPoints) {  
	    reward.setGiftProductId(productId);  
	  RewardInformation info = reward.applyReward(  
	            getSampleOrder(), customerPoints);  
	  
	  assertTrue(info.getDiscount() > 0);  
	}
	
	static Stream<Arguments> productIdsCustomerPoints() {  
	    return productIds()  
	            .mapToObj(  
	                    productId ->  
	                            Arguments.of(productId, 100 * productId) // Object ... arguments  
	  );  
	}
	```
	
- **@CsvSource**
	- comma-separated string literals
	```java
	@ParameterizedTest  
	@CsvSource({ "1, 200", "2, 150", "5, 100" })  
	void discountShouldBeAppliedCsvSource(long productId, long customerPoints) {  
	    reward.setGiftProductId(productId);  
	  RewardInformation info = reward.applyReward(  
	            getSampleOrder(), customerPoints);  
	  
	  assertTrue(info.getDiscount() > 0);  
	}
	```
- **@CsvFileSource** 
	- CSV files from classpath
- 	 **@ArgumentsSource** 
		- For custom sources
		-  TODO

#### Argument  Conversion !!!
 When working with parameterized test, if the argument and the parameter types do not match, there is the possibility to create a custom converter that implements either `Argumentconverter` interface or `SimpleArgumentConverter`, and indicates which parameter will receive the converted input using `@ConvertWith{CustomConverter.class}`.

**Example** - CustomConverter

```java
public class ProductArgumentConverter extends SimpleArgumentConverter {  
  
    @Override  
  protected Object convert(Object source, Class<?> targetType) {  
        assertEquals(String.class, source.getClass(), "Can only convert from String");  
  assertEquals(Product.class, targetType, "Can only convert to Product");  
  
  String[] productString = source.toString().split(";");  
  
  Product product = new Product(  
                Long.parseLong(productString[0]),  
  productString[1].trim(),  
  Double.parseDouble(productString[2])  
        );  
  
 return product;  
  }  
}
```

**Example** - Using the custom converter to transform an argument to the right type of the parameter {@ConvertWith}

```java
@ParameterizedTest  
@ValueSource(strings = { "1; Small Decaf; 1.99", "2; Big Decaf; 2.49" })  
void discountShouldBeApplied(  
        @ConvertWith(ProductArgumentConverter.class) Product product) {  
    System.out.println("Testing product " + product.getName());  
  reward.setGiftProductId(product.getId());  
  RewardInformation info = reward.applyReward(  
            getSampleOrder(), 200);  
  
  assertTrue(info.getDiscount() > 0);  
}
```

### Extensin Points
JUnit Jupiter extensions can declare interest in certain junctures of the test life cycle. When the JUnit Jupiter engine processes a test, it steps through these junctures and calls each registered extension.

Each extension point corresponds to an interface and their methods take arguments that capture the context at that specific point in the test’s lifecycle.
> Prefer extension points over features
> \- JUnit design principles 


TODO
