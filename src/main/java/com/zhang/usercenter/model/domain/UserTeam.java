package com.zhang.usercenter.model.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用户队伍关系表
 * @TableName user_team
 */
@TableName(value ="user_team")
@Data
public class UserTeam implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 队伍id
     */
    private Long teamId;

    /**
     * 加入时间
     */
    private Date joinTime;

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