package com.example.demo.test;

import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.pool.HikariProxyConnection;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Haruka 曾春佳
 * @version 1.0.0
 * @ClassName H2database.java
 * @Description TODO 测试h2数据库连接池
 * @createTime 2024年05月28日 20:03:00
 */

@SpringBootTest
public class H2database {
    @Autowired
    ApplicationContext applicationContext;

    @Test
    void testDataSource() throws SQLException{
        Assert.assertNotNull(applicationContext.getBean("dataSource"));
        DataSource dataSource = applicationContext.getBean(DataSource.class);

        Assert.assertTrue(dataSource instanceof HikariDataSource);
        Connection connection = dataSource.getConnection();

        Assert.assertTrue(connection instanceof HikariProxyConnection);
        connection.close();
    }
}
