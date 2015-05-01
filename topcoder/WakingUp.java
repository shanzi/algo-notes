public class WakingUp {
    int gcd(int a, int b) {
        if ((a % b) > 0) {
            return gcd(b, a % b);
        }
        else return b;
    }

    int gcdn(int[] a) {
        if (a.length == 0) return 0;
        int g = a[0];
        for (int i = 1; i < a.length; i++) {
            g = gcd(g, a[i]);
        }
        return g;
    }

    long lcm(int[] a) {
        int g = gcdn(a);
        long l = g;
        System.out.printf("g: %d\n", g);
        for (int v : a) l *= v / g;
        return l;
    }

    public int maxSleepiness(int[] period, int[] start, int[] volume, int D) {
        long cycle = lcm(period);
        long sleepiness = 0;
        long lowest = 0;
        System.out.printf("Cycle: %d\n", cycle);
        for (int i = 0; i < cycle; i++) {
            int alarm = getAlarm(period, start, volume, i + 1);
            sleepiness += D - alarm;
            if (sleepiness < lowest) lowest = sleepiness;
        }
        System.out.println(sleepiness);
        if (sleepiness < 0) return -1;
        if (lowest > 0) return 0;
        return (int)(-lowest);
    }

    int getAlarm(int[] period, int[] start, int[] volume, int t) {
        int alarm = 0;
        int l = period.length;
        for (int i = 0; i < l; i++) {
            if (t % period[i] == start[i] ||
                    t % period[i] + period[i] == start[i]) {
                alarm += volume[i];
            }
        }
        return alarm;
    }

    public static void main(String[] args) {
        int[] period = {9, 2, 5, 5, 7};
        int[] start = {6, 1, 4, 1, 6};
        int[] volume = {71, 66, 7, 34, 6};

        WakingUp app = new WakingUp();

        System.out.println(app.maxSleepiness(period, start, volume, 50));
    }
}
