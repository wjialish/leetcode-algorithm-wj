import java.util.List;

/*
 * @lc app=leetcode.cn id=1346 lang=java
 *
 * [1346] 检查整数及其两倍数是否存在
 */

// @lc code=start
class Solution {
    // public boolean checkIfExist(int[] arr) {

    //     List<Integer> list = new ArrayList<Integer>();
    //     int count = 0;
    //     for(int a :arr){
    //         if(a == 0){
    //            count += 1;
    //         }
    //         list.add(a);
    //     }

    //     if(count >= 2){
    //         return true;
    //     }

    //     for(int tt:list){
    //         if(list.contains(2 * tt) && tt != 0){
    //             return true;
    //         }
    //     }
    //     return false;
    // }



    public boolean checkIfExist(int[] arr) {

         for(int i = 0; i<arr.length; i++){
            for(int j = 0 ;j <arr.length; j++){
                if(arr[i] == 0 && arr[j] == 0 && i != j){
                    return true;
                }
                if(arr[i] == 2 * arr[j] && i != j){
                    return true;
                }
            }
        }
        return false;
        
    }
   
}
// @lc code=end

