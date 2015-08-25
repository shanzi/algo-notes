# Garland (POJ 1759)

This is an insteresting problem. Given a iteration formular that defines the heights of
serveral points and the first point's position, you are asked to give the lowest possible height
of last point that let none of the points falls below zero height.

The formular looks like:
$$
H_i = (H_{i - 1} + H_{i + 1}) / 2 - 1
$$

The key to this problem is that when using binary guess, we do not guess value of the last point,
instead, we guess on the height of second point $$H_2$$ as if the first two points' heights have been decided
all heights of following points including the last are decided. It is a good solution because it is not
convenient to calculate the heights of points between with the first and last points but after transform the
formular above, we can get:

$$
H_{i + 1} = 2(H_i + 1) - H_{i - 1} \Rightarrow H_i = 2(H_{i - 1} + 1) - H_{i - 2}
$$

Note to pass all tests, we have to set the search range correctly. At first the second light may be at a higher
position than the first one, we must give a high enough upper bound. Also, consider into the float number's caculate
precision problem, we should allow a guess that is a little below zero as the height of second point, so we set `-1`
as lower bound.

!CODEFILE "../poj/Garland.java"
