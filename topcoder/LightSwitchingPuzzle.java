public class LightSwitchingPuzzle {
    private void reverse(char[] s, int i) {
        i++;
        for (int j = i; j <= s.length; j+=i) {
            if (s[j - 1] == 'N') s[j - 1] = 'Y';
            else s[j - 1] = 'N';
        }
    }

    public int minFlips(String state) {
        char[] s = state.toCharArray();
        int count = 0;
        for (int i = 0; i < s.length; i++) {
            if (s[i] == 'Y') {
                reverse(s, i);
            	count++;
            }
        }
        return count;
    }
}

