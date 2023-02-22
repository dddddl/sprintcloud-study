package com.dongcheng.nacos.utils;



import java.io.Serializable;
import java.util.Map;

/**
 * 通用返回对象
 * Created by macro on 2019/4/19.
 */
public class CommonResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    private long code;
    private String message;
    private T data;
    private Map<String, Object> otherInfo;

    public CommonResult() {
    }

    protected CommonResult(long code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    protected CommonResult(long code, String message) {
        this.code = code;
        this.message = message;
    }


    protected CommonResult(long code, String message, Map<String, Object> otherInfos) {
        this.code = code;
        this.message = message;
        this.otherInfo = otherInfos;
    }

    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<T>(0, "success", data);
    }
}
