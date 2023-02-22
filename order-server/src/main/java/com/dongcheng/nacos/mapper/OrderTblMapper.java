package com.dongcheng.nacos.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dongcheng.nacos.entity.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderTblMapper extends BaseMapper<Order> {
}
