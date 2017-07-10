import java.io.*;
import java.util.*;

class Person implements Comparable<Person> {
    String name;
    int count = 0;
    Person(String name) {
        this.name = name;
        int mask = 0;
        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                mask |= (1 << c);
            }
        }
        while (mask != 0) {
            count ++;
            mask -= mask & -mask;
        }
    }

    @Override
    public int compareTo(Person that) {
        if (this.count == that.count) {
            return this.name.compareTo(that.name);
        } else {
            return that.count - this.count;
        }
    }
}

public class ContryLeader {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.valueOf(reader.readLine());
        for (int t = 0; t < T; t++) {
            int N = Integer.valueOf(reader.readLine());
            Person[] people = new Person[N];
            for (int i = 0; i < N; i++) {
                people[i] = new Person(reader.readLine());
            }
            Arrays.sort(people);
            System.out.printf("Case #%d: %s\n", t + 1, people[0].name);
        }
    }
}
