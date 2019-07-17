package com.demo.service;

import com.demo.entity.common.R;
import com.demo.entity.model.User;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface UserService {

    R<List<User>> getUserList();

    PageInfo<User> selectUsers(int pageNum, int pageSize);
}
