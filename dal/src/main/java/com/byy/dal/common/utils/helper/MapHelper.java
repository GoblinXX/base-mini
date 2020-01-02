package com.byy.dal.common.utils.helper;

import java.util.HashMap;
import java.util.Map;

/**
 * 散列表工具类
 *
 * @author: yyc
 * @date: 19-6-27 下午4:24
 */
public class MapHelper<K, V> {

  private Map<K, V> value;

  private MapHelper(Map<K, V> value) {
    this.value = value;
  }

  /**
   * 初始化连续hashMap的包装实体
   *
   * @param <K> K
   * @param <V> V
   * @return MapHelper
   */
  public static <K, V> MapHelper<K, V> builder() {
    return new MapHelper<>(new HashMap<>());
  }

  /**
   * 添加值(默认过滤空key值)
   *
   * @param k K
   * @param v V
   * @return MapHelper
   */
  public MapHelper<K, V> put(K k, V v) {
    if (k != null) {
      this.value.put(k, v);
    }
    return this;
  }

  /**
   * 获得map
   *
   * @return Map
   */
  public Map<K, V> build() {
    return this.value;
  }

  /**
   * 设置一个k-v
   *
   * @param k K
   * @param v V
   * @param <K> K
   * @param <V> V
   * @return Map
   */
  public static <K, V> Map<K, V> of(K k, V v) {
    return MapHelper.<K, V>builder().put(k, v).build();
  }

  /**
   * 设置两个k-v
   *
   * @param k1 K
   * @param v1 V
   * @param k2 K
   * @param v2 V
   * @param <K> K
   * @param <V> V
   * @return Map
   */
  public static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2) {
    return MapHelper.<K, V>builder().put(k1, v1).put(k2, v2).build();
  }

  /**
   * 设置三个k-v
   *
   * @param k1 K
   * @param v1 V
   * @param k2 K
   * @param v2 V
   * @param k3 K
   * @param v3 V
   * @param <K> K
   * @param <V> V
   * @return Map
   */
  public static <K, V> Map<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3) {
    return MapHelper.<K, V>builder().put(k1, v1).put(k2, v2).put(k3, v3).build();
  }

  /**
   * 初始化空的hashMap
   *
   * @param <K> K
   * @param <V> V
   * @return Map
   */
  public static <K, V> Map<K, V> newHashMap() {
    return new HashMap<>();
  }
}
