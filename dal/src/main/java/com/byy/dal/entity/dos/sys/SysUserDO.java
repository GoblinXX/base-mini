package com.byy.dal.entity.dos.sys;

import com.byy.dal.entity.beans.sys.SysAuthority;
import com.byy.dal.entity.beans.sys.SysUser;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author: yyc
 * @date: 2019-06-27 19:52:14
 */
@Setter
@Getter
@ToString
public class SysUserDO extends SysUser {

  /** 菜单列表 */
  private List<SysAuthority> menus;
}
