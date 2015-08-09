# Quad Tiling (POJ 3420)

This is a problem need applying fast matrix power technique. The fast matrix power calculation
is similar to fast integer power and with divide and conquer method we can finish it in $$O(\log N)$$ time.

Quad Tiling is a dynamic programming problem. At first we can give the transition relationship between the
state of current line with that of last line.

1. If last line is `====`
    * `____`
    * `==__`
    * `_==_`
    * `__==`
    * `====`
2. If last line is `==__`
    * `====`
    * `__==`
3. If last line is `__==`
    * `====`
    * `==__`
4. If last line is `_==_`
    * `=__=`
5. If last line is `=__=`
    * `_==_`
6. If last line is `___`
    * `====`

Note that every state won't transit to itself except `====`. Then we can write the transition matrix and
the result is value of first cell from top left of `n`th power of this matrix.

!CODEFILE "../poj/QuadTiling.java"

