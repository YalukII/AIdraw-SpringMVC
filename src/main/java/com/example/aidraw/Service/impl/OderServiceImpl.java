package com.example.aidraw.Service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.aidraw.Bean.Order;
import com.example.aidraw.Service.OrderService;
import com.example.aidraw.mapper.Ordermapper;
import org.springframework.stereotype.Service;

@Service
public class OderServiceImpl extends ServiceImpl<Ordermapper, Order> implements OrderService {
}
