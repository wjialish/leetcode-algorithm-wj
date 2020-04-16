package com.wj.leetcode;

import java.util.HashMap;
import java.util.Map;

public class NumPairsDivisibleBy60 {
	
	public static void main(String[] args) {
		int[] a=new int[] {30,20,150,100,40};
		System.out.println(numPairsDivisibleBy602(a));
	}
	
	/*
	 * In a list of songs, the i-th song has a duration of time[i] seconds. 

Return the number of pairs of songs for which their total duration in seconds is divisible by 60. 
 Formally, we want the number of indices i < j with (time[i] + time[j]) % 60 == 0.

 

Example 1:

Input: [30,20,150,100,40]
Output: 3
Explanation: Three pairs have a total duration divisible by 60:
(time[0] = 30, time[2] = 150): total duration 180
(time[1] = 20, time[3] = 100): total duration 120
(time[1] = 20, time[4] = 40): total duration 60

Example 2:

Input: [60,60,60,60,60,60]
Output: 3
Explanation: All three pairs have a total duration of 120, which is divisible by 60.
5+4+3+2+1=15
6*5/2=15

	 */
	
    public static int numPairsDivisibleBy602(int[] time) {
        
    	int total=0;
    	
    	Map<Integer, Integer> map=new HashMap<Integer, Integer>();
    	
    	for(int i:time) {
    		int divis=i%60;
    		int another=(60-divis)%60;
    		if(map.containsKey(another)) {
    			total+=map.get(another);
    		}
    		/*if(!map.containsKey(divis)) {
    			map.put(divis, 0);
    		}
    		map.put(divis, map.get(divis)+1);*/
    		map.put(divis, map.getOrDefault(divis, 0)+1);
    		
    	}
    	
    	return total;
    }
	
    public static int numPairsDivisibleBy60(int[] time) {
        
    	int total=0;
    	
    	for(int i=0;i<time.length-1;i++) {
    		for(int j=i+1;j<time.length;j++) {
    			if(((time[i]+time[j])%60)==0) {
        			total++;
        		}
    		}
    		
    	}
    	
    	return total;
    }
}
