package com.wj.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

public class FrequencySort {

	/*
	 * 451. Sort Characters By Frequency
Given a string, sort it in decreasing order based on the frequency of characters.

Example 1:

Input:
"tree"

Output:
"eert"

Explanation:
'e' appears twice while 'r' and 't' both appear once.
So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
Example 2:

Input:
"cccaaa"

Output:
"cccaaa"

Explanation:
Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
Note that "cacaca" is incorrect, as the same characters must be together.
	 */
	
	public String frequencySort(String s) {

		//将字符串每个字符分隔开，统计各个字符出现的频率--map
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		for(char c : s.toCharArray()) {
			map.put(c, map.getOrDefault(c, 0)+1);
		}
		
	    //放入一个大根堆，完成排序
		PriorityQueue<Entry<Character, Integer>> maxHeap = new PriorityQueue<Entry<Character, Integer>>((v1,v2)->v2.getValue()-v1.getValue());
		maxHeap.addAll(map.entrySet());
		
		StringBuilder sb = new StringBuilder();
		
		while(!maxHeap.isEmpty()) {
			for(int i=0;i<maxHeap.peek().getValue();i++) {
				sb.append(maxHeap.peek().getKey());
			}
			maxHeap.poll();
		}
		
		return sb.toString();
		
    }
	
	
	public String frequencySort2(String s) {

		//将字符串每个字符分隔开，统计各个字符出现的频率--数组
		//数组中存放的是字母对应ASCII码，而不是对应的字符，int类型的数组
		int[] latters = new int[256];
		for(char c : s.toCharArray()) {
			latters[c]++;
		}
		
	    //放入一个大根堆，完成排序
		PriorityQueue<Latter> maxHeap = new PriorityQueue<Latter>((v1,v2)-> v2.count-v1.count);
		for(int i=0;i<latters.length;i++) {
			if(latters[i] != 0) {
				maxHeap.offer(new Latter((char)i, latters[i]));
			}
		}
		StringBuilder sb = new StringBuilder();
//		while(!maxHeap.isEmpty()) {
//			for(int i=0;i<maxHeap.peek().count;i++) {
//				sb.append(maxHeap.peek().latter);
//			}
//			maxHeap.poll();
//		}
		//相比与上面方法效率高一些
		while(!maxHeap.isEmpty()) {
			Latter latter = maxHeap.poll();
			for(int i=0;i<latter.count;i++) {
				sb.append(latter.latter);
			}
		}
		return sb.toString();
    }
	
	public class Latter implements Comparable<Latter>{

		public char latter = '0';
		public int count = 0;
		
		public Latter(char latter,int count){
			this.latter=latter;
			this.count=count;
		}
		
		@Override
		public int compareTo(Latter o) {
			// TODO Auto-generated method stub
			return o.count-this.count;
		}
	}
	
	
}
