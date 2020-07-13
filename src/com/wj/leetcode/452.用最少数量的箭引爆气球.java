import java.util.Arrays;

/*
 * @lc app=leetcode.cn id=452 lang=java
 *
 * [452] 用最少数量的箭引爆气球
 */

// @lc code=start
class Solution {
    public int findMinArrowShots(int[][] points) {
        if(points.length==0) return 0;

        //按照结束点从小到大排序
        Arrays.sort(points,(o1,o2)->Integer.compare(o1[1], o2[1]));



        //count 目前为止需要的arrow的个数，即合并后的区间的个数
        int count=1;
        //end记录当前的结束时间点
        int end = points[0][1];


        //从第二个区间遍历所有的气球
        for(int i=1;i<points.length;i++){
            //如果气球的开始点大于前一个的结束点，则需要多一个arrow
            //更新当前最大的结束点为新的结束点
            if(points[i][0] > end){
                count++;
                end=points[i][1];
            }
        }

        return count;

    }
}
// @lc code=end

