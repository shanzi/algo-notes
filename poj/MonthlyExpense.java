import java.io.*;
import java.util.*;

public class MonthlyExpense {
    private static boolean canDivide(int[] expense, int v, int M) {
        long sum = 0;
        for (int e : expense) {
            if (sum + e > v) {
                if (e > v) return false;
                if ((--M) <= 0) return false;
                sum = e;
            } else {
                sum += e;
            }
        }
        return true;
    }
    private static void solve(int[] expense, int M) {
        int l = 0, u = 1000000000;
        while (l <= u) {
            int mid = (l + u) / 2;
            if (canDivide(expense, mid, M)) u = mid - 1;
            else l = mid + 1;
        }
        System.out.println(l);
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt(), M = in.nextInt();
        int[] expense = new int[N];
        for (int i = 0; i < N; i++) {
            expense[i] = in.nextInt();
        }
        solve(expense, M);
    }
}
