package com.example.aidraw.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.aidraw.Bean.Order;
import com.example.aidraw.VO.R;

public interface AIdrawService extends IService<Order> {
    /**
     * 请求百度AI绘画
     * @param order
     */
    Order createAI(Order order);
}
