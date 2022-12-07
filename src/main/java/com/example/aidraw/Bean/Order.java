package com.example.aidraw.Bean;

import com.baomidou.mybatisplus.annotation.*;
import com.sun.org.apache.xpath.internal.operations.Or;
import lombok.Data;

import java.util.Date;



@Data
@TableName(value = "orders")
public class Order {
    @TableId(value = "orderid", type = IdType.AUTO)
    private Integer orderid;

    @TableField(value = "taskid")
    private Integer taskId;

    @TableField(value = "text")
    private String text;

    @TableField(value = "orderstatus")
    private int status;

    @TableField(value = "style")
    private String style;

    @TableField(value = "resolution")
    private String resolution;

    @TableField(value = "creatorid")
    private int creatorid;

    @TableField(value = "url")
    private String url;

    public static Order createOrder(int taskId, String url,int creatorid){
        Order order=new Order();
        order.taskId=taskId;
        order.url=url;
        order.creatorid=creatorid;
        return order;
    }


   /* @TableField(value = "createtime",fill = FieldFill.INSERT)
    private Date createtime;*/

}
