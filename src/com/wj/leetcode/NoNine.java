package com.wj.leetcode;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NoNine {

	
	
	
	/*
	 * 1. it is a natural number
	 * 2. does not contain 9
	 * 3. can't be divided by 9/not divisible by 9
	 */
	
	/*
	 * input:
	 * 2
	 * 16 26
	 * 88 102
	 */
	
	
	/*
	 * output
	 * case#1:9
	 * case#2:4
	 */
	public static void main(String[] args) {
		
		Scanner scanner=new Scanner(System.in);
		
		while(scanner.hasNext()) {
			//int totalSannerLineNum=scanner.nextInt();
			
			//int[totalSannerLineNum][2] arr=new int[totalSannerLineNum][2];
			int start=scanner.nextInt();
			int end=scanner.nextInt();
			int output=end-start+1;
			int num=start+1;
			while(num<end) {
				int remainder = num % 9;
				if(remainder==0) {
					output--;
					num++;
					continue;
				}
				
				String numString=Integer.toString(num);
				Pattern pattern=Pattern.compile(".*[9]+.*");
				Matcher matcher=pattern.matcher(numString);
				if(matcher.matches()) {
					output--;
				}
				
				num++;
			}
			
			System.out.println("case#"+output);
			
			
			
		}
		scanner.close();
		
	
		
		
	}
	
	
	
	/*
	 * judge whether the num contains 9
	 */
	public boolean ifContainNine(int num) {
		String numString =Integer.toString(num);
		if(numString.contains("9")) {
			return true;
		}
		return false;
	}
	
	
	/*
	 * use reg judge whether the num contains 9
	 */
	public boolean ifContainMineUseReg(int num) {
		String numString=Integer.toString(num);
		/*
		 * . 匹配除“\r\n”之外的任何单个字符。要匹配包括“\r\n”在内的任何字符，请使用像“[\s\S]”的模式
		 * 
		 * * 匹配前面的子表达式任意次。例如，zo*能匹配“z”，“zo”以及“zoo”。*等价于{0,}。
		 */
		Pattern pattern=Pattern.compile(".*[9]+.*");
		Matcher matcher=pattern.matcher(numString);
		return matcher.matches();
	}
	
}
