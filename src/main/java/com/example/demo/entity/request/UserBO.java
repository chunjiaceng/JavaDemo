package com.example.demo.entity.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.tomcat.util.modeler.BaseModelMBean;

@Data
@Accessors(chain = true)
public class UserBO {
    private String name;

    private Integer age;

    private String password;
}
