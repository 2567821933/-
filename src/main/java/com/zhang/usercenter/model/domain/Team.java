package com.zhang.usercenter.model.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 队伍
 * @TableName team
 */
@TableName(value ="team")
@Data
public class Team implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 队伍昵称
     */
    private String name;

    /**
     * 队伍描述
     */
    private String description;

    /**
     * 最大人数
     */
    private Integer maxNum;

    /**
     * 过期时间
     */
    private Date expireTime;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 密码
     */
    private String password;

    /**
     * 状态 0-公开 1-私有 2-加密v
     */
    private Integer status;

    /**
     * 创建时间默认当前
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updataTime;

    /**
     * 时候删除
     */
    @TableLogic // 逻辑删除注释
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}