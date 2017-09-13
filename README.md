# Calculator

## Assignment
Educational application.

## Description
Application performs various operations under numbers.

## Input / Output
Basic I/O source - Console.
To read and/or writer data in/from file system you should pass a route to the file as an argument into the application.
Examples:
`-i=C:\Calculator\input.txt` : application will use this file as an input source.
`-o=C:\Calculator\output.txt` : application will use this file as an output source.

## Commands
* `quit`: Close application.
* `help`: Get help message.
* `eval`: Calculate expression.

## Operations and input format
* Addition : `add(sequence)`. Max number of input sequence - 20.
* Subtraction : `sub(sequence)`. Minuend - first element, subtrahends - second and other. Result is a difference between first element and each followed elements step by step. Max number of input sequence - 20.
* Multiplication : `mul(sequence)`. Max number of input sequence - 20.
* Division : `div(sequence)`. Dividend - first element, divisors - second and other. Result is quotient of first element and each followed elements step by step. Max number of input sequence - 20.
* Power : `pow(base exponent)`. Result is a base to the power of exponent.
* Logarithm : `log(base argument)`. Result is an exponent to which base must be raised to reach argument value.
All operations can be at any position inside another operation. The result of this operation will process as a number of the outside operation.

## Input examples
```java
help
```
```java
quit
```
```java
eval add(2 2)
```
```java
eval add(1 pow(2 8) sub(mul(5.5 2) log(10 1000)) div(5.5 5))
```