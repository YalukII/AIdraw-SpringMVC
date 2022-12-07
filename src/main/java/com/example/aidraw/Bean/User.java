package com.example.aidraw.Bean;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "user")
public class User {
    @TableId(value = "uid", type = IdType.AUTO)
    private Integer uid;
    @TableField(value = "name")
    private String name;
    @TableField(value = "password")
    private String password;
    @TableField(value = "phone")
    private String phone;

}
