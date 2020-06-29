package test;

import org.assertj.core.util.Lists;

import java.util.List;

/**
 * @Author: Goblin
 * @Description:
 * @Date: Create in 下午2:03 20-6-29
 */
public class Test5 {
    /**
     * 给定一个没有重复数字的序列，返回其所有可能的全排列
     *
     * 实例:
     * 输入： [1,2,3]
     * 输出： [[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1]]
     */

    private static List<List<Integer>> list = Lists.newArrayList();

    public static List<List<Integer>> permute(int[] nums){
        if(nums.length == 0){
            return Lists.emptyList();
        }
        backTrace(nums,Lists.newArrayList());

        return list;
    }

    public static void backTrace(int[] nums, List<Integer> temp){
        if(temp.size() == nums.length){
            list.add(Lists.newArrayList(temp));
            return;
        }
        for (int i=0;i<nums.length;i++){
            if(temp.contains(nums[i])){
                continue;
            }
            temp.add(nums[i]);
            backTrace(nums,temp);
            temp.remove(temp.size() - 1);
        }
    }

    public static void main(String[] args){
      int[] subsets = new int[]{1,2,3};
      permute(subsets);
      System.out.println("subsets is " + list);
    }
}
