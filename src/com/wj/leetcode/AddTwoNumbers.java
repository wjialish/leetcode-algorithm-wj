package com.wj.leetcode;

import java.awt.List;

public class AddTwoNumbers {

	
	
	
	
	public static void main(String[] args) {
		ListNode l1=new ListNode(2);
		//l1.val=2;
		l1.next.val=4;
		l1.next.next.val=3;
		
		ListNode l2=new ListNode(5);
		//l1.val=2;
		l2.next.val=6;
		l2.next.next.val=4;
		
		ListNode l=addTwoNumbers2(l1, l2);
		
		System.out.println(l.val);
		
	}
	
	public static class ListNode {
		int val;
		ListNode next;
		public ListNode(int x) {
            this.val=x;
		}
	}
	
	public static ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (l1 != null || l2 != null) {
            int n1 = l1 != null ? l1.val : 0;
            int n2 = l2 != null ? l2.val : 0;
            int sum = n1 + n2 + carry;
            carry = sum / 10;
            cur.next = new ListNode(sum % 10);
            cur = cur.next;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (carry != 0) {
            cur.next = new ListNode(carry);
        }
        return dummy.next;
    }
	
	

	/*
	 * You are given two non-empty linked lists representing two non-negative integers. 
	 * The digits are stored in reverse order and each of their nodes contain a single digit. 
	 * Add the two numbers and return it as a linked list.
     *
     * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
     * 
	 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
       Output: 7 -> 0 -> 8
       Explanation: 342 + 465 = 807.
	 */
	
	public static ListNode addTwoNumbers(ListNode l1,ListNode l2) {
		ListNode listNode=null;
		
		StringBuilder s1 = new StringBuilder();
		ListNode tmp=l1;
		while(tmp != null) {
			s1.append(tmp.val);
			tmp=tmp.next;
		}
		
		
		StringBuilder s2=new StringBuilder();
;		ListNode tmp2=l2;
		while(tmp2 != null) {
			s2.append(tmp2.val);
			tmp2=tmp2.next;
		}

		String s3=reverse(s1.toString());
		
		String s4=reverse(s2.toString());

		Integer sum=Integer.parseInt(s3)+Integer.parseInt(s4);
		
        String sumStr=reverse(String.valueOf(sum));	
		
        
        
        //listNode.val=Integer.parseInt(sumStr);
        
        
        String[] str=sumStr.split("");
        
        listNode=new ListNode(Integer.parseInt(str[0]));
        
        
        for(int i=1; i<str.length;i++) {
        	if(str[i] != " "){
        	  int add=Integer.parseInt(str[i]);
        	  listNode=new ListNode(add);
        	  listNode=listNode.next;
        	}
        	
        }		
		
		return listNode;
	}
	
	
	
	public static String reverse(String s) {
        StringBuilder sb=new StringBuilder();
	
        String[] t=s.split("");
        
        
		for(int i=t.length-1; i>=0;i--) {
			if(t[i] != " ") {
				sb.append(t[i]);
			}
			
		}
		
		return sb.toString();
	}
	
	
}
