package com.example.aidraw.Service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.aidraw.Bean.User;
import com.example.aidraw.Service.UserService;
import com.example.aidraw.mapper.Usermapper;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl extends ServiceImpl<Usermapper,User> implements UserService {
}
