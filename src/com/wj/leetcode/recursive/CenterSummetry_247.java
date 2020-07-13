package com.wj.leetcode.recursive;

import java.util.Arrays;
import java.util.List;

public class CenterSummetry_247 {

    public List<String> helper(int n,int m){
        //检查完整性
        if（n<0 || m< 0 || n < m）{
            throw new IllegalAccessException("invalid input!");
        }

        //终止条件
        if(n==0){
            return new ArrayList<String>(Arrays.asList(""));
        }
        if(n==1){
            return new ArrayList<String>(Arrays.asList("0","1","8"));
        }


        //缩小问题规模
        List<String> list = helper(n-2, m);


        //整合结果
        //在n-2的基础上添加11，69 96 88即可
        List<String> res = new ArrayList<String>();

        for(int i = 0; i<list.size();i++){
            String s = list.get(i);

            //如果当前处理的闺蜜长度不是最后所需要的规模长度的时候，我们也要考虑在两边添加 0 0 的情况
            if(n != m ){
                res.add("0","s","0");
            }

            res.add("1"+s+"1");
            res.add("6"+s+"9");
            res.add("9"+s+"6");
            res.add("8"+s+"8");



        }
    }
    
}