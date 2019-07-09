package com.demo.dao;

import com.demo.entity.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component("userMapper")
public interface UserMapper {

    List<User> selectUsers();
}
