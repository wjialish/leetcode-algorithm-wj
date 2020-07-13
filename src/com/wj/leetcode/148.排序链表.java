import java.util.Comparator;
import java.util.PriorityQueue;

import com.wj.leetcode.AddTwoNumbers.ListNode;
import com.wj.leetcode.sort.MergeSort;

/*
 * @lc app=leetcode.cn id=148 lang=java
 *
 * [148] 排序链表
 */

// @lc code=start
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    // public ListNode sortList(ListNode head) {

    //     //小跟堆
    //     if(head == null){
    //         return null;
    //     }

        
    //     PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>(new Comparator<ListNode>() {
    //         public int compare(ListNode o1,ListNode o2){
    //             return o1.val-o2.val;
    //         }
    //     });

    //     while(head != null){
    //         queue.add(head);
    //         head = head.next;
    //     }

    //     ListNode dummy = new ListNode(-1);
    //     ListNode result = dummy;

    //     while(!queue.isEmpty()){
    //         dummy.next=queue.poll();
    //         dummy = dummy.next;
    //     }

    //     dummy.next=null;
    //     return result.next;

    // }






    public ListNode sortList(ListNode head) {

        //分治法
        if(head == null || head.next == null){
            return head;
        }

        //找到链表中间节点并断开链表 & 递归下探
        ListNode midNode = middleNode(head);
        ListNode rightHead = midNode.next;

        midNode.next = null;

        ListNode left = sortList(head);
        ListNode right = sortList(rightHead);

        //合并有序链表
        return mergeTwoList(left, right);

    }


    //找到链表中间节点
    private ListNode middleNode(ListNode head){

        ListNode slow = head;

        ListNode fast = slow.next.next;

        while(fast != null && fast.next != null){
            slow = slow.next;
            fast=fast.next.next;
        }

        return slow;
    }


    private ListNode sortList(ListNode head,int low,int high){
        if(low == high) return head;

        int mid = low + (high-low)/2;

        return mergeTwoList(sortList(head,low,mid), sortList(head,mid,high));

    }

    private ListNode mergeTwoList(ListNode a, ListNode b){
        if(a == null) return b;
        if(b == null) return a;

        if(a.val <= b.val){
            a.next = mergeTwoList(a.next, b);
            return a;
        }else{
            b.next = mergeTwoList(a, b.next);
            return b;
        }
    }
}

// @lc code=end

