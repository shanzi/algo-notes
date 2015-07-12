# Sqrt(x)

Implement integer square root function. There are two ways to solve this
problem. The simplier one is using binary search.

```java
public class Sqrt {
    public int mySqrt(int x) {
        if (x <= 0) return 0;
        if (x <= 3) return 1;
        
        long a = 1;
        long b = x >> 1;
        long mid;
        
        while (a <= b) {
            mid = a + ((b - a) >> 1);
            if (mid * mid > x) {
                b = mid - 1;
            } else if (mid * mid < x) {
                a = mid + 1;
            } else {
                return (int)mid;
            }
        }
        
        return (int)b;
    }
}
```

A more advanced way is [Newton's method](useful_knowledge/newtons_method.md).
To apply Newton's method we have to take notice of:

1. Newton's method is possible to converge to a negative root. On LeetCode, a positive root is required.
2. Intermediate result may exceed `int`'s range
3. We take a max difference of `1` as end condition. Require $$x_{k+1} == x_k$$ may lead to infinite looping.

```java
public class Sqrt {
    public int mySqrt(int x) {
        if (x <= 0) return 0;
        if (x <= 3) return 1;
        
        long x0, x1 = x << 1;
        
        do {
            x0 = x1;
            x1 = (x0 * x0 + x) / (2 * x0);
        } while (Math.abs(x0 - x1) > 1);
        
        if (x1 * x1 > x) return (int)Math.abs(x1) - 1;
        else return (int)Math.abs(x1);
    }
}
```

Yet another way is to use bit manipulation. We start from the highest bit position and gradually test
if a bit can be `1` to the lowest bit position. As for a 32bit number, the highest `1` in binary
of its square root is at most at the 16 bit position, so we start from there.

```java
public class Sqrt {
    public int mySqrt(int x) {
        if (x <= 0) return 0;
        
        long X = x;
        long res = 0;
        long marker = (1 << 16);
        
        
        while (marker > 0) {
            long tempr = res | marker;
            while (tempr * tempr > X) {
                marker >>= 1;
                tempr = res | marker;
            }
            res |= marker;
            marker >>= 1;
        }
        
        return (int)res;
    }
}
```
