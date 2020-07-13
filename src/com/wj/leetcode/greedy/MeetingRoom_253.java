package com.wj.leetcode.greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MeetingRoom_253 {

    public int minMeetingRooms(Intervals[] intervals){
        
        if(intervals == null || intervals.length==0){
            return 0;
        }

        //将输入的一系列会议按照会议的起始时间排序.因为会议的执行本来就是按照时间顺序执行的
        Arrays.sort(intervals,new Comparator<Intervals>() {

            @Override
            public int compare(Intervals a, Intervals b) {
                // TODO Auto-generated method stub
                return a.start-b.start;
            }
            
        });


        //用一个最小堆来维护目前开辟的所有会议室，最小堆里的会议室按照会议的结束时间排序
        //意思就是每次从最小堆里提取的都是最先结束会议的会议室
        PriorityQueue<Intervals> heap = new PriorityQueue<Intervals>(intervals.length,new Comparator<Intervals>() {
        
            public int compare(Intervals a, Intervals b){
                return a.end- b.end;
            }

        });


        //让第一个会议在第一个会议室里举行
        //因为一开始是没有会议室的，我们需要全新的开辟一个
        heap.offer(intervals[0]);


        //从i第二个会议开始，每次都从最小堆里取出一个会议室,那么，这个会议室里的会议一定是最早结束的
        for(int i =1; i<intervals.length;i++){
            Intervals interval = heap.poll(); //当前的会议室
            //若当前要开的会议可以等会议室腾出来之后再开始，那么就可以重复利用这个会议室
            if(intervals[i].start >= interval.end){
                interval.end = intervals[i].end;
            }else{//否则，开辟一个新的会议室
                heap.offer(intervals[i]);
            }

            //把旧的会议室也放入到最小堆里
            heap.offer(interval);

            //最后，最小堆里会议室的个数就是我们要求的最少的会议室的个数
            return heap.size();


             
            
        }



    }


    public class Intervals{
        int start=0;
        int end=0;
    }
 
    
}