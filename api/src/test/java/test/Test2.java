package test;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/** @Author: Goblin @Description: 关于集合的回溯算法问题 @Date: Create in 下午3:01 20-6-28 */
@Slf4j
public class Test2 {
    /**
     * 给定一组可能包含重复元素的整数数组nums,返回该数组所有可能的子集
     * 说明：解集不能包含重复的子集
     * 实例：
     * 输入 nums = [1,2,2]
     * 输出 [[], [1], [1, 2], [1, 2, 2], [2], [2, 2]]
     */
  private static List<List<Integer>> list = new ArrayList<>();
  private static Set<List<Integer>> set = new HashSet<>();

  public static List<List<Integer>> subsets(int[] nums) {
    if(nums.length == 0){
      return list;
    }
    //这步排序是为了避免元素一样但顺序不一样的相同子集，例如[1,2]和[2,1]
    Arrays.sort(nums);
    //backTrace1(0,nums, Lists.newArrayList());
    backTrace2(0,nums, Lists.newArrayList());
    return list;
  }

  /**
   * 模式一:利用set去重
   * @param start
   * @param nums
   * @param temp
   */
  public static void backTrace1(int start, int[] nums, List<Integer> temp) {
    if(!set.contains(temp)){
      list.add(Lists.newArrayList(temp));
      set.add(Lists.newArrayList(temp));
    }
    //从start开始为了避免重复
    for(int i = start; i < nums.length;i++){
      temp.add(nums[i]);
      backTrace1(i+1,nums,temp);
      temp.remove(temp.size() - 1);
    }
  }

  /**
   * 模式二：剪枝策略模式（同层只保留一个相同的元素。后面的元素全部减掉）
   * @param start
   * @param nums
   * @param temp
   */
  public static void backTrace2(int start, int[] nums, List<Integer> temp){
    list.add(new ArrayList<>(temp));

    for(int i = start; i < nums.length; i++){
      //剪枝策略
      if(i > start && nums[i] == nums[i-1]){
        continue;
      }
      temp.add(nums[i]);
      backTrace2(i+1,nums,temp);
      temp.remove(temp.size()-1);
    }
  }


  public static void main(String[] args) {
    List<List<Integer>> subsets = subsets(new int[]{1,2,2});
    System.out.println("集合输出为：" + subsets);
  }
}
