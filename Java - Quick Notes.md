

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
