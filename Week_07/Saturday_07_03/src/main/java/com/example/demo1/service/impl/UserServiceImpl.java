package com.example.demo1.service.impl;

import com.example.demo1.entity.User;
import com.example.demo1.mapper.UserMapper;
import com.example.demo1.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public List<User> list() {
        return userMapper.list();
    }
}
