package com.wj.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class AAA {
	public static void main(String[] args) {
		
		/*小Q今天在上厕所时想到了这个问题：有n个数，两两组成二元组，差最小的有多少对呢？差最大呢？
			输入描述:
                                          输入包含多组测试数据。
			 对于每组测试数据：
			 N - 本组测试数据有n个数
			 a1,a2...an - 需要计算的数据
			 保证:
			 1<=N<=100000,0<=ai<=INT_MAX.
			输出描述:
			对于每组数据，输出两个数，第一个数表示差最小的对数，第二个数表示差最大的对数。
			示例1
			输入
			6
			45 12 45 32 5 6
			输出
			1 2*/
		
		Scanner sc=new Scanner(System.in);
		while(sc.hasNext()){
			int n=sc.nextInt();
			int[] a=new int[n];
			for(int i=0;i<n;i++){
				a[i]=sc.nextInt();
			}
			Arrays.sort(a);
			int minCount=0,maxCount=0;
			if(a[0]==a[n-1]){//所有元素都相等   
				minCount=maxCount=(n*(n-1))/2;
				System.out.println(minCount+" "+maxCount);
				continue;
			}
			
			//元素不完全相同
			//把元素放到map集合中，记录出现的次数
			//放到TreeMap会实现自动排序，在求最大插值的时候可以用到
			Map<Integer, Integer> map=new TreeMap<Integer,Integer>();
			for(int i=0;i<n;i++){
				if(!map.containsKey(a[i])){
					map.put(a[i], 1);
				}else{
					map.put(a[i], map.get(a[i])+1);
				}
			}
			//差最小的对数
			int min=Math.abs(a[1]-a[0]);
			if(map.size()==n){//如果数组中没有重复的数字
			   minCount=1;
			   for(int i=2;i<n;i++){
				   int temp=Math.abs(a[i]-a[i-1]);
				   if(temp==min){
					   minCount++;
				   }else{
					   if(temp<min){
						   min=temp;
						   minCount=1;
					   }
				   }
			   }
			}else{
				for(Integer t:map.keySet()){
					if(map.get(t)>1){
						minCount+=((map.get(t)*(map.get(t)-1))/2);
					}
				}
			}
			//差最大的对数
			List<Integer> list=new ArrayList<Integer>(map.keySet());
			maxCount=map.get(list.get(0))*map.get(list.get(list.size()-1));
			System.out.println(minCount+" "+maxCount);
		}
		sc.close();
}
}
