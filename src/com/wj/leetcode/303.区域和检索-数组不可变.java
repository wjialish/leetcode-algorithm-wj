/*
 * @lc app=leetcode.cn id=303 lang=java
 *
 * [303] 区域和检索 - 数组不可变
 */

// @lc code=start
class NumArray {

    private int[] sums;
    public NumArray(int[] nums) {

        for(int i=1;i<nums.length;i++){
            nums[i] += nums[i-1];
        }

        this.sums=nums;
    }
    
    public int sumRange(int i, int j) {

        return sums[j] - (i == 0 ? 0 : sums[i-1]);
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(i,j);
 */
// @lc code=end

