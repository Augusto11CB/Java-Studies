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

## Test Doubles
> Double is a generic term for any case where you replace a production object for testing purposes
> \- Martin Fowler

### Types of Doubles
- Dummy
	- Not used in the test
	- Fills parameters
	- "Keeps the compiler happy"
- Fake 
	- It is a lightweight example of an object that the unit under test requires
	- Ex: In-memory database 
- Stub
	- provides "canned" answers (Canned responses are predetermined responses to common questions.)
- Spy
	- TODO
- Mock
	- configured with expectations
	- can fail the test if unecpected calls are made
	- the focus is on behavior verification 
	- verify interactions




