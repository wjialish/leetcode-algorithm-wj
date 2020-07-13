import java.util.HashMap;
import java.util.Map;

/*
 * @lc app=leetcode.cn id=1 lang=java
 *
 * [1] 两数之和
 */

// @lc code=start
class Solution {
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        int[] res = new int[2];
        for(int i = 0; i<nums.length;i++){
            int another = target-nums[i];
            if(map.containsKey(another)){
                res[0] = map.get(another);
                res[1] = i;
            }
            map.put(nums[i], i);
            
        }

        return res;
    }


    // public int[] twoSum(int[] nums, int target) {
    //     HashMap<Integer, Integer> tracker = new HashMap<Integer, Integer>();
    //     int len = nums.length;
    //     for(int i = 0; i < len; i++){
    //         if(tracker.containsKey(nums[i])){
    //             int left = tracker.get(nums[i]);
    //             return new int[]{left, i};
    //         }else{
    //             tracker.put(target - nums[i], i);
    //         }
    //     }
    //     return new int[2];
    // }
}
// @lc code=end

