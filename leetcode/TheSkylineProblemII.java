import java.util.*;

// Solve the skyline problem with priority queue 

public class TheSkylineProblemII {
    public List<int[]> getSkyline(int[][] buildings) {
        ArrayList<int[]> result = new ArrayList<int[]>();
        if (buildings.length == 0 || buildings[0].length == 0) return result;

        final int[][] innerBuildings = buildings;
        
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(
                buildings.length,
                new Comparator<Integer>() {
                    public int compare(Integer a, Integer b) {
                        return innerBuildings[a][1] - innerBuildings[b][1];
                    }
                });

        for (int i = 0; i < buildings.length; i++) {
            int l = buildings[i][0], r = buildings[i][1], h = buildings[i][2];
            
            while (!queue.isEmpty() && buildings[queue.peek()][1] < l) {
                int right = buildings[queue.poll()][1];
                int height = queue.isEmpty() ? 0 : buildings[queue.peek()][2];
                int[] newpoint = {right, height};
                result.add(newpoint);
            }
            
            if (queue.isEmpty() || buildings[queue.peek()][2] < h) {
                
                if (!result.isEmpty() && result.get(result.size() - 1)[0] == l) {
                    int[] last = result.get(result.size() - 1);
                    last[1] = Math.max(last[1], h);
                } else {
                    int[] newpoint = {l, h};
                    result.add(newpoint);
                }
            }

            Iterator<Integer> iter = queue.iterator();

            boolean flag = true;
            while (iter.hasNext()) {
                int index = iter.next();
                if (buildings[index][1] <= r && buildings[index][2] <= h) {
                    iter.remove();
                    continue;
                } 

                if (buildings[index][1] >= r && buildings[index][2] >= h) {
                    flag = false;
                }
            }

            if (flag) queue.offer(i);
        }
        
        while (!queue.isEmpty()) {
            int right = buildings[queue.poll()][1];
            int height = queue.isEmpty() ? 0 : buildings[queue.peek()][2];
            int[] newpoint = {right, height};
            result.add(newpoint);
        }
        
        return result;
    }
}


