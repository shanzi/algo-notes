import java.util.*;

public class TheSkylineProblem {
    private void addResult(ArrayList<int[]> res, int x, int y) {
        int[] newres = {x, y};
        res.add(newres);
    }

    public List<int[]> getSkyline(int[][] buildings) {
        ArrayList<int[]> res = new ArrayList<int[]>();
        
        if (buildings.length < 1) return res;


        int left = buildings[0][0];
        int right = buildings[0][1];
        int height = buildings[0][2];
        
        Stack<Integer> rightStack = new Stack<Integer>(); // we should use a queue
        Stack<Integer> heightStack = new Stack<Integer>();
        Stack<Integer> tempRightStack = new Stack<Integer>();
        Stack<Integer> tempHeightStack = new Stack<Integer>();

        addResult(res, left, height);

        rightStack.push(right);
        heightStack.push(height);
        
        for (int i = 1; i < buildings.length; i++) {

            left = buildings[i][0];
            right = buildings[i][1];
            height = buildings[i][2];

            for (int j = 0; j < rightStack.size() - 1; j++) {
                if (rightStack.get(j) >= left) break;
                addResult(res, rightStack.get(j), heightStack.get(j + 1));
            }

            if (left > rightStack.peek()) {
                addResult(res, rightStack.peek(), 0);
            }

            while (!rightStack.isEmpty() && left <= rightStack.peek()) {
                tempRightStack.push(rightStack.pop());
                tempHeightStack.push(heightStack.pop());
            }

            rightStack.clear();
            heightStack.clear();

            while (!tempRightStack.isEmpty() && tempHeightStack.peek() >= height) {
                rightStack.push(tempRightStack.pop());
                heightStack.push(tempHeightStack.pop());
            }

            if (heightStack.isEmpty()) {
                addResult(res, left, height);
                rightStack.push(right);
                heightStack.push(height);
            } else if (rightStack.peek() < right) {
                while (!heightStack.isEmpty() && heightStack.peek() == height) {
                    rightStack.pop();
                    heightStack.pop();
                }
                rightStack.push(right);
                heightStack.push(height);
            }

            while (!tempRightStack.isEmpty()) {
                if (tempRightStack.peek() > right) {
                    rightStack.push(tempRightStack.pop());
                    heightStack.push(tempHeightStack.pop());
                } else {
                    tempRightStack.pop();
                    tempHeightStack.pop();
                }
            }
        }

        for (int j = 0; j < rightStack.size() - 1; j++) {
            addResult(res, rightStack.get(j), heightStack.get(j + 1));
        }

        addResult(res, rightStack.peek(), 0);

        return res;
    }
}

