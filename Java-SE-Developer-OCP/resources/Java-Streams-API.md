# Java Streams API

## Characteristics of Streams
- **Stream is an immutable flow of elements**
- Stream processing can be **sequencial** or **parallel**
- once an elements is processed, it is no longer available from the stream
- stream pipeline traversal uses **method chaining** - intermediate operations return streams
- pipeline traversal is **lazy**
	- **intermediate actions are deferred until stream is traversed by the `terminal` operation**
	- the chain of activities could be fused into a single pass on data
	- stream processing ends as soon as the result is determined; remaining stream data can be ignored
- streams operations use functional interfaces and can be implemented as **lambda expressions**

## Create Streams Using Stream API
- Streams handling is described by the following interfaces:
	- The `BaseStreams` -  defines core streams behaviors, such as managig the stream in a parallel or sequential mode
	- `Stream, DoubleStream, IntStream, LongStream` interfaces extend the `BaseStream` and provide stream processing operations
	- Streams use generics
	- to avoid **excessive boxing and unboxing**, primitive stream variants are also provided (move from stack to the heap all the time)
	- Stream can be obtained from any `collection and array` or by using static methods of the `Stream` class

```java
IntStream.generate(() -> (int) (Math.random()*10)).takeWhile(n -> n !=3 ).sum();
// Will generate random value until number 3 happens
// Stream handling operations categories: Short-circuit

Stream.of(new Food(), new Drink()).forEach(p -> p.setPrice(1));

Arrays.stream(array).filter(...).forEach(...)
```

## Stream Pipeline Processing Operations
- Stream handling operations categories:
	- `Intermediate` - Perform actions and **produce another stream**
	- `Terminal` - Traverse stream pipeline and end the stream processing
	- `Short-circuit` - produces finite result, even if presented with infinite input

![](resources/stream-handling-operations-categories.png)

- `peek` vs `forEach` : the difference etween them is that `peek` is a **intermediate** operation and produces a **stream as a result** while `forEach` is a **terminal** operation

## Using Functional Interfaces

- Basic functions purposes
	- `Predicate<T>` performs tests
		- defines method `boolean test(T t)` to apply conditions to filter elements
	- `Functions<T,R>` converts types
		- defines method ` R apply (T t)` to convert types of elements
	- `UnaryOperator<T>` converts values
		- defines method `T apply (T t)` to convert values
	- `Consumer<T>` processes elements
		- `void accept (T t)` to process elements
	- `Supplier` produces elements
		- defines method `T get()` to produces elements


```java
Stream.generate(<supplier>)
	.filter(<Predicate>)
	.peek(<consumer>)
	.map(<Function>/<UnaryOperator>)
	.forEach(<Consumer>)
```

## Primitive Variants of Functional Interfaces
- improve stream pipeline handlin performance by avoiding excessive auto-boxing-unboxing
![](resources/primitive-variants-of-functional-interfaces.png)

- `ToIntFunction<T>`: takes an object and return a primitive (in this case `int`)
- `IntFunction<R>`: creates objects out of primitives
- `IntToLongFunction`: stream of primitive to stream of primitives of different types
- `IntPredicate`: apply the condition to a primitive
- `IntSupplier`: produces intergers 

## Bi-Argument Variants of Functional Interfaces
- process more than one value at a time
- extra parameter is provided compared to basic function interfaces
	- `BiPredicate<T,U>` defines method `boolean test(T t, U u)` to apply conditions
	- `BiFunction<T, U, R>` defines methods `R apply (T t, U u)` to convert two types into a single result
	- `BinaryOperator<T>` defines method `T apply (T t1, T t2)`  to convert two values
	- `BiConsumer<T>` defines method `void accept (T t, U u)` to process a pair of elements 

![](resources/primitive-variants-of-bi-args-functional-interfaces.png.pngprimitive-variants-of-bi-args-functional-interfaces.png)

```
Map<Product, Integer> items = ...;
items.forEach((p,q) -> p.getPrice().multiply(BigDecimal.valueOf(q.intValue())));
```