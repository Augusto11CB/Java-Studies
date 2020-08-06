
# Java 8 Best Practices
## TO DO
* [10 Examples of Optional in Java 8](https://javarevisited.blogspot.com/2017/04/10-examples-of-optional-in-java-8.html#axzz4pXFCC65F)
* [How Join Multiple String into One in Java 8](https://www.java67.com/2016/08/java-8-stringjoin-example.html)
* Dates in Java 8
* [java-merge-maps](https://www.baeldung.com/java-merge-maps)

## Lambdas
Lambda expressions basically express instances of [functional interfaces](#functional-interfaces). So that, lambda expressions just implement abstract function and therefore implement functional interfaces.

An agreement about lambda expression is that the right side must have an expression. If the logic is too large to declare it as an expression, put it in a method and have lambda call that method.

[Good Practices with Lambda](https://medium.com/@gelopfalcon/best-practices-when-you-use-lambda-expressions-in-java-f51e96d44b25)

```java
public class Main {  
  
    public static void main(String[] args) {  
        Calculator calculator = new Calculator();  
		int myReturn = calculator.performCalculation(4, 5, (x, y) -> addTwoNumbers(x, y));  
  }    
    private static int addTwoNumbers(int a, int b) {  
        return a + b;  
  }  
}  
```
``` java  
class Calculator {  
    public int performCalculation(int numberA, int numberB, WrapperOfOperations calculateOperation) {  
        return calculateOperation.operation(numberA, numberB);  
  }  
}  
```
```java
//Functional Interface  
@FunctionalInterface
interface WrapperOfOperations {  
    int operation(int a, int b);  
}
```

### Why use Lambda
####  Case - Large methods Same Some Code
 - Two Large methods contains same code
 - Except for one bit in the middle
 - Can use a lambda to express the difference

**Case exemplification - Problematic Version**
```java
private int doVerificationAssistent() {
	//CODE XYZ
	//SPECIFIC LOGIC
	//CODE ABC
}
private int doVerificationDirector() {
	//CODE XYZ
	//SPECIFIC LOGIC2
	//CODE ABC
}
```

**Case exemplification - Applying Lambda**
Rearrange the code to have a lambda passing just a specific peace of logic
```java
private int doVerificationAssistent() {
	return doVerification(lambdaExpressionForAssistent)
}

private int doVerification(Function<A,B> fn){
	//CODE XYZ
	result = fn.apply(arg)
	//CODE ABC
}
```

### What is meant by lambda *target type* and *target type context* in Java?
> lambdas expression infer their type from their target context

[ref](https://stackoverflow.com/questions/55205665/what-is-meant-by-lambda-target-type-and-target-type-context-in-java)

## Method Reference

Method references are a special type of lambda expressions. Each time when lambda expression is used to just referring a method of a functional interface,  lambda expression can be replaced by method reference, but instead of providing a method body, they refer an existing method by name.

Four types of method references:
-  Static Methods
-   Instance methods of particular objects
-   Instance methods of an arbitrary object of a particular type
-   Constructor

### Referencing Static Method
Converting words to uppercase

```java
List<String> msgs = Arrays.asList("Hej","tack");
// Normal lambda
msgs.forEach(w -> StringUtils.capitalize(w));

//Method Reference
msgs.forEach(StringUtils::capitalize);
```
### Referencing Constructor
```java
interface Messageable{
	Message getMessage(String msg);
}
class Message{
	Message(String msg){
		System.out.print(msg);
	}
}

Messageable hello = Message::new;
hello.getMessage("Hello");

```

## Streams
- A Stream **is** a pipeline of functions that can be evaluated
- Streams **can** transform data
- A Stream **is not** a data structure
- Streams **cannot** mutate data - 
	- However the data can be transformed and later stored in another data structure or perhaps consumed by another operation.

### Intermediate Operations
Operations that outputs another stream.

 - **filter**: returns a stream of elements that satisfy the conditions passed in as parameter to the operation
 - **map**: returns a stream of elements after they have been converted by some function passes in as parameter.
 - **distinct**: Distinct returns a stream of elements such that each element is unique in the stream

### Terminal Operations
Terminal operations either return concrete types or produce a side effect.

- reduce
- collect
- forEach

**Example**
```java
//Get the unique surnames in uppercase of the first 15 book authors that are 50 years old or older.
   library.stream()
          .map(book -> book.getAuthor())
          .filter(author -> author.getAge() >= 50)
          .map(Author::getSurname)
          .map(String::toUpperCase)
          .distinct()
          .limit(15)
          .collect(toList()));
```

### **Using Lambdas in Streams**
```java
new ArrayList<String>().stream().
// peek: debug streams without changes
	peek(e -> System.out.println(e)).
// map: convert every element into something
	map(e -> e.hashCode()).
// filter: pass some elements through	
	filter(hc -> (hc % 2) == 0).
// collect all values from the stream
	collect(Collectors.toCollection(TreeSet::new))
```

### map()
There are some variations of `map()` methods such as `IntStream`, `LongStream`, and `DoubleStream`.
```java
IntStream intStream = 
       palavras.stream().mapToInt(String::length);
```

### flatMap()
How **flatMap()** works:
```
{ {1,2}, {3,4}, {5,6} } -> flatMap -> {1,2,3,4,5,6}

{ {'a','b'}, {'c','d'}, {'e','f'} } -> flatMap -> {'a','b','c','d','e','f'}
```

**Example**
```java
 String[][] data = new String[][]{{"a", "b"}, {"c", "d"}, {"e", "f"}};

 //Stream<String[]>
 Stream<String[]> temp = Arrays.stream(data);

//Stream<String>, GOOD!
Stream<String> stringStream = temp.flatMap(x -> Arrays.stream(x));
```

**Output**
```
{'a','b','c','d','e','f'}
```

#### Stream.collect()
> Collect all values from the stream

The collect() method of Stream class can be used to accumulate elements of any Stream into a Collection. In Java 8, generally code is written  converting a Collection like a List or Set to Stream and then applies some logic using functional programming methods like the filter, map, flatMap and then converts the result back to the Collection like a List, Set or Map.

>`Collector.toConcurrentMap()` to collect the result of Stream into List, Set, Map, and ConcurrentMap in Java.

#### Collectors.groupingBy()
``` java
List<String> items = Arrays.asList("apple", "apple","banana","apple", "orange", "banana", "papaya");

 Map<String, Long> result =
                items.stream().collect(
                        Collectors.groupingBy(
                                Function.identity(), Collectors.counting()
                        )
                );
```

Output
```json
{
	apple=3, banana=2, papaya=1, orange=1
}
```

To see all **Collector** methods use click [here](https://www.baeldung.com/java-8-collectors).

## Optional and Null
### Optional Users Recommendation
- Fields - User Plain Objects
- Methods parameter - Use Plain Objects
- Return values - Use `Optional` as a return type for any other business logic methods that have an optional result.
- Use `orElse()` instead of get() 
- Use `Optional` for getters that access the optional field.
- Do not use `Optional` in setters or constructors.

**Exemplification**
``` java
//com.google.common.base.Preconditions.checkNotNull(...)
  public class Address {
    private final String addressLine;  // never null
    private final String city;         // never null
    private final String postcode;     // optional, thus may be null

    // constructor ensures non-null fields really are non-null
    // optional field can just be stored directly, as null means optional
    public Address(String addressLine, String city, String postcode) {
      this.addressLine = checkNotNull(addressLine);
      this.city = checkNotNull(city);
      this.postcode = postcode;
    }

    // normal getters
    public String getAddressLine() { return addressLine; }
    public String getCity() { return city; }

    // special getter for optional field
    public Optional<String> getPostcode() {
      return Optional.ofNullable(postcode);
    }

    // return optional instead of null for business logic methods that may not find a result
    public static Optional<Address> findAddress(String userInput) {
      return ... // find the address, returning Optional.empty() if not found
    }
  }
  
```

[Code of Exemplification Reference](https://blog.joda.org/2015/08/java-se-8-optional-pragmatic-approach.html)

### orElse x isPresent()
```java
//Prefer
Optional<User> myOptional = Optional.ofNullable(a);
User user = myOptional.orElse(User.getDefaultUser());

//Avoid
if(myOptional.isPresent()) {
	//Some Code
}
```

### Map Of Optional
The `map` of `Optional` is the termination method.  _If a value is present, apply the provided mapping function to it, and if the result is non-null, return an Optional describing the result. Otherwise return an empty Optional._
[Map Documentation of Optional](https://docs.oracle.com/javase/8/docs/api/java/util/Optional.html#map-java.util.function.Function-) 

## Higher Order Functions
Functions that take others functions as its parameter or return a function. 

#### Higher Order Functions x  First class functions
>_First class functions_  are functions that are treated like an object (or are assignable to a variable).
>
>_Higher order functions_  are functions that take at least one first class function as a parameter.  

### Avoid Methods Overloads
Because of lambdas use **target typing**, the compiler can't necessarily figure out which one's of the methods should be used.
```java
//AVOID!! AVOID!! AVOID!! 
public class Foo<T> {
	public Foo<R> apply (Function<T,R> fn);
	public Foo<R> apply (UnaryOperator<T> fn);		
} 
```

## Interfaces
### Functional Interfaces
Functional Interfaces are interfaces with single abstract method is called functional interface.

- Function<T,R>
	- Represents a function that accepts one argument and produces a result.
- Predicate\<T>
	- Represents a predicate (Boolean-valued function) of one argument.
- Supplier\<T>
	- Represents a supplier of results.
	- XYSupplier - BooleanSupplier, DoubleSupplier etc.
- Consumer\<T>
	- Represents an operation that accepts a single input argument and returns no result.
	- XYConsumer - BiConsumer<T,U>, IntConsumer\<T>, ObjDoubleConsumer\<T> etc.
- UnaryOperator\<T>
	- Represents an operation on a single operand that produces a result of the same type as its operand.
- **java.util.function**

[See All List of Functional Interfaces](https://www.tutorialspoint.com/java8/java8_functional_interfaces.htm)

### Custom Functional Interfaces
**@FunctionalInterface** annotation is used to ensure that the functional interface can’t have more than one abstract method. In case more than one abstract methods are present, the compiler flags an ``Unexpected @FunctionalInterface annotation`` message.

The main benefic of using this annotation is to avoid that someone create another method in the functional interface

See more [here](https://www.geeksforgeeks.org/functional-interfaces-java/).

### Defaut Methods
Once released, is hard to add new methods to an interface without affecting the existing implementation. From Java 8, default methods were introduced to enable interfaces evolve withoud breaking these existing implementations.

The problem above described was solve due to a new concept called Virtual Extension or Defender method that says an interface can implement methods. The great benefit is that it's possible add new default methods without compromise the implementations.

```java
public interface Vehicle {
	String speedUp();
	String type();
	default String turnOn() {
		return "Vehicle started";
	}
	
	default String turnOff() {
		return "Vehicle off";
	}

}
```
```java
public class car Implements Vehicle {
	private String brand;
	//constructor getters
}
```

The _default_ methods _turnOff()_ and _turnOn()_ from the _Vehicle_ interface are **automatically available in the  _Car_  class**.

#### Multiple Interface Inheritance Rules
Since Java allows classes to implement multiple interfaces, it's important to know **what happens when a class implements several interfaces that define the same  _default_  methods**.

``` java
public interface Engine {
	String power;
		default String turnOn() {
		return "Vehicle started";
	}
	
	default String turnOff() {
		return "Vehicle off";
	}

}
```
```java
public class car Implements Vehicle, Engine {
	private String brand;
	//constructor getters
}
```

In the scenario above, **the code simply won't compile, as there's a conflict caused by multiple interface inheritance**.

To solve this problem we have to explicitly create an implementation for those methods:

```java
public class car Implements Vehicle, Engine {
	//code
	//constructor getters
	@Override
	public String turnOn() {
		return  Engine.super.turnOn();
	}
	
	@Override
	default String turnOff() {
		return "Vehicle off";
	}
}
```

####  Static Interface Methods
define and implement  _static_  methods in interfaces is possible. It can be invoked within other static and default mthods

```java
public interface Vehicle {
	//regular and default methods
	static int getHorsePower(int rpm, int torque) {
	    return (rpm * torque) / 5252;
    }
}
```
#### Abstract Classes Versus Interfaces in Java 8
Concrete and abstract classes both may (although don't necessarily) contain fields which might be altered by an object's methods. **These fields are a state of the object**, which can change over the object's lifetime. **An interface can't store instance fields**, because there is no instance - all it has are methods without implementation. So there is nothing about an interface which might change over the lifetime of the program, because it can't store anything changeable.

Also, methods in interfaces are often referred to as behavior. Java allows multiple inheritance of behavior through the use of interfaces, but not state.

## Reference
[Static and Default Methods in Interfaces in Java](https://www.baeldung.com/java-static-default-methods)
[Java 8 explained: Default Methods](https://jrebel.com/rebellabs/java-8-explained-default-methods/)
[Java 8 Best Practices Cheat Sheet](https://jrebel.com/rebellabs/java-8-best-practices-cheat-sheet/)
[Java 8 – Stream Collectors groupingBy examples](https://www.mkyong.com/java8/java-8-collectors-groupingby-and-mapping-example/)
[Difference between Abstract class and Interface in Java 8](https://www.java67.com/2017/08/difference-between-abstract-class-and-interface-in-java8.html)
[Interface Default Methods in Java 8](https://dzone.com/articles/interface-default-methods-java)
[Default Methods In Java 8](https://www.geeksforgeeks.org/default-methods-java/)
[Java 8 Revealed: Lambdas, Default Methods and Bulk Data Operations](https://jrebel.com/rebellabs/java-8-revealed-lambdas-default-methods-and-bulk-data-operations/)
[Java 8 best practices by Stephen Colebourne](https://www.youtube.com/watch?v=wOks4LW6I24&list=WL&index=3&t=0s)
