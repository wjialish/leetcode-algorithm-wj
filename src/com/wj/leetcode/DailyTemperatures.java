package com.wj.leetcode;

import java.util.Stack;

public class DailyTemperatures {

	
	/* 739. Daily Temperatures
	 * Given a list of daily temperatures T, return a list such that, for each day in the input, tells you how many days you would have to wait until a warmer temperature. If there is no future day for which this is possible, put 0 instead.

    For example, given the list of temperatures T = [73, 74, 75, 71, 69, 72, 76, 73], your output should be [1, 1, 4, 2, 1, 1, 0, 0].

    Note: The length of temperatures will be in the range [1, 30000]. Each temperature will be an integer in the range [30, 100].

	 */
	
	
	
	/* 
	 * 思考： 
	 * [73, 74, 75, 71, 69, 72, 76, 73]
	 * 当我们找比75大的数的过程中，实际上已经找到了比71和69大的数是72，如何减少循环，使得这种操作只进行一次
	 * 
	 * thinking:使用栈的操作,一次入栈的过程中就记录这次操作
	 * 
	 * 
	 */
	public static int[] dailyTemperatures2(int[] T) {
		int[] res = new int[T.length];
		Stack<Integer> stack = new Stack<>();
		for(int i=0;i<T.length;i++) {
			if(!stack.isEmpty()) {
				/*
				 * 如果数组中当前元素比栈中元素大，栈中的元素出栈，当前元素入栈，
				 */
				while(!stack.isEmpty() && T[i]>T[stack.lastElement()]) {
					res[stack.lastElement()]= (i-stack.lastElement());
					stack.pop();
				}
				stack.push(i);
			}else {
				/*
				 * 栈中存放数组下标index,当数组中当前元素和栈中元素比较时，栈中元素的值为T[index]
				 */
				stack.push(i);
			}
			
		}
        return res;
   }
	
	public static int[] dailyTemperatures(int[] T) {
		 int[] res = new int[T.length];
		 
        for (int i=0;i<T.length;i++) {
       	 for(int j=i+1;j<T.length;j++) {
					if(T[j]>T[i]) {
						res[i]= (j-i);
						break;
					}
				}
		 }
        return res;
   }
	
	
	public static void main(String[] args) {
		int[] T = new int[]{73, 74, 75, 71, 69, 72, 76, 73};
		int[] res = dailyTemperatures2(T);
		for(Integer i:res) {
			System.out.println(i);
		}
	}
}
