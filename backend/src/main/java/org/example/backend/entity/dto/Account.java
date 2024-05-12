package org.example.backend.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.backend.entity.BaseData;

import java.util.Date;

@TableName("account")
@AllArgsConstructor
@Data
public class Account implements BaseData {
    @TableId(value = "id", type = IdType.AUTO)
    Integer id;
    @TableField("username")
    String username;
    @TableField("password")
    String password;
    @TableField("email")
    String email;
    @TableField("role")
    String role;
    @TableField("register_time")
    Date registerTime;
}
