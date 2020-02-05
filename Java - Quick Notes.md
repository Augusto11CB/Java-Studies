

## Virtual Dispatch


## Inherit private members
A subclass does not inherit the private members of its parent class. However, if the superclass has public or protected methods for accessing its private fields, these can also be used by the subclass

Constructors are not inherited (instead, they are chained.

## Abstract Methods and Classes
* Any class with an abstract method is automatically abstract itself and must be declared as such. To fail to do so is a compilation error.
* An abstract class cannot be instantiated.
* A subclass of an abstract class can be instantiated only if it overrides each of the abstract methods of its superclass and provides an implementation (i.e., a method body) for all of them. Such a class is often called a concrete subclass, to emphasize the fact that it is not abstract.
* If a subclass of an abstract class does not implement all the abstract methods it inherits, that subclass is itself abstract and must be declared as such.
* static, private, and final methods cannot be abstract, because these types of methods cannot be overridden by a subclass. Similarly, a final class cannot contain any abstract methods.
* A class can be declared abstract even if it does not actually have any abstract methods. Declaring such a class abstract indicates that the implementation is somehow incomplete and is meant to serve as a superclass for one or more subclasses that complete the implementation. Such a class cannot be instantiated.

### Study Case 
* Abstract class shape
* * Abstract method: area()
* Subclasses of shape: square, circle , rectangle.

``` java 
 List<Shape> shapes = ...
 Shapes.foreach {
  System.out.println(Shape::area)
 }
``` 
When you do this, the method to be invoked is found using virtual dispatch, which we met earlier. 

## Interfaces 

### Default Methods
an interface may wish to mark that some API methods are optional, and that implementing classes do not need to implement them if they choose not to. This is done with the default keyword—and the interface must provide an implementation of these optional methods, which will be used by any implementating class that elects not to implement them.

### Fields 
The only fields allowed in an interface definition are constants that are declared both static and final.

## Generics 

### Problem Contextualisation 1
```java 
List shapes = new ArrayList();   // Create a List to hold shapes 
// Create some centered shapes, and store them in the list
shapes.add(new CenteredCircle(1.0, 1.0, 1.0));
// This is legal Java­but is a very bad design choice 
shapes.add(new CenteredSquare(2.5, 2, 3));
// List::get() returns Object, so to get back a 
// CenteredCircle we must cast CenteredCircle 
c = (CentredCircle)shapes.get(0);
// Next line causes a runtime failure
 CenteredCircle c = (CentredCircle)shapes.get(1);
```

A problem with this code stems from the requirement to perform a cast to get the shape objects back out in a usable form—the List doesn’t know what type of objects it contains. Not only that, but it’s actually possible to put different types of objects into the same container—and everything will work fine until an illegal cast is used, and the program crashes.

### Solution for Homogeneous collections - Problem Contextualisation 1
To indicate that a type is a container that holds instances of another reference type, we enclose the payload type that the container holds within angle brackets

```java 
//CreateaList­of­CenteredCircle 
List<CenteredCircle>shapes=newArrayList<CenteredCircle>();
//Createsomecenteredshapes,andstoretheminthelist 
shapes.add(newCenteredCircle(1.0,1.0,1.0));
//Nextlinewillcauseacompilationerror 
shapes.add(newCenteredSquare(2.5,2,3));
```
 
Thissyntaxensuresthatalargeclassofunsafecodeiscaughtbythecompiler,beforeitgets anywherenearruntime.

### Generic Types and Type Parameters
Thesyntax<T>hasaspecialnameit’scalledatypeparameter,andanothernamefora generictypeisaparameterizedtype.
> Typeparametersalwaysstandinforreferencetypes.Itisnotpossibletousea primitivetypeasavalueforatypeparameter.
> 
### Type erasure
* Todo after type we can get some problems related with overloaded generic methods. The raw type of them are exactly the same, so in the moment that the code will run the system don't know which of them use.

The type erasure process can be imagined as a translation from generic Java source code back into regular Java code.


### Bounded type parameters
Use to impose restrictions in generic definitions. 
#### Study case
There is a generic definition of Box. This definition needs to ensure that only numbers will be holded by the abstraction. To achieve this use *bound* on the type parameter.


```java
public class NumberBox<T extends Number> extends Box<T> {   
  
	public int intValue() {         
		return value.intValue();
    } 
} 
```
The type bound T extends Number ensures that T can only be substituted with a type that is compatible with the type Number. **As a result of this, the compiler knows that value will definitely have a method intValue() available on it.**

### Covariance
### Wildcards
The question mark (?) is known as the wildcard in generic programming . It represents an unknown type.

**Types of wildcards in Java:**
**Upper Bounded Wildcards:** These wildcards can be used when there is the necessity to relax the restrictions on a variable. For example, to write a method that works on List < integer >, List < double >, and List < number > , is possible do this using an upper bounded wildcard.

`public static void add(List<? extends Number> list)`

**Lower Bounded Wildcards:** It is expressed using the wildcard character (‘?’), followed by the super keyword, followed by its lower bound: <? super A>. 

`Collectiontype <? super A>`

```java
  public static void printOnlyIntegerClassorSuperClass(List<? super Integer> list) 
    { 
        System.out.println(list); 
    } 
```
Here arguments can be Integer or superclass of Integer(which is Number). The method printOnlyIntegerClassorSuperClass will only take Integer or its superclass objects.

**Unbounded Wildcard:** This wildcard type is specified using the wildcard character (?), for example, List. This is called a list of unknown type. These are useful in the following cases

-   When writing a method which can be employed using functionality provided in Object class.
-   When the code is using methods in the generic class that don’t depend on the type parameter
- 

if Cat extends Pet, then List<Cat> is a subtype of List<? extends Pet>, and so: 
```java
List<Cat> cats = new ArrayList<Cat>();
List<? extends Pet> pets = cats;
```

However, this differs from the array case, because type safety is maintained in the following way:

```
pets.add(new Cat()); // won't compile
pets.add(new Pet()); // won't compile 
cats.add(new Cat());
```

**The compiler cannot prove that the storage pointed at by pets is capable of storing a Cat and so it rejects the call to add(). ** However, as cats definitely points at a list of Cat objects, then it must be acceptable to add a new one to the list.

### Type parameters x Wildcard
* A parameterized type, such as ArrayList<T>, is not instantiable; we cannot create instances of them. This is because <T> is just a type parameter—merely a placeholder for a genuine type.
It is only when we provide a concrete value for the type parameter (e.g.,
ArrayList<String>) that the type becomes fully formed and we can create objects of that type.


[https://stackoverflow.com/questions/45086324/generics-using-wild-cards-vs-using-type-parameters-e/45086777](https://stackoverflow.com/questions/45086324/generics-using-wild-cards-vs-using-type-parameters-e/45086777)
[https://stackoverflow.com/questions/3486689/java-bounded-wildcards-or-bounded-type-parameter](https://stackoverflow.com/questions/3486689/java-bounded-wildcards-or-bounded-type-parameter)
[https://stackoverflow.com/questions/38944896/difference-between-bounded-type-parameter-t-extends-and-upper-bound-wildcard](https://stackoverflow.com/questions/38944896/difference-between-bounded-type-parameter-t-extends-and-upper-bound-wildcard)

## Lambda
Lambda expressions represent the creation of an object of a specific type. The type of the instance that is created is known as the **target type** of the lambda.

Target types are also called functional interfaces and they must:
* Be interfaces  
* Have only one nondefault method (but may have other methods that are default)

### How Does Lambda work?
When javac encounters a lambda expression, it interprets it as the body of a method with a specific signature

Javac Looks at the lambda surrounding code:

* The lambda must appear where an instance of an interface type is expected.
* The expected interface type should have exactly one mandatory method.
* The expected interface method should have a signature that exactly matches that of the lambda expression.
