package com.queuesystem.queuesystem.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


/**
 * Created by IntelliJ IDEA.
 * @author niuwenyu
 * description:
 * @since 2021/4/25 下午9:33
 * version: 1.0.0
 */

/**
 * 排队信息表
 */
@Data
@TableName(value = "queue_info")
public class QueueInfo {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 创建人
     */
    @TableField(value = "create_username")
    private String createUsername;

    /**
     * 修改人username
     */
    @TableField(value = "update_username")
    private String updateUsername;

    /**
     * 修改时间
     */
    @TableField(value = "update_time")
    private Long updateTime;

    /**
     * create time
     */
    @TableField(value = "create_time")
    private Long createTime;

    /**
     * id
     */
    @TableField(value = "snow_flake_id")
    private Long snowFlakeId;

    /**
     * user firstName
     */
    @TableField(value = "first_name")
    private String firstName;

    /**
     * user lastName
     */
    @TableField(value = "last_name")
    private String lastName;

    /**
     * id_card_number
     */
    @TableField(value = "user_id")
    private String userId;
}