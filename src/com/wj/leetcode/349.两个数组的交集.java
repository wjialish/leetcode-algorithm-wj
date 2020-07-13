import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

/*
 * @lc app=leetcode.cn id=349 lang=java
 *
 * [349] 两个数组的交集
 */

// @lc code=start
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {

        /*
        这里不用List是因为结果集有重复元素，最后求交集，不需要重复元素
        */
        HashSet<Integer> set = new HashSet<Integer>();
        //List<Integer> set = new ArrayList<Integer>();
        /*
        这里结果集合不能用set集合原因：无序，没有index，无法使用get()方法
        */
        //TreeSet<Integer> res = new TreeSet<Integer>();
        List<Integer> res = new ArrayList<Integer>();

        for(int a :nums1){
            set.add(a);
        }

        //Collections.sort(list);
        for(int b :nums2){
            if(set.contains(b)){
                res.add(b);
                set.remove(b);
            }
        }

        int[] resArr = new int[res.size()];
        for(int i = 0; i< res.size();i++){
            resArr[i] = res.get(i);
        }
        // Integer[] resArr = res.toArray(new Integer[res.size()]);

        // int[] res_arr = Arrays.stream(resArr).mapToInt(Integer::valueOf).toArray();
        return resArr;
    }
}
// @lc code=end

