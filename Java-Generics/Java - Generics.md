
## [Java Generics WildCard: <? extends Number> vs <T extends Number>](https://stackoverflow.com/questions/11497020/java-generics-wildcard-extends-number-vs-t-extends-number)
`T` is a bounded type, i.e. whatever type you use, you have to stick to that particular type which extends `Number`, e.g. if you pass a `Double` type to a list, you cannot pass it a `Short` type as `T` is of type `Double` and the list is already bounded by that type. In contrast, if you use `?` (`wildcard`), you can use "any" type that extends `Number` (add both `Short` and `Double` to that list).


## [Is there a generic class that accepts either of two types?](https://stackoverflow.com/questions/9141960/generic-class-that-accepts-either-of-two-types)
The answer is no. At least there is no way to do it using generic types. I would recommend a combination of generics and factory methods to do what you want.
```java
class MyGenericClass<T extends Number> {
  public static MyGenericClass<Long> newInstance(Long value) {
    return new MyGenericClass<Long>(value);
  }

  public static MyGenericClass<Integer> newInstance(Integer value) {
    return new MyGenericClass<Integer>(value);
  }

  // hide constructor so you have to use factory methods
  private MyGenericClass(T value) {
    // implement the constructor
  }
  // ... implement the class
  public void frob(T number) {
    // do something with T
  }
}
```

## [Java Generics Wildcarding With Multiple Classes](https://stackoverflow.com/questions/745756/java-generics-wildcarding-with-multiple-classes)

##  [Java nested generic type](https://stackoverflow.com/questions/22806202/java-nested-generic-type)

Fundamentally,  `List<List<?>>`  and  `List<? extends List<?>>`  have distinct type arguments.

It's actually the case that one is a subtype of the other, but first let's learn more about what they mean individually.

### Understanding semantic differences

Generally speaking, the wildcard  `?`  represents some "missing information". It means  _"there was a type argument here once, but we don't know what it is anymore"_. And because we don't know what it is, restrictions are imposed on how we can use anything that refers to that particular type argument.

For the moment, let's simplify the example by using  `List`  instead of  `Map`.

-   A  `List<List<?>>`  holds  _any kind of List with any type argument_. So i.e.:
    
    ```java
    List<List<?>> theAnyList = new ArrayList<List<?>>();
    
    // we can do this
    theAnyList.add( new ArrayList<String>() );
    theAnyList.add( new LinkedList<Integer>() );
    
    List<?> typeInfoLost = theAnyList.get(0);
    // but we are prevented from doing this
    typeInfoLost.add( new Integer(1) );
    ```
    
    We can put any  `List`  in  `theAnyList`, but by doing so we have lost knowledge of  _their elements_.
    
