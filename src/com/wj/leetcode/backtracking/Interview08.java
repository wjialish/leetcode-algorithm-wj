package com.wj.leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Interview08 {

	/*
	 * Write a method to compute all permutations of a string whose charac­ters are not necessarily unique. The list of permutations should not have duplicates.

Example1:

 Input: S = "qqe"
 Output: ["eqq","qeq","qqe"]
Example2:

 Input: S = "ab"
 Output: ["ab", "ba"]

	 */
	
//	public static void main(String[] args) {
//		Interview08 inter08 = new Interview08();
//		
//		for(String s : inter08.permutation("112"
//				+ "")){
//			System.out.println(s);
//		}
//		
//		
//	}
	
	public static void main(String[] args) {
		Interview08 inter08 = new Interview08();
		
		for(String s : inter08.permutation("qqee"
				+ "")){
			System.out.println(s);
		}
		
		
	}
	
	
    
	
 	
	public String[] permutation(String S) {


		//List<List<Character>> resList = new ArrayList<List<Character>>();
		List<String> resList = new ArrayList<>();
		
	    char[] arr = S.toCharArray();
	    Arrays.sort(arr);
		
		//backtracking(list,n,0);
		//dfs(resList, new ArrayList<>(), arr, new boolean[arr.length]);
	    
	    dfs(resList,new StringBuilder(),arr,new boolean[arr.length]);
		
		//集合转数组
//		List<String> res = new ArrayList<>();
//		for(List<Character> ll :resList) {
//			StringBuilder sb = new StringBuilder();
//			for(char s: ll) {
//				sb.append(s);
//			}
//			res.add(sb.toString());
//		}
		
		String[] resArr = new String[resList.size()];
		
		/*
		 * 以下这种写法编译没有问题，但是运行的时候会报错ClassCastException
		 * 这是因为java中允许向上或者向下转型，但是这种转型是否成功是根据java虚拟机中这个对象的类型来实现的。
		 * java虚拟机中保存了每个对象的类型。而数组也是一个对象，数组的类型是java.lang.Object.把java.lang.Object转成java.lang.String显然是不可能的。
		 * 因为这里是一个向下转型，而虚拟机只保存了这是一个object类型的数组，不能保证数组中的元素是string类型的，所以这个转型不能成功。
		 * 数组里面的元素只是元素的引用，不能存储具体的元素，所以数组中元素的类型还是保存在虚拟机中的。
		 */
		//arr = (String[]) res.toArray();
		
		resArr = resList.toArray(new String[resList.size()]);
		return resArr;
		
    }
	
	private void dfs(List<String> resList,StringBuilder sb, char[] arr,boolean[] isvisit) {
		if(sb.length() == arr.length) {
			resList.add(sb.toString());
			return;
		}
		
		for(int i =0; i<arr.length;i++) {
			if(isvisit[i]) continue;
			if(i>0 && arr[i] == arr[i-1] && !isvisit[i-1]) {
				continue;
			}
			sb.append(arr[i]);
			isvisit[i] = true;
			dfs(resList, sb, arr, isvisit);
			sb.deleteCharAt(sb.length()-1);
			isvisit[i] = false;
		}
		
		
	}
	
//	private void dfs(List<List<Character>> resList, List<Character> curList, char[] arr,boolean[] isvisit) {
//		if(curList.size() == arr.length) {
//			resList.add(new ArrayList<>(curList));
//			return;
//		}
//		
//		for(int i =0; i<arr.length;i++) {
//			if(isvisit[i]) continue;
//			if(i>0 && arr[i] == arr[i-1] && !isvisit[i-1]) {
//				continue;
//			}
//			curList.add(arr[i]);
//			isvisit[i] = true;
//			dfs(resList, curList, arr, isvisit);
//			curList.remove(curList.size()-1);
//			isvisit[i] = false;
//		}
//		
//		
//	}
		
//	List<List<String>> resList = new ArrayList<List<String>>();
//	
// 	
//	public String[] permutation(String S) {
//
//	    List<String> list = new ArrayList<>();
//	
//		for(Character c: S.toCharArray()) {
//			list.add(c.toString());
//		}
//		
//		int n = S.length();
//		
//		backtracking(list,n,0);
//		
//		//集合转数组
//		List<String> res = new ArrayList<>();
//		for(List<String> ll :resList) {
//			StringBuilder sb = new StringBuilder();
//			for(String s: ll) {
//				sb.append(s);
//			}
//			res.add(sb.toString());
//		}
//		
//		String[] arr = new String[res.size()];
//		
//		/*
//		 * 以下这种写法编译没有问题，但是运行的时候会报错ClassCastException
//		 * 这是因为java中允许向上或者向下转型，但是这种转型是否成功是根据java虚拟机中这个对象的类型来实现的。
//		 * java虚拟机中保存了每个对象的类型。而数组也是一个对象，数组的类型是java.lang.Object.把java.lang.Object转成java.lang.String显然是不可能的。
//		 * 因为这里是一个向下转型，而虚拟机只保存了这是一个object类型的数组，不能保证数组中的元素是string类型的，所以这个转型不能成功。
//		 * 数组里面的元素只是元素的引用，不能存储具体的元素，所以数组中元素的类型还是保存在虚拟机中的。
//		 */
//		//arr = (String[]) res.toArray();
//		
//		arr = res.toArray(new String[res.size()]);
//		return arr;
//		
//    }
//	
//	
//	
//	
//	
//	public void backtracking(List<String> list, int n, int index) {
//		if(index == n) {
//			resList.add(new ArrayList<>(list));
//		}
//		
//		for(int i = index;i<n;i++) {
//			if(!isRepeat(list, index, i)) {
//				Collections.swap(list, index, i);
//				backtracking(list, n, index+1);
//				if(index == i) continue;
//				Collections.swap(list, index, i);
//			}
//		}
//	}
//	
//	
//	public boolean isRepeat(List<String> list, int first,int index) {
//		String temp = list.get(index);
//		for(int i= first;i<index;i++) {
//			if(list.get(i) == temp) {
//				return true;
//			}
//		}
//		return false;
//	}
}
