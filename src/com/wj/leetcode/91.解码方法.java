/*
 * @lc app=leetcode.cn id=91 lang=java
 *
 * [91] 解码方法
 */

// @lc code=start
class Solution {
    public int numDecodings(String s) {
        //以0开头的所有数无效，返回0
        if(s.charAt(0) == '0' || s.equals("0")){
             return 0;
        }
        
        char[] c = s.toCharArray();
        return decode(c,c.length -1);
    }

    public int decode(char[] c, int index){
         if(index <= 0){
             return 1;
         }

         int count = 0; 

         char cur = c[index];
         char pre = c[index-1];

         if(cur > '0'){
             /*
             对于中间有0的情况，可以直接不计算就好了，不用作为边界条件单独处理
             */
             count = decode(c, index-1);
         }

         if(pre == '1' || (pre == '2' && cur <= '6')){
             count += decode(c, index-2);
         }

         return count;
    }
}
// @lc code=end

