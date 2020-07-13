/*
 * @lc app=leetcode.cn id=28 lang=java
 *
 * [28] 实现 strStr()
 */

// @lc code=start

class Solution {

    public static void main(String[] args) {
        String haystack = "hello";
        String needle = "ll";
        System.out.println(strStr(haystack, needle));
    }

    public static int strStr(String haystack, String needle) {

        int h = haystack.length();
        int n = needle.length();

        if(n == 0) return 0;
        if(h<n) return -1;

        int res = haystack.indexOf(needle);

        return res;

    }
}
// @lc code=end

