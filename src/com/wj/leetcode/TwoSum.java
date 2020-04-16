package com.wj.leetcode;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map.Entry;

public class TwoSum {

	
	
	
	
	public static void main(String[] args) {
		/*int[] nums=new int[]{2,7,11,15};
		
		int[] res=new int[2];
		res=twoSum4(nums, 9);
		
		System.out.println(res[0]+"-----"+res[1]);*/
		
		HashMap<String, Integer> hashmap=new HashMap<>();
		hashmap.put("3", 1);
		hashmap.put("3", 0); //this method can overwrite the original value, to solve this problem, need to override hashcode() and equals() 
		hashmap.put("4", 5);
		
		/*System.out.println(hashmap.get(3).hashCode()+" "+hashmap.get(3));
		System.out.println(hashmap.get(3).hashCode()+" "+hashmap.get(3));
		System.out.println(hashmap.get(3).hashCode()+" "+hashmap.get(3));
		System.out.println(hashmap.hashCode()+" "+hashmap.get(4));
		System.out.println(hashmap.hashCode()+" "+hashmap.get(3));
		System.out.println(hashmap.hashCode()+" "+hashmap.get(3));
		System.out.println(hashmap.hashCode()+" "+hashmap.get(3));*/
		
		Iterator<String> ite=hashmap.keySet().iterator();
		
		while(ite.hasNext()) {
			
			//Entry<Integer, Integer> entry=ite.next();
			
			String key=ite.next();
			
			System.out.println("key:"+key+"   hashcode:"+key.hashCode()+"  value:"+hashmap.get(key));
		}
		
		
		
		
		
	}
	
	/*
	 * Example:
	 * Given nums = [2, 7, 11, 15], target = 9,
	 * Because nums[0] + nums[1] = 2 + 7 = 9,
	 * return [0, 1].
	 */
	
	
	public int[] twoSum(int[] nums, int target) {
	
		int[] res=new int[2];
		
		for(int i=0;i<nums.length-1;i++) {
			for(int j=i+1;j<nums.length;j++) {
				if(nums[j]==(target-nums[i])) {
					res[0]=i;
					res[1]=j;
					break;
				}
			}
		}
		
		return res;		
		
	}
	
	
	
	
	public int[] twoSum2(int[] nums, int target) {
		
		int[] res=new int[2];
		
		HashMap<Integer, Integer> hashmap=new HashMap<>();
		
		for(int i=0;i<nums.length;i++) {
			hashmap.put(nums[i], i );// 注意key 和value存放的值
		}
		
		for(int i=0;i<nums.length;i++) {
			int tmp=target-nums[i];
			if(hashmap.containsKey(tmp) && (hashmap.get(tmp)!=i)) {
				res[0]=i;
				res[1]=hashmap.get(tmp);
				break;
			}
		}
		
		return res;		
		
	}
	
	
	
	
	
	
	public static int[] twoSum3(int[] nums, int target) {
			
			int[] res=new int[2];
			/*
			 * 由于Hashtable是线程安全的也是synchronized，所以在单线程环境下它比HashMap要慢。
			 * 如果你不需要同步，只需要单一线程，那么使用HashMap性能要好过Hashtable。
			 */
			Hashtable<Integer, Integer> hashtable=new Hashtable<>();
			
			for(int i=0;i<nums.length;i++) {
				hashtable.put(nums[i], i );// 注意key 和value存放的值
			}
			
			System.out.println(hashtable.size());
			
			for(int i=0;i<nums.length;i++) {
				int tmp=target-nums[i];
				if(hashtable.containsKey(tmp) && (hashtable.get(tmp)!=i)) {
					res[0]=i;
					res[1]=hashtable.get(tmp);
					break;
				}
			}
			
			return res;		
			
	}
	
	
	
	/*
	 * Runtime: 2 ms, faster than 99.45% of Java online submissions for Two Sum.
	 * Memory Usage: 36.1 MB, less than 92.83% of Java online submissions for Two Sum.
	 */
	public static int[] twoSum4(int[] nums, int target) {
			
			int[] res=new int[2];
			
			HashMap<Integer, Integer> hashmap=new HashMap<>();
			//HashMap<Integer, Integer> hashmap=new HashMap<>(nums.length,1F);
			
			for(int i=0;i<nums.length;i++) {
				/*
				 * if we put  hashmap.put(nums[i], i); here 
				 * will show error below
				 */
				
				/*
				 * 
				 * Wrong Answer
				 * Input
				 * [3,3]
				 * 6
				 * Output
				 * [0,0]
				 * Expected
				 * [0,1]
				 */
				
				int tmp=target-nums[i];
				if(hashmap.containsKey(tmp) && (hashmap.get(tmp)!=i)) {
					res[0]=hashmap.get(tmp);
					res[1]=i;
					break;
				}
				
				hashmap.put(nums[i], i);
			}
			
			return res;		
			
	}
	
	
}
