package com.byy.dal.common.utils.helper;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author: yyc
 * @date: 19-3-30 下午5:16
 */
public class SortHelper {

  /**
   * Map按key字典顺序进行排序
   *
   * @param map Map
   * @param <V> V
   * @return Map
   */
  public static <V> Map<String, V> sortMapKeys(Map<String, V> map, Comparator<String> comparator) {
    if (map == null || map.isEmpty()) {
      return MapHelper.newHashMap();
    }
    Map<String, V> sortMap = new TreeMap<>(comparator);
    sortMap.putAll(map);
    return sortMap;
  }

  /**
   * 分组并排序
   *
   * @param list List
   * @param classifier Function
   * @param <T> T
   * @return Map
   */
  public static <T> Map<String, List<T>> groupByAndSort(
      List<T> list, Function<? super T, String> classifier) {
    if (list == null || list.isEmpty()) {
      return MapHelper.newHashMap();
    }
    return sortMapKeys(
        list.stream().collect(Collectors.groupingBy(classifier)),
        (s, anotherString) -> {
          if ("#".equals(s)) {
            s = "Z#";
          }
          if ("#".equals(anotherString)) {
            anotherString = "Z#";
          }
          return s.compareTo(anotherString);
        });
  }
}
