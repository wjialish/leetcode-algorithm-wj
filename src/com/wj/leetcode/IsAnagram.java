package com.wj.leetcode;

import java.util.HashMap;
import java.util.Map;

public class IsAnagram {

	 public static boolean isAnagram(String s, String t) {
	       /** 1)数组
		    # 1. 把26个英文字母放到一个数组里，还是放到一个map集合里？
	        # 第一感觉map集合更好一点，两种都试下
	        * 1）数组
	        * 2）map
	        # 2. 怎么把26个字母放进去，一个一个手动放吗？
	        不需要放字母，可以把字母转化为对应的ACICC--题目中说明了这里只包含小写字母
	        */
	        int[] arr = new int[26];
	        // s.toCharArray() 将一个字符串拆分为每个字符
	        /*
	         * java中将字符与ASCII码互转方法,直接强制将char转成int就行
	         * char c = 'a';  int a = (int)(c);
	         * int 转成char
	         * char c2= (char)(97);
	         */
	        for(char c: s.toCharArray()) {
//	        	int tmp = (int)(c) - 97;
//	        	arr[tmp]++;
	        	arr[c-'a']++;
	        }
	        for(char c: t.toCharArray()) {
//	        	int tmp = (int)(c) - 97;
//	        	arr[tmp]--;
	        	arr[c-'a']--;
	        }
	        /*
	         * 最后判断数组中所有值都为0，除了遍历一遍，还有没有更好的方法？
	         */
	        for(int i=0;i<arr.length;i++) {
	        	if(arr[i] != 0) {
	        		return false;
	        	}
	        }        
	        return true;
	    }
	 
	
	 public static boolean isAnagram2(String s, String t) {
	       // 2）map
		    
	        Map<Character,Integer> map = new HashMap<Character,Integer>();
	        
	        for(char c: s.toCharArray()) {
	        	map.put(c, map.getOrDefault(c, 0) + 1);
	        }
	        
	        // 这里移除的时候不是简单的减1就可以了，需要特别注意一下
	        for(char c: t.toCharArray()) {
	        	Integer count = map.get(c);
	        	// 如果map里没有这个值
	        	if(count == null) {
	        		return false;
	        	}else if(count>1) {
	        		map.put(c, map.get(c)-1);
	        	}else {
	        		map.remove(c);
	        	}
	        }
	        
	        return map.isEmpty();
	
	    }
	 
	 
	 
	 
	 public static void main(String[] args) {
	       boolean b = isAnagram("liwenjia", "wenjialiww");
	       System.out.println(b);
	}
	
}
