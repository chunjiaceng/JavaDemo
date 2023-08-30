package com.example.demo.entity.response;

import com.example.demo.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class UserQuery extends User {
    Integer pageNumber;
    Integer pageSize;
    Integer total;
}
