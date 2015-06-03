import java.util.*;

public class ContainsDuplicateIII{
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Integer> set = new TreeSet<Integer>();
        for(int i = 0; i < nums.length; i++) {
            if (i - k - 1 >= 0) set.remove(nums[i - k - 1]);
            Integer ceil = set.ceiling(nums[i]);
            if (ceil != null && ((long)ceil - nums[i]) <= t) return true;
            Integer floor = set.floor(nums[i]);
            if (floor != null && ((long)nums[i] - floor) <= t) return true;
            set.add(nums[i]);
        }
        return false;
    }
}
