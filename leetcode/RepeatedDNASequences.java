import java.util.*;
public class RepeatedDNASequences {
    public List<String> findRepeatedDnaSequences(String s) {
        HashSet<String> resSet = new HashSet<String>();
        HashSet<String> duplicateSet = new HashSet<String>();
        // Be careful about the boundary of loop. Here must be `<=`
        for (int i = 0; i <= s.length() - 10; i++) {
            String sub = s.substring(i, i + 10);
            if (duplicateSet.contains(sub)) resSet.add(sub);
            duplicateSet.add(sub);
        }
        ArrayList<String> res = new ArrayList<String>();
        res.addAll(resSet);
        return res;
    }
}
