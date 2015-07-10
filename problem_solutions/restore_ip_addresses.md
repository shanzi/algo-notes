# Restore IP Addresses

We just using a brute force method to get all possible 4 parts split of the string, then we valid if
the four parts is can represent an IP component. Note that a string is able to represent an IP component
if and only if:

1. It equals "0"
2. It represents a number between 1 and 255, inclusive. But it cannot starts with an "0". ("010" is not valid!)

```java
public class RestoreIPAddresses {
    
    private int ipComponent(String s) {
        if (s.length() > 1 && s.charAt(0) == '0') return -1; 
        
        int val = Integer.parseInt(s);
        if (val >=0 && val < 256) return val;
        else return -1;
    }
    
    public List<String> restoreIpAddresses(String s) {
        ArrayList<String> result = new ArrayList<String>();
        
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                for (int k = 1; k <= 3; k++) {
                    int l4 = s.length() - i - j - k;
                    if (l4 > 3 || l4 <= 0) continue;
                    
                    StringBuilder sbd = new StringBuilder();
                    
                    int l = 0, comp;
                    comp = ipComponent(s.substring(l, l + i));
                    if (comp < 0) continue;
                    sbd.append(comp);
                    sbd.append('.');
                    l += i;
                    
                    comp = ipComponent(s.substring(l, l + j));
                    if (comp < 0) continue;
                    sbd.append(comp);
                    sbd.append('.');
                    l += j;
                    
                    comp = ipComponent(s.substring(l, l + k));
                    if (comp < 0) continue;
                    sbd.append(comp);
                    sbd.append('.');
                    l += k;
                    
                    comp = ipComponent(s.substring(l));
                    if (comp < 0) continue;
                    sbd.append(comp);
                    
                    result.add(sbd.toString());
                }
            }
        }
        
        return result;
    }
}
```
