# Googol String

Given the initial string $$s_0 = ""$$ and iteration rules. You are asked to given the value of a specific position
in $$s_{googol}$$ where $$googol = 10^{100}$$.

The iteration rule is $$s_{i + 1} = s_i + "0" + switch(reverse(s_i))$$. Here the reverse function reverse
the order of string in $$s_i$$ and switch function change "0" to "1" and "1" to "0".

Although the value of $$googol$$ is large, but there is no need to calculate such a big number. It is easy
to find the prefix of any $$s_i$$ won't change from previous string once it is fixed. The largest case you
are asked to give $$10^18$$th character in the string, so we only need to find an $$s_i$$ that is longer
than the query position. To get the character, we calculate it recursively. We can also notes that the length
of $$s_{i + 1}$$ is $$2\cdot l + 1$$ where $$l$$ is the length of $$s_{i}$$. Also we have
$$\lfloor\frac{l}{2}\rfloor = "0"$$ always holds.

This solution has $$O(\log N)$$ time complexity, so it is feasible even for large case.

!CODEFILE "../apac/2016/RoundA/GoogolString.java"
