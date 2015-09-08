# Cut Tiles

This is a bin packing problems. As we can easily find, in this problem every larger tiles can perfectly contain
an integer number of smaller tiles, that hint we can apply greedy strategies to put as many as possible larger
tiles first and then put smaller tiles in areas remain. In this case we can divide the free area in two rectangle
area follow one edge of current used tiles and there is no arrangement that requires we cut smaller tiles across
the boundaries of the rectangles we split. We use recursive ways to greedily cut as many as tiles from the largest
material tile given and add a new material tile until we get all tiles we want.

!CODEFILE "../apac/2015/RoundA/CutTiles.java"
