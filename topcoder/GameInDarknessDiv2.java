public class GameInDarknessDiv2 {
    int[] xdelta = { 1, -1, 0, 0};
    int[] ydelta = { 0, 0, 1, -1};

    private String concatMoves(String[] moves) {
        StringBuffer sbuf = new StringBuffer();
        for (String s : moves) {
            sbuf.append(s);
        }
        return sbuf.toString();
    }

    private int[] getPosition(String[] field, char player) {
        int[] res = new int[2];
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length(); j++) {
                if (field[i].charAt(j) == player) {
                    res[0] = i;
                    res[1] = j;
                    return res;
                }
            }
        }
        return res;
    }

    private int[][] getPath(int[] position, String moves) {
        int[][] path = new int[moves.length() + 1][2];
        path[0][0] = position[0];
        path[0][1] = position[1];
        for (int i = 1; i < path.length; i++) {
            char direction = moves.charAt(i - 1);
            switch (direction) {
                case 'U': 
                    path[i][0] = path[i - 1][0] - 1;
                    path[i][1] = path[i - 1][1];
                    break;
                case 'D': 
                    path[i][0] = path[i - 1][0] + 1;
                    path[i][1] = path[i - 1][1];
                    break;
                case 'L': 
                    path[i][0] = path[i - 1][0];
                    path[i][1] = path[i - 1][1] - 1;
                    break;
                case 'R': 
                    path[i][0] = path[i - 1][0];
                    path[i][1] = path[i - 1][1] + 1;
                    break;
            }
        }
        return path;
    }

    private boolean canBobWin(int x, int y, int s, String[] field, int[][] path, int[][][]cache) {
        if (s >= path.length) return true;
        if (x < 0 || x >= field.length || y < 0 || y >= field[0].length()) return false;
        if (cache[s][x][y] == 1) return false;

        if (path[s][0] == x && path[s][1] == y || field[x].charAt(y) == '#') {
            cache[s][x][y] = 1;
            return false;
        }

        if (s + 1 < path.length && path[s + 1][0] == x && path[s + 1][1] == y) {
            cache[s][x][y] = 1;
            return false;
        }

        for (int i = 0; i < 4; i++) {
            if (canBobWin(x + xdelta[i], y + ydelta[i], s + 1, field, path, cache))
                return true;
        }

        cache[s][x][y] = 1;
        return false;
    }

    public String check(String[] field, String[] moves) {
        String smoves = concatMoves(moves);
        int[] apos = getPosition(field, 'A');
        int[] bpos = getPosition(field, 'B');
        int[][] path = getPath(apos, smoves);
        int cache[][][] = new int[smoves.length() + 1][field.length][field[0].length()];
        boolean res = canBobWin(bpos[0], bpos[1], 0, field, path, cache);
        if (res) return "Bob wins";
        else return "Alice wins";
    }
}
