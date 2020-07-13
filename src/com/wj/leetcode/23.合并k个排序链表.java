import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import com.wj.leetcode.AddTwoNumbers.ListNode;

/*
 * @lc app=leetcode.cn id=23 lang=java
 *
 * [23] 合并K个排序链表
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

	//暴力解：List
    // public static ListNode mergeKLists(ListNode[] lists) {

    //     List<Integer> list = new ArrayList<Integer>();
    //     for(ListNode ln : lists){
    //         while(ln != null){
    //             list.add(ln.val);
    //             ln = ln.next;
    //         }
    //     }

    //     Collections.sort(list);

    //     ListNode resListNode = new ListNode(-1);

    //     ListNode result = resListNode;

    //     for(int i=0;i<list.size();i++){
    //         ListNode temp=new ListNode(0);
    //         temp.val=list.get(i);
	// 		resListNode.next=temp;
	// 		resListNode=resListNode.next;
    //     }

    //     //resListNode.next=null;
	// 	return result.next;
		

	// }
	

    //优化解：最小堆
	/**
	 * 每次比较k个链表头，时间复杂度O(k)
	 * 对k个链表头创建时大小为k的最小堆
	 *    创建一个大小为k的最小堆时间复杂度为O(k)
	 *    从堆中取最小的数，时间复杂度为O(logk)
	 *    如果每个链表平均长度为n 则共有nk个元素 即用大小为k的最小堆过滤nk个元素
	 *    整体的时间复杂度就是O(nk*log(nk))
	 */
    // public ListNode mergeKLists(ListNode[] lists) {
	// 	if(lists==null || lists.length==0) {
	// 		return null;
	// 	}
	// 	//创建一个堆，并设置元素的排序方式
	// 	PriorityQueue<ListNode> queue = new PriorityQueue(new Comparator<ListNode>() {
	// 		public int compare(ListNode o1, ListNode o2) {
	// 			return (o1.val - o2.val);
	// 		}
	// 	});
	// 	//遍历链表数组，然后将每个链表的每个节点都放入堆中
	// 	for(int i=0;i<lists.length;i++) {
	// 		while(lists[i] != null) {
	// 			queue.add(lists[i]);
	// 			lists[i] = lists[i].next;
	// 		}
	// 	}
	// 	ListNode dummy = new ListNode(-1);
	// 	ListNode head = dummy;
	// 	//从堆中不断取出元素，并将取出的元素串联起来
	// 	while( !queue.isEmpty() ) {
	// 		dummy.next = queue.poll();
	// 		dummy = dummy.next;
	// 	}
	// 	dummy.next = null;
	// 	return head.next;
	// }






	//分治法 利用归并排序的思想
	public ListNode mergeKLists(ListNode[] lists){

		if(lists==null || lists.length==0) {
			return null;
	    }

		return mergeKLists(lists,0,lists.length-1);

	}

	private ListNode mergeKLists(ListNode[] lists,int low,int high){
		if(low == high){
			return lists[low];	
		}

		int mid = low + (high-low)/2;

		return mergeTwoLists(mergeKLists(lists,low,mid), mergeKLists(lists,mid+1,high));

	}

	private ListNode mergeTwoLists(ListNode a, ListNode b){
		if(a ==null) return b;
		if(b == null) return a;

		if(a.val<b.val){
			a.next=mergeTwoLists(a.next,b);
			return a;
		}

		b.next = mergeTwoLists(a, b.next);
		return b;

	}
	

}
// @lc code=end

