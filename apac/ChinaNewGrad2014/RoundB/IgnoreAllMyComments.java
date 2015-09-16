import java.io.*;
import java.util.*;

public class IgnoreAllMyComments {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();
        int count = 0;
        String s = null;
        while ((s = in.readLine()) != null) {
            for (int i = 0; i < s.length(); i++) {
                if (i + 1 < s.length()) {
                    if (s.charAt(i) == '/' &&  s.charAt(i + 1) == '*') {
                        count++;
                        i++;
                        continue;
                    } else if (s.charAt(i) == '*' && s.charAt(i + 1) == '/') {
                        if (count > 0) {
                            count--;
                            i++;
                            continue;
                        }
                    }
                }

                if (count == 0) out.append(s.charAt(i));
            }
            if (count == 0) out.append('\n');
        }
        System.out.println("Case #1:");
        System.out.println(out.toString());
    }
}
