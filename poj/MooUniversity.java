import java.io.*;
import java.util.*;

class Cow {
    long score;
    long faid;

    public Cow(long s, long f) {
        score = s;
        faid = f;
    }
}

public class MooUniversity {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int N = in.nextInt(), C = in.nextInt();
            long F = in.nextInt();

            Cow[] cows = new Cow[C];
            for (int i = 0; i < C; i++) {
                cows[i] = new Cow(in.nextInt(), in.nextInt());
            }

            Arrays.sort(cows, new Comparator<Cow>() {
                public int compare(Cow a, Cow b) {
                    if (a.score - b.score < 0) return -1;
                    else if (a.score - b.score > 0) return 1;
                    else return 0;
                }
            });

            int K = N / 2;
            long[] leftF = new long[C - K * 2];

            PriorityQueue<Long> pq = new PriorityQueue<Long>(K + 1, new Comparator<Long>() {
                public int compare(Long a, Long b) {
                    if (b - a < 0) return -1;
                    else if (b - a > 0) return 1;
                    else return 0;
                }
            });
            
            for (int i = 0; i < K; i++) {
                pq.offer(cows[i].faid);
                leftF[0] += cows[i].faid;
            }

            for (int i = 1; i < leftF.length; i++) {
                pq.offer(cows[i + K - 1].faid);
                leftF[i] = leftF[i - 1] + cows[i + K - 1].faid - pq.poll();
            }

            pq.clear();

            long rightSum = 0;
            for (int i = C - 1; i >= C - K; i--) {
                pq.offer(cows[i].faid);
                rightSum += cows[i].faid;
            }

            long median = -1;

            for (int i = leftF.length - 1; i >= 0; i--) {
                if (rightSum + leftF[i] + cows[K + i].faid <= F) {
                    median = cows[K + i].score;
                    break;
                }

                pq.offer(cows[K + i].faid);
                rightSum += cows[K + i].faid - pq.poll();
            }

            System.out.println(median);
        }
    }
}
