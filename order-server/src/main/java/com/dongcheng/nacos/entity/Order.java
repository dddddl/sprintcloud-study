package com.dongcheng.nacos.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.Tolerate;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@TableName("order_tbl")
@Accessors(chain = true)
@Builder
public class Order implements Serializable {

    @TableField("id")
    private Integer id;

    @TableField("user_id")
    private String userId;

    @TableField("commodity_code")
    private String commodityCode;

    @TableField("count")
    private Integer count;

    @TableField("money")
    private BigDecimal money;

    @Tolerate
    public Order() {
    }
}
