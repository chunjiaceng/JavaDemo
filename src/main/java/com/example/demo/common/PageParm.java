package com.example.demo.common;

import lombok.Data;

/**
 * @author Ruby Ceng 曾春佳
 * @version 1.0.0
 * @ClassName PageParm.java
 * @Description TODO 分页对象
 * @createTime 2024年06月03日 13:23:00
 */

@Data
public class PageParm {
    Integer page = 1;
    Integer pageSize = 10;
    Integer total;
}
