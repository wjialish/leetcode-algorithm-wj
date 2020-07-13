import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
 * @lc app=leetcode.cn id=350 lang=java
 *
 * [350] 两个数组的交集 II
 */

// @lc code=start
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {

        
        List<Integer> list = new ArrayList<Integer>();
        /*
        这里结果集合不能用set集合原因：无序，没有index，无法使用get()方法
        */
        //TreeSet<Integer> res = new TreeSet<Integer>();
        List<Integer> res = new ArrayList<Integer>();

        for(int a :nums1){
            list.add(a);
        }

        //Collections.sort(list);
        for(int b :nums2){
            if(list.contains(b)){
                res.add(b);
                Iterator<Integer> iterator = list.iterator();
                while(iterator.hasNext()){
                    int tmp = iterator.next();
                    if(tmp == b){
                        iterator.remove();
                        break;
                    }
                    
                }
               
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