-   When we use  `? extends`, the  `List`  holds  _some specific subtype of List, but we don't know what it is anymore_. So i.e.:
    
    ```java
    List<? extends List<Float>> theNotSureList =
        new ArrayList<ArrayList<Float>>();
    
    // we can still use its elements
    // because we know they store Float
    List<Float> aFloatList = theNotSureList.get(0);
    aFloatList.add( new Float(1.0f) );
    
    // but we are prevented from doing this
    theNotSureList.add( new LinkedList<Float>() );
    ```
    
    It's no longer safe to add anything to the  `theNotSureList`, because we don't know the actual type of its elements. (_Was_  it originally a  `List<LinkedList<Float>>`? Or a  `List<Vector<Float>>`? We don't know.)
    
-   We can put these together and have a  `List<? extends List<?>>`. We don't know what type of  `List`  it has in it anymore, and we don't know the element type of  _those_  `List`s either. So i.e.:
    
    ```java
    List<? extends List<?>> theReallyNotSureList;
    
    // these are fine
    theReallyNotSureList = theAnyList;
    theReallyNotSureList = theNotSureList;
    
    // but we are prevented from doing this
    theReallyNotSureList.add( new Vector<Float>() );
    // as well as this
    theReallyNotSureList.get(0).add( "a String" );
    ```
    
    We've lost information  _both_  about  `theReallyNotSureList`,  _as well as_  the element type of the  `List`s inside it.
    
    (But you may note that we can  _assign_  any kind of  _List holding Lists_  to it...)
    

So to break it down:

```
//   ┌ applies to the "outer" List
//   ▼
List<? extends List<?>>
//                  ▲
//                  └ applies to the "inner" List
```

The  `Map`  works the same way, it just has more type parameters:

```
//  ┌ Map K argument
//  │  ┌ Map V argument
//  ▼  ▼
Map<?, ? extends List<?>>
//                    ▲
//                    └ List E argument
```

### Why  `? extends`  is necessary

You may know that  ["concrete"](http://www.angelikalanger.com/GenericsFAQ/FAQSections/ParameterizedTypes.html#FAQ101)  generic types have  _invariance_, that is,  [`List<Dog>`  is not a subtype of  `List<Animal>`](https://stackoverflow.com/questions/2745265/is-listdog-a-subclass-of-listanimal-why-arent-javas-generics-implicitly-p)  even if  `class Dog extends Animal`. Instead, the wildcard is how we have  _covariance_, that is,  `List<Dog>`  _is_  a subtype of  `List<? extends Animal>`.

```
// Dog is a subtype of Animal
class Animal {}
class Dog extends Animal {}

// List<Dog> is a subtype of List<? extends Animal>
List<? extends Animal> a = new ArrayList<Dog>();

// all parameterized Lists are subtypes of List<?>
List<?> b = a;
```

So applying these ideas to a nested  `List`:

-   `List<String>`  is a subtype of  `List<?>`  but  `List<List<String>>`  is  _not_  a subtype of  `List<List<?>>`. As shown before, this prevents us from compromising type safety by adding wrong elements to the  `List`.
-   `List<List<String>>`  _is_  a subtype of  `List<? extends List<?>>`, because the bounded wildcard allows covariance. That is,  `? extends`  allows the fact that  `List<String>`  is a subtype of  `List<?>`  to be considered.
-   `List<? extends List<?>>`  is in fact a shared supertype:
    
    ```
         List<? extends List<?>>
              ╱          ╲
    List<List<?>>    List<List<String>>
    ```
    

### In review

1.  `Map<Integer, List<String>>`  accepts  _only_  `List<String>`  as a value.
2.  `Map<?, List<?>>`  accepts  _any_  `List`  as a value.
3.  `Map<Integer, List<String>>`  and  `Map<?, List<?>>`  are distinct types which have separate semantics.
4.  One cannot be converted to the other, to prevent us from doing modifications in an unsafe way.
5.  `Map<?, ? extends List<?>>`  is a shared supertype which imposes safe restrictions:
    
    ```
            Map<?, ? extends List<?>>
                 ╱          ╲
    Map<?, List<?>>     Map<Integer, List<String>>
    ```
    

----------

### How the generic method works

By using a type parameter on the method, we can assert that  `List`  has some concrete type.

```
static <E> void test(Map<?, List<E>> m) {}
```

This particular declaration requires that  _all_  `List`s in the  `Map`  have the same element type. We don't know what that type actually  _is_, but we can use it in an abstract manner. This allows us to perform "blind" operations.

For example, this kind of declaration might be useful for some kind of accumulation:

```
static <E> List<E> test(Map<?, List<E>> m) {
    List<E> result = new ArrayList<E>();

    for(List<E> value : m.values()) {
        result.addAll(value);
    }

    return result;
}
```

We can't call  `put`  on  `m`  because we don't know what its  _key type_  is anymore. However, we can manipulate its  _values_  because we understand they are all  `List`  with the same  _element type_.

### Just for kicks

Another option which the question does not discuss is to have both a bounded wildcard and a generic type for the  `List`:

```
static <E> void test(Map<?, ? extends List<E>> m) {}
```

We would be able to call it with something like a  `Map<Integer, ArrayList<String>>`. This is the most permissive declaration, if we only cared about the type of  `E`.

We can also use bounds to nest type parameters:

```
static <K, E, L extends List<E>> void(Map<K, L> m) {
    for(K key : m.keySet()) {
        L list = m.get(key);
        for(E element : list) {
            // ...
        }
    }
}
```

This is both permissive about what we can pass to it, as well as permissive about how we can manipulate  `m`  and everything in it.


## References
[Generic Types (The Java™ Tutorials > Learning the Java Language > Generics (Updated))](https://docs.oracle.com/javase/tutorial/java/generics/types.html)
