package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.pojo.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author Ruby Ceng 曾春佳
 * @version 1.0.0
 * @ClassName MenuMapper.xml.java
 * @Description TODO 菜单Mapper接口
 * @createTime 2024年06月04日 16:35:00
 */

@Mapper
public interface MenuMapper extends BaseMapper<Menu> {
    /*
     * @description: TODO 关联菜单，用户，角色表，根据用户ID返回权限标识
     * @author: Ruby Ceng 曾春佳
     * @date: 2024/6/4 16:43
     * @param: [userId] 用户ID
     * @return: java.util.List<java.lang.String>
     **/
    @Select("select distinct m.`perms` from sys_user_role ur " +
            "left join `sys_role` r on ur.`role_id` = r.`id` " +
            "left join `sys_role_menu` rm on ur.`role_id` = rm.`role_id` " +
            "left join `sys_menu` m on  m.`id` = rm.`menu_id` " +
            "where " +
            "`user_id` = #{userId} " +
            "and r.`status` = 0 " +
            "and m.`status` = 0 ")
    public List<String> selectPremsByUserId(Long userId);
}
