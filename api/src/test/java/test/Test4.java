package test;

import org.assertj.core.util.Lists;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: Goblin
 * @Description:
 * @Date: Create in 上午10:33 20-6-29
 */
public class Test4 {
    /**
     * 给定一组可能重复元素的整数数组nums和一个目标数target,找出nums中所有数字和等于target的组合。nums中的元素在每个子集中只能使用一次
     * 说明：所有数字包括target都是正整数
     *      解集不能包含重复子集
     * 实例：
     * 输入 nums = [10,1,2,7,6,1,5]  target = 8
     * 输出 [[1, 1, 6], [1, 2, 5], [1, 7], [2, 6]]
     *
     * 输入 nums = [2,5,2,1,2] target = 5
     * 输出 [[1, 2, 2], [5]]
     */
    private static List<List<Integer>> list = Lists.newArrayList();

    private static List<List<Integer>> combinationSum(int[] nums, int target){
        if(nums.length == 0 || target < 0){
            return Lists.emptyList();
        }
        //因为每个元素只能使用一次，所有先去重
        Arrays.sort(nums);
        backTrace(0,target,nums,Lists.newArrayList());
        return list;
    }
    private static void backTrace(int start,int target,int[] nums ,List<Integer> temp){
        if(target == 0){
            list.add(Lists.newArrayList(temp));
        }
        if(target < 0){
            return;
        }
        for (int i=start;i<nums.length;i++){
            //剪枝：保证同一层中只有1个相同的元素，不同层可以有重复元素
            if(i > start && nums[i] == nums[i-1]){
                continue;
            }
            temp.add(nums[i]);
            backTrace(i+1,target-nums[i],nums,temp);
            temp.remove(temp.size() - 1);
        }

    }

    public static void main(String[] args) {
        List<List<Integer>> subsets = combinationSum(new int[]{10,1,2,7,6,1,5},8);
        System.out.println("集合输出为：" + subsets);
    }
}
