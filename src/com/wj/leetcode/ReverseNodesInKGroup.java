package com.wj.leetcode;

import java.util.Stack;

import com.wj.leetcode.SwapNodesInPairs.ListNode;

public class ReverseNodesInKGroup {

	
	
	/*
	 * 
	 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.

Example:

Given this linked list: 1->2->3->4->5

For k = 2, you should return: 2->1->4->3->5

For k = 3, you should return: 3->2->1->4->5
	 */
	public ListNode reverseKGroup(ListNode head, int k) {
		   
	     ListNode curr=head;
	     
	     int currLength = 0;
	     
	     ListNode countLength=head;
	     
	     //检查链表长度是否满足翻转
	     while(currLength<k && countLength!=null) {
	    	 countLength = countLength.next;
	    	 currLength++;
	     }
	     
	     //如果链表长度满足翻转条件
	     if(currLength==k) {
	    	 ListNode prev = null;
	    	 ListNode next = null;
	    	 while(curr != null & currLength-- >0) {
	    		 next = curr.next;
	    		 curr.next = prev;
	    		 prev = curr;
	    		 curr = next;
	    	 }
	    	 
	    	 if(next != null) {
	    		 head.next = reverseKGroup(next, k);
	    	 }
	    	 
	    	 return prev;
	    	 
	     }else {
	    	 //如果不满足翻转条件，直接按原链表顺序返回链表,返回head
	    	 return head;
	     }
		
    }
	
	
	//使用栈的方式，push进去再拿出来就正好是翻转后的链表
	public ListNode reverseKGroup2(ListNode head, int k) {
		ListNode curr = head;
		//定义一个栈，用来翻转链表
		Stack<ListNode> stack = new Stack<ListNode>();
		//定义返回链表，一开始初始化为null，就没有办法通过.next将别的链表挂在后面，所以可以初始化为0,最后返回final.next()就可以了
		ListNode fina = new ListNode(0);
		//通过操作p，fina链表会一起变化？
        ListNode p = fina;
		while(true) {
			int count=0;
			//将k长度的链表压入栈中
			while(count<k && curr!=null) {
				stack.push(curr);
				curr=curr.next;
				count++;
			}
			//如果不满足翻转条件，count<k
			if(count<k) {
			    p.next=head;
			    break;
			}
			/*
			 * 单链表next循环时就会把前面的节点丢失，可以通过循环赋值链表fina.next=某个链表
			 */
			//如果满足翻转条件
			while(!stack.isEmpty()) {
				p.next = stack.pop();
                p = p.next;
			}
            p.next = curr;
            head = curr;	
		}
		return fina.next;
			
	}
		
	   
	   public static void main(String[] args) {
		 ReverseNodesInKGroup reverse = new ReverseNodesInKGroup();
		 ListNode head = new ListNode(1);
		 ListNode head2 = new ListNode(2);
		 ListNode head3 = new ListNode(3);
		 ListNode head4 = new ListNode(4);
		 ListNode head5 = new ListNode(5);
		 head.next=head2;
		 head2.next=head3;
		 head3.next=head4;
		 head4.next=head5;
		 
		 
		 ListNode ll = reverse.reverseKGroup2(head, 2);
		 
		 
		  
		  
	}
		
		
//		//尾插法
//		public ListNode reverseKGroup3(ListNode head, int k) {
//			//ListNode curr = head;
//			ListNode countLength = head;
//			int currLength = 0;
//			//检查链表长度是都满足要求
//			while(currLength<k && countLength != null) {
//				countLength = countLength.next;
//				currLength++;
//			}
//			/**
//			 * 尾插法思路：
//			 * 把当前链表最前面的节点移到k个节点链表长度的尾端
//			 */
//			
//			Stack<Integer> stack = new Stack<Integer>();
//			
//			//如果链表长度满足翻转条件
//			if(currLength==k) {
//				ListNode prev=null;
//			    
//				while(currLength-- >0 && curr!=null) {
//			     	
//				}
//				
//			
//				
//				
//				
//				
//				
//			}
//		   
//	     
//		
//   }
	public static class ListNode{
		int val;
		ListNode next;
		ListNode(int x){val=x;}
	}
}
