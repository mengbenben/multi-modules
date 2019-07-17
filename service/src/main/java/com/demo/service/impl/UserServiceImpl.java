package com.demo.service.impl;

import com.demo.dao.UserMapper;
import com.demo.entity.common.R;
import com.demo.entity.model.User;
import com.demo.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

    /*
     * 这个方法中用到了我们开头配置依赖的分页插件pagehelper
     * 很简单，只需要在service层传入参数，然后将参数传递给一个插件的一个静态方法即可；
     * pageNum 开始页数
     * pageSize 每页显示的数据条数
     * */
    @Override
    public PageInfo<User> selectUsers(int pageNum, int pageSize) {
        //将参数传给这个方法就可以实现物理分页了，非常简单。
        PageHelper.startPage(pageNum, pageSize);
        List<User> userDOs = userMapper.selectUsers();
        PageInfo result = new PageInfo(userDOs);
        return result;
    }

}
