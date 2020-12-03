package com.example.demo.controller;

import com.example.demo.config.TargetDataSource;
import com.example.demo.entity.User;
import com.example.demo.service.IUserService;
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
    @TargetDataSource("master")
    public List<User> listByMaster() throws Exception {
        return iUserService.list();
    }

    @GetMapping("/slave")
    @TargetDataSource("slave")
    public List<User> listBySlave() throws Exception {
        return iUserService.list();
    }
}
