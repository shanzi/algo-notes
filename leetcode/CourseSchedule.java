import java.util.*;

public class CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        HashMap<Integer, HashSet<Integer>> outedges = new HashMap<Integer, HashSet<Integer>>();
        HashMap<Integer, HashSet<Integer>> inedges = new HashMap<Integer, HashSet<Integer>>();
        
        for (int i = 0; i < prerequisites.length; i++) {
            int in = prerequisites[i][0];
            int out = prerequisites[i][1];
            if (in < 0 || in >= numCourses) return false;
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
        
        for (int n : outedges.keySet()) {
            if (!inedges.containsKey(n)) {
                removequeue.addLast(n);
            }
        }
            
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
            }
            outedges.remove(toremove);
        }
        
        return outedges.isEmpty();
    }
}
