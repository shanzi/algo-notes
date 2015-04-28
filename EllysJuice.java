import java.util.*;

public class EllysJuice {
    public String[] getWinners(String[] players) {
        if (players.length <= 1) {
            return players;
        }

        HashMap<String, Integer>map = new HashMap<String, Integer>();

        for (String player : players) {
            if (map.containsKey(player)) {
                map.put(player, map.get(player) + 1);
            } else {
                map.put(player, 1);
            }
        }

        ArrayList<String> winner = new ArrayList<String>();

        for (String player : map.keySet()) {
            if (map.get(player) >= 2) winner.add(player);
        }

        String[] res = new String[winner.size()];
        res = winner.toArray(res);
        Arrays.sort(res);
        return res;
    }
}
