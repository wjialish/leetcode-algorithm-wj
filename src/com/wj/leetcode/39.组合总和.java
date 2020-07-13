import java.util.ArrayList;
import java.util.List;

/*
 * @lc app=leetcode.cn id=39 lang=java
 *
 * [39] 组合总和
 */

// @lc code=start
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if(candidates.length == 0 ) return null; 
        List<Integer> list = new ArrayList<Integer>();
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        
        backtracking(candidates,target,list,res,0);

        return res;
    
    }

    public void backtracking(int[] candidates,int target,List<Integer> list, List<List<Integer>> res, int start){
        // if(target<0){
        //     return;
        // }
        
        if(target == 0){
            res.add(new ArrayList<>(list));
            return;
        }

        for(int i = start; i< candidates.length;i++){
            if(target-candidates[i]>=0){
                list.add(candidates[i]);
                backtracking(candidates, target-candidates[i], list, res, i);
                list.remove(list.get(list.size()-1));
            }
            
        }


    }
}
// @lc code=end

