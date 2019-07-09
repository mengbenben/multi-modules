package com.demo.Controller;

import com.demo.entity.common.R;
import com.demo.entity.model.User;
import com.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/list")
    public R<List<User>> getUserList() {
        try {
            return userService.getUserList();

        } catch (Exception e) {
            return null;
        }
    }

}