# Largest Number

Give an array of integer, it is asked to return the largest number that if we turn these numbers into
string, reorder them and concat them into a new string. It is a sort problem, there are two points we
need to take care of:

1. The order of string is not in alphabet order. Say 121 and 12, obviously 12121 is greater than 12112,
but if we are using default string compare function, we have `"12" < "121"`.
2. If the greatest number in the array is `0`, then we should just return `"0"` instead of something like `"0000"`.

The code:

```java
public class LargestNumber {
    public String largestNumber(int[] nums) {
        if (nums.length == 0) return "0";
        
        String[] strs = new String[nums.length];
        
        for (int i = 0; i < strs.length; i++) {
            strs[i] = String.valueOf(nums[i]);
        }
        
        Arrays.sort(strs, new Comparator<String>() {
            public int compare(String a, String b) {
                return (a + b).compareTo(b + a);
            }
        });
        
        for (int i = 0; i < strs.length / 2; i++) {
            String temp = strs[i];
            strs[i] = strs[strs.length - i - 1];
            strs[strs.length - i - 1] = temp;
        }
        
        if (strs[0].equals("0")) return "0";
        StringBuilder sbd = new StringBuilder();
        for (String s: strs) {
            sbd.append(s);
        }
        return sbd.toString();
    }
}
```
