# Cow Exhibition (POJ 2148)

This problem is a little hard. To solve this problem we have let $$DP[i][s]$$ denotes the greatest possible
$$TF$$ with first $$i$$ cows and $$TS = s$$. We first ignore all $$TS < 0$$ but reserve $$TF < 0$$ in the values of
$$DP[i][s]$$. The iteration formular will be:

$$
DP[i + 1][s] = \max\left\{DP[i][s - S_i] + F_i\right\}
$$

Where $$S_i$$ and $$F_i$$ are respectively the smartness and funness of the $$i$$th cow. After optimize space cost
and iteration order, we have:

!CODEFILE "../poj/CowExhibition.java"
