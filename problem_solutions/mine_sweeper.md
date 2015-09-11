# Mine Sweeper

This problem is a game to simulate Mine Sweeper. To solve this problem we first count what number
each cell should hold given a configration of mines. Then we use flood fill methods to mark all cells
that holds a number that is zero or is adjoined with one zero. Every time we perform the flood filling
we count it as one click. At last we count cells that holds number which have not been marked.

!CODEFILE "../apac/2015/RoundC/Minesweeper.java"
