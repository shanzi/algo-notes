# Packets (POJ 1017)

A box packing problem, we have object of size $$1\times 1$$, $$2\times 2$$, $$3\times 3$$
$$4\times 4$$, $$5 \times 5$$ and $$6 \times 6$$. We can always use a box of size $$6\times 6$$
to pack these objects. We find that all objects will compitible to each other except $$2\times 2$$
and $$3\times 3$$. Two types of objects are compitible if they are either not able to be put into
a box at the same time, or if size of one objects is divisible by the other.

If the objects is all compitible, we can greedily put large objects into the box and divide the box's
free space to two parts and recursively put other objects. So we follow this idea and take
$$3\times 3$$ as a special case to handle and solve this problem.

!CODEFILE "../poj/Packets.java"
