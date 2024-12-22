package com.zhang.usercenter.model.domain.request;

import com.baomidou.mybatisplus.annotation.*;
import com.zhang.usercenter.common.PageRequest;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 队伍
 * @TableName team
 */
@TableName(value ="team")
@Data
public class TeamQuery extends PageRequest implements Serializable {
    /**
     * 
     */
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
     * 状态 0-公开 1-私有 2-加密v
     */
    private Integer status;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}