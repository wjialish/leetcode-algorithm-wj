/*
 * @lc app=leetcode.cn id=9 lang=java
 *
 * [9] 回文数
 */

// @lc code=start
class Solution {
    //m1
    public boolean isPalindrome(int x) {

        // if(x < 0 || x!=0 && x%10==0)
		// return false;

      String s = String.valueOf(x);
      
      int left = 0;

      int right = s.length() - 1;
      while(left < right){
          if(s.charAt(left) != s.charAt(right)){
              return false;
          }
          left++;
          right--;
      }

      return true;
      
    }
}
// @lc code=end

