package com.byy.biz.service.address.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.byy.biz.service.address.ProvinceService;
import com.byy.dal.entity.beans.address.Province;
import com.byy.dal.mapper.address.ProvinceMapper;
import org.springframework.stereotype.Service;

/**
 * @author: yyc
 * @date: 19-4-10 下午9:38
 */
@Service
public class ProvinceServiceImpl extends ServiceImpl<ProvinceMapper, Province>
    implements ProvinceService {}
