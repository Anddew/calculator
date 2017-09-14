# Calculator

## Project objective
Educational application.

## Description
Application performs various operations with numbers.

## Input / Output
Basic I/O stream - Console.
To read and/or writer data in/from file system you should provide a path to the file as an argument.
Examples:
`-i=C:\Calculator\input.txt` : application will use this file as an input stream.
`-o=C:\Calculator\output.txt` : application will use this file as an output stream.

## Commands
* `quit`: closes application.
* `help`: prints out help message.
* `eval`: evaluates following expression.

## Operations and input format
* Addition : `add(operand_1, operand_2, ... , operand_n)`. Max number of operands - 20.
* Subtraction : `sub(operand_1, operand_2, ... , operand_n)`. Minuend - first, subtrahends - second and others. Result is a difference between first and each followed step by step. Max number of input operands - 20.
* Multiplication : `mul(operand_1, operand_2, ... , operand_n)`. Max number of operands - 20.
* Division : `div(operand_1, operand_2, ... , operand_n)`. Dividend - first, divisors - second and others. Result is quotient of first and each followed step by step. Max number of operands - 20.
* Power : `pow(base exponent)`. Result is a base in power of exponent.
* Logarithm : `log(base operand)`. Result is a logarithm to base 'base' of 'operand'.
Operations can be nested: result of an operation can be provided as an operand to other operation.

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