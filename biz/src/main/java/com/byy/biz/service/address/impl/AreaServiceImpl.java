package com.byy.biz.service.address.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.byy.biz.service.address.AreaService;
import com.byy.dal.entity.beans.address.Area;
import com.byy.dal.mapper.address.AreaMapper;
import org.springframework.stereotype.Service;

/**
 * @author: yyc
 * @date: 19-4-10 下午9:38
 */
@Service
public class AreaServiceImpl extends ServiceImpl<AreaMapper, Area>
    implements AreaService {}
