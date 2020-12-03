package com.example.demo1.controller;

import com.example.demo1.entity.User;
import com.example.demo1.service.IUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/test")
public class UserController {

    @Resource
    private IUserService iUserService;

    @GetMapping("/master")
    public List<User> listByMaster() throws Exception {
        return iUserService.list();
    }

    @GetMapping("/slave")
    public List<User> listBySlave() throws Exception {
        return iUserService.list();
    }
}
