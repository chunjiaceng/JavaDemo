package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
public class User extends Model {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id")
    private Long id;

    private String name;

    private Integer age;

    private String password;
    @TableLogic(value = "0",delval = "1")
    //insert 时不会自动填充 0 需要数据库给初始值
    private String deleted;
    @Version
    private Integer version;


}
