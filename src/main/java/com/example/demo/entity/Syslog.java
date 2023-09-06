package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author haruka
 * @since 2023-09-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("SYSLOG")
public class Syslog extends Model {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 操作人
     */
    @TableField("operationUser")
    private String operationUser;

    /**
     * 请求路径
     */
    private String path;

    /**
     * 方法执行时间
     */
    private String time;

    /**
     * 方法入参
     */
    private String parameter;

    /**
     * 执行方法
     */
    private String title;

    /**
     * 方法描述
     */
    private String action;

    /**
     * 系统类型
     */
    @TableField("sysType")
    private String sysType;

    /**
     * 操作类型
     */
    @TableField("opType")
    private String opType;

    private String deleted;

    private Integer version;


}
