package com.dongcheng.nacos.rest;

import com.dongcheng.nacos.service.StockTblService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

@Api(tags = "库存API")
@RestController
@RequestMapping("/api/stock")
public class StockController {

    @Autowired
    private StockTblService stockTblService;

    @PostMapping(path = "/deduct")
    public Boolean deduct(String commodityCode, Integer count) {
        stockTblService.deduct(commodityCode, count);
        return true;
    }
}
