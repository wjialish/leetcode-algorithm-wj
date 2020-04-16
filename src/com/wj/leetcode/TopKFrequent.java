package com.wj.leetcode;

import java.awt.RenderingHints.Key;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKFrequent {
	
	/*
	 * 692. Top K Frequent Words
Given a non-empty list of words, return the k most frequent elements.

Your answer should be sorted by frequency from highest to lowest. If two words have the same frequency, then the word with the lower alphabetical order comes first.

Example 1:
Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
Output: ["i", "love"]
Explanation: "i" and "love" are the two most frequent words.
    Note that "i" comes before "love" due to a lower alphabetical order.
Example 2:
Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
Output: ["the", "is", "sunny", "day"]
Explanation: "the", "is", "sunny" and "day" are the four most frequent words,
    with the number of occurrence being 4, 3, 2 and 1 respectively.
	 */
	
	
	//m1:排序 
	// 计算每个单词的频率，并使用这些频率的自定义排序关系对单词进行排序，并取前k
	public List<String> topKFrequent(String[] words, int k) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		for(int i=0;i<words.length;i++) {
			map.put(words[i], map.getOrDefault(words[i], 0)+1);
		}
		
		List<String> resList = new ArrayList<String>(map.keySet());
		// v1.compareTo(v2) 按字母大小写 从小到大 排序
		// map.get(v2)-map.get(v1)  单词频次高低按照从大到小排序
		Collections.sort(resList,(v1,v2)-> map.get(v1).equals(map.get(v2)) ? v1.compareTo(v2) : map.get(v2)-map.get(v1));
		
	    return resList.subList(0, k);

    }
	
	
	//m2: 小根堆
	public List<String> topKFrequent2(String[] words, int k) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		for(int i=0;i<words.length;i++) {
			map.put(words[i], map.getOrDefault(words[i], 0)+1);
		}
		
		List<String> resList = new ArrayList<String>();
		
		//注意小根堆中： v2.compareTo(v1) 按字母大小写 从大到小 排序--》放到小根堆中就是字母顺序在后的（大的）会被压到下面
		// map.get(v1)-map.get(v2) 单词频次高低按照从大到小排序
		PriorityQueue<String> minHeap = new PriorityQueue<String>((v1,v2) -> map.get(v1).equals(map.get(v2)) ? v2.compareTo(v1) : map.get(v1)-map.get(v2));
		
		//注意使用这种方式，不要使用如下方式
		for(String key: map.keySet()) {
			minHeap.offer(key);
			if(minHeap.size()>k) {
				minHeap.poll();
			}
		}
		
//		for(String key: map.keySet()) {
//			if(minHeap.size()!=k) {
//				minHeap.offer(key);
//			}else {
		        //如果字母排序在前，单词频次一样，就不会替换小根堆堆顶的元素，这样是有问题的
//				if(map.get(minHeap.peek()) < map.get(key)) {
//					minHeap.poll();
//					minHeap.offer(key);
//				}
//			}
//		}
		
		while(!minHeap.isEmpty()) {
			resList.add(minHeap.poll());
		}
		
		Collections.reverse(resList);
		
	    return resList;

    }

}
