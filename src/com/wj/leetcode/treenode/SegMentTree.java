package com.wj.leetcode.treenode;

import javax.print.attribute.standard.Sides;

public class SegMentTree {

	public static void main(String[] args) {
		int[] arr = {1,3,5,7,9,11};
		
		int size= 6;
		
		int[] tree = new int[100];
		
		buildSegmentTree(arr,tree,0,0,size-1); // node = 0 表示跟节点，表示的范围是0-5
		
		for(int i:tree) {
			System.out.print(i + " ");
		}
		
		System.out.println();
		
		updateTree(arr, tree, 0, 0, size-1, 4, 6);
		
		for(int i:tree) {
			System.out.print(i + " ");
		}
		
		System.out.println();
		int resSUm = querySegmentTree(arr, tree, 0, 0, size-1, 2, 5);
		System.out.println(resSUm);
		
	}

	private static void buildSegmentTree(int[] arr, int[] tree, int node, int start, int end) {
		if(start == end) {
			tree[node] = arr[start];
		}else {
			int mid = (start+end)/2;
			
			int left_node = 2 * node + 1;
			int right_node = 2* node + 2;
			
			buildSegmentTree(arr, tree, left_node, start, mid);
			buildSegmentTree(arr, tree, right_node, mid + 1, end);
			
			tree[node] = tree[left_node] + tree[right_node];
		}
		
	}
	
	
	private static int querySegmentTree(int[] arr,int[] tree, int node, int start, int end, int L,int R) {
		System.out.println("start="+start);
		System.out.println("end="+end);
		System.out.println();
		if(start > R || end < L) {
			return 0;
		}else if(start>= L && end <= R) {
			return tree[node];
		}else {
			int mid = start + (end - start)/2;
			int left_node = 2 * node + 1;
			int right_node = 2* node + 2;
			int sum_left = querySegmentTree(arr, tree, left_node, start, mid, L, R);
			int sum_right = querySegmentTree(arr, tree, right_node, mid+1, end, L, R);
			return sum_left+sum_right;
		}
	}
	
	
	
	
	
	private static void updateTree(int[] arr, int[] tree, int node,int start,int end, int idx, int value) {
		if(start == end) {
			arr[idx] = value;
			tree[node] = value;
		}else {
			int mid = start + (end - start) /2;
			
			//这是个递归，递归的边界条件就是正好碰到那个我要改的那个数字的时候
			int left_node = 2 * node + 1;
			int right_node = 2* node + 2;
			
			if(idx >= start && idx <= mid) {
				updateTree(arr, tree, left_node, start, mid, idx, value);
			}else {
				updateTree(arr, tree, right_node, mid+1, end, idx, value);
			}
			
			//改完做分支或者右分支之后，需要吧tree[node]更新
			tree[node] = tree[left_node] + tree[right_node];
		}
		
	}
	
}
