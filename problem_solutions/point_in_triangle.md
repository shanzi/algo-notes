# Point in Triangle

Given a triangle by its vertices' coordinations $$A = (x_a, y_a)$$, $$B = (x_b, y_b)$$ and $$C = (x_c, y_c)$$.
You are asked that if any point $$P = (x_p, y_p)$$ is in the triangle.

There are serveral solutions to this problem. Most of them will make use of cross product operator of
two vector.

Assume we have two vectors $$\vec{a}$$ and $$\vec{b}$$, the cross product of them is:

$$
\vec{a}\times \vec{b} = |\vec{a}||\vec{b}|\sin \theta \vec{n}
$$

Where $$\sin \theta$$ is the angle between those two vectors and $$\vec{n}$$ is a vector in a third dimention
that is perpendicular to both $$\vec{a}$$ and $$\vec{b}$$. If we use Cartesian coordinate to prepresent the two
vectors as $$\vec{a} = (x_a, y_a)$$ and $$\vec{b} = (x_b, y_b)$$, we can get

$$
\vec{a}\times \vec{b} = x_a\cdot y_b - y_a\cdot x_a
$$

Based on this, we can give some solutions to this problem.

## Use areas of triangles

This is a straightforward solution. If $$P$$ is in $$\triangle ABC$$, then we must have the sum of areas of
$$\triangle ABP$$, $$\triangle BCP$$ and $$\triangle ACP$$ is the same of the area of $$\triangle ABC$$.
Otherwise the sum will be greater than that of $$\triangle ABC$$.

To calculate the area of a triangle $$\triangle ABC$$, we can use cross product as

$$
S_{\triangle ABC} = |\vec{AB}||\vec{AC}|\sin \theta / 2 = |(x_b - x_a)(y_c - y_a) - (y_b - y_a)(x_c - x_a)| / 2
$$

## Use vector space

We know if $$ABC$$ form a triangle, at most two points of the three are on a line. So we can uses $$\vec{AB}$$
and $$\vec{AC}$$ to form a vector space where $$P$$ can be represented as:

$$
\vec{AP} = u\vec{AB} + v\vec{AC}
$$

We caluclate the dot products of the equation above with $$\vec{AB}$$ and $$\vec{AC}$$ and get

$$
\begin{eqnarray*}
\vec{AP}\cdot\vec{AB} & = & u\vec{AB}\cdot\vec{AB}+v\vec{AC}\cdot\vec{AB}\\
\vec{AP}\cdot\vec{AC} & = & u\vec{AB}\cdot\vec{AC}+v\vec{AC}\cdot\vec{AC}
\end{eqnarray*}
$$

And from this we can induct:

$$
\begin{eqnarray*}
u & = & \frac{\vec{(AB}\cdot\vec{AB})\vec{(AP}\cdot\vec{AC})-\vec{(AC}\cdot\vec{AB})\vec{(AP}\cdot\vec{AB})}{\vec{(AC}\cdot\vec{AC})\vec{(AB}\cdot\vec{AB})-\vec{(AB}\cdot\vec{AC})\vec{(AB}\cdot\vec{AC})}\\
v & = & \frac{\vec{(AC}\cdot\vec{AC})\vec{(AP}\cdot\vec{AB})-\vec{(AC}\cdot\vec{AB})\vec{(AP}\cdot\vec{AC})}{\vec{(AC}\cdot\vec{AC})\vec{(AB}\cdot\vec{AB})-\vec{(AB}\cdot\vec{AC})\vec{(AB}\cdot\vec{AC})}
\end{eqnarray*}
$$

Then if $$u\in[0, 1]$$ and $$v\in[0, 1]$$ and $$u + v \le 1$$, $$P$$ is in $$\triangle ABC$$, otherwise it is out of it.

## Use vector relationships

The third and the best solution is making use of the relative position of $$P$$ and the all of three side of
the triangle. We know, if $$\vec{AP}$$ is at the clockwise side of $$\vec{AB}$$, the cross product of
$$\vec{AB}\times \vec{AP}$$ will be negative, or if it is at counterclockwise, the cross product is positive.

So we can uses cross product to decide if $$P$$ is at the same side of $$\vec{AB}$$, $$\vec{BC}$$ and $$\vec{CA}$$.
In other words, if we let:

$$
\begin{eqnarray*}
t_{1} & = & \vec{AB}\times\vec{AP}\\
t_{2} & = & \vec{BC}\times\vec{BP}\\
t_{3} & = & \vec{CA}\times\vec{CP}
\end{eqnarray*}
$$

We can decide if $$P$$ is in $$\triangle ABC$$ by checking if $$t_1, t_2, t_3$$ is both positive or negative.
If so, it is in the triangle. Especially, if one of $$t_i$$ is zero, then $$P$$ is on one side of $$\triangle ABC$$.

