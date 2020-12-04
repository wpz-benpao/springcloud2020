package com.pancras.springcloud.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 后台与前台交互的吧包装类
 * @param <T>
 */

@Data                //set get toString方法
@AllArgsConstructor  //全参构造
@NoArgsConstructor   //无参构造
public class CommonResult<T> {
    private Integer code;
    private String message;
    private T data;

    //自定义部分参数的构造方法
    public CommonResult(Integer code,String message) {
        this.code=code;
        this.message=message;
    }
}
