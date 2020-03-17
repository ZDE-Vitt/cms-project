package com.gem.back.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("user")
public class User implements Serializable {
    private Integer id;
    private String username;
    private String password;
    private String telephone;
    private Integer account;
    @TableField("regTime")
    private Date regTime;
    private String sex;
    private String avatar;
    private Integer status; //用户状态

    @TableField(exist = false)
    private String formatetime;
}
