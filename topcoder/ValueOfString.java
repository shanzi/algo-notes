public class ValueOfString {
    public int findValue(String s) {
        int[] timesCount = new int[26];
        for (int i = 0; i < s.length(); i++) {
            timesCount[s.charAt(i) - 'a']++;
        }

        int sum = timesCount[0] * timesCount[0]; // don't forget to square!

        for (int i = 1; i < 26; i++) {
            int times = timesCount[i];
            timesCount[i] += timesCount[i - 1];
            sum += (i + 1) * timesCount[i] * times;
        }

        return sum;
    }
}
