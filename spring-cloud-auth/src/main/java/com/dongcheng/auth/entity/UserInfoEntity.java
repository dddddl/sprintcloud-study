package com.dongcheng.auth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

@Data
@TableName("user_info")
@Builder
public class UserInfoEntity {

    private static final long serialVersionUID = 92049696933194360L;

    @TableId(value = "id", type = IdType.AUTO)
    /**
     * 主键自增
     */
    @TableField("id")
    private Long id;

    /** 账号信息 */
    @TableField("account")
    private String account;

    /** 状态 **/
    @TableField("status")
    private Integer status;

    /** 密码 **/
    @TableField("password")
    private String password;

}
