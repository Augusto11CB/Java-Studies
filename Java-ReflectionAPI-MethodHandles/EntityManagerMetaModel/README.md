# Entity Manager MetaModel


### Project - Designing an EntityManager for Reading and Writing to a Database
The **EntityManager** interface models the writing and the reading of instances of **T** to any storage file or media, without knowing what is **T** at compile time.

## Functionality Goals
- Define what to save from the object
- How to read a bean without knowing its instance 
- How to save

**Execution script**
Give an instance of **T **
1. read the fields
2. check for the annotations
3. find the primary key
4. find the fields to read/write	
