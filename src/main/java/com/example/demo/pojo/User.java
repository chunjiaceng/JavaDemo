package com.example.demo.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author haruka
 * @since 2023-08-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_user")
public class User extends Model implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id")
    private Long id;
    private String username;

    private String nickName;

    private String password;
// 停用状态 0正常 1停用
    private String status;

    private String email;

    private String phoneNumber;

    private String sex;

    private String avatar;


    private String level;
    @TableField(fill = FieldFill.INSERT) //创建时自动填充
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE) //创建时自动填充
    private Date updateTime;
    @TableLogic(value = "0",delval = "1")
    //insert 时不会自动填充 0 需要数据库给初始值
    private String deleted;
    @Version
    private Integer version;




}
