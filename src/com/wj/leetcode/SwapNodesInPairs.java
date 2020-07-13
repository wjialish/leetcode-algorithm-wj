package com.wj.leetcode;

import java.util.List;

public class SwapNodesInPairs {

	
	   public ListNode swapPairs(ListNode head) {
		   int n = 2;
		   ListNode cur=head;  //当前节点
		   if(cur!=null && cur.next!=null) {
			   ListNode prev=null; //前一个节点
			   ListNode next=null; //下一个节点
			   while(cur!=null && n-- > 0 ) {  //不断重复如下步骤，直到把这组当中的k的元素翻转完毕
				  next = cur.next; //将cur指向的下一个节点保存到next指针
				  cur.next = prev;  //cur指针指向prev
				  prev = cur;  //prev和cur指针一起向前移动一步
				  cur = next;
			   }
			   
			   if(next!=null) {
				   head.next = swapPairs(next);
			   }
			   return prev;
			   
		   }else {
			   return head;
		   }
		   
	    }
	   
	   
	   public ListNode swapPairs2(ListNode head) {
		  ListNode tempNode = head;
		   while(tempNode!=null && tempNode.next!=null) {
			  int temp = tempNode.val;
			  tempNode.val = tempNode.next.val;
			  tempNode.next.val = temp;
			  tempNode = tempNode.next.next;
		   }
		   
		   return head;
		   
	    }
	   
	   
	   
	   public static class ListNode{
		   int val;
		   ListNode next;
		   ListNode(int x){
			   val=x;
		   }
	   }
	   
	   
	   public static void main(String[] args) {
		 SwapNodesInPairs snp =new SwapNodesInPairs();
		 ListNode head = new ListNode(1);
		 ListNode head2 = new ListNode(2);
		 ListNode head3 = new ListNode(3);
		 ListNode head4 = new ListNode(4);
		 head.next=head2;
		 head2.next=head3;
		 head3.next=head4;
		 
		 
		 ListNode ll = snp.swapPairs(head);
		 
		 
		  
		  
	}
	   
	   
	   
	   /**
	    * 
	    * class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode prev = null;
        ListNode cur = head;
        ListNode next = null;
        ListNode check = head;
        int canProceed = 0;
        int count = 0;
        // 检查链表长度是否满足翻转
        while (canProceed < k && check != null) {
            check = check.next;
            canProceed++;
        }
        // 满足条件，进行翻转
        if (canProceed == k) {
            while (count < k && cur != null) {
                next = cur.next;
                cur.next = prev;
                prev = cur;
                cur = next;
                count++;
            }
            if (next != null) {
                // head 为链表翻转后的尾节点
                head.next = reverseKGroup(next, k);
            }
            // prev 为链表翻转后的头结点
            return prev;
        } else {
            // 不满住翻转条件，直接返回 head 即可
            return head;
        }
    }
}
	    */
	   
}
