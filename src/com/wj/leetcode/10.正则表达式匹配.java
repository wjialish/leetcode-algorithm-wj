/*
 * @lc app=leetcode.cn id=10 lang=java
 *
 * [10] 正则表达式匹配
 */

// @lc code=start
class Solution {
    // public static void main(String[] args) {
    //     String s = "aa";
    //     String p ="a";
    //     System.out.println(isMatch(s, p));
    // }


    /**
     * 递归算法会造成很多重叠的计算
     * 也可以采用动态规划，自底向上
     * 时间空间复杂度都是0(n^2)
     */
    public boolean isMatch(String s,String p){

        int m = s.length();
        int n = p.length();

        //定义一个二维布尔矩阵dp
        boolean[][] dp = new boolean[m+1][n+1];

        //初始化dp[0][0]等于true时，表示当两字符串长度都为0时，也就是空字符串时，他们互相匹配
        dp[0][0] = true;

        /**
         * 初始化二维矩阵中所有的值
         * 当s为空字符串时，对p字符串的任一位置，要使得这个位置的子串能和空字符串匹配，
         * 要求这个子串必须由一系列的星号组合构成
         */
        for(int j=1;j<=n;j++){
            /**
             * dp[0][j] 表示s为空字符串，p为任意长度字符串
             * dp[0][1] 表示s为空字符串,长度为0，p长度为1，除非p.charAt(1)=='*',否则dp[0][1]一定等于false
             */
            dp[0][j] = j>1 && p.charAt(j-1) == '*' && dp[0][j-2];
        }

       //接下来对二维矩阵填表，用到的逻辑和递归一样
       for(int i=1;i<=m;i++){
           for(int j=1;j<=n;j++){
               /**
                * p当前字符不是*号，判断两字符是否相等，如果相等，则看dp[i-1][j-1]的值，
                * 因为dp[i-1][j-1] 保存了前一个匹配的结果
                */
               if(p.charAt(j-1)!='*'){
                   dp[i][j] = isMatch(s.charAt(i-1),p.charAt(j-1)) && dp[i-1][j-1];
               }else{
                   /**
                    * 当p的当前字符是星号时，进行两种尝试
                    * 1. 用星号组合表示空字符串时，看看是否匹配 dp[i][j-2]
                    * 2. 用星号组合表示一个子串时，看看是否匹配 dp[i-1][j]
                    *      比如当s="aa" p="a*" 当*匹配一个字符时，即s="a",p="a*" 即dp[i-1][j]
                    */
                   dp[i][j] = dp[i][j-2] || dp[i-1][j] && isMatch(s.charAt(i-1), p.charAt(j-2));
               }

           }
       }
       
       return dp[m][n];

    }

    private boolean isMatch(char a, char b){
        return a==b || b=='.';
    }







     /**
     * 递归
     * 从前往后进行递归调用
     */
    // public static boolean isMatch(String s, String p) {

    //     /**
    //      * *匹配符它表示
    //      * 它匹配的是p字符串中 该*前面的那个字符
    //      * 它可以匹配零个或多个
    //      * * 匹配符前面必须有一个非*的字符
    //      * 在分析*匹配符的时候一定要把*和它前面的一个字符合在一起来看，而不能把*作为单独的一个字符来看
    //      * 
    //      * .* 可以表示空串
    //      *    可以表示一个.
    //      *    可以表示一个..
    //      *    可以表示一个...无数个.
    //      *    而.又可以匹配任意字符，所以.*就可以匹配任意字符
    //      */ 


    //      if(s==null || p==null){
    //         return false;
    //      }

    //      //调用递归函数，指针i和j都指向0的为止
    //      return isMatch(s,0,p,0);
    // }

    // private static boolean isMatch(String s,int i,String p, int j){
    //     int m = s.length();
    //     int n = p.length();

