public class HappyNumber {
    public boolean isHappy(int n) {
        int sum = 0;
        int num = n;
        int digit = 0;
        while (true) {
            while (num > 0) {
                digit = num % 10;
                sum += digit * digit;
                num /= 10;
            }
            // from 1 -> 9 only 1 and 7 are happy numbers, and for any digits,
            // after infinite iteration it will product a sum in one digit.
            // So if this digit is happy, then it's happy. 
            //
            // In fact, 7 will finally turned into 1, so we can just check
            // if the sum is one. It is quite amazing!
            //
            // All positive numbers in 32 bit at most take 11 digits in this desimal representation.
            // So sum of those positive numbers' digits is at most 11*9^2 = 9911. We can only check these
            // 9911 numbers to prove that all their sequence will finally came into a one digit number,
            // and thus if it is a happy number is the same as that digit number.
            if (sum == 1) return true;
            else if (sum > 10) {
                num = sum;
                sum = 0;
            } else return false;
        }
    }
}
