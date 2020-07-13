package com.wj.leetcode.HighFrequenceProblems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class AlienOrder_269 {

    /**
     * 题目：火星字典
     * https://kaiwu.lagou.com/course/courseInfo.htm?courseId=3#/detail/pc?id=37
     * 用到拓扑排序的思想
     * 
     * 我们所要做的就是把每个字母看成是图里面的顶点，他们之间的关系就好比是连接顶点和顶点之间的边
     * 而且是有向边，这个图就是个有向图，对这个图进行拓扑排序就可以得到最终结果
     * 
     * 
     */

     public String alienOrder(String[] words){
         //基本处理
         if(words==null || words.length==0){
             return null;
         }

         if(words.length==1){
             return words[0];
         }

         //构建有向图：定义邻接链表adjList, 表示有向图 也可以使用邻接矩阵
         Map<Character,List<Character>> adjList = new HashMap<>();


         for(int i=0;i<words.length-1;i++){

            //两两比较字符串
            String w1 = words[i];
            String w2 = words[i+1];

            int n1 = w1.length();
            int n2 = w2.length();

            boolean found = false;

            //对每个出现的字母都创建一个图中的节点
            for(int j=0;j<Math.max(n1, n2);j++){
                Character c1 = j<n1?w1.charAt(j):null;
                Character c2 = j<n2?w2.charAt(j):null;

                //一旦发现两个字母不通，就连接两个顶点
                /**
                 * 上面定义一个found变量
                 * 表明一旦出现不同字母，只需处理好这两个字母，或顶点的关系，之后的字母不需要考虑
                 */
                if(c1!=null && !adjList.containsKey(c1)){
                    adjList.put(c1, new ArrayList<Character>());
                }

                if(c2!=null && !adjList.containsKey(c2)){
                    adjList.put(c2, new ArrayList<Character>());
                }

                if(c1 != null && c2 != null && c1 != c2 && !found){
                    adjList.get(c1).add(c2);
                    found=true;
                }
            }
         }

        /**
         * 接下来就是经典的拓扑排序
         * 进行拓扑排序时：
         * 我们需要一个堆栈stack来记录顶点的顺序
         * 最后将stack倒过来输出，即为最终结果
         */

        Set<Character> visited = new HashSet<>();
        Set<Character> loop = new HashSet<>();
        Stack<Character> stack = new Stack<Character>();

        for(Character key: adjList.keySet()){
            if(!visited.contains(key)){
                if(!topologicalSort(adjList,key,visited,loop,stack)){
                    return "";
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        while(!stack.isEmpty()){
            sb.append(stack.pop());
        }


        return sb.toString();


     }


     /**
      *  使用深度优先的算法进行拓扑排序:
          ---visited 集合：用来记录已访问过的顶点
          ---stack 堆栈： 
            当从某顶点触发，访问完其他所有顶点，最后才把当前顶点加入到堆栈
            即要把该点加入到stack里，必须先把其他和他有联系的顶点都处理完毕

        ---loop集合 有效防止有效图中出现环的情况
      */

     boolean topologicalSort(Map<Character,List<Character>> adjList,char u,Set<Character> visited,
                    Set<Character> loop,Stack<Character> stack){

        //将当前节点u加入到visited 集合以及loop集合中
        visited.add(u);
        loop.add(u);

        if(adjList.containsKey(u)){
            //逐个访问与顶点u相邻的其他顶点v
            for(int i=0;i<adjList.get(u).size();i++){
                char v = adjList.get(u).get(i);
                
                //如果在此轮访问过程中，v其实早就被访问过了
                //则此处有环出现，返回false 
                if(loop.contains(v)){
                    return false;
                }

                //如果顶点v还没有被访问过，就递归地访问它，如果在访问顶点v时发现了环，就返回false
                if(!visited.contains(v)){
                    if(!topologicalSort(adjList, u, visited, loop, stack)){
                        return false;
                    }
                }
            }
        }

        //当这一轮访问结束后，即从顶点u出发
        //访问完毕所有能访问的点，将u从loop集合中删除
        loop.remove(u);

        //把u加入到堆栈中
        stack.push(u);

        //返回true 表明此时访问没有环
        return true;
    
    }





    
}