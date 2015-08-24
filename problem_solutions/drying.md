# Drying

Using binary search method to guess a drying value first and then we can use greedy strategy to verify
if the guessed value is feasible. If the `i`th cloth contains `A[i]` amount of water and the guest value
if `m`, then that cloth will be totally dry if `(m - x) + x * k >= A[i]` where `x` is the minues we spend using
radiator. Whis this formula we can find the minium value of `x` for this cloth. For each cloth we apply
this formular and add up all the minutes of radiator usage needed. If the minutes is less than `m`, then
the solution is valid, or it is not feasible.

If a guessed value is feasible, we should try decrease the guess to find a minimum value, or we increase it.

!CODEFILE "../poj/Drying.java"
