public class SpecialStrings {
    public String findNext(String current) {
        char[] chars = current.toCharArray();

        int i = chars.length - 1;

        for (; i > 0; i--) {
            if (chars[i] == '0') {
                break;
            }
        }
        if (i == 0) return "";


        for (int j = chars.length - 1; j >= i; j--) {
            if (chars[j] == '0') {
                chars[j] = '1';
                for (int k = j + 1; k < chars.length; k++) {
                    chars[k] = chars[k - j - 1];
                }
                if (j == chars.length - 1) {
                    break;
                } else {
                    j = chars.length;
                }
            }
        }
        return String.valueOf(chars);
    }
}
