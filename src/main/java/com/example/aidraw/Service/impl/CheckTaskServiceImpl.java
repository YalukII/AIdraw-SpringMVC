package com.example.aidraw.Service.impl;

import cn.hutool.http.HttpRequest;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.example.aidraw.Bean.Task;
import com.example.aidraw.Service.CheckTaskService;
import com.example.aidraw.mapper.Taskmapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Slf4j
@Service
public class CheckTaskServiceImpl extends ServiceImpl<Taskmapper, Task> implements CheckTaskService {
    @Override
    public Task checkTask(Task task){
        try {
            String token = "24.8a5bb5c8619d3b0566340641a9209183.86400000.1669396643384.7f90824b30dd909f4f0b7bf017439ed7-131679";
            String url = "https://wenxin.baidu.com/moduleApi/portal/api/rest/1.0/ernievilg/v1/getImg?access_token=" + token;
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("taskId",task.getTaskId());
            String result = HttpRequest.post(url)
                    .form(paramMap)
                    .timeout(20000)
                    .execute().body();
            log.info(result);
            JSONObject resultObject = JSONUtil.parseObj(result);//把获取的json转换成Object，已达到通过key获取value
            Task result2 = JSONUtil.toBean(resultObject.getStr("data"), Task.class);//把获取的json转为bean
            log.info(String.valueOf(result2));
            return result2;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

}
