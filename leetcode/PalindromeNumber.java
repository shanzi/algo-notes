public class PalindromeNumber {
    public static boolean isPalindrome(int x) {
        if (x < 0) return false;
        
        long l = 1; // must use long here
        long r = 10;
        while (x / l >= 10) l *= 10;
        
        while (l >= r) {
            long left = (x % (l * 10)) / l;
            long right = (x % r) * 10 / r;
            if (left != right) return false;
            
            l /= 10;
            r *= 10;
        }
        
        return true;
    }
}
