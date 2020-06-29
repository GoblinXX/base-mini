package com.byy.dal.entity.beans.sys;

import com.byy.dal.entity.beans.base.BaseUser;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 后台用户
 *
 * @author: yyc
 * @date: 19-5-15 上午11:55
 */
@Setter
@Getter
@ToString
public class SysUser extends BaseUser {

  /** 姓名 */
  private String name;
}
