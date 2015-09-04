# Extended Lights Out (POJ 1222)

As for this kind of problem, at first we iterate every possible operation on the first line by
binary iterating. Then based on the result after the first line has changed, we start from the second line
and in this line we only consider to turn off all on lights in one previous line and just record but not care about
current line's result. We do this every line below and at last we check if the last line is all off.
If true, we got the way to turn of it all with minimum numbers of operations, or it is impossible to turn
all lights off.

!CODEFILE "../poj/ExtendedLightsOut.java"
