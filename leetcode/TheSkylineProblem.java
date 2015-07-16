import java.util.*;

public class TheSkylineProblem {
    public List<int[]> getSkyline(int[][] buildings) {
        ArrayList<int[]> result = new ArrayList<int[]>();
        if (buildings.length == 0 || buildings[0].length == 0) return result;
        
        LinkedList<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < buildings.length; i++) {
            int l = buildings[i][0], r = buildings[i][1], h = buildings[i][2];
            
            while (!queue.isEmpty() && buildings[queue.getFirst()][1] < l) {
                int right = buildings[queue.pollFirst()][1];
                int height = queue.isEmpty() ? 0 : buildings[queue.getFirst()][2];
                int[] newpoint = {right, height};
                result.add(newpoint);
            }
            
            if (queue.isEmpty() || buildings[queue.getFirst()][2] < h) {
                
                if (!result.isEmpty() && result.get(result.size() - 1)[0] == l) {
                    int[] last = result.get(result.size() - 1);
                    last[1] = Math.max(last[1], h);
                } else {
                    int[] newpoint = {l, h};
                    result.add(newpoint);
                }
            }
            
            
            ListIterator<Integer> iter = queue.listIterator();
            while (iter.hasNext()) {
                int index = iter.next();
                if (buildings[index][1] <= r && buildings[index][2] <= h) {
                    iter.remove();
                }
            }
            
            if (queue.isEmpty()) queue.addLast(i);
            else {
                iter = queue.listIterator();
                while (iter.hasNext()) {
                    int index = iter.next();
                    if (buildings[index][1] >= r && buildings[index][2] >= h) break;
                    
                    if (buildings[index][2] < h) {
                        iter.set(i);
                        iter.add(index);
                        break;
                    }
                    
                    if (!iter.hasNext()) {
                        iter.add(i);
                        break;
                    }
                }
            }
        }
        
        while (!queue.isEmpty()) {
            int right = buildings[queue.pollFirst()][1];
            int height = queue.isEmpty() ? 0 : buildings[queue.getFirst()][2];
            int[] newpoint = {right, height};
            result.add(newpoint);
        }
        
        return result;
    }
}

