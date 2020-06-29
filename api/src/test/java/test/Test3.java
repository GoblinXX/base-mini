package test;

import org.assertj.core.util.Lists;

import java.util.List;

/** @Author: Goblin @Description: @Date: Create in 下午5:38 20-6-28 */
public class Test3 {
  /**
   * 给定一组无重复元素的整数数组nums和一个目标数target,找出nums中所有数字和等于target的组合。nums中的元素可以无限选择 说明：所有数字包括target都是正整数
   * 解集不能包含重复子集 实例：
   * 输入 nums = [2,3,5] target = 8
   * 输出 [[2, 2, 2, 2], [2, 3, 3], [3, 5]]
   */
  private static List<List<Integer>> list = Lists.newArrayList();

  private static List<List<Integer>> combinationSum(int[] nums, int target) {
    if (nums.length == 0 || target < 0) {
      return Lists.emptyList();
    }
    backTrace(0, target, nums, Lists.newArrayList());
    return list;
  }

  private static void backTrace(int start, int target, int[] nums, List<Integer> temp) {
    if (target < 0) {
      return;
    }
    if (target == 0) {
      list.add(Lists.newArrayList(temp));
    }
    for (int i = start; i < nums.length; i++) {
      temp.add(nums[i]);
      // 这里从i开始而不是i+1是因为元素可以重复选择
      backTrace(i, target - nums[i], nums, temp);
      temp.remove(temp.size() - 1);
    }
  }

  public static void main(String[] args) {
    List<List<Integer>> subsets = combinationSum(new int[] {2, 3, 5}, 8);
    System.out.println("集合输出为：" + subsets);
  }
}
