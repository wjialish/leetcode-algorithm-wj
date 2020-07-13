import javax.swing.text.Position.Bias;

/*
 * @lc app=leetcode.cn id=33 lang=java
 *
 * [33] 搜索旋转排序数组
 */

// @lc code=start
class Solution {
    public int search(int[] nums, int target) {

        return binarySearch(nums, target,0, nums.length-1);

    }


    public int binarySearch(int[] nums, int target, int low,int high){
        if(low>high){
            return -1;
        }

        int mid = low + (high - low)/2;
        if(nums[mid] == target){
            return mid;
        }

        //判断左半边是不是排好序的
        //是的，则在左半边进行二分搜索
        if(nums[low]<=nums[mid]){
            //判断目标值是否在左半边
             if(nums[low]<=target && nums[mid]>=target){
                 return binarySearch(nums, target, low, mid-1);
             }else{
 
                return binarySearch(nums, target, mid+1, high);
             }
                 
             
             

        }else{//不是的话，在右半边进行二分搜索

            if(nums[mid]<=target && target<=nums[high]){
                return binarySearch(nums, target, mid+1, high);
            }else{
                return binarySearch(nums, target, low, mid-1);
            }

        }
    }
}
// @lc code=end

