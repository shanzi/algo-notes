# Corn Fields (POJ 3254)

A state compressed dynamic programming problem. We can use a binary integer to represent the state
of a row in the fields and keep the count of ways under each state. Then for each row, we iterate
new state and count based on previous row's count and state. At first we added a mock row before the
first row with only the empty state has count 1 to simplify the implementing.

!CODEFILE "../poj/CornFields.java"
