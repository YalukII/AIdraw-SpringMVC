package com.example.aidraw.Bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "task")
public class Task {
    @TableId(value = "taskId")
    private Integer taskId;

    @TableField(value = "status")
    private Integer status;

    @TableField(exist=false)
    private String waiting;

    @TableField(value = "img")
    private String img;


    @TableField(value = "orderid")
    private Integer orderid;

    @TableField(value = "creatorid")
    private Integer creatorID;


  @TableField(exist=false)
    private String createTime;

/*    @TableField(exist=false)
    private int code;

    @TableField(exist=false)
    private String msg;

    @TableField(exist=false)
    private Object data;*/

    public static Task createTask(Integer taskId, Integer status, Integer orderId){
        Task task=new Task();
        task.taskId=taskId;
        task.status=status;
        task.orderid=orderId;
        return task;
    }
}
