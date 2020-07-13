package com.wj.leetcode.PriorityQueue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.TreeMap;



public class TopKFrequentElements {
	/*
	 * Given a non-empty array of integers, return the k most frequent elements.出现频率前k的词

Example 1:

Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]
Example 2:

Input: nums = [1], k = 1
Output: [1]
	 */
	
	 /*
     * PriorityQueue 默认是一个小顶堆，然后可以通过自定义的Comparator函数来实现大顶堆。
     * 如下代码实现了初始大小为11的大顶堆。
     */
    final int DEFAULT_INITIAL_CAPACITY = 11;
    PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(DEFAULT_INITIAL_CAPACITY,new Comparator<Integer>() {

		@Override
		public int compare(Integer o1, Integer o2) {
			// TODO Auto-generated method stub
			//从大到小排序
			return o2-o1;
		}
    	
	});
	
    
    
    
    //hashmap的2种遍历循环方式
    //method1：效率高
//    Map<Integer, Integer> map =new HashMap<Integer, Integer>();
//    Iterator<Entry<Integer, Integer>> it= map.entrySet().iterator();
//    if(it.hasNext()) {
//    	Map.Entry<Integer, Integer> entry = it.next();
//    	int key = entry.getKey();
//    	int value = entry.getValue();
//    }
//    //method2:效率低
//    Iterator<Integer> it2= map.keySet().iterator();
//    while(it2.hasNext()) {
//    	int key = it2.next();
//    	int value = map.get(key);
//    }
    
    
    

	public List<Integer> topKFrequent(int[] nums, int k) {

		//hashtable统计词频出现的次数
		Hashtable<Integer, Integer> hashtable = new Hashtable<Integer,Integer>();
		for(int element:nums) {
			if(hashtable.contains(element)) {
				//hashtable.putIfAbsent(element, hashtable.get(element)+1);
				hashtable.put(element, hashtable.get(element)+1);
			}else {
				//hashtable.putIfAbsent(element, 1);
				hashtable.put(element, 1);
			}
		}
		
        List<Integer> result = new ArrayList<Integer>();

		//使用大根堆优先级队列来统计出现频次最高的前k个数,优先级队列的大小为k
		//声明一个类型为hashhtable的优先级队列,将统计好的hashtable的数放入到优先级队列中
        PriorityQueue<Entry<Integer,Integer>> maxHeap = new PriorityQueue<>((v1,v2)->((Entry<Integer, Integer>) v2).getValue()-((Entry<Integer, Integer>) v1).getValue());
        maxHeap.addAll(hashtable.entrySet());
        for(int i=0;i<k;i++) {
        	result.add(maxHeap.poll().getKey());
        }
       
        return result;	
    }
	
	
	
	//优先级队列-大跟堆
	public List<Integer> topKFrequent2(int[] nums, int k) {

		//hashmap统计词频出现的次数
		Map<Integer, Integer> map = new HashMap<Integer,Integer>();
		for(int element:nums) {
			if(map.containsKey(element)) {
				//map.putIfAbsent(element, map.get(element)+1);
				map.put(element, map.get(element)+1);
			}else {
				//map.putIfAbsent(element, 1);
				map.put(element, 1);
			}
		}
		
        List<Integer> result = new ArrayList<Integer>();

		//使用大根堆优先级队列来统计出现频次最高的前k个数
		//声明一个类型为Entry的优先级队列,将统计好的hashmap的数放入到优先级队列中
        PriorityQueue<Entry<Integer,Integer>> maxHeap = new PriorityQueue<>(k,(v1,v2)-> v2.getValue()- v1.getValue());
        
        maxHeap.addAll(map.entrySet());
        for(int i=0;i<k;i++) {
        	result.add(maxHeap.poll().getKey());
        }
       
        return result;	
        
    }
	
	
	
	//优先级队列-小跟堆
	public List<Integer> topKFrequent3(int[] nums, int k) {

		//hashmap统计词频出现的次数
		Map<Integer, Integer> map = new HashMap<Integer,Integer>();
		for(int element:nums) {
			map.put(element, map.getOrDefault(element, 0)+1);
		}
		
        List<Integer> result = new ArrayList<Integer>();

        /*
         * 思考：求前k大的元素--> 小根堆
         * 使用小根堆优先级队列来统计出现频次最高的前k个数,优先级队列的大小为k,优先级队列中只存放出现的词，不存放出现的次数
         */
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>((v1,v2)-> map.get(v1)-map.get(v2));
        
        for(int key:map.keySet()) {
        	if(maxHeap.size()<k) {
        		maxHeap.offer(key);
        	}else {
        		//使用小顶堆，比堆中的数大就进堆
        		if(map.get(maxHeap.peek()) < map.get(key)) {
        			maxHeap.poll();
            		maxHeap.offer(key);
        		}
        	}
        }
        
        /*
         * 将小跟堆中的数翻转过来 m1:栈 m2:Collections.reverse(result);
         */
//        Stack<Integer> stack = new Stack<Integer>();       
//        while(!maxHeap.isEmpty()) {
//        	stack.push(maxHeap.poll());
//        }
//        
//        while(!stack.isEmpty()) {
//        	result.add(stack.pop());
//        }
        
        while(!maxHeap.isEmpty()) {
        	result.add(maxHeap.poll());
        }
        Collections.reverse(result);
        
        return result;	
        
    }
	
	
	
	
	
}
