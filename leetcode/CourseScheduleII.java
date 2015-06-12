import java.util.*;

public class CourseScheduleII {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        HashMap<Integer, HashSet<Integer>> outedges = new HashMap<Integer, HashSet<Integer>>();
        HashMap<Integer, HashSet<Integer>> inedges = new HashMap<Integer, HashSet<Integer>>();
        
        for (int i = 0; i < prerequisites.length; i++) {
            int out = prerequisites[i][0];
            int in = prerequisites[i][1];
            if (in < 0 || in >= numCourses) return new int[0];
            if (out < 0 || out >= numCourses) continue;
            
            if (outedges.containsKey(in)) {
                outedges.get(in).add(out);
            } else {
                HashSet<Integer> edges = new HashSet<Integer>();
                edges.add(out);
                outedges.put(in, edges);
            }
            
            if (inedges.containsKey(out)) {
                inedges.get(out).add(in);
            } else {
                HashSet<Integer> edges = new HashSet<Integer>();
                edges.add(in);
                inedges.put(out, edges);
            }
        }
        
        LinkedList<Integer> removequeue = new LinkedList<Integer>();
        
        for (int n = 0; n < numCourses; n++) {
            if (!inedges.containsKey(n)) {
                removequeue.addLast(n);
            }
        }

        int[] res = new int[numCourses];
        int p = -1;
            
        while(!removequeue.isEmpty()) {
            int toremove = removequeue.pollFirst();

            if (outedges.containsKey(toremove)){
                for(int i : outedges.get(toremove)) {
                    HashSet<Integer> edgesSet = inedges.get(i);
                    edgesSet.remove(toremove);
                    if (edgesSet.isEmpty()) {
                        removequeue.addLast(i);
                        inedges.remove(i);
                    }
                }
                outedges.remove(toremove);
            }
            res[++p] = toremove;
        }

        if (outedges.isEmpty()) return res;
        return new int[0];
    }
}
