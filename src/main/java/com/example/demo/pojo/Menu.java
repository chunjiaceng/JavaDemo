package com.example.demo.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Ruby Ceng 曾春佳
 * @version 1.0.0
 * @ClassName Menu.java
 * @Description TODO 菜单权限DO
 * @createTime 2024年06月04日 16:27:00
 */



@TableName(value="sys_menu")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId
    private Long id;
    /**
     * 菜单名
     */
    private String menuName;
    /**
     * 路由地址
     */
    private String path;
    /**
     * 组件路径
     */
    private String component;
    /**
     * 菜单状态（0显示 1隐藏）
     */
    private String visible;
    /**
     * 菜单状态（0正常 1停用）
     */
    private String status;
    /**
     * 权限标识
     */
    private String perms;
    /**
     * 菜单图标
     */
    private String icon;

    private Long createBy;

    private Long updateBy;

    @TableField(fill = FieldFill.INSERT) //创建时自动填充
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE) //创建时自动填充
    private Date updateTime;
    @TableLogic(value = "0",delval = "1")
    //insert 时不会自动填充 0 需要数据库给初始值
    private String deleted;
    @Version
    private Integer version;
    /**
     * 备注
     */
    private String remark;
}
