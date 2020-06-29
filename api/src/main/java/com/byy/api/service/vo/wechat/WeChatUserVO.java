package com.byy.api.service.vo.wechat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 用户
 *
 * @author: yyc
 * @date: 19-1-29 上午11:31
 */
@Setter
@Getter
@ToString
public class WeChatUserVO {

  /** 主键id */
  private Long id;

  /** 三方openId */
  private String openId;

  /** 三方unionId */
  private String unionId;

  /** 手机号 */
  private String phone;

  /** 性别(0是未知，1代表男，2代表女) */
  private Integer gender;

  /** 昵称 */
  private String nickname;

  /** 头像 */
  private String avatar;
}
