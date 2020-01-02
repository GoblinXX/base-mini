package com.byy.biz.service.wechat.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.byy.biz.service.wechat.WeChatUserService;
import com.byy.dal.common.errors.UserAuthException;
import com.byy.dal.common.utils.helper.CheckHelper;
import com.byy.dal.common.utils.provider.WrapperProvider;
import com.byy.dal.entity.beans.wechat.WeChatUser;
import com.byy.dal.enums.GlobalErrorCode;
import com.byy.dal.mapper.wechat.WeChatUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author: yyc
 * @date: 19-3-20 下午1:29
 */
@Service
public class WeChatUserServiceImpl extends ServiceImpl<WeChatUserMapper, WeChatUser>
    implements WeChatUserService {

  private final PasswordEncoder passwordEncoder;

  @Autowired
  public WeChatUserServiceImpl(PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public WeChatUser saveOrUpdateWeChatUser(WeChatUser weChatUser, String defaultPwd) {
    weChatUser.setUsername(weChatUser.getNickname());
    WeChatUser one =
        getOne(WrapperProvider.oneQueryWrapper(WeChatUser::getOpenId, weChatUser.getOpenId()));
    if (one == null) {
      weChatUser.setPassword(passwordEncoder.encode(defaultPwd));
      CheckHelper.trueOrThrow(
          save(weChatUser), UserAuthException::new, GlobalErrorCode.USER_CREATE_ERROR);
    } else {
      weChatUser.setId(one.getId());
      CheckHelper.trueOrThrow(
          updateById(weChatUser), UserAuthException::new, GlobalErrorCode.USER_UPDATE_ERROR);
    }
    return weChatUser;
  }
}
