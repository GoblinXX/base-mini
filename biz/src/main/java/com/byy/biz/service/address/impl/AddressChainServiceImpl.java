package com.byy.biz.service.address.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.byy.biz.service.address.AddressChainService;
import com.byy.dal.entity.beans.address.AddressChain;
import com.byy.dal.mapper.address.AddressChainMapper;
import org.springframework.stereotype.Service;

/**
 * @author: yyc
 * @date: 19-4-28 下午6:40
 */
@Service
public class AddressChainServiceImpl extends ServiceImpl<AddressChainMapper, AddressChain>
    implements AddressChainService {

  @Override
  public AddressChain loadAddressChain(Long areaId) {
    return baseMapper.selectAddressChain(areaId);
  }
}
