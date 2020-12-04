package com.pancras.springcloud.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data                //set get toString方法
@AllArgsConstructor  //全参构造
@NoArgsConstructor   //无参构造
public class Order {
    private Long id;
    private Long userId;
    private Long productId;
    private Integer count;
    private BigDecimal money;
    private Integer status;     //订单状态：  0 表示创建中 ， 1 表示已完结

}
