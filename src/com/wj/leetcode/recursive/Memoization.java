package com.wj.leetcode.recursive;

import java.util.HashMap;

/*
递归实现的基础上实现记忆化

*/
public class Memoization {
    
    //1. 首先定义一个hash表cache，用来保存我们的记忆结果
    static HashMap<Integer,Integer> cache;


    public int f(int[] nums,int n){
        //2.在每次调用递归函数的时候，判断一下是否已经计算过了，也就是cache里是否已经保存了这个值
        //是的化，立即返回
        if(cache.containsKey(n)){
            return cache.get(n);
        }
        //不是的话，继续递归调用
        if(n<=1){
            return n;
        }

        int result = 0;
        int maxEndingHere = 1;

        for(int i=1;i<n;i++){
           // ...
        }

        //在返回结果前，保存到cache里
        //这样下次遇到同样输入时，就不用再浪费时间计算了
        cache.put(n, maxEndingHere);

        return maxEndingHere;
    }


}