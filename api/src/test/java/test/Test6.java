package test;

import org.assertj.core.util.Lists;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: Goblin
 * @Description:
 * @Date: Create in 下午2:20 20-6-29
 */
public class Test6 {
    /**
     * 给定一个可包含重复数字的序列，返回其所有不重复的全排列
     *
     * 实例:
     * 输入： [1,1,2]
     * 输出：[[1, 1, 2], [1, 2, 1], [2, 1, 1]]
     */

    private static List<List<Integer>> list = Lists.newArrayList();

    public static List<List<Integer>> findOnly(int[] nums){
        if(nums.length == 0){
            return Lists.emptyList();
        }
        Arrays.sort(nums);
        backTrace(new boolean[nums.length],Lists.newArrayList(),nums);
        return list;
    }

    public static void backTrace(boolean[] visited,List<Integer> temp,int[] nums){
       //结束条件
        if(temp.size() == nums.length){
            list.add(Lists.newArrayList(temp));
            return;
        }
        for (int i=0;i<nums.length;i++){
            //已经选择过的不需要再放进去
            if(visited[i])continue;
            //去重
            if(i>0 && nums[i] == nums[i-1] && visited[i-1])break;
            temp.add(nums[i]);
            visited[i] = true;

            backTrace(visited,temp,nums);
            temp.remove(temp.size() - 1);
            visited[i] = false;
        }
    }

    public static void main(String[] args){
        int[] subsets = new int[]{1,1,2};
        findOnly(subsets);
        System.out.println("subsets is " + list);
    }
}
