# Mockito

**Definition Mocking**
Mocking allows you to focus on the unit you are trying to test by replacing the unit real dependencies with test-only collaborators. This allows you to reason about the unit in isolation without having to deal with the rest of the codebase at the same time.

**Why Mocking**
- Eliminates non-determinism and randomness
- Reduces complexity and Increases flexibility 
	- It is possible to mock communication with external services such as databases
	-  by replacing complex components using mock specific datas can be provided to the test 
- Improves test execution
	- increase speed by replacing dependencies that consumes a considerable amount of time to be loaded  

**What is a Unit**
> But it is a situational thing - the team decides what makes sense to be a unit for the purposes of their understanding of the system.
> \- Martin Fowler

**How does mockito match argumets?**
Mockito uses the `equals` method to match arguments

### **Compiler error inside when/then mockito methods because of void return methods - How to deal with?**
```java
public interface CustomService{
	void notify(Long id);
}
```
**Code with compilation error because of void method in the  mockito's when/then methods**
```java
//Test Class

private CustomService customService; //Mock

@Test
void testControllerWhenCustomServiceNotifyMethodException{
	final Long someId= 10L
	// Here we have a compilation error	
	when(customService.nofity(someId)).thenThrow(new RuntimeException()); 
}
```

**Solution - doThrow/when pattern **
```java
//Test Class

private CustomService customService; //Mock

@Test
void testControllerWhenCustomServiceNotifyMethodException{
	final Long someId= 10L
	// Here we have a compilation error	
	doThrow(new RuntimeException()).when(customService).nofity(someId); 
}
```

### Stubbing vs Verification
When a method is stubbed the unit under test asking for some data, and when you verify a method it is the unit under test telling a collaborator to do something.

- Stubbing gives you implicit verification
- Verification means you don't care about the return value, so it will not be stubbed

> Although it is possible to verify a stubbed invocation, usually **it's just redundant**
> \- [Mockito Docs](https://www.javadoc.io/doc/org.mockito/mockito-core/2.11.0/org/mockito/Mockito.html#stubbing)

**PS:** This true rules are trye most of the time

Explore: [Are you verifying stubs?](https://sergiuoltean.com/2017/11/09/mockito-when-and-verify/)

## Test Doubles
> Double is a generic term for any case where you replace a production object for testing purposes
> \- Martin Fowler

### Types of Doubles
- **Dummy**
	- Not used in the test
	- Fills parameters
	- "Keeps the compiler happy"
- **Fake** 
	- It is a lightweight example of an object that the unit under test requires
	- Ex: In-memory database 
- **Stub**
	- provides "canned" answers (Canned responses are predetermined responses to common questions.)
- **Spy** (Partial Mocks)
	- When creating a spy, Mockito creates a copy of the object, it does not delegate calls
	- When you call a method on a spy, the real method gets called unless this method is explicitly stubbed out.
	- It really useful in a situation with code that is difficult to test and is impossible, or too costly, to change the source code in order to simplify the test 
	- PS: When using Spy prefer use **doSomething/when** instead of **when/then**
- **Mock**
	- configured with expectations
	- can fail the test if unecpected calls are made
	- the focus is on behavior verification 
	- verify interactions


## Argument Matchers
- Useful when there is no interest in the values of arguments
- Or when a return value have to be defined for a range of arguments (or for an unknown argument).

**WARNING:** Once used argument matchers in a method stub, all arguments must be argument matchers.

**Fail use of Argument matchers**
```java
// It will fail when executed, when using argument matchers, all the informed arguments must be using argument matchers as well
when(customService.obtainId("FirstParameters", anyString(), 20).thenReturn(customObject)
```

**Successful Execution of Argument Matcher**
```java

when(customService.obtainId(eq("FirstParameters"), anyString(), eq(20)).thenReturn(customObject)
```

- anyString()
- eq()
- any()

## Argument Captor
An **argument caption** is just an special version of an **argument matcher**, which just captures argumets for futher inspection or assertion.

// TODO Get Example

## Iteractions Verification
### Verifying No More Interactions - `verifyNoMoreInteractions()`
When using this method in stubs, use the method wrapper `ignoreStubs(stub1, stub2,...)`.

```java
...
verifyNoMoreInteractions(ignoreStubs(stub)); //For stubs
verifyNoMoreInteractions(realObject); //For stubs
...
```

## Selective Partial Mocks
It is possible turn a mock into a spy by selectively enabling real methods

```java
SomeType mock = mock(SomeType.class)
when(mock.someMethod).thenCallRealMethod();
```

## Testing Code that Deals with Time
Context: If there is an code unit that handles something such as `LocalDate.now()`, test this unit would be hard, because it is impossible to set a default value for the return of `LocalDate.now()`, making it impossible to make assertions about the unit under test.

### Solution - Java Clock Class
- Pluggable abstraction of time which simplifies test

**Service Class that Deals with Time**
```java
public class AccountClosingService {
	private Clock clock;
	public AccountClosingService(Clock clock) {
		this.clock = this.clock
	}
	public AccountClosingResponse closeAccount(Account account) {
		Period accouldHolderAge = Period.between(account.getDob(), LocalDate.now(clock); // HERE! Passing the clock as the parameter of LocalDate.now()
	}
}
```

**Test Class**
```java
public class AccountClosingServiceTest {
	private	Instant fixedTime = LocalDate.of(2019,7,4)
	private Clock clock = Clock.fixed(fixedTime, ZoneId.systemDefault());
	
	private AccountClosingService underTest = new AccountClosingService(clock)
	
	@Test
	public void test(){
		final AccountClosingResponse response = ....;
		assertEquals(LocalDateTime.ofInstant(fixedTime, ZoneOffset.systemDefaul()), response.getProcessingDate());
	}
}
```

## BDDMockito
-   _given_  some preconditions (Arrange)
-   _when_  an action occurs (Act)
-   _then_  verify the output (Assert)

`import static org.mockito.BDDMockito.*;`

### Mockito --> BDDMockito

when/thenReturn --> given/willReturn
verify(object).someMethod --> then(object).should().someMethod();

**Mockito**
```java
when(phoneBookRepository.contains(momContactName))
  .thenReturn(false);
 
phoneBookService.register(momContactName, momPhoneNumber);
 
verify(phoneBookRepository)
  .insert(momContactName, momPhoneNumber);
```

**BDD Mockito**
```java
given(phoneBookRepository.contains(momContactName))
  .willReturn(false);
 
phoneBookService.register(momContactName, momPhoneNumber);
 
then(phoneBookRepository)
  .should()
  .insert(momContactName, momPhoneNumber);
```
