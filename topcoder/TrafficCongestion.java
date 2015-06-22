/* 
 * for treeHeight = 0 and 1, we can just use one car
 * for treeHeight is even, the root must have a car, and to sub tree of root is tree with odd
 * height, so both the root node of two subtree has already has a car visited. Then
 * the totol car need is 2 * (sub tree cars) + 1.
 *
 * On the other hand, if tree height is odd, two of this subtree is has even tree height.
 * Both the root of sub trees is visited by a single car, we can use only one car for a
 * left -> root -> right path. So the cars needed is 2 * (sub tree cars) - 1.
 *
 * We can also use the total city number to divide 3 to get the answer. After division,
 * if there is a remain number 1, we add 1 onto the result. But this solution does not
 * work on very large input as the division operation has not modulo rule like multiplication operation.
 * The result MOD 1000000007 won't be the correct answer.
 */

public class TrafficCongestion {
    final static int MOD = 1000000007;

    public int theMinCars(int treeHeight) {
        if (treeHeight < 2) return 1;
        long res = 1;
        for(int i = 2; i <= treeHeight; i++) {
            if (i % 2 == 0) res = ((res << 1) % MOD + 1) % MOD;
            else res = ((res << 1) % MOD + MOD - 1) % MOD;
        }
        return (int)res;
    }
}
