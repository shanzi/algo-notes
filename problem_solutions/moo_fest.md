# Moo Fest (POJ 3109)

A simple problem need using binary indexed tree. At first we sort the cows according to their volumes,
then we iterate over all cows and calculate the volume produced by current cow and added them into a 
binary indexed tree according to their position. As the cows is sorted by volumes so for each cow `i`,
previous cows added in the tree all have volumes less than or equal to its volume. So the volume of converse
should between `i` with these cows is `i`'s volume. We can use binary indexed tree to
fast query the number of cows on the left or right of `i` and apply different formular to calculate the
total distance according to their distances to the left most coordinate.

As the position space is large and we can not cost so much memory, we have to find all distinct `x`
coordinate and compress them. 

!CODEFILE "../poj/MooFest.java"
