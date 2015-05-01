public class BlackWhiteMagic {
    public int count(String creatures) {
        int wc = 0;
        int bc = 0;
        int n = creatures.length();
        for (int i = 0; i < n; i++) {
            if (creatures.charAt(i) == 'w') {
                wc++;
            }
        }
        for (int i = 0; i < wc; i++) {
            if (creatures.charAt(i) == 'b') {
                bc++;
            }
        }
        return bc;
    }
}
