# Prime Path (POJ 3126)

For prime numbers between 1000 and 9999, you are asked how many step it will cost to transfrom
one to another one. During each step you can only change one place in the integer and no step
can produce a number that is not prime.

The solution to this problems is that we first find all prime numbers in required range and build
a bidirectional graph between them with each prime number as vertex. Then we perform basic graph algorithm
to find the shortest path between to given prime. Here we can use dijkstra algorithm.

!CODEFILE "../poj/PrimePath.java"
