import java.util.*;

public class TeamContestEasy {
    public int worstRank(int[] strength) {
        int ourteam = strength[0] + strength[1] + strength[2]
            - Math.min(strength[0], Math.min(strength[1], strength[2]));
        strength[0] = -1;
        strength[1] = -1;
        strength[2] = -1;
        Arrays.sort(strength);
        int count = 1;

        int left = strength.length - 1;
        int right = 3;
        while (right < left && left > strength.length / 3 * 2) {
            if (strength[left] + strength[right] > ourteam) {
                count++;
                left--;
                right++;
            } else {
                right++;
            }
        }
        return count;
    }
}
