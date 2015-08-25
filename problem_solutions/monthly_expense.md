# Monthly Expense (POJ 3273)

We use binary search to guess a monthly expense value and verify if it is feasible with gready strategy.
As every expense in each day must be arranged to one of the months, we can start from the first day and
put as many as days into this month as possible as long as the total expense won't exceed our guessed value.
If at last we can divide the days into less than or equal to `M` months, then the value is feasible.

If the value is feasible, we should try decrease the value as to find the minimum one, or we increase the value.

!CODEFILE "../poj/MonthlyExpense.java"
