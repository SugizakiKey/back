package com.back.entity;


import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.util.Date;

/**
 * (Customer)表实体类
 *
 * @author songjie
 * @since 2022-11-20 15:33:26
 */
@SuppressWarnings("serial")
public class Customer extends Model<Customer> {
    @TableId(type = IdType.AUTO)
    private Integer id;
    
    private String customerid;
    
    private String customername;
    
    private String introduction;
    
    private String customerphone;
    
    private String wechat;
    
    private String newmediatype;
    
    private String newmediaaccount;
    
    private String customeraddress;
    
    private String customertype;
    
    private String customersex;
    
    private String customeridcard;
    
    private String customerform;
    
    private String customerwebform;
    
    private String customercallform;

    private Date calltime;
    
    private String customernature;
    
    private String customerlable;
    
    private String grade;
    
    private String customertext;
    @Version
    private Integer version;
    @TableLogic
    private Integer deleted;
    @TableField(fill= FieldFill.INSERT)
    private Date gmtCreate;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustomerid() {
        return customerid;
    }

    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getCustomerphone() {
        return customerphone;
    }

    public void setCustomerphone(String customerphone) {
        this.customerphone = customerphone;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getNewmediatype() {
        return newmediatype;
    }

    public void setNewmediatype(String newmediatype) {
        this.newmediatype = newmediatype;
    }

    public String getNewmediaaccount() {
        return newmediaaccount;
    }

    public void setNewmediaaccount(String newmediaaccount) {
        this.newmediaaccount = newmediaaccount;
    }

    public String getCustomeraddress() {
        return customeraddress;
    }

    public void setCustomeraddress(String customeraddress) {
        this.customeraddress = customeraddress;
    }

    public String getCustomertype() {
        return customertype;
    }

    public void setCustomertype(String customertype) {
        this.customertype = customertype;
    }

    public String getCustomersex() {
        return customersex;
    }

    public void setCustomersex(String customersex) {
        this.customersex = customersex;
    }

    public String getCustomeridcard() {
        return customeridcard;
    }

    public void setCustomeridcard(String customeridcard) {
        this.customeridcard = customeridcard;
    }

    public String getCustomerform() {
        return customerform;
    }

    public void setCustomerform(String customerform) {
        this.customerform = customerform;
    }

    public String getCustomerwebform() {
        return customerwebform;
    }

    public void setCustomerwebform(String customerwebform) {
        this.customerwebform = customerwebform;
    }

    public String getCustomercallform() {
        return customercallform;
    }

    public void setCustomercallform(String customercallform) {
        this.customercallform = customercallform;
    }

    public Date getCalltime() {
        return calltime;
    }

    public void setCalltime(Date calltime) {
        this.calltime = calltime;
    }

    public String getCustomernature() {
        return customernature;
    }

    public void setCustomernature(String customernature) {
        this.customernature = customernature;
    }

    public String getCustomerlable() {
        return customerlable;
    }

    public void setCustomerlable(String customerlable) {
        this.customerlable = customerlable;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getCustomertext() {
        return customertext;
    }

    public void setCustomertext(String customertext) {
        this.customertext = customertext;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

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

