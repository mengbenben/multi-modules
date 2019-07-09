package com.demo.service;

import com.demo.entity.common.R;
import com.demo.entity.model.User;

import java.util.List;

public interface UserService {

    R<List<User>> getUserList();
}
