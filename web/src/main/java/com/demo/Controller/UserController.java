package com.demo.Controller;

import com.demo.annotation.RequestAnalysis;
import com.demo.entity.common.R;
import com.demo.entity.model.User;
import com.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/list")
    @ResponseBody
    public R<List<User>> getUserList() {
        return userService.getUserList();
    }

    @ResponseBody
    @RequestAnalysis(value = "获取用户列表分页方法")
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Object findAllUser(
            @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                    int pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10")
                    int pageSize) {

        return userService.selectUsers(pageNum, pageSize);
    }

}