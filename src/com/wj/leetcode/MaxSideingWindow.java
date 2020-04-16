package com.wj.leetcode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.BlockingDeque;

public class MaxSideingWindow {

   /*
    * 239. 滑动窗口最大值
给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。

返回滑动窗口中的最大值。


示例:

输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
输出: [3,3,5,5,6,7] 
解释: 

  滑动窗口的位置                最大值
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
 

提示：

你可以假设 k 总是有效的，在输入数组不为空的情况下，1 ≤ k ≤ 输入数组的大小。

 

进阶：

你能在线性时间复杂度内解决此题吗？
    */
	
	
	public static int[] maxSlidingWindow(int[] nums, int k) {
		/*
		 * LinkedList:
		 * 作为FIFO(先进先出)队列，等价与：(先进的是队首，后进的是队尾，所以添加元素肯定是在队尾，删除元素在队首)
		 * 队列            等价方法
		 * add(e)         addLast()
		 * offer()        offerLast()
		 * remove()       removeFirst() 
		 * poll()         pollFirst()
		 * element()      getFirst()
		 * peek()         peekFirst()
		 * 
		 *         
		 * 作为LIFO(后进先出)的栈，等价与：
		 * 栈             等价方法
		 * push()        addFirst()
		 * pop()         removeFirst()
		 * peek()        peekFirst()
		 * 
		 *      
		 * 
		 * 双端队列ListedList:允许我们在队列的头尾两端在O(1)时间内进行数据的添加 查询和删除
		 * 常用场景：实现一个长度动态变化的窗口或者连续区间
		 * 当前的数比队尾的数大，将队尾的数弹出
		 * 如果当前的数比队尾的数小，将当前的数压入到双端队列的队尾
		 * 这样可以保证队头的数永远是最大的
		 * 
		 * 思考：怎么保证当前的滑动窗口的范围呢？R-L+1=k
		 */
         int[] res = new int[nums.length-k+1];
         
         //当nums[]为空 或者nums[].length<2时
         if(nums==null || nums.length<2)
        	 return nums;
         
         //双端队列中的元素保存的是元素在原数组中的位置坐标
         LinkedList<Integer> list = new LinkedList<Integer>();
         
         //需要一个指针，指向数组中的当前元素
         for(int i=0; i<nums.length;i++) {
        	//当前的数比队尾(peekLast())的数大，将队尾的数弹出（pollLast()）
        	 while(!list.isEmpty() && nums[i]>=nums[list.peekLast()]) {
        		 list.pollLast();
        	 }
        	 
        	 //1.当双端队列为空
        	 //2.当前的数比队尾的数小
        	 //将当前的数压入到双端队列的队尾作为队尾
        	 list.addLast(i);
        	 
        	 // 初始化窗口，等到窗口长度为k时，下次移动再删除队首（peekFirst()）过期数值
        	 if(list.peekFirst()<=(i-k)) {
        		 list.pollFirst();//弹出队首的值
        	 }
        	 
        	 //当窗口长度等于k时，保存当前窗口的最大值，即双端队列队首的值
        	 if(i-k+1>=0) {
        		 res[i-k+1] = nums[list.peekFirst()];
        	 }
         }
         
         return res;
		
    }
	
	
	
	public static int[] maxSlidingWindow2(int[] nums, int k) {
		/*
		 * 双端队列Deque:允许我们在队列的头尾两端在O(1)时间内进行数据的添加 查询和删除
		 * 常用场景：实现一个长度动态变化的窗口或者连续区间
		 * 当前的数比队尾的数大，将队尾的数弹出
		 * 如果当前的数比队尾的数小，将当前的数压入到双端队列的队尾
		 * 这样可以保证队首的数永远是最大的
		 * 
		 * 思考：怎么保证当前的滑动窗口的范围呢？R-L+1=k
		 */
         int[] res = new int[nums.length-k+1];
         
         //当nums[]为空 或者nums[].length<2时
         if(nums==null || nums.length<2)
        	 return nums;
         
         //双端队列中的元素保存的是元素在原数组中的位置坐标
         LinkedList<Integer> list = new LinkedList<Integer>();
         
         //需要一个指针，指向数组中的当前元素
         for(int i=0; i<nums.length;i++) {
        	//当前的数比队尾的数大，将队尾的数弹出
        	 while(!list.isEmpty() && nums[i]>=nums[list.getLast()]) {
        		 list.pollLast();
        	 }
        	 
        	 //1.当双端队列为空
        	 //2.当前的数比队尾的数小
        	 //将当前的数压入到双端队列的队尾
        	 list.addLast(i);
        	 //System.out.println(list.getLast(i));
        	 
        	
        	 // 初始化窗口，等到窗口长度为k时，下次移动再删除队首过期数值
        	 if(list.getFirst()<=(i-k)) {
        		 list.removeFirst();
        	 }
        	 
        	 //当窗口长度等于k时，保存当前窗口的最大值，即双端队列队首的值
        	 if(i-k+1>=0) {
        		 res[i-k+1] = nums[list.getFirst()];
        	 }
        	 
         }
         
         return res;
		
    }
	
	public static void main(String[] args) {
		int[] arr = new int[] {1,3,1,2,0,5};
		
		int[] res= new int[4];
		
		res= maxSlidingWindow(arr, 3);
		
		for(Integer i:res) {
			System.out.println(i);
		}
	}
	
	
}

