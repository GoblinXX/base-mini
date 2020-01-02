package com.byy.biz.service.wechat;

import com.baomidou.mybatisplus.extension.service.IService;
import com.byy.dal.entity.beans.wechat.WeChatUser;

/**
 * @author: yyc
 * @date: 19-3-29 下午1:03
 */
public interface WeChatUserService extends IService<WeChatUser> {

  /**
   * 保存或更新用户
   *
   * @param weChatUser WeChatUser
   * @param defaultPwd String
   * @return WeChatUser
   */
  WeChatUser saveOrUpdateWeChatUser(WeChatUser weChatUser, String defaultPwd);
}
