public class PeriodicJumping {
	long sum(int[] jumpLengths) {
		long total = 0;
		for (int i : jumpLengths) total += i;
		return total;
	}
    public int minimalTime(int x, int[]jumpLengths) {
		x = Math.abs(x);
		long total = sum(jumpLengths);
		if (x % total == 0) return (int)(x / total);
		
        int steps = 0;
        long min = 0;
        long max = 0;
        while (steps < Integer.MAX_VALUE) {
            if (max >= x && min <= x) {
                return steps;
            }

            long stepLen = jumpLengths[steps % jumpLengths.length];
            
            long minl = min - stepLen;
            long minr = max - stepLen;
            if ((minl ^ minr) >= 0) {
				min = Math.min(Math.abs(minl), Math.abs(minr));
			} else {
				min = 0;
			}
			max += stepLen;
            steps++;
        }

        return -1;
    }
}
