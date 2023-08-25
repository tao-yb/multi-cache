package com.tao.controller;

import com.tao.entity.User;
import com.tao.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tyb
 * @Description
 * @create 2021-09-14 10:51
 */
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/test/{id}")
    public User getUser(@PathVariable Integer id){
        return userService.getUser(id);
    }
}
