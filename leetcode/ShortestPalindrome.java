/*
 * Using KMP string search algorithm.
 *
 * At first we make the next transit table for KMP algorithm from the first half
 * of string. Then we search for the longest prefix reversely in the s using KMP algorithm.
 *
 * Let's say current position is i, to the next matching length is k. That means k characters
 * after i is the same as the reverse of the first characters. When i meet k or i moves over
 * k, we know that there is a palindrome prefix of s has been found. The first palindrome prefix
 * found will be the longest one, so we can build the final string based on this.
 */
public class ShortestPalindrome {
    private int[] getNext(char[] str, int len) {
        // This is a classic implementation of KMP alogrithm's transit table generation method.
        // Just KEEP THE CODE IN YOUR MIND
        len = Math.min(str.length, len);
        int[] next = new int[len];

        next[0] = -1;
        int i = 0;
        int k = -1;

        while (i < len - 1) {
            if (k < 0 || str[k] == str[i]) {
                next[++i] = ++k;
            } else {
                k = next[k];
            }
        }
        return next;
    }

    public String shortestPalindrome(String s) {
        if (s.length() < 2) return s;

        char[] str = s.toCharArray();
        int[] next = getNext(str, str.length / 2 + 1);

        int i = str.length - 1;
        int k = 0;

        while (i > k) {
            if (k < 0 || str[i] == str[k]) {
                k++;
                i--;
            } else {
                k = next[k];
            }
        }

        int l = 0;

        if (i == k) {
            l = i * 2 + 1;
        } else {
            l = k * 2;
        }

        StringBuilder sbd = new StringBuilder();
        sbd.append(s.substring(l));
        sbd.reverse();
        sbd.append(s);
        return sbd.toString();
    }
}
