// this solution only works on small input
public class ReflectiveRectangle {
    static final long p = (long)(10E9 + 7);
    public int findSum(int sideA, int sideB, int bounces) {
        long sa = sideA;
        long sb = sideB;
        long b = bounces;
        if (b % 2 == 1) return 0;
        long A = (sa * sa % p + sb * sb % p) % p;
        long B = 1 + b * b / 2 % p + 3 * b / 2 % p;
        B += 2 * ((b * (b / 2 + 1) % p * (b + 1)) % p) / 3;
        B %= p;
        return (int)(A * B % p);
    }
}
