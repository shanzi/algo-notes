public class Decipherability {
    public String check(String s, int k) {
        char[] str = s.toCharArray();
        if (k == str.length) return "Certain";
        
        for (int i = 0; i < str.length; i++) {
            for (int j = i + 1; j <= i + k && j < str.length; j++) {
                if (str[i] == str[j]) return "Uncertain";
            }
        }
        return "Certain";
    }
}
