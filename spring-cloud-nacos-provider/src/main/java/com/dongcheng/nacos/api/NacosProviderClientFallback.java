package com.dongcheng.nacos.api;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
public class NacosProviderClientFallback implements NacosProviderClient {

    @Override
    public String echo2(String string) {
        return "error";
    }

    @Override
    public String ribbonTest() {
        return "error";
    }

    @GetMapping("hystrix-test")
    public String hystrixTest() {
        return "hystrix error";
    }
}