    //     /**
    //      * 考虑递归函数的结束时间：
    //      * 当j指针遍历完p字符串后,即可跳出递归
    //      * 而当i指针也刚好遍历完，说明s和p安全匹配
    //      */
    //     if(j==n){
    //         return i==m;
    //     }

    //     /**
    //      * j指针的下一个是否为*?
    //      * 如果不是*号，则递归地调用isMatch函数
    //      */
    //     if(j==n-1 || p.charAt(j+1) != '*'){
    //         return (i<m) && (p.charAt(j) == s.charAt(i) || p.charAt(j) == '.')
    //                && (isMatch(s,i+1,p,j+1));
    //     }

    //     /**
    //      * 如果j指向的下一个字符为*号，则不断地将它和*作为一个整体
    //      * *分别表示空字符串，一个j指向的字符，两个字符，以此类推。
    //      * 如果其中一种情况能出现s和p匹配，则返回true
    //      */
    //     if(j<n-1 && p.charAt(j+1)=='*'){
    //         /**
    //          * while循环，整个算法的核心
    //          *  ---i指向的字符必须要能和j指向的字符匹配，其中j指向的有可能是点匹配符
    //          *  ---如果无法匹配，则i++，表示*组合去匹配更长的一段字符串
    //          */
    //         while((i<m) && (p.charAt(j)==s.charAt(i) || p.charAt(j)=='.')){
    //             if(isMatch(s,i,p,j+2)){
    //                 return true;
    //             }
    //             i++;
    //         }
            
    //     }


    //     /**
    //      * 当i与j指向的字符不相同，或i已经遍历完s字符串
    //      * 同时j指向的字符后跟着一个*号的情况
    //      * 我们只能用*去匹配一个空字符串，然后递归下去
    //      */
    //     return isMatch(s,i,p,j+2);

    // }




    /**
     * 递归
     * 从后往前进行递归调用
     */
    // public static boolean isMatch(String s, String p) {

    //     /**
    //      * *匹配符它表示
    //      * 它匹配的是p字符串中 该*前面的那个字符
    //      * 它可以匹配零个或多个
    //      * * 匹配符前面必须有一个非*的字符
    //      * 在分析*匹配符的时候一定要把*和它前面的一个字符合在一起来看，而不能把*作为单独的一个字符来看
    //      * 
    //      * .* 可以表示空串
    //      *    可以表示一个.
    //      *    可以表示一个..
    //      *    可以表示一个...无数个.
    //      *    而.又可以匹配任意字符，所以.*就可以匹配任意字符
    //      */ 


    //      if(s==null || p==null){
    //         return false;
    //      }

    //      //调用递归函数，指针i和j都指向最后一个元素
    //      return isMatch(s,s.length(),p,p.length());
    // }

    // private static boolean isMatch(String s,int i,String p, int j){
   
    //     //p字符串为空，s也为空，表示匹配
    //     if(j==0) return i==0;

    //     //p不为空，s为空
    //     //当s为空的时候，p为a*,只要p总是由*组合构成，则一定匹配，否则不行
    //     if(i==0){
    //         return j>1 && p.charAt(j-1)=='*' && isMatch(s,i,p,j-2);
    //     }

    //     /**
    //      * 当p的当前字符不是星号时，判断当前字符是否相等，如果相等，则递归地看前面的字符 */
    //     if(p.charAt(j-1)!='*'){
    //         return (isMatch(s.charAt(i-1), p.charAt(j-1))) && isMatch(s, i-1, p, j-1);
    //     }else{
    //         /**
    //          * 否则，如果p的当前字符时星号，则进行两种尝试
    //          * 1. 用星号组合表示空字符串，看看是否能匹配
    //          * 2. 用星号组合表示一个字符，看看时都能匹配
    //          */
    //         return isMatch(s, i, p, j-2) || 
    //               (isMatch(s, i-1, p, j) && isMatch(s.charAt(i-1), p.charAt(j-2)));
    //     } 


    // }

    // private static boolean isMatch(char a,char b){
    //     return a == b || b =='.';
    // }
}
// @lc code=end

