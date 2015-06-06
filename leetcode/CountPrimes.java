public class CountPrimes {
    // 1. removes all even numbers
    // 2. using sieve method
    // 3. for an i, i * (i - 1), i * (i - 2) ... has already been sieved by i - 1, i - 2 and so on.
    //    so for any I we can start sieving from i * i
    // 4. for an n, we only need to divide it from 2 to sqrt(n) to decide if it is a prime integer.
    //    also, we just only need to sieve from 3 to sqrt(n) to decide if each number from 1 to n 
    //    is prime.
    public int countPrimes(int n) {
        if (n < 3) return 0;
        
        boolean[] marks = new boolean[n / 2];
        for (int i = 3; i * i < n; i+= 2) {
            for (int j = i * i; j < n; j += i) {
                if (j % 2 == 1) marks[j / 2 - 1] = true;
            }
        }
        int count = 1;
        for (int i = 1; i * 2 + 1 < n; i++) {
            if (marks[i - 1]) continue;
            count++;
        }
        return count;
    }
}
