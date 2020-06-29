package test;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/** @Author: Goblin @Description: 关于集合的回溯算法问题 @Date: Create in 下午3:01 20-6-28 */
@Slf4j
public class Test1 {
    /**
     * 给定一组不含重复元素的整数数组nums,返回该数组所有可能的子集
     * 说明：解集不能包含重复的子集
     * 实例：
     * 输入 nums = [1,2,3]
     * 输出 [[], [1], [1, 2], [1, 2, 3], [1, 3], [2], [2, 3], [3]]
     */
  private static List<List<Integer>> list = new ArrayList<>();

  public static List<List<Integer>> subsets(int[] nums) {
    if(nums.length == 0){
      return list;
    }
    backTrace(0,nums, Lists.newArrayList());
    return list;
  }

  public static void backTrace(int start, int[] nums, List<Integer> temp) {
    list.add(Lists.newArrayList(temp));
    //从start开始为了避免重复
    for(int i = start; i < nums.length;i++){
      temp.add(nums[i]);
      backTrace(i+1,nums,temp);
      temp.remove(temp.size() - 1);
    }
  }

  public static void main(String[] args) {
    LocalDateTime startTime = LocalDateTime.now();
    List<List<Integer>> subsets = subsets(new int[]{1,2,3});
    LocalDateTime endTime = LocalDateTime.now();
    Duration time = Duration.between(startTime, endTime);
    System.out.println("集合输出为：" + subsets + ",运算时间为" + time.toMillis());
  }
}
