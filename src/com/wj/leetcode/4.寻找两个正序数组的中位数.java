/*
 * @lc app=leetcode.cn id=4 lang=java
 *
 * [4] 寻找两个正序数组的中位数
 */

// @lc code=start
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int m = nums1.length;
        int n = nums2.length;

        // if(m==0 && n==0){
        //     return 0.0f;
        // }

        // if(m==1 && n==0){
        //     return nums1[0];
        // }

        // if(m==0 && n==1){
        //     return nums2[0];
        // }

        int k = (m+n)/2;

        if((m+n)%2 == 1){
            return findKth(nums1,0, m-1,nums2, 0, n-1, k+1);
        }else{
            return (findKth(nums1, 0, m-1, nums2,0, n-1, k) + findKth(nums1, 0, m-1,nums2, 0, n-1, k+1))/2.0;
        }

    }

    public double findKth(int[] nums1,int m1, int h1,int[] nums2,int n1,int h2,int k){

        int m = h1-m1+1;
        int n = h2-n1+1;

        if(m>n){
            return findKth(nums2, n1, h2, nums1,m1, h1, k);
        }

        if(m == 0){
            return nums2[n1+k-1];
        }

       

        if(k==1){
            return Math.min(nums1[m1], nums2[n1]);
        }

        //分别选两个数组的中间数
        int na = Math.min(k/2,m);
        int nb = k - na;
        int va = nums1[m1+na-1];
        int vb = nums2[n1+nb-1];

        if(va==vb){
            return va;
        }else if(va < vb){
            return findKth(nums1, m1+na, h1, nums2, n1, n1+nb-1, k-na);
        }else{
            return findKth(nums1, m1, m1+na-1, nums2, n1+nb, h2, k-nb);
        }
    }
}
// @lc code=end

