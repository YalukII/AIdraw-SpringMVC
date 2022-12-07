package com.example.aidraw.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.aidraw.VO.R;
import com.example.aidraw.Bean.User;
import com.example.aidraw.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")

public class RegisterController{
    @Autowired
    private UserService userService;

    @RequestMapping(value="register",method = RequestMethod.POST)
    public R register(@RequestBody User bean){
        System.out.println(bean);
        userService.save(bean);
        System.out.println(bean);
        return R.ok().data(bean);
    }

    @RequestMapping(value="login",method = RequestMethod.POST)
    public R login(@RequestBody User bean) {
        System.out.println(bean);
        //通过query wrapper构建sql的条件
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", bean.getPhone());
        User user = userService.getOne(queryWrapper);
        System.out.println("接收到的" + bean + "；查到的" + user);
        //判断输入的账户密码是否正确
        if (user != null) {
            if (user.getPassword().equals(bean.getPassword()))
                return R.ok().data(user);
            else
                return R.error("密码输入错误");
        }
            else
            return R.error("账户不存在，请重新输入");
    }
}
