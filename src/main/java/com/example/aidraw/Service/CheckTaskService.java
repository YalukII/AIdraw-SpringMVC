package com.example.aidraw.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.aidraw.Bean.Order;
import com.example.aidraw.Bean.Task;
import com.example.aidraw.VO.R;

public interface CheckTaskService extends IService<Task> {
    /**
     * 请求百度AI绘画
     * @param
     */
    Task checkTask(Task task);
}
