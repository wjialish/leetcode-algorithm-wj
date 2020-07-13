/*
 * @lc app=leetcode.cn id=1370 lang=java
 *
 * [1370] 上升下降字符串
 */

// @lc code=start
class Solution {
    public String sortString(String s) {

        int[] count = new int[26];
        for(char c : s.toCharArray()){
            count[c-'a']++;
        } 

        StringBuilder sb = new StringBuilder();
        while(sb.length() != s.length()){
            for(int i =0;i<26;i++){
                if(count[i]>0){
                    sb.append((char)(i+'a'));
                    count[i]--;
                }
            }

            for(int j=25;j>=0;j--){
                if(count[j]>0){
                    sb.append((char)(j+'a'));
                    count[j]--;
                }
            }
        }

        return sb.toString();
    }
}
// @lc code=end

