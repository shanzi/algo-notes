public class FoxProgression {
    public int theCount(int[] seq) {
        if (seq.length <= 1) return -1;
        int delta = seq[1] - seq[0];
        boolean flag1 = true;
        int next1 = seq[1];

        for (int i = 1; i < seq.length; i++) {
            if (next1 != seq[i]) {
                flag1 = false;
                break;
            }
            next1 = seq[i] + delta;
        }

        int ratio = (seq[0] == 0) ? 1: seq[1] / seq[0];
        boolean flag2 = (ratio != 0);
        int next2 = seq[0] * ratio;

        for (int i = 1; i < seq.length; i++) {
            if (next2 != seq[i]) {
                flag2 = false;
                break;
            }
            next2 = seq[i] * ratio;
        }

        if (flag1 && flag2) {
            return (next1 == next2) ? 1 : 2;
        } else if (flag1 || flag2) {
            return 1;
        } else {
            return 0;
        }
    }
}
