import java.util.*;
public class ChristmasTreeDecoration {
    private boolean tryRemoveEdge(HashMap<Integer, HashSet<Integer>> graph, int a, int b) {
        graph.get(a).remove(b);
        graph.get(b).remove(a);

        HashSet<Integer> visited = new HashSet<>();
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(a);
        while (!queue.isEmpty()) {
            int node = queue.pollFirst();
            visited.add(node);

            if (node == b) return true;

            for (int neighbour : graph.get(node)) {
                if (!visited.contains(neighbour)) {
                    queue.addLast(neighbour);
                }
            }
        }
        graph.get(a).add(b);
        graph.get(b).add(a);
        return false;
    }
    public int solve(int[] col, int[] x, int[] y) {
        HashMap<Integer, HashSet<Integer>> graph = new HashMap<>();
        for (int i = 0; i < x.length; i++) {
            int a = x[i];
            int b = y[i];
            if (graph.containsKey(a)) {
                graph.get(a).add(b);
            } else {
                HashSet<Integer> neighbours = new HashSet<>();
                neighbours.add(b);
                graph.put(a, neighbours);
            }
            if (graph.containsKey(b)) {
                graph.get(b).add(a);
            } else {
                HashSet<Integer> neighbours = new HashSet<>();
                neighbours.add(a);
                graph.put(b, neighbours);
            }
        }
        int count = 0;
        for (int i = 0; i < x.length; i++) {
            int a = x[i];
            int b = y[i];
            if (col[a - 1] == col[b - 1]) {
                if (!tryRemoveEdge(graph, a, b)) count++;
            }
        }
        return count;
    }
}
