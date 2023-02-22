package com.dongcheng.nacos.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dongcheng.nacos.entity.Order;

public interface OrderTblService extends IService<Order> {

    void placeOrder(String userId, String commodityCode, Integer count);

}
