package com.pancras.springcloud.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.pancras.springcloud.entities.CommonResult;

public class CustomerBlockHandler{
    public static CommonResult handlerException(BlockException e){
        return new CommonResult(6666,"按用户自定义的兜底解耦方法，global handlerException" );
    }
    public static CommonResult handlerException2(BlockException e){
        return new CommonResult(7777,"按用户自定义的兜底解耦方法2，global handlerException" );
    }
}
