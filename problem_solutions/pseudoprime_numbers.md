# Pseudoprime Numbers (POJ 3641)

A number is pseudoprime numbers if and only if `power(a, p) % p == a` and `p` is prime.
So we apply quick power calculation with mod and using dividing to $$\sqrt{p}$$ method to test if $$p$$ is prime.

!CODEFILE "../poj/PseudoPrimeNumbers.java"
