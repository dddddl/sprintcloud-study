package com.dongcheng.nacos.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dongcheng.nacos.entity.Stock;

public interface StockTblService extends IService<Stock> {

    void deduct(String commodityCode, int count);
}
