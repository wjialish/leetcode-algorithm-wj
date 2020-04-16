package com.wj.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LastStoneWeight {

	
	public static void main(String[] args) {
		int[] stones=new int[] {2,7,4,1,8,1};
		System.out.println(lastStoneWeight(stones));
		
	}
	
	/*
	 * We have a collection of rocks, each rock has a positive integer weight.

Each turn, we choose the two heaviest rocks and smash them together.  Suppose the stones have weights x and y with x <= y.  The result of this smash is:

    If x == y, both stones are totally destroyed;
    If x != y, the stone of weight x is totally destroyed, and the stone of weight y has new weight y-x.

At the end, there is at most 1 stone left.  Return the weight of this stone (or 0 if there are no stones left.)

 

Example 1:

Input: [2,7,4,1,8,1]
Output: 1
Explanation: 
We combine 7 and 8 to get 1 so the array converts to [2,4,1,1,1] then,
we combine 2 and 4 to get 2 so the array converts to [2,1,1,1] then,
we combine 2 and 1 to get 1 so the array converts to [1,1,1] then,
we combine 1 and 1 to get 0 so the array converts to [1] then that's the value of last stone.

 

Note:

    1 <= stones.length <= 30
    1 <= stones[i] <= 1000

	 */
	
	
    public static int lastStoneWeight(int[] stones) {
        int res=0;
        List<Integer> list=new ArrayList<>();
        //String[] s= {"a"};
        //list=Stream.of(s).collect(Collectors.toList());
        for(int i=0;i<stones.length;i++) {
        	list.add(stones[i]);
        }
        //Arrays.sort(stones);
        while(list.size()>1) {
        	Collections.sort(list);
            int diff=list.get(list.size()-1)-list.get(list.size()-2);
            list.remove(list.get(list.size()-1));
            list.remove(list.get(list.size()-1));
            if(diff!=0) {
            	list.add(diff);
            }
        }
        if(list.size()==1) {
        	return list.get(0);
        }else {
        	return 0;
        }
        
    }
}
