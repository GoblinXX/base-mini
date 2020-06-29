package com.byy.api.service.vo.action;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 通用主键返回VO
 *
 * @author: yyc
 * @date: 19-5-11 下午6:30
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CommonVO {

  /** 主键 */
  private Long id;

  public static CommonVO withId(Long id) {
    return new CommonVO(id);
  }
}
