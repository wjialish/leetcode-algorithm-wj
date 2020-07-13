package com.wj.leetcode.backtracking;

public class BacktrackingTemplate {

    public void backtracking(){
        

        //1. 判断边界条件，输入输出是否合法（完整性检查）非法立即返回
        if(input/state is valid){
            return;
        } 

        //2. 判断递归结束条件,如果满足，就将当前结果保存起来，并返回
        if(match condition){
            return some value;
        }


        //3. 遍历所有可能出现的情况，并进行递归
        for(all possible)

        //4。 尝试下一步的可能

        //5。递归完成后，立即回溯到上一步，回溯的方法就是取消前一步进行的尝试

    }



    
}