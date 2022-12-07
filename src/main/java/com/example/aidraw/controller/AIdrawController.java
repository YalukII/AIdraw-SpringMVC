package com.example.aidraw.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.aidraw.Bean.Order;
import com.example.aidraw.Bean.Task;
import com.example.aidraw.Bean.User;
import com.example.aidraw.Service.AIdrawService;
import com.example.aidraw.Service.CheckTaskService;
import com.example.aidraw.Service.OrderService;
import com.example.aidraw.VO.R;

import com.sun.org.apache.xpath.internal.operations.Or;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/draw")
public class AIdrawController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private AIdrawService aIdrawService;

    @Autowired
    private CheckTaskService checkTaskService;

    @RequestMapping(value="create",method = RequestMethod.POST)
    public R createAI(@RequestBody Order order) {
       //Order result= aIdrawService.createAI(order);
        //输入order id,调用接口生成task id，把order连同task id存入数据库中
        //log.info(String.valueOf(result));
        log.info(String.valueOf(order));
        if (order.getTaskId()!=null){
            //orderService.save(result);
            Task task=Task.createTask(order.getTaskId(),order.getStatus(),order.getOrderid());
            checkTaskService.save(task);
            return R.ok().data(order);
        }
        else
            return R.error("创建失败");
    }
//需要传task id和creator id
    @RequestMapping(value="check",method = RequestMethod.POST)
    public R checkAI(@RequestBody Task task){
        Task result=checkTaskService.checkTask(task);//更新task的数据
       //如果task的status为1（任务已完成），把url计入order中并返回，否则不更新
        checkTaskService.saveOrUpdate(result);
        if(result.getStatus()==1){
            log.info(String.valueOf(task));
            Order order=Order.createOrder(result.getTaskId(),result.getImg(),task.getCreatorID());
            order.setStatus(1);
            //增加限定，where=taskid,update url和status
            UpdateWrapper<Order> updateWrapper=new UpdateWrapper<>();
            updateWrapper.eq("taskid", result.getTaskId());
            updateWrapper.set("url",result.getImg());//似乎没用
            updateWrapper.set("orderstatus",1);
            boolean flag=orderService.update(order,updateWrapper);
            log.info(String.valueOf(order));
            if(flag)
                return R.ok().data(order);
            else
                return R.error("更新sql失败");
        }
        else
            return R.ok().data(result);

    }

    @RequestMapping(value="list",method = RequestMethod.GET)
    public R getTaskList(@RequestParam int UID){
        //通过UID查找现在用户数据库里的order,返回列表
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("creatorid",UID);
        List<Order> orderList=orderService.list(queryWrapper);
        log.info(Arrays.toString(orderList.toArray()));
        return R.ok().data(orderList);
    }
}
