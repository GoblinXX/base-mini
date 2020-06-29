package com.byy.api.service.form.phone;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author: yyc
 * @date: 19-6-27 下午4:14
 */
@Getter
@Setter
@ToString
public class PhoneForm {

  /** 手机号 */
  private String phone;

  /** 密码 */
  private String password;
}
