import java.util.*;

public class CoinsGameEasy {
    static class BoardState {
        int aX;
        int aY;
        int bX;
        int bY;

        void setState(int aX, int aY, int bX, int bY) {
            if (aX < bX || (aX == bX && aY < bY)) {
                this.aX = aX;
                this.aY = aY;
                this.bX = bX;
                this.bY = bY;
            } else {
                this.aX = bX;
                this.aY = bY;
                this.bX = aX;
                this.bY = bY;
            }
        }

        public BoardState(int aX, int aY, int bX, int bY) {
            setState(aX, aY, bX, bY);
        }

        public boolean isFinished(String[] board) {
            if (isTerminated(board)) {
                int w = board.length;
                int h = board[0].length();
                return ((aX >= 0 && aX < w) && (aY >= 0 && aY < h)) || ((bX >= 0 && bX < w) && (bY >= 0 && bY < h));
            }
            return false;
        }

        public boolean isTerminated(String[] board) {
            int w = board.length;
            int h = board[0].length();

            return (aX < 0 || aX >= w) || (aY < 0 || aY >= h) || (bX < 0 || bX >= w) || (bY < 0 || bY >= h);
        }
        
        public boolean shouldEnd(String[] board) {
			return (isTerminated(board) || (aX == bX && aY == bY));
		}

        public boolean canMove(String[] board, int x, int y) {
            if (x < 0 || y < 0 || x >= board.length || y >= board[0].length()) return true;
            return board[x].charAt(y) != '#';
        }

        public BoardState move(String[] board, int direction) {
            int dx = 0;
            int dy = 0;
            switch (direction) {
                case 0:
                    dx = -1;
                    break;
                case 1:
                    dy = -1;
                    break;
                case 2:
                    dx = 1;
                    break;
                case 3:
                    dy = 1;
                    break;
            }

            int naX = aX + dx;
            int naY = aY + dy;
            int nbX = bX + dx;
            int nbY = bY + dy;

            if (!canMove(board, naX, naY)) {
                naX = aX;
                naY = aY;
            }

            if (!canMove(board, nbX, nbY)) {
                nbX = bX;
                nbY = bY;
            }

            return new BoardState(naX, naY, nbX, nbY);
        }
        
        public int hashCode() {
			int code = 0;
			code = code * 100 + aX;
			code = code * 100 + aY;
			code = code * 100 + bX;
			code = code * 100 + bY;
			return code;
		}
		
		public boolean equals(Object obj) {
			BoardState state = (BoardState) obj;
			return (aX == state.aX) && (aY == state.aY) && (bX == state.bX) && (bY == state.bY);
		}
    }

    BoardState initialState(String[] board) {
        int aX = 0, aY = 0, bX = 0, bY = 0;
        boolean first = true;

        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[0].length(); y++) {
                if (board[x].charAt(y) == 'o') {
                    if (first) {
                        aX = x;
                        aY = y;
                        first = false;
                    } else {
                        bX = x;
                        bY = y;
                        return new BoardState(aX, aY, bX, bY);
                    }
                }
            }
        }

        return null;
    }

    public int minimalSteps(String[] board) {
        BoardState initState = initialState(board);
        HashSet<BoardState> visitedStates = new HashSet<BoardState>();
        ArrayList<BoardState> queue = new ArrayList<BoardState>();
        queue.add(initState);
        int steps = 0;

        while (steps < 10 && !queue.isEmpty()) {
            ArrayList<BoardState> nqueue = new ArrayList<BoardState>();
            steps++;
            for (BoardState state : queue) {
                visitedStates.add(state);
                for (int i = 0; i < 4; i++) {
                    BoardState newState = state.move(board, i);
                    if (newState.isFinished(board)) {
                        return steps;
                    }

                    if (newState.shouldEnd(board) || visitedStates.contains(newState)) continue;
                    nqueue.add(newState);
                }
            }

            queue = nqueue;
        }

        return -1;
    }
}

