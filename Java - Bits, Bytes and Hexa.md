# Java - Bits, Bytes and Hexa

### Binary Numbers

[Interviewcake - Binary Numbers](https://www.interviewcake.com/concept/python/binary-numbers?)

### Bit Manipulation

[https://stackoverflow.com/questions/11380062/what-does-value-0xff-do-in-java](https://stackoverflow.com/questions/11380062/what-does-value-0xff-do-in-java) [https://coderanch.com/t/236675/java-programmer-SCJP/certification/xff](https://coderanch.com/t/236675/java-programmer-SCJP/certification/xff) [https://stackoverflow.com/questions/13422259/how-are-integers-internally-represented-at-a-bit-level-in-java/28478572#28478572](https://stackoverflow.com/questions/13422259/how-are-integers-internally-represented-at-a-bit-level-in-java/28478572#28478572)

***

[https://stackoverflow.com/questions/13422259/how-are-integers-internally-represented-at-a-bit-level-in-java](https://stackoverflow.com/questions/13422259/how-are-integers-internally-represented-at-a-bit-level-in-java)

***

[http://sys.cs.rice.edu/course/comp314/10/p2/javabits.html](http://sys.cs.rice.edu/course/comp314/10/p2/javabits.html) [https://www.topcoder.com/community/competitive-programming/tutorials/a-bit-of-fun-fun-with-bits/](https://www.topcoder.com/community/competitive-programming/tutorials/a-bit-of-fun-fun-with-bits/) [https://www.developer.com/java/data/using-the-java-bit-manipulation-operators.html](https://www.developer.com/java/data/using-the-java-bit-manipulation-operators.html) [https://www.tutorialspoint.com/Java-Bitwise-Operators](https://www.tutorialspoint.com/Java-Bitwise-Operators)

***

resolved exercies [https://www.hackerearth.com/pt-br/practice/basic-programming/bit-manipulation/basics-of-bit-manipulation/tutorial/](https://www.hackerearth.com/pt-br/practice/basic-programming/bit-manipulation/basics-of-bit-manipulation/tutorial/) [exercises :](https://leetcode.com/tag/bit-manipulation/)

### Byte Manipulation

[Operations on "byte" Data Type Values](http://www.herongyang.com/Java/Byte-Data-Type-Operations.html)

### Bit Shifting

#### Left Shifts

When shifting left, the most-significant bit is lost, and a 00 bit is inserted on the other end.

The left shift operator is usually written as "<<".

```
0010 << 1  →  0100
0010 << 2  →  1000
```

A single left shift multiplies a binary number by 2:

```
0010 << 1  →  0100

0010 is 2
0100 is 4
```

#### Logical Right Shifts

When shifting right with a **logical right shift**, the least-significant bit is lost and a 00 is inserted on the other end.

```
1011 >>> 1  →  0101
1011 >>> 3  →  0001
```

For positive numbers, a single logical right shift divides a number by 2, throwing out any remainders.

```
0101 >>> 1  →  0010

0101 is 5
0010 is 2
```

#### Arithmetic Right Shifts

When shifting right with an **arithmetic right shift**, the least-significant bit is lost and the most-significant bit is _copied_.

Languages handle arithmetic and logical right shifting in different ways. **Java provides two right shift operators: >> does an **_**arithmetic**_** right shift and >>> does a **_**logical**_** right shift.**

```
1011 >> 1  →  1101
1011 >> 3  →  1111

0011 >> 1  →  0001
0011 >> 2  →  0000
```

The first two numbers had a 11 as the most significant bit, so more 11's were inserted during the shift. The last two numbers had a 00 as the most significant bit, so the shift inserted more 00's.

If a number is encoded using [two's complement,](https://www.interviewcake.com/concept/binary-numbers#twos-complement) then an arithmetic right shift preserves the number's sign, while a **logical right shift makes the number positive**.

```
// Arithmetic shift
1011 >> 1  →  1101
    1011 is -5
    1101 is -3

// Logical shift
1111 >>> 1  →  0111
    1111 is -1
    0111 is  7
```
