package com.dongcheng.nacos.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dongcheng.nacos.entity.Stock;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StockTblMapper extends BaseMapper<Stock> {
}
