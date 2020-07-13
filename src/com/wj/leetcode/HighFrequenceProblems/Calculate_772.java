package com.wj.leetcode.HighFrequenceProblems;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Calculate_772 {

    public int calculate(String s){
        Queue<Character> queue = new LinkedList<>();
        for(char c : s.toCharArray()){
             queue.offer(c);
        }
        queue.offer('+');

        //主函数中调用递归函数
        return calculate(queue);
    }

    public int calculate(Queue<Character> queue){

        //记录符号
        char sign = '+';

        int num = 0;

        Stack<Integer> stack = new Stack<>();
        
        while(!queue.isEmpty()){
            char c = queue.poll();

            if(Character.isDigit(c)){
                num = 10 * num + c - '0';
            }else if(c == '('){
                //当遇到一个左括号，就开始递归的调用
                //求的括号中的计算结果，将它赋值给当前的num
                num = calculate(queue);
            }else{
                if(sign == '+'){
                    stack.push(num);
                }else if(sign == '-'){
                    stack.push(-num);
                }else if(sign == '*'){
                    stack.push(stack.pop()*num);
                }else if(sign == '/'){
                    stack.add(stack.pop()/num);
                }

                num=0;
                sign = c;

                //当遇到了右括号，就可以结束循环，直接返回当前的总和
                if(c == ')'){
                    break;
                }
            }
        }

        int sum = 0;

        while(!stack.isEmpty()){
            sum += stack.pop();
        }

        return sum;

    }
    
}