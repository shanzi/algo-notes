# Mth Matrix (POJ 3658)

According to the statement, we can get that the items in a same column are aways increasing from top to bottom,
thus, we given a guessed value, we can easily count how many items in a column that is less than it.
So we can use binary search method to guess value and using lower bound binary search to count number in each column,
then we sum up the number and decide if the guessed value is exactly the `M`th.

!CODEFILE "../poj/MthMatrix.java"
