import java.io.*;
import java.util.*;

public class VirtualRabbit {
    private static int L = 24 * 60 * 60;
    private static int[] dayCount = new int[L];
    private static int timeToSeconds(String s) {
        String[] comps = s.split(":");
        int seconds = 0;
        seconds += Integer.parseInt(comps[0]) * 60 * 60;
        seconds += Integer.parseInt(comps[1]) * 60;
        seconds += Integer.parseInt(comps[2]);
        return seconds;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int t = 0; t < T; t++) {

            int G = timeToSeconds(in.next());
            int W = timeToSeconds(in.next());
            int H = timeToSeconds(in.next());
            int B = timeToSeconds(in.next());
            int X = timeToSeconds(in.next());
            long D = in.nextLong();

            Arrays.fill(dayCount, 0);
            dayCount[0] = 1;
            
            int curT = 0;
            int curD = 0;

            long res = 0;

            while (curD < D) {
                int nextT = curT + X;

                if (nextT >= L) {
                    if (curD + 1 == D) {
                        for (int i = 0; i < D; i++) res += dayCount[i];
                        break;
                    } else if (nextT >= L + G) {
                        curT -= L;
                        nextT -= L;
                        curD++;
                    }
                }

                if (nextT >= B) {
                    nextT = B - 1;
                } else if (nextT < H && nextT >= W) {
                    nextT = W - 1;
                }

                if (nextT < G || nextT <= curT) {
                    res = 0;
                    break;
                }
                 
                curT = nextT;
                dayCount[curD]++;
            }

            System.out.printf("Case #%d: %d\n", t + 1, res - 1);
        }
    }
}
