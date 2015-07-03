# Combination Sum

Combination sum is a series of problems on LeetCode:
[Combination Sum](https://leetcode.com/problems/combination-sum/),
[Combination Sum II](https://leetcode.com/problems/combination-sum-ii/) and
[Combination Sum III](https://leetcode.com/problems/combination-sum-iii/).

The first stage of Combination Sum is similar to coin combination problem.
The so-called coin combination problem is that given several par value of available
coins and a money value, ask how many ways to use these coins to reach exactly amount
of the money value. We have unlimited coins for each par value.
In this case, not only the count of ways, but also the coins used is required.

There are too ways to solve this problem. One is using DFS method try all combinations.
This is a brute-force solution but is good enough to this problem:

```java
public class CombinationSum {
    private void genCombination(int[] candidates, int target, int idx, List<Integer> subres, List<List<Integer>> res) {
        
        
        if (target == 0) {
            ArrayList<Integer>copy = new ArrayList<Integer>();
            copy.addAll(subres);
            res.add(copy);
            return;
        }

        // this condition must be after (target == 0). case: [1], 1
        if (idx >= candidates.length || target < 0) return;
        
        for (int i = idx; i < candidates.length; i++) {
            subres.add(candidates[i]);
            genCombination(candidates, target - candidates[i], i, subres, res);
            subres.remove(subres.size() - 1);
        }
    }
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        ArrayList<Integer> subres = new ArrayList<Integer>();
        ArrayList<List<Integer>> res = new ArrayList<List<Integer>>();
        genCombination(candidates, target, 0, subres, res);
        return res;
    }
}
```

Another way is using similar way to solve coin combination problem.
At first we use 0, 1, 2, 3, ... coins with the largest par value until the sum
exceed target. Then we recursively using the coins left to find ways to sum to
the target value left.

```java
public class CombinationSum {
    private List<List<Integer>> combinationSum(int[] candidates, int target, int fromIndex) {
        ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();
        if (fromIndex < 0) return result;
        
        int n = candidates[fromIndex];
        ArrayList<Integer> combination = new ArrayList<Integer>();
        for (int i = 0; i <= target; i++) {
            
            int sum = n * i;
            if (sum > target) break;
            else if (sum == target) {
                result.add(combination);
                break;
            } else {
                List<List<Integer>> subCombinations = combinationSum(candidates, target - sum, fromIndex - 1);
                for (List<Integer> comb : subCombinations) {
                    comb.addAll(combination);
                    result.add(comb);
                }
            }
            
            combination.add(n);
        }
        return result;
    }
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        return combinationSum(candidates, target, candidates.length - 1);
    }
}
```


Combination Sum II is one step further compared to the first one in a way that
number of each coins is limited. So each coin can only be used once. I can address this
by change the DFS solution just a little.

```java
public class Solution {
    private void genCombination(int[] candidates, int target, int idx, List<Integer> subres, List<List<Integer>> res) {
        
        if (target == 0) {
            ArrayList<Integer>copy = new ArrayList<Integer>();
            copy.addAll(subres);
            res.add(copy);
            return;
        }
        
        // this condition must be after (target == 0). case: [1], 1
        if (idx >= candidates.length || target < 0) return;
        
        for (int i = idx; i < candidates.length; i++) {
            if (i > idx && candidates[i - 1] == candidates[i]) continue;
            subres.add(candidates[i]);
            genCombination(candidates, target - candidates[i], i + 1, subres, res);
            subres.remove(subres.size() - 1);
        }
    }
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        ArrayList<Integer> subres = new ArrayList<Integer>();
        ArrayList<List<Integer>> res = new ArrayList<List<Integer>>();
        genCombination(candidates, target, 0, subres, res);
        return res;
    }
}
```

In Combination Sum III, the array contains values from 1 to 9, and the combinations must contains `k`
elements.  We can reuse DFS method like above and preserve only results of length `k`.
This solution is in fact enumerated all combinations of `k` elements from `1~9` and find
the combinations that conforms to the condition.

```java
public class Solution {
    private void generateCombine(int[] combines, int i, int k, int n, List<List<Integer>> res) {
        if (i == k) {
            int sum = 0;
            for (int c : combines) sum += c;
            if (sum == n) {
                ArrayList<Integer> newcomb = new ArrayList<Integer>(k);
                for (int c : combines) newcomb.add(c);
                res.add(newcomb);
            }
            return;
        }
        
        int last = (i > 0) ? combines[i - 1] : 0;
        for (int j = last + 1; j <= 9; j++) {
            combines[i] = j;
            generateCombine(combines, i + 1, k, n, res);
        }
    }
    
    public List<List<Integer>> combinationSum3(int k, int n) {
        int[] combines = new int[k];
        ArrayList<List<Integer>> res = new ArrayList<List<Integer>>();
        generateCombine(combines, 0, k, n, res);
        return res;
    }
}
```
