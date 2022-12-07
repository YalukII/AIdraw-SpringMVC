package com.example.aidraw.Service.impl;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.aidraw.Bean.Order;
import com.example.aidraw.Bean.Task;
import com.example.aidraw.Service.AIdrawService;

import com.example.aidraw.VO.R;
import com.example.aidraw.mapper.Ordermapper;
import com.sun.org.apache.xpath.internal.operations.Or;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.Map;

/**
 * @author mia
 */
//log.info的注解
@Slf4j

@Service
public class AIdrawServiceImpl extends ServiceImpl<Ordermapper,Order> implements AIdrawService {
    @Override
    public Order createAI(Order order) {
        //传入一个order类，更新order类里的taskid，传出order
        String text = order.getText();
        String resolution = order.getResolution();
        String style = order.getStyle();
        try {
            String token = "24.8a5bb5c8619d3b0566340641a9209183.86400000.1669396643384.7f90824b30dd909f4f0b7bf017439ed7-131679";
            String url = "https://wenxin.baidu.com/moduleApi/portal/api/rest/1.0/ernievilg/v1/txt2img?access_token=" + token;
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("text", text);
            paramMap.put("resolution",resolution);
            paramMap.put("style", style);
            String result = HttpRequest.post(url)
                    .form(paramMap)
                    .timeout(20000)
                    .execute().body();
            log.info(result);
            JSONObject resultObject = JSONUtil.parseObj(result);//把获取的json转换成Object，已达到通过key获取value
            String data=resultObject.getStr("data");
            Order order1=JSONUtil.toBean(JSONUtil.parseObj(data), Order.class);//把转成了object的data转成order bean
            order.setTaskId(order1.getTaskId());
            return order;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}


