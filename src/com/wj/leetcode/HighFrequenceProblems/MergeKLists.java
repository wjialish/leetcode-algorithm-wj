package com.wj.leetcode.HighFrequenceProblems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MergeKLists {


    public static void main(String[] args) {
        // ListNode list1= new ListNode(1);
        // list1.next.val=4;
        // list1.next.next.val=5;

        // ListNode list2= new ListNode(1);
        // list2.next.val=3;
        // list2.next.next.val=4;

        // ListNode list3= new ListNode(2);
        // list3.next.val=6;
        // list3.next.next.val=8;

        // ListNode[] lists=new ListNode[]{list1,list2,list3};
        ListNode list1= new ListNode(1);    

        ListNode list2= new ListNode(4);

        ListNode list3= new ListNode(5);
       
        list1.next=list2;
        list2.next=list3;


        ListNode list4= new ListNode(1);    

        ListNode list5= new ListNode(3);

        ListNode list6= new ListNode(4);
       
        list4.next=list5;
        list5.next=list6;


        



        ListNode[] lists=new ListNode[]{list1,list4};

        ListNode res = mergeKLists(lists);
    }


   
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
    

    

    public static class ListNode{
        int val;
        ListNode next;
        ListNode(int x){
            this.val=x;
        }
    }
    
}