# Course Schedule

Given a set of courses and prerequisites of each courses, you are asked to decide
if you can finish all these courses in [Course Schdule](https://leetcode.com/problems/course-schedule/)
and return a valid order of finish all courses in [Course Schedule II](https://leetcode.com/problems/course-schedule-ii/).

You can finish the course if and only if there is no cycle in the prerequisites relationships.
In other words, if we regard the prerequisites as directed edges between courses and the courses a graph nodes,
then the graph built should be a [directed acyclic graph](https://en.wikipedia.org/wiki/Directed_acyclic_graph).

A general method to solve this type of problems is using [Topological sorting](https://en.wikipedia.org/wiki/Topological_sorting).
But as for the simpler decision problem of former problem in series ([Course Schdule](https://leetcode.com/problems/course-schedule/)),
we can use DFS method to find if there is a valid sequence. Note taht if we reverse each edge's direction,
the result of the decision problem won't be changed.

Below we give two type of solutions to the decision problem, the former uses DFS and the latter uses
Topological sorting.

```java
public class CourseSchedule {
    private boolean dfs(int root, int cur, int[] visited, HashMap<Integer, HashSet<Integer>> edges) {
        if (visited[cur] == 1) return true;
        if (visited[cur] > 1) return false;
        
        if (edges.containsKey(cur)) {
            visited[cur] = 2;
            for (int next: edges.get(cur)) {
                if (!dfs(root, next, visited, edges)) return false;
            }
        }
        visited[cur] = 1;
        return true;
    }
    
    
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        HashMap<Integer, HashSet<Integer>> edges = new HashMap<Integer, HashSet<Integer>>();
        for (int i = 0; i < prerequisites.length; i++) {
            int a = prerequisites[i][0];
            int b = prerequisites[i][1];
            
            if (edges.containsKey(a)) {
                edges.get(a).add(b);
            } else {
                HashSet<Integer> set = new HashSet<Integer>();
                set.add(b);
                edges.put(a, set);
            }
        }
        int[] visited = new int[numCourses];
        
        for (int i = 0; i < visited.length; i++) {
            if (visited[i] > 0) continue;
            if(!dfs(i, i, visited, edges)) return false;
        }
        return true;
    }
}

```

!CODEFILE "../leetcode/CourseSchedule.java"

As for the latter problem which a sequence of valid course schedule is asked to be returned,
We have to use Topological sorting. Although DFS is also possible to solve this problem,
the implementation may be quite complex.

!CODEFILE "../leetcode/CourseScheduleII.java"

