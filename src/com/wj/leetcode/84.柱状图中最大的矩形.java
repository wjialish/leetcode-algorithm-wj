import java.util.Stack;

/*
 * @lc app=leetcode.cn id=84 lang=java
 *
 * [84] 柱状图中最大的矩形
 */

// @lc code=start
class Solution {
    public int largestRectangleArea(int[] heights) {

        /**
         * 暴力法
         * 从左到右扫描输入的数组
         * 将每根柱子的高度作为当前矩形的高度
         * 矩形的宽度就从当前柱子出发一直延伸到左边和右边
         * 一旦遇到低于当前高度的柱子就停止
         * 计算面积，最后统计所有面积中的最大值
         * 
         */


        /**
         * 时间复杂度O(n)
         * 因为从头到尾扫描数组，
         * 每个元素被压入堆栈一次，弹出一次
         * 
         * 
         * 空间复杂度是 o(n)
         * 因为使用一个堆栈来保存各元素下标
         * 最坏情况是各高度按照从矮到高的顺序排序，我们需要将他们都压入堆栈
         *  */ 
        int n = heights.length;

        //初始化最大面积max=0
        int max = 0;

        Stack<Integer> stack = new Stack<Integer>();

        //从头开始扫描数组
        for(int i = 0;i<=n;i++){
            /**
             * 一旦发现当前高度比堆栈顶端所记录的高度要矮，即可开始对堆栈顶端记录的高度计算面积了
             * 此处巧妙处理了当i等于n的情况
             * 同时判断当前面积是否为最大值
             */
            while(!stack.isEmpty() && ( (i==n) || heights[i] < heights[stack.peek()] )){
                 int height = heights[stack.pop()];
                 int width = stack.isEmpty() ? i : i-1-stack.peek();

                 max = Math.max(max, width*height);
            }

            //如果当前的高度比堆栈顶端所记录的高度要高，就压入堆栈
            stack.push(i);
        }

        return max;
        



    }
}
// @lc code=end

