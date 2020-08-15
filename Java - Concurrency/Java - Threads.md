

## Threads
`Thread` are based on the **Template Method** Design pattern and the `run` method must be override. When invoked, threads cannot be invoked by their `run` method (unless you want to be synchronous). Ir order to run multi-threaded programming, the method to be invoked is **start**.

The method `start` is a method in the `Thread` class that sets up all the mechanisms for context switching. And then it calls `run`.

```java
List<MyThread> threads = Stream.iterate(0, n -> n + 1)
	,map(MyThread::new)
	.limit(10)
	.collect(Collectors.toList());

//Calling start method instead of run -> 
threads.forEach(MyThread::start);
```

## Runnable
The `Runnable` interface should be implemented by any class whose instances are intended to be executed by a thread. The class must define a method of no arguments called `run`.

A class that implements `Runnable` can run without subclassing `Thread` by instantiating a `Thread` instance and passing itself in as the target.

```java
public class CustomRunnable implements Runnable {
	private int id;
	private Thread thread = new Thread(this);

	@Override
	public void run() {
		// do something
	}


	public void start() {
		thread.start();
	}
}
```

## ExecutorService

### ExecutorService's `execute` method
```java
void execute(Runnable command)
```

Executes the given command at some time in the future. The command may execute in a new thread, in a pooled thread, or in the calling thread, at the discretion of the Executor implementation.

```java
List<CustomRunnable> listRunnables = Stream.iterate(0, n -> n + 1)
	,map(CustomRunnable::new)
	.limit(10)
	.collect(Collectors.toList());


ExecutorService service = Executor.newCachedThreadPool();
runnables.forEach(service::execute);

service.shutdown();
```

> About the above code, I've supplied all those runnables, but now I'm not dealing with the low-level threads. Instead, the executor service is managing a pool of threads on my behalf. I am submitting jobs to it and having them delegate them to threads and use the pool in an efficient way and execute everything for me.

### Executors
**Factory** and **utility** methods for Executor, ExecutorService, ScheduledExecutorService, ThreadFactory, and Callable classes defined in this package. 

**Some Methods**
- `newCachedThreadPool`() - Creates a thread pool that creates new threads as needed, but will reuse previously constructed threads when they are available.

- `newFixedThreadPool`(int nThreads) - Creates a thread pool that reuses a fixed number of threads operating off a shared unbounded queue.

- 	`newScheduledThreadPool`(int corePoolSize) - Creates a thread pool that can schedule commands to run after a given delay, or to execute periodically.

### Shutdown 
An ExecutorService can be shut down, which will cause it to reject new tasks.

- shutdown() - method will allow previously submitted tasks to execute before terminating

- shutdownNow() - method prevents waiting tasks from starting and attempts to stop currently executing tasks. 

## Callable
The _Callable_ interface is a generic interface containing a single _call()_ method – which returns a generic value _V_. The result of _call()_ method is returned within a _Future_ object.

```java
public class FactorialTask implements Callable<Integer> {
    int number;
 
    // standard constructors
    public FactorialTask(int num) {
		this.number = num;
	}
 
    @Override
    public Integer call() throws InvalidParamaterException {
        int fact = 1;
        // ...
        for(int count = number; count > 1; count--) {
            fact = fact * count;
        }
 
        return fact;
    }
}
```

```java
public class Main {
	List<Callable<Integer> callablesList= Stream.iterate(0, n -> n + 1)
	,map(FactorialTask::new)
	.limit(10)
	.collect(Collectors.toList());


	ExecutorService executor = Executors.newFixedThreadPool(5);

	try {
		// invokeall, you see that takes a collection of callables.
		List<Future<Integer>> futuresResults = service.invokeAll(callables)
		for (Future<String> future: futures) {
			System.out.println(future.get());
		}

	} catch (Exception e) {
		e.printStackTrance();
	} finally {
		executor.shutdown();
	}
}

```

## X - Coordinating Threads

### ExecutorService
`Future<?> submit(Runnable task)` - 
Submits a Runnable task for execution and returns a Future representing that task.  The Future's get method will return null upon _successful_ completion.


Here is a class called Counter. A counter wraps an integer called `count`.There are some simple methods in this classe. The first one is called `increment` and it increments the `count` and a `get` method to return it.

```java
public class Counter {
	private int count;

	public void increment() {
		count++;
	}
	
	public int getCount() {
		return count;
	}
}
```

```java
public void demoCounter() {
	ExecutorService executor = Executors.newCachedThreadPool();

	IntStream.range(0,1000)
		.foreach(i -> service.submit(counter::increment));

service.shutdown();
sout(counter.getCount());
	
}
```
But the problem in the above code is that the **increment** method is being executed by all these different threads all at the same time. And it's very possible that one could get in there and increment, because this thing is actually multiple calls. And it's not thread safe.

### How to Coordenate Operations - Synchronized Method (Implicit Look)

How synchronized methods works is that when a thread enters a synchronized method, it acquires the lock on that object (**this is an implicit lock**). It's a built-in lock associated with the object, and *when a particular thread has acquired the lock, no other thread can get in any of the other synchronized methods until that first method completes and releases the lock.* 

```java
	// 
public synchronized void increment() {
	count++;
}

/*
public void increment() {

	synchronized (this) {
		count++;
	}
}
*/
```
When calling the previus method `demoCounter`, but using `syncronized` the the class `Counter`. The problem of two or more threads concurrency goes away, because when a thread gets the method to execute, the others will be prevented to execute while the first thread does not finish.

### How to Coordenate Operations - `ReentrantLock` class (Explicit Look)
`ReentrantLock` class  has two methods to perform synchronization. They are `lock()` and `unlock()`

```java
private ReentrantLook lock = new ReentrantLook();

public synchronized void increment() {
	lock.lock();
	try {
		count++;
	} finally {
		lock.unlock();
	}
}
```

#### ReentrantLock's `tryLock()` methods
TODO

### How to Coordenate Operations - Atomic Classes
These Atomic classes are wrappers that hold some kind of type variable, such as String, boolean etc. It makes sure that any update on it happens atomically in the transactional sense

```java
private AtomicInteger count = new AtomicInteger(0);

public synchronized void increment() {
	count.incrementAndGet();
}
```
### How to Coordenate Operations - `CountDownLatch` class


## Thread.join()

## References
[Advanced Java Development](https://learning.oreilly.com/videos/learning-path-mastering/9781491970812/9781491970812-video256559)
