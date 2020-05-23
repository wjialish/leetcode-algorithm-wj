package com.wj.leetcode.backtracking;

import java.awt.image.RescaleOp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RemoveInValidParentheses_301 {

	/*
	 * Remove the minimum number of invalid parentheses(括弧) in order to make the input string valid. Return all possible results.

Note: The input string may contain letters other than the parentheses ( and ).

Example 1:

Input: "()())()"
Output: ["()()()", "(())()"]
Example 2:

Input: "(a)())()"
Output: ["(a)()()", "(a())()"]
Example 3:

Input: ")("
Output: [""]


	 */
	
	
	public static void main(String[] args) {
		RemoveInValidParentheses_301 rp = new RemoveInValidParentheses_301();
		List<String> list = rp.removeInvalidParentheses("()())()");
		for(String s :list) {
			System.out.println(s);
		}
	}
	
	/*
	 * 思考：从左到右遍历，右括号比左括号多的话 就是无效的 应该删掉
	 *      左右括号数量对比，数量多的就是应该删掉的
	 *      数量相同的时候，应该是先左括号 再右括号才是对的
	 *      
	 *      
	 * 思考：如何找出给定表达式中放错的左括号和右括号的数目？
	 *      1. 我们从左边开始，一次处理一个括号的表达式
	 *      2. 假设我们遇到的是左括号（ , 它可能会导致 可能不会导致一个无效的表达式。因为表达式的其余部分的某个地方可能有一个匹配的右括号。
	 *         这时，只需要用计数器来增加左括号的出现次数。即left += 1
	 *      3. 假设我们遇到的是右括号），这里有两种含义：
	 *         1）要不这个右括号没有匹配的左括号，在这种情况下，这种表达式是无效的，即left = 0,
	 *            这种情况下，我们需要增加一个计数器right += 1来表示放错了位置的右括号。
	 *         2）或者有一些未匹配的左括号来匹配右括号。即left>0 , 在这种情况下，我们只需要减少我们的left计数器,即left -= 1
	 *      4. 继续处理字符串，直到所有字符串处理完成。
	 *      5. 左括号和右括号的值 left 和 right 分别告诉我们不匹配的 ( 和 ）的值。
	 *      
	 * 算法：
	 *    递归的状态有五个不同的变量定义
	 *    index 它表示我们必须在原始字符串中处理当前的字符
	 *    left_count 它表示添加到我们正在构建的表达式中的左括号
	 *    right_count 它表示添加到我们正在构建的表达式的右括号
	 *    left_remain 要删除的左括号数
	 *    right_remain 表示保留要删除的右括号数，总的来世，为了使最终表达式有效。left_remian = right_remian == 0     
	 *      
	 */
	
	
	
	
	
	
	/*
	 * 思考：如何找出给定表达式中放错的左括号和右括号的数目？
	 * 1. 遍历字符串
	 * 2. 如果遇到的是左括号，无法判断是有效或者无效的表达式，所以需要left++;
	 * 3. 如果遇到的是右括号 有两种情况：
	 *    1）没有匹配的左括号，那么该右括号肯定是无效的，记录right++
	 *    2) 找到了匹配的左括号,那么left--;
	 * 4  继续遍历循环，直至整个字符串循环完毕
	 * 5. 最后记录leftRem和rightRem的值
	 */

	
	
	private Set<String> validParentheses = new HashSet<String>();
	
	public List<String> removeInvalidParentheses(String s) {

		int left = 0; 
		int right =0;
		
		//First，we find out the number of misplaced left and right parentheses
		for(int i =0; i<s.length();i++) {
			//simply record the left one
			if(s.charAt(i) == '(') {
				left++;
			}else if(s.charAt(i) == ')') {
				//if we don't have a matching left,then this is a misplaced right,recoed it.
				//right = left ==  0 ? right++ : right; //right++是不对的
				right = left == 0? right+1 : right;
				
				//Decrement count of left parentheses because we have found a right
				//left = left > 0 ? left-- : left;  // left--是不对的
				left = left > 0 ? left -1 : left;
			}
		}
		
		recurse(s, 0, 0, 0, left, right, new StringBuilder());
		
		return new ArrayList<String>(this.validParentheses);
    }
	

	
	
	private void recurse(String s,int index,int leftCount,int rightCount,int leftRem,int rightRem,StringBuilder expression) {
		//if we reached the end of the string,just check if the resulting expression is valid or not
		//and also if we have removed the total number if left and right parentheses that we should removed
		if(index == s.length()) {
			if(leftRem == 0 && rightRem == 0) {
				this.validParentheses.add(expression.toString());
			}
		}else {
			char character = s.charAt(index);
			int length = expression.length();
			
			//The discard case, note that here we have our pruning condition(prune 剪枝）
			//we won't recurse if the remaining count for that parentheses is == 0
			if((character == '(' && leftRem > 0) || (character == ')' && rightRem > 0)) {
				//？？？can't understand -- why character == '(' ,the leftRem--
				recurse(s, index+1, leftCount, rightCount, leftRem - (character == '(' ? 1 : 0), rightRem - (character ==')' ? 1 : 0), expression);
			}
			
			expression.append(character);
			
			//Simply recurse one step further  if the current character is not a parentheses.
			if(character != '(' && character!= ')') {
				recurse(s, index+1, leftCount, rightCount, leftRem, rightRem, expression);
			}else if(character == '(') {
				//consider an opening bracket
				recurse(s, index+1, leftCount+1, rightCount, leftRem, rightRem, expression);
			}else if((character == ')') && (rightCount < leftCount)) {
				recurse(s, index+1, leftCount, rightCount+1, leftRem, rightRem, expression);
			}
			
			//delete for bracket
			expression.deleteCharAt(length);
		}
		
	}
	

	
	


	
}
