import java.util.Arrays;

/*
 * @lc app=leetcode.cn id=435 lang=java
 *
 * [435] 无重叠区间
 */

// @lc code=start
class Solution {

    public int eraseOverlapIntervals(int[][] intervals){
        if(intervals.length==0) return 0;

        //将所有区间按照结束时间排序
        Arrays.sort(intervals,(o1,o2)->Integer.compare(o1[1], o2[1]));


        //end 记录当前的结束时间点
        //count 记录到目前为止没有重叠的区间
        int end = intervals[0][1];
        int count = 1;

    
        //从第二个区间开始：判断当前区间的起始时间和前一个区间的结束时间
        for(int i=1;i<intervals.length;i++){
        /**
         * 如果发现当前区间的起始区间和前一个区间的结束没有重叠，即
         * 即当前区间的起始时间大于上一个区间的结束时间
         * 则更新结束区间
         * 记数+1
         */

           if(intervals[i][0]>=end){
               count++;
               end = intervals[i][1];
           }

        }

        //最后，用总区间的个数减去没有重叠的区间个数，得到最少要删除的区间个数
        return intervals.length-count;

    }



    // public int eraseOverlapIntervals(int[][] intervals){
    //     if(intervals.length==0) return 0;

    //     //将所有区间按照起始时间排序
    //     Arrays.sort(intervals,(o1,o2)->Integer.compare(o1[0], o2[0]));


    //     //end 记录当前的最小结束时间点
    //     //count 记录到目前为止删除了多少区间
    //     int end = intervals[0][1];
    //     int count = 0;

    
    //     //从第二个区间开始：判断当前区间的结束区间和前一个区间的结束时间
    //     for(int i=1;i<intervals.length;i++){
    //     /**
    //      * 如果发现当前区间和前一个区间有重叠，即
    //      * 即当前区间的起始时间小于上一个区间的结束时间
    //      * 则end变量记录下两个结束时间的最小值
    //      * 意味着把结束时间晚的区间删除，记数+1
    //      */

    //        if(intervals[i][0]<end){
    //         end = Math.min(end, intervals[i][1]);
    //         count++;
    //        }else{
    //         //如果没有发生重叠，根据贪婪法: 更新end变量为当前区间的结束时间
    //         end=intervals[i][1];
    //        }

    //     }

    //     return count;

    // }





    // //Time Limit Exceeded
    // public int eraseOverlapIntervals(int[][] intervals) {

    //     //主体函数中，先将区间按照起始时间的先后顺序排序
    //     Arrays.sort(intervals, (o1,o2)-> Integer.compare(o1[0], o2[0]));

    //     //调用递归函数
    //     return eraseOverlapIntervals(-1,0,intervals);
    // }

    // private int eraseOverlapIntervals(int prev,int curr,int[][] intervals){

    //     //先检查是不是已经处理完所有区间,是的话表明不需要删除操作，直接返回
    //     if(curr == intervals.length){
    //         return 0;
    //     }

    //     //定义两个变量 tokern nottoken，用来记录
    //     //token 如果保留当前区间，最少需要删除多少其他区间
    //     //nottoken 如果删除当前区间，最小需要删除多少区间
    //     int token = Integer.MAX_VALUE;
    //     int nottoken=0;

    //     //只有当prev curr没有发生重叠的时候，才可以选择保留当前区间
    //     if(prev == -1 || intervals[prev][1] <= intervals[curr][0]){
    //         token = eraseOverlapIntervals(curr,curr+1,intervals);
    //     }


    //     //其他情况，可以考虑删除掉curr,看看删除后会不会产生最好的结果
    //     nottoken = eraseOverlapIntervals(prev,curr+1,intervals)+1;

    //     //返回两种情况的最小值：删好还是不删好
    //     return Math.min(token, nottoken);
         
    // }
}
// @lc code=end

