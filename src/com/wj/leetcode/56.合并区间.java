import java.util.Arrays;
import java.util.List;

/*
 * @lc app=leetcode.cn id=56 lang=java
 *
 * [56] 合并区间
 */

// @lc code=start
class Solution {
    public int[][] merge(int[][] intervals) {

        /**
         * 时间复杂度为O(nlogn)
         * 因为一开始要对数组进行排序
         * 
         * 空间复杂度O(n)
         * 因为我们用了一个result数组来保存结果
         */

        //intervals[0] 表示开始时间
        //intervals[1] 表示结束时间

        //将所有的区间按照起始时间的先后顺序排序
        Arrays.sort(intervals, (i1,i2)-> Integer.compare(i1[0], i2[0]));

        //定义一个previous变量，初始化为null
        int[] previous = null;

        //定义一个result变量，用来保存最终的区间结果
        List<int[]> result = new ArrayList<int[]>();

        //从头开始遍历给定的区间
        for(int[] current: intervals){
            //如果这是第一个区间，或者当前区间和前一个区间没有重叠，那么将当前区间加入到结果中
            if(previous == null || current[0] > previous[1]){
                result.add(previous=current);
            }else{
                previous[1] = Math.max(previous[1], current[1]);
            }
        }

        return result.toArray(new int[result.size()][]);

    }
}
// @lc code=end

