/*
 * @lc app=leetcode.cn id=518 lang=java
 *
 * [518] 零钱兑换 II
 */

// @lc code=start
class Solution {

    public int change(int amount,int[] coins){
        if(coins.length == 0 && amount == 0){
            return 1;
        }
        
        if(amount < 0 || coins == null || coins.length == 0){
            return 0;
        }

        /*
        dp[i][j]的含义是使用coins[0...i]货币的情况下，组成钱数为j有多少种方法
        */
        int[][] dp = new int[coins.length][amount+1];

        /*
        对于矩阵dp的第一列值dp[..][0]，表示组成钱数为0的方法数，很明显是一种，也就是不使用任何一种货币。
        所以dp第一列的值统一设为1
        */
        for(int i = 0; i<coins.length;i++){
            dp[i][0] = 1;
        }

        /*
        对于矩阵dp第一行的值dp[0][..]  表示只能使用coins[0]这一种货币的情况下，组成钱的方法数
        */
        for(int j = 1;coins[0] * j <= amount;j++){
            dp[0][coins[0] * j] = 1;
        }

        /**
         * 除了第一行和第一列的其他位置，记为dp[i][j]
         * dp[i][j]的值是以下几个值的累计
         * 1. 完全不使用coins[i]的货币，只使用coins[0..i-1]货币时，方法数为 dp[i-1][j]
         * 2. 使用1张coins[i]的货币，剩下的钱用coins[0..i-1]组成时，方法数为 dp[i-1][j-coins[i]]
         * 3. 使用2张coins[i]的货币，剩下的钱用coins[0..i-1]组成时，方法数为 dp[i-1][j-2*coins[i]]
         * ...
         * k. 使用k张coins[i]的货币，剩下的钱用coins[0..i-1]组成时，方法数为 dp[i-1][j-k*coins[i]]
         */
        int num = 0;
        for(int i = 1;i<coins.length;i++){
            for(int j = 1; j<=amount;j++){
                num = 0;
                for(int k = 0; j - k * coins[i] >= 0;k++){
                    num += dp[i-1][j-k*coins[i]];
                }
                dp[i][j] = num;
            }
        }
        return dp[coins.length-1][amount];
    }

    //记忆搜索的方法
    // public int change(int amount, int[] coins) {

    //     if(coins.length == 0 && amount == 0){
    //         return 1;
    //     }

    //     if(coins == null || coins.length == 0 || amount<0){
    //         return 0;
    //     }

    //     /*map[i][j] 表示递归过程p[i][j]的返回值
    //       map[i][j]== 0  表示递归过程p[i][j]从来没有计算过
    //       map[i][j]== -1  表示递归过程p[i][j]计算过，但返回值为0
    //       如果map[i][j] 的值，既不等于 -1   也不等于 0 , 记为a
    //     */
    //     int[][] map = new int[coins.length+1][amount+1];
    //     return process(amount, coins, 0, map);

    // }


    // public int process(int amount, int[] coins, int index,int[][] map){

    //     int res = 0;

    //     if(index == coins.length){
    //         res = amount == 0?1:0;
    //     }else{
    //         int mapValue = 0;
    //         for(int i = 0;coins[index] * i <= amount;i++ ){
    //             mapValue = map[index+1][amount - coins[index] * i];
    //             if(mapValue != 0){
    //                 //如果这个递归过程已经计算过，就把值直接拿出来用
    //                 res += mapValue == -1 ? 0 : mapValue;
    //             }else{
    //                 res += process(amount - coins[index]*i, coins, index+1,map);
    //             }
                
    //         }
    //     }

    //     map[index][amount] = res == 0 ? -1 :  res;
    //     return res;

    // }



    //暴力搜索
    // public int change(int amount, int[] coins) {

    //     if(coins.length == 0 && amount == 0){
    //         return 1;
    //     }

    //     if(coins == null || coins.length == 0 || amount<0){
    //         return 0;
    //     }

    //     return process(amount, coins, 0);

    // }


    // public int process(int amount, int[] coins, int index){

    //     int res = 0;

    //     if(index == coins.length){
    //         res = amount == 0?1:0;
    //     }else{
    //         for(int i = 0;coins[index] * i <= amount;i++ ){
    //             res += process(amount - coins[index]*i, coins, index+1);
    //         }
    //     }

    //     return res;

    // }
}
// @lc code=end

