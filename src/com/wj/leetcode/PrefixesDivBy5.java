package com.wj.leetcode;

import java.util.ArrayList;
import java.util.List;

public class PrefixesDivBy5 {

	public static void main(String[] args) {
		int[] arr=new int[] {1,0,1,1,1,1,0,0,0,0,1,0,0,0,0,0,1,0,0,1,1,1,1,1,0,0,0,0,1,1,1,0,0,0,0,0,1,0,0,0,1,0,0,1,1,1,1,1,1,0,1,1,0,1,0,0,0,0,0,0,1,0,1,1,1,0,0,1,0};
		List<Boolean> list=prefixesDivBy52(arr);
		for(Boolean b:list) {
			System.out.println(b);
		}
	}
	
	/*
	 * Given an array A of 0s and 1s, consider N_i: the i-th subarray from A[0] to A[i] interpreted as a binary number (from most-significant-bit to least-significant-bit.)

Return a list of booleans answer, where answer[i] is true if and only if N_i is divisible by 5.

Example 1:

Input: [0,1,1]
Output: [true,false,false]
Explanation: 
The input numbers in binary are 0, 01, 011; which are 0, 1, and 3 in base-10.  Only the first number is divisible by 5, so answer[0] is true.

Example 2:

Input: [1,1,1]
Output: [false,false,false]

Example 3:

Input: [0,1,1,1,1,1]
Output: [true,false,false,false,true,false]

Example 4:

Input: [1,1,1,0,1]
Output: [false,false,false,false,false]

	 */
	
	/*
	 * 535的二进制1000010111，可以被5整除。
1：摘取535的偶数位置的二进制数：00111,奇数位置的数字10001
2：交替给予符号+-进行加和
偶数位置为+0-0+1-1+1 = 1
奇数位置为+1-0+0-0+1 = 2
3：计算奇数位置加和的2倍加上偶数位置的加和，2×2+1=5,这个结果可以被5整除，则原数535可以被5整除，否则就不能被5整除。

例如2的二进制数字是：10,偶数位置总和+1=1,奇数位置总和+0=0,偶数位置总和+奇数位置总和×2=1,不能被5整除，2也就不能被5整除。
例如15的二进制数字是：1111，偶数位置总和+1-1=0,奇数位置总和+1-1=0,偶数位置总和+奇数位置总和×2=0,能被5整除，15也就能被5整除。

原理其实也很简单，想想以下的式子：
2的0次方 = 1 = 1 mod 5
2的1次方 = 2 = 2 mod 5
2的2次方 = 4 = -1 mod 5
2的3次方 = 8 = -2 mod 5
2的4次方 = 16 = 1 mod 5 以后就开始循环了。
	 */
	
	
	
	/*
	 * Prerequisite

You need to know how to contruct a number with bits in some numeric representation.

For example:

    Contruct the number from a string in decimal like "12345678", key: num = num * 10 + (c - '0') where c is the current character.
    Contruct the number from a string in binary like "01010101", key: num = (num << 1) | (c - '0') where c is the current character.
    Or array format like [0, 1, 0, 1, 0, 1], key: num = (num << 1) | c where c is the current bit.

You should be already very familar with that.

Strategy

If you know above, then you can just contruct the number num and check if num % 5 = 0 and add it to result res.

But Trap:
In Java, an integer int is a 32-bit number, that's why it is in range of [-2^31, 2^31 - 1]. So if we use above way, then it can maximumly represent 32 bits in the array A. If beyond that, then overflow will happen, you may not get correct result.

So we need to use som Trick:
Consider the formula below which is the key to this problem:

(a * b + c) % d = ((a % d) * (b % d) + c % d) % d

Simply say is that we mod each part in a * b + c, then mod the result.

(I don't know where this math should be learnt from which course, I learnt from cryptography anyway. If you have know the source for helping ppl review, please leave a comment plz.)

So in this problem, num = (num << 1) + cur which can be written as num = num * 2 + (0 or 1). From above trick, we get num % 5 = (num % 5) * (2 % 5) + (0 or 1) % 5. Since 2, 0, 1 all smaller than 5, so they mod 5 do not cause any difference, we simplify the formula to => num % 5 = 2 * (num % 5) + (0 or 1).

From above we know that we can update num to num % 5 each time which then avoids overflow for us.
	 */
	
	public static List<Boolean> prefixesDivBy52(int[] A) {
        List<Boolean> resList=new ArrayList<Boolean>();
        int num=0;
        for(int i:A) {
        	//num=(num << 1 +i)%5;
        	num=(num*2+i)%5;
        	resList.add(num==0);
        }
        
        return resList;
    }
	
    public static List<Boolean> prefixesDivBy5(int[] A) {
        List<Boolean> resList=new ArrayList<Boolean>();
        
        for(int i=0;i<A.length;i++) {
        	long sum=0;
        	long current=0;
        	int j=i;
        	int position=0;
        	while(j>=0) {
        		current=(long)(A[j]* Math.pow(2, position));
        		j--;
        		position++;
        		sum+=current;
        	}
        	System.out.println(sum);
        	if(sum%5==0) {
        		resList.add(true);
        	}else {
        		resList.add(false);
        	}
        	
        }
        return resList;
    }
}
