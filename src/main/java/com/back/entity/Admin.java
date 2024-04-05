package com.back.entity;


import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



import java.io.Serializable;
import java.util.Date;

/**
 * (Admin)表实体类
 *
 * @author makejava
 * @since 2022-10-10 15:45:35
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin extends Model<Admin> {
    
    private String coname;
    
    private String name;
    
    private String account;
    
    private String password;
    
    private String phone;

    private String sex;

    private String email;


    private String circleurl;

    private String salty;


    @TableId(type = IdType.AUTO)
    private Integer id;
    //乐观锁
    @Version
    private Integer version;
    //逻辑删除
    @TableLogic
    private Integer deleted;

    //创建时间
    @TableField(fill= FieldFill.INSERT)
    private Date gmtCreate;
    //更新时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.id;
    }
    }

