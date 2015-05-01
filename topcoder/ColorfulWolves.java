import java.util.*;

public class ColorfulWolves {
    int getMin(String[] colormap, int color, boolean[]visited, int[] cache) {
        if (color == colormap.length - 1) return 0;
        if (cache[color] >= 0) return cache[color];
        
        String path = colormap[color];
        int skipped = 0;
        int min = 3000;
        visited[color] = true;
        for (int i = 0; i < path.length(); i++) {
            char c = path.charAt(i);
            if (c == 'N') {
                continue;
            }
            if (visited[i]) {
                skipped++;
                continue;
            }
            int submin = getMin(colormap, i, visited, cache);
            if (submin + skipped < min) min = submin + skipped;
            skipped++;
        }
        visited[color] = false;
        cache[color] = min;
        return min;
    }

    public int getmin(String[] colormap) {
        boolean[] visited = new boolean[colormap.length];
        int[] cache = new int[colormap.length];
        Arrays.fill(cache, -1);
        int res = getMin(colormap, 0, visited, cache);
        if (res >= 3000) return -1;
        else return res;
    }
}
