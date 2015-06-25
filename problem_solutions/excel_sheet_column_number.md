# Excel Sheet Column Number/Title

There are two kind of related problem. One is given the column title and asked for corresponding number,
the other is given an integer and ask for the column title.

The first is simple:

```java
public class ExcelSheetColumnNumber {
    public int titleToNumber(String s) {
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            res = (res * 26) + (s.charAt(i) - 'A' + 1);
        }
        return res;
    }
}
```

To get the title is a little complex. When you performing `MOD` operation and get a zero,
you have to pick out a `26` to get `Z` and deduct `26` from `n`. We are using a `StringBuilder`
to build the string reversely and reverse the string before return.

```java
public class ExcelSheetColumnTitle {
    public String convertToTitle(int n) {
        StringBuilder sbd = new StringBuilder();
        while (n > 0) {
            int c = n % 26;
            if (c == 0) {
                c = 26;
                n -= 26;
            }
            sbd.append((char)('A' + c - 1));
            n /= 26;
        }
        return sbd.reverse().toString();
    }
}
```
