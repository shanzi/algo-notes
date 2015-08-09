# Rectangular Covering (POJ 2836)

We uses a binary integer to represent the covering state of all points. Then for each state
and each pairs of points, we try to add new covering rectangle and calculate new minimum total area. 
We start from the empty state and every iteration we added a new rectangle. We at most need to add `n - 1`
rectangles to get the minimum possible area.

Note that the minimum possible area must not have two intersecting rectangles as in this case we can always
find a way to cover all those points with non-intersecting rectangles and with lower area. So we need not
to calculate the intersecting area during each interation. Also we should handle two points with the same
`x` or `y` coordinates correctly. In these case, we always make rectangles with at least `1` unit width and height.
Ignore those same coordinates cases is not acceptable, just consider what if all points given are in a row.

To improve efficiency, we pre calculate the areas made by each pairs of points as will as retrieve the points
will be covered by that rectangle and save them to two matrix.

!CODEFILE "../poj/RectangularCovering.java"
