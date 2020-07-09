


# Java - JVM Class Loading and Reflection

how to create a jar file: `jar cvf helper.jar com.example.Helper.class`

## Classloading - Java 8

- Core classes: They are loaded as part of the standard java

- Extension classes: They do not necessarily ship along with the Runtime, but may be used as they were runtime classes.  

- Delegation: It is how class loading works in Java. There is a hierarchy of class loaders.

- Displaying the delegation: Provides information about the class loaders such as their names, where they are trying to load their classes from.
