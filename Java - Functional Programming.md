Summary  
Functional Programming Java

![](https://lh6.googleusercontent.com/NmK2_6N5LShnfQNjHl6IL6F5gLsOV10e1Kz6qOSB2_ltFcjQcdxc8aYmkpLz_Nf8k5JD9cAVNU4Wzn4dgbJotgmDc3Zz-8NoWeOVqi7G_36uYZnpfIasXtZjdqGYE0HECV8JOGCK "linha curta")

Augusto Bueno  
July, 14 of 2019

  
  
  
  

# What is Functional Programming?

# Interfaces New Features

Erstwhile java interfaces just were capable to contains implicitly public abstract methods ( just method signatures) and static final variables.

public interface Scalable {

void setScale(double scale);

double DEFAULT_SCALE = 1.0;

}

Since the introduction of the new features the interfaces, now, are capable to contain static concreat methods like the follow:

static boolean isScalable(Object obj) {

return obj instanceof Scalable;

}

Another feature is the default method, such as abstract classes, interfaces can have the declaration of concrete method instance. Interfaces just used to provide the signature.

default void resetScale() {

// setScale is a method signature declared above in the introduction

setScale(DEFAULT_SCALE)

}

Default methods can, also, be overridden by the classes that implement the interface.

  

Interfaces Vs Abstract Classes

1.  Type of methods: Interface can have abstract methods, static concreate methods and default methods(method instance). Abstract class can have abstract and non-abstract methods.
    
2.  Final Variables: Variables declared in a Java interface are by default final. An abstract class may contain non-final variables.
    
3.  Type of variables: Abstract class can have final, non-final, static and non-static variables. Interface has only static and final variables.
    
4.  Implementation: Abstract class can provide the implementation of interface. Interface can’t provide the implementation of abstract class.
    
5.  Inheritance vs Abstraction: A Java interface can be implemented using keyword “implements” and abstract class can be extended using keyword “extends”.
    
6.  Multiple implementations: An interface can extend another Java interface only, an abstract class can extend another Java class and implement multiple Java interfaces.
    
7.  Accessibility of Data Members: Members of a Java interface are public by default. A Java abstract class can have class members like private, protected, etc.
    

  
  

Brief Review When to use What?

Abstract Classes

1.  In java application, there are some related classes that need to share some lines of code then you can put these lines of code within abstract class and this abstract class should be extended by all these related classes.
    
2.  You can define non-static or non-final field(s) in abstract class, so that via a method you can access and modify the state of Object to which they belong.
    
3.  You can expect that the classes that extend an abstract class have many common methods or fields, or require access modifiers other than public (such as protected and private).
    

Interfaces

1.  It is total abstraction, All methods declared within an interface must be implemented by the class(es) that implements this interface.
    
2.  A class can implement more than one interface. It is called multiple inheritances.
    
3.  You want to specify the behavior of a particular data type, but not concerned about who implements its behavior.
    

  

# Functional Interfaces

Functional interfaces are any interface with a single abstract method and static and default methods allowed. An F.i. intended to be implemented by stateless classes (classes with no instance fields).

## @FunctionalInterface Annotation

Interfaces annotated with @FunctionalInterface will been checked by the compiler that verify the “single abstract method” property

# Lambda Expressions

All lambda expressions are a compact syntax to implement functional interfaces.

Comparator<Employee> byNameLambda =

(Employee a, Employee b) -> {return a.getName().compareTo(b.getName());}

This feature works for functional Interfaces because functional interfaces have a single abstract method, therefore there is no ambiguity of which method we need to implement since just there is one.

//Expression with no parameter

Runnable r = () -> {System.out.println(“A compact run);};

## Type inference and Lambda

What is type inference?

Type inference is a mechanism used by the compiler to infer a type that has not been specified.

Type inference is also used to identify the functional interface which is being implemented by a given lambda expression. Additionally, when the type of the parameters are omitted the compiler will infer which type is based on the context.

//The below code is not going to work because there is not enough information to figure out what functional interface we are trying to implement

Object x1 = msg -> System.out.println(msg.length())

//The next snipet of code will work since we insert a cast to the type Consumer of string

Object works = (Consumer<String>) ((String msg )-> System.out.println(msg.length()))

To summarize, the context of a lambda expression must contain enough information to identify the appropriate function interface. Normally we have the right amount of context in four situations:

1.  Consumer<String> = lambda //lambda assigned to Consumer
    
2.  new Thread(lambda) // Infer that the lambda type will be the same type specified in the Thread constructor method
    
3.  return lambda //Infers that the lambda type is going to be the return type of the method in which this statement is been implemented.
    
4.  (Consumer<String>) lambda
    

## Lambda - Capturing Fields

can capture class field and instance fields, capture local variables.

What do we mean of capturing fields? we mean that the lambda expression can access a static field of the enclosing class and the instance field of the object. How it is possible? They store a reference to the enclosing object.

Also, lambda expressions can access local variables of the methods they are located in provided they are effectively final. Why do we have this rule? As lambda store a copy of this variable, without this rule, the lambda can end up with an out of date information.

  
  
  

Capturing Values - Lambda vs Anonymous Class Particularities

public  class  LambdaImplementation {

  

public  static  void main(String[] args) {

  

// Anonymous class, multiple instances

System.out.println("\nAnonymous class:");

for  (int i=0; i<5; i++) {

Consumer<String> myPrinter1 =  new  Consumer<String>() {

@Override

public  void accept(String msg) {

System.out.println("Consuming "  + msg);

}

};

myPrinter1.accept(myPrinter1.toString());

}

  

// Non-capturing lambda, one instance

System.out.println("\nNon-capturing lambda:");

for  (int i=0; i<5; i++) {

Consumer<String> myPrinter2 =

msg ->  System.out.println("Consuming "  + msg);

myPrinter2.accept(myPrinter2.toString());

}

  

// Constant-capturing lambda, one instance

System.out.println("\nConstant-capturing lambda:");

final  int secret =  42;

for  (int i=0; i<5; i++) {

Consumer<String> myPrinter3 =

msg ->  System.out.println("Consuming "  + msg +  ", "  + secret);

myPrinter3.accept(myPrinter3.toString());

}

  

(new  LambdaImplementation()).foo();

}

  

private  int id =  1;

public  void foo() {

System.out.println("\nInstance-capturing lambda:");

for  (int i=0; i<5; i++) {

// this-capturing lambda, many instances!

Consumer<String> myPrinter4 =

msg ->  System.out.println("Consuming "  + msg +  ", "  + id);

myPrinter4.accept(myPrinter4.toString());

}

}

  

Output

![](https://lh6.googleusercontent.com/5P9t-D_P2_Xp1Qkk4vLULxK9Qq1WgfdAexasXbY2AX6F7GNp8DkSAnfkx8260OA9zhJL7ZJVVqfgC1yZfRjn-OkpLoJ3O8IG_sW4R-50Kdcmzr6UaT9D_8cS1iJwD4AsJL2qp9Go)

  

# Method References

Method references are an expression denoting a method. They have no intrinsic type, meaning that a type inference process is required to assign them a type that is based on the context where the method reference is put. For M.R. the context must be such that a unique functional interface can be identified.

![](https://lh3.googleusercontent.com/MMMKHOPIV_jx9joQ-xLCxjIlaeFoxRqaMMGLdDzGiO_l5TVdVYs4kuL0MIg4_QgcycT0773XAtMCkddfB-3co30PvVjNFHWWA-5E_yGKUZPek0maFjssqdX5epfiiyh1VgLxcrrG)

  

//Instance method (instance specified):

  

Employee frank = new mployee (“Frank”, 3000);

Interger salary = frank.getSalary();

Supplier<Interger> s2 = frank::getSalary; // Return supplier when method reference is used

System.out.println(s2.get());

  

//An instance method (instance not specified):

  

Function<Employee, interger> f1 = Employee::getSalary; // receives an Employee and return an

//integer

Integer francSalary = f1.apply(frank)

  

The printAll is going to take an array and accepts an arbitrary function that receives T and returns a String when applying this function to each object in the array.

  

public static <T> void printAll ( T[] array, Function<T, String> toStringFun) {

  

int i = 0;

for (T t : array) {

System.out.println(i++ + “:\t” + toStringFun.apply(t)

}

}

  
  

//Calling the function

Employee dept [] = {new Employee(“Aug”,10000)};

printAll(dept, Employee:: getName);

  

printAll(dept, emp -> emp.getSalary() + “”)

  

# New Functional Interfaces

![](https://lh3.googleusercontent.com/s5yDQvYvPvHJKA2AnztAnBOKDnZymjWJCDcQCDsRnXyzqV_8IqZgC_-OrYpdEcfZhDWic7Q21P7neUSNn2giLUxMLuaZ7EUapGvS0gCuaALQ03gXAte1KGGfM2TNF0Mm3x9Gfsfd)

UnaryOperator F.I.

-   Represents an object updater/modifier, which preservers the type
    

BinaryOperator F.I.

-   Represents an operation between two objects, preserving the type
    

Function F.I.

-   Represents an object transformer, from one type to another
    
-   Example;
    

Comparator<Employee> byName = comparator.comparing (e -> e.getName());

The (e -> e.getName()), receiver an object and extrat one of its field (receives an object Employee and return an string name).

Predicate F.I.

-   Represents a property that some object have
    

  

[https://stackoverflow.com/questions/40521954/understanding-java-8-lambda-expressions-used-to-define-spring-security-beans](https://stackoverflow.com/questions/40521954/understanding-java-8-lambda-expressions-used-to-define-spring-security-beans)

  

[https://www.geeksforgeeks.org/difference-between-anonymous-inner-class-and-lambda-expression/](https://www.geeksforgeeks.org/difference-between-anonymous-inner-class-and-lambda-expression/)

  
  
  

  
  
  
  
  

![](https://lh4.googleusercontent.com/uX6bQNnLaeK2BkDQ2nsCMGvovMGOtcpKxs9JtpUXik1bNU6KaFXNXCr5KyNioW32SDndX1AngGWAMRpGOIschhW9_VO9r8MGkdLraZxf3fy_tKIu0EzQ7ngQh9wJM_eWTgk1PNZw "traço curto")
