import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
 * @lc app=leetcode.cn id=3 lang=java
 *
 * [3] 无重复字符的最长子串
 */

// @lc code=start
class Solution {
//     public int lengthOfLongestSubstring(String s) {

        
//         /**
//          * 方案一：
//          * 使用快慢指针策略，字符串最多会被遍历两边
//          * 快指针会被添加到哈希集合，慢指针遇到的字符会从哈希集合里删除
//          * 
//          * 987/987 cases passed (9 ms)
// Your runtime beats 52.23 % of java submissions
// Your memory usage beats 5.2 % of java submissions (40.7 MB)
//          */

//         char[] arr = s.toCharArray();

//         int max = 0;
//         Set<Character> set = new HashSet<>();
//         int i =0;
//         for(char c:arr){

//             while(set.contains(c)){
//                 set.remove(s.charAt(i));
//                 i++;
//             }

//             set.add(c);
//             max = Math.max(max, set.size());
            
//         }

//         return max;


//     }




    public int lengthOfLongestSubstring(String s) {

            
        /**
         * 方案二：优化方案一
         * 使用快慢指针策略，字符串最多会被遍历两边
         * 快指针会被添加到哈希集合，慢指针遇到的字符会从哈希集合里删除
         * 
         * 慢指针是不是可以一步跳到重复的字符，而不是一步一步地挪动
         * 
         * Your runtime beats 69.27 % of java submissions
Your memory usage beats 5.2 % of java submissions (39.8 MB)
         * 
         */

        int max = 0;

        //定义一个hash表，用来记录上次某个字符出现的位置
        Map<Character,Integer> map = new HashMap<Character,Integer>();
        
        //利用快慢指针扫描一遍字符串
        for(int i=0,j=0;j<s.length();j++){
            //如果发现快指针所对应的字符已经出现过，慢指针就进行跳跃
            if(map.containsKey(s.charAt(j))){
                i = Math.max(i,map.get(s.charAt(j))+1);
            }

            //把快指针所对应的字符添加到哈希表中
            map.put(s.charAt(j), j);

            //更新max
            max = Math.max(max,j-i+1);
        }

        return max;

    }

}
// @lc code=end

