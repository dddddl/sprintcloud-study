package com.dongcheng.nacos.rest;

import com.dongcheng.nacos.api.NacosProviderClient;
import com.dongcheng.nacos.utils.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/nacos")
@RefreshScope
public class NacosController {


    private final RestTemplate restTemplate;

    @Autowired
    public NacosController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Value("${test.config.refresh:true}")
    private boolean refresh;

    @Autowired
    private NacosProviderClient nacosProviderClient;

    @GetMapping("")
    public boolean get() {
        return refresh;
    }

    @GetMapping("/echo/{str}")
    public String echo(@PathVariable String str) {
        return restTemplate.getForObject("http://winter-nacos-provider/nacos/echo/" + str, String.class);
    }

    @GetMapping("/feign-test/{str}")
    public String feignTest(@PathVariable String str) {
        return nacosProviderClient.echo2(str);
    }

    @GetMapping("/ribbon-test")
    public String ribbonTest1() {
        return nacosProviderClient.ribbonTest();
    }

    @GetMapping("/hystrix-test")
    public String hystrixTest() {
        return nacosProviderClient.hystrixTest();
    }
}
