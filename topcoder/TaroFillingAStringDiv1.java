import java.util.*;
public class TaroFillingAStringDiv1 {
    static final int MOD = 1000000007;
    public int getNumber(int N, int[] position, String value) {
        HashMap<Integer, Character> map = new HashMap<Integer, Character>();
        for (int i = 0; i < position.length; i++) {
            int p = position[i];
            map.put(p, value.charAt(i));
        }
        Arrays.sort(position);
        long sum = 1;
        for (int i = 0; i < position.length - 1; i++) {
            char cha = map.get(position[i]);
            char chb = map.get(position[i + 1]);
            int d = position[i + 1] - position[i];
            if ((cha != chb && d % 2 == 1) || (cha == chb && d % 2 == 0)) {
                continue;
            } else {
                sum *= d;
                sum %= MOD;
            }
        }

        return (int)sum;
    }
}
