# Conway's Game of Life

[Conway's Game of Life](http://en.wikipedia.org/wiki/Conway's_Game_of_Life) is a famous
simulation game in the history of computer science. Generally speaking, the rules
of the game is quite simple, not to mention that in an interview you will usually meet
a simplier version of it. But as to perfectly give the answer, more consideration should
be put on it. The space and time analysis is also very important.

In fact, Conway's Game of Life usually appears in an interview as a measure of the knowledge
of the matrices' reprensentation. In different situation, different types of representation of
matrix or 2D array have differenet performance in time and space cost, which should be
well grasped.

Here we talk about a simplified version of Conway's Game of Life. The rules of game is:

1. Any live cells with fewer than **two** neighbours dies in the next generation
2. Any live or empty cell with more or equal to **two** neighbours become live cell in the next
generation.

We will disscuss this problem step by step.

## Stage 1: small board

If the board is small, we can iterate through every possible position of cell and count
the neighbours of a possition to decide if there should be a live cell there. At first
we can give a version of program that initiate a new board at the begining of every generation
and copy the result back to the original.

```java

class GameOfLife {
    void nextGeneration(int[][] board) {
        int m = board.length;
        int n = board[0].length;
        int[][] newboard = new int[m][n];
        for(int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int neighbours = countNeighbours(board, i, j);
                if (neighbours >= 2) newboard[i][j] = 1;
            }
        }
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                board[i][j] = newboard[i][j];
    }

    int countNeighbours(int[][] board, int i, int j) {
        int count = 0;
        int m = board.length;
        int n = board[0].length;
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                if (x == 0 && y == 0) continue;
                int px = i + x;
                int py = j + y;
                if (px >= 0 && px < m && py >= 0 && py < n) {
                    if (board[px][py] == 1) count++;
                }
            }
        }
        return count;
    }
```

The solution above is not fast, nor saving in the space cost. One way to speed up
the algorithm is to expand the for loop in `countNeighbours` into eight conditional expression,
but this won't help a a lot. This elementary version is not acceptable for an interview.

Both the time and space cost is $$O(N^2)$$, but it will actually spend a lot of time to allocation
memery for the `newboard` in every generation. And when the board is very large, it is not even feasible.

## Step 2: in place generation

We can come up an in place algorithm by modifying the cell by plus 2 to avoid the repeadly allocation.
Thus we can distinguish the original positions of live cell and the positions should be live cell next generation.
The program may looks like:

```java

class GameOfLife {
    void nextGeneration(int[][] board) {
        int m = board.length;
        int n = board[0].length;
        for(int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int neighbours = countNeighbours(board, i, j);
                if (neighbours >= 2) board[i][j] += 2;
            }
        }
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (board[i][j] >= 2) board[i][j] = 1;
                else board[i][j] = 0;
    }

    int countNeighbours(int[][] board, int i, int j) {
        int count = 0;
        int m = board.length;
        int n = board[0].length;
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                if (x == 0 && y == 0) continue;
                int px = i + x;
                int py = j + y;
                if (px >= 0 && px < m && py >= 0 && py < n) {
                    if (board[px][py] == 1 || board[px][py] == 3) count++;
                }
            }
        }
        return count;
    }
```

Note that we will count the neighbours when the value is `1` or `3`, which means there is a life
cell originally at that position. A position has value `2` means it originally empty but will
has a live cell the next generation. At last, both `2` and `3` means that position will has a
live cell, so at last we traverse around all positions and marks these positions as `1`.

This solution is enough for the first version of answer. But it should be better, especially when
the board is very large and even infinite.

For this solution especially, if the live cells are sparse on the board. We will have a way
to optimize under some programming languanges. These languanges has condition branch prediction
and when check the count of neighbours of a position, it will usually assume the check result to be `true`.
But on a sparse board, the check usually returns `false`. So change the code to be like below might
improves the performance on some machines with some languages:

```java
if (neighbours < 2) board[i][j] = 0;
else board[i][j] = 1;
```

## Step 3: large or infinite board

If we have very large or infinite board, traverse very position will be unacceptable. The program
above is also not good enough if the live cells on the board is very sparse. A better way is to
change the representation of board.

> Data structures decide everything!

Just remember the motto above. Many times the interviewers is not examining your algorithm,
they are examining your knowledge and understanding of data structure!

And **DO NOT** limited your mind in a specified languange and the data structures it provides.
For example, Java does not have anything like a `Pair<L, R>` in C++ or `tuple` in Python,
but you can always use a similar data structure as long as you can explain what it does.

In this example, we will use something like the `Pair` or `tuple` in Java. The data structure
may looks like:

```java
class Pair<L, R> {
    L left;
    R right;
    
    Pair(L l, R r) {
        left = l;
        right = r;
    }
    
    int hashCode() {
        return left.hashCode() * right.hashCode();
    }
    
    bool equals(Pair other) {
        return left == other.left && right == other.right;
    }
}
```

The `hashCode` and `equals` function make sure the class can be correctly handled by `HashSet` and `HashMap`.
You are not necessary to write these code down, just tell the interviewee that there exists a similar
data structure can act properly.

Other than check every position in this solution and find those with more than one live neighbours, we
iterate over current live cells and marks every cell it affects. Then we find all affects positions and
put live cells there.

An implementation for the situation of infinite board may be like:

```java
void nextGeneration(HashSet<Pair> liveCells) {
    HashMap<Pair, Integer> map = new HashMap<Pair, Integer>();
    for (Pair p : liveCells) {
        for (int i = -1; i <= 1; i++)
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue;
                Pair newp = new Pair(p.left + i, p.right + j);
                if (map.containsKey(newp)) {
                    map.put(newp, map.get(newp) + 1);
                } else {
                    map.put(newp, 1);
                }
            }
    }
    liveCells.clear();
    for (Pair p : map.keySet()) {
        if (map.get(p) >= 2) liveCells.add(p);
    }
}
```

The new solution is in fact more clear than the old one, neither to say it is faster and more space efficient
especially when the live cells are sparse on the board. The time cost depends on the implementation of `Set`
and `Map` is used. In our case, we assume the `HashSet` and `HashMap` can provide const time cost to insert
and find an element, then the total time cost for a single generation is $$O(N)$$ where $$N$$ is the number
of live cells on the board currently. The space cost is $$O(N)$$ too.

The last solution is usually the best one if you can using self-defined data structures. It is also
good to mention the first to solutions as references.
