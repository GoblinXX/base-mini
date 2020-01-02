package com.byy.dal.common.utils.helper;

import java.util.ArrayList;
import java.util.List;

/**
 * 集合工具类
 *
 * @author: yyc
 * @date: 19-6-27 下午4:24
 */
public class ListHelper<T> {

  private List<T> value;

  private ListHelper(List<T> value) {
    this.value = value;
  }

  /**
   * 初始化连续arrayList的包装实体
   *
   * @param <T> T
   * @return ListHelper
   */
  public static <T> ListHelper<T> builder() {
    return new ListHelper<>(newArrayList());
  }

  /**
   * 添加值(默认过滤空值)
   *
   * @param t T
   * @return ListHelper
   */
  public ListHelper<T> add(T t) {
    if (t != null) {
      this.value.add(t);
    }
    return this;
  }

  /**
   * 获取list
   *
   * @return List
   */
  public List<T> build() {
    return this.value;
  }

  /**
   * 设置一个t
   *
   * @param t T
   * @param <T> T
   * @return List
   */
  public static <T> List<T> of(T t) {
    return ListHelper.<T>builder().add(t).build();
  }

  /**
   * 设置两个t
   *
   * @param t1 T
   * @param t2 T
   * @param <T> T
   * @return List
   */
  public static <T> List<T> of(T t1, T t2) {
    return ListHelper.<T>builder().add(t1).add(t2).build();
  }

  /**
   * 设置三个t
   *
   * @param t1 T
   * @param t2 T
   * @param t3 T
   * @param <T> T
   * @return List
   */
  public static <T> List<T> of(T t1, T t2, T t3) {
    return ListHelper.<T>builder().add(t1).add(t2).add(t3).build();
  }

  /**
   * 初始化空的arrayList
   *
   * @param <T> T
   * @return List
   */
  public static <T> List<T> newArrayList() {
    return new ArrayList<>();
  }
}
