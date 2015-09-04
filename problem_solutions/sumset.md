# Sumset (POJ 2549)

At first we have $$a + b + c = d \Rightarrow a + b = d - c$$. The order of $$a$$, $$b$$ and $$c$$ can be
arbitrary exchanged so we let $$a < b < c$$. Then the order of $$a, b, c, d$$ in increasing order may be
$$a < b < c < d$$, $$a < b < d < c$$, $$a < d < b < c$$, $$d < ak < b < c$$. For the first
two cases we iterate $$a + b$$'s values first and put it into `HashSet` for quick query and then iterate $$c$$
and $$d$$ to check if there is a combination that makes $$a + b + c = d$$. Conversely for the last two cases,
we iterate $$b + c$$'s values and uses $$a$$ and $$d$$ to find combinations.

!CODEFILE "../poj/Sumset.java"
