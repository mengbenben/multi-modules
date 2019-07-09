package com.demo.service.impl;

import com.demo.dao.UserMapper;
import com.demo.entity.common.R;
import com.demo.entity.model.User;
import com.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public R<List<User>> getUserList() {

        List<User> users= userMapper.selectUsers();

        R<List<User>> res = new R<>();
        res.setData(users);

        return res;
    }

}
