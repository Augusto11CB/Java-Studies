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
