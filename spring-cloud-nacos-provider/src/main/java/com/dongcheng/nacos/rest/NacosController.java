package com.dongcheng.nacos.rest;

import com.dongcheng.nacos.utils.CommonResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/nacos")
@RefreshScope
public class NacosController {

    @Value("${test.config.refresh:true}")
    private boolean refresh;

    @Value("${server.port}")
    private String port;


    @GetMapping("")
    public boolean get() {
        return refresh;
    }


    @GetMapping("/echo/{string}")
    public String echo(@PathVariable String string) {
        return "Hello Nacos Discovery " + string;
    }

    @GetMapping("feign-test/{string}")
    public String feignTest(@PathVariable String string) {
        return "Hello feign " + string;
    }

    @GetMapping("/ribbon-test")
    public String ribbonTest() {
        return "Hello ribbon, my port" + port;
    }

    @GetMapping("/hystrix-test")
    public String hystrixTest() {
        throw new RuntimeException("ex");
    }
}
