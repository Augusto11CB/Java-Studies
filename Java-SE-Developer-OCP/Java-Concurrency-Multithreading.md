# Concurrency-Multithreading
## Java Concurrency Concepts
- Thread in a Java program is an execution path. It may involve a series of different method calls, and you carry out whatever business logic you need within a given thread.
- Each thread can be an object that is an implementation of a `Runnable` interface. `Runnable` interface decribes a single method `run` where the thread actions should be put. 
- The method `run` is not called directly, instead the `runnable` object is wrapped into an object of the type `Thread`. The thread schedule will try to execute the method run, but it will do it in a separate thread from that one of the method main.
- Thread scheduler will allocatie portions of CPU time (time-slice) to execute thread actions.
```java
Lateral la = new Lateral();
new Thread(la).start();
```
```java
puublic class Lateral implements Runnable {
	public void run() {
		//thread actions
	}
}
```
- Java threads time-slice hardware threads (processors) provided by the CPU cores and can be interrupted at any time to give way to another thread, making the order of actions performed by different threads stochastic
- The return of method `main` or `run` terminates the thread.

- Thread is like a car.
- the actions defined in the method run is like a route that a car should follow.

## Implement Threads
**Common Practice**
```java
Lateral la = new Lateral();
new Thread(la).start();
```
```java
puublic class Lateral implements Runnable {
	public void run() {
		//thread actions
	}
}
```

**Good For small amount of actions**
```
Runnable r = () -> {
	//code
}

Thread t = new Thread(r);
t.start();
```

## Thread Life Cycle
![](resources/thread-life-cycle.png)

- The same `Runnable` instance can be wrapped up by more than one `Thread` instance and stated as different threads.
- You can verify if thread has not yet terminated and its life cycle phase
- **Same `Thread` instance cannot be started twice.**

```java
Runnable r = () -> {
	//code
}
Thread t1 = new Thread(r);
Thread t2 = new Thread(r);
t1.start();

t2.isAlive();
Thread.State phase = t2.getState();
```

## Interrupt Thread
Logic of a `run` method is in charge of making life cycle decisions.
- a thread in a `runnable` state may check if it has received an interrupt signal
- A thread that has entered a `waiting` or `time waiting` state musch catch `InterruptedException`, which puts it back to `runnable` state, and then decided what it should do.

- `myThread.interrupt()` - When thread is in the running state it is not forced to check this signal unless there are a check (ex: `Thread.currentThread().isInterrupted()`) in the method `run` for this operation.

- When the thread is "sleeping", it is not running, actually, it is a timed waiting state so no interrupted checks is being performed.
	- So if, at the point in time when the thread is asleep, somebody call an `interrupt` method upom the thread, the method `sleep` will be interrupted and will throw an exception `InterruptedException`. This exception can be catch and a decision can be made to choose what is going to happen with that thread. 

![](resources/thread-interrupt.png)

## Block Thread
The `Monitor` object helps to coordinate order of execution of threads
- any object or a class cam be used as a monitor
- it allows threads to enter blocked or waiting states
- it enables mutual exclusion of threads and signaling mechanisms

Keyword `synchronized` enforces exclusive access to the block of code
- Thread that first enterns the `synchronized block`  remais in **runnable state**
- All other threads accessing the same block enter the **block state**
- When a runnable thread exits the `synchronized block`, the lock is released
- Another thread is now allowed to enter the runnable state and place a new lock

```java
public class Some {
	public void synchronized a () {}
	public static void synchronized b() {}
	public void c () {}

}

Some s = new Some();
Runnable r = () -> {
  s.a();
  Some.b();
  synchronized (s) {
	  s.c();
  }
}
```
### apply `synchronized` keyword to a instance method
- Put the current object (this) for the method with de `synchronized` keyword  in a blocked state
- If a current instance is choosen to be the monitor then each instance of some will have it's own monitor. So they will not be blocking each other. **However**, if you launch more than one thread using the same instance of `some` **in between these threads, they will be blocking each other**, when they are using current object as the monitor.


### apply apply `synchronized` keyword to a static method
- Allow to use a class as a monitor rather than a current object. It will block the thread against the overall class
- It threads are associated with different instances of some, **using synchronized against static context** then all those different instances of some will be blocking each other against the class itself (PS: remember, class context is shared between all instances.) 

### apply `synchronized` keyword in a block of code inside a method

