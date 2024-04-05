package com.back.entity;


import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.util.Date;

/**
 * (CarPay)表实体类
 *
 * @author songjie
 * @since 2022-11-30 16:42:10
 */
@SuppressWarnings("serial")
public class CarPay extends Model<CarPay> {
    @TableId(type = IdType.AUTO)
    private Integer id;
    
    private String userphone;
    
    private String inventoryid;
    
    private String branddept;
    
    private Double mustpay;
    
    private Double hadpay;
    
    private Double leftpay;
    
    private Double nowpay;
    
    private String payproject;
    
    private Date paydate;
    
    private String personinchargeaccount;
    
    private String paytext;

    private String customerid;
    @Version
    private Integer version;
    @TableLogic
    private Integer deleted;
    @TableField(fill= FieldFill.INSERT)
    private Date gmtCreate;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

    public String getCustomerid() {
        return customerid;
    }

    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserphone() {
        return userphone;
    }

    public void setUserphone(String userphone) {
        this.userphone = userphone;
    }

    public String getInventoryid() {
        return inventoryid;
    }

    public void setInventoryid(String inventoryid) {
        this.inventoryid = inventoryid;
    }

    public String getBranddept() {
        return branddept;
    }

    public void setBranddept(String branddept) {
        this.branddept = branddept;
    }

    public Double getMustpay() {
        return mustpay;
    }

    public void setMustpay(Double mustpay) {
        this.mustpay = mustpay;
    }

    public Double getHadpay() {
        return hadpay;
    }

    public void setHadpay(Double hadpay) {
        this.hadpay = hadpay;
    }

    public Double getLeftpay() {
        return leftpay;
    }

    public void setLeftpay(Double leftpay) {
        this.leftpay = leftpay;
    }

    public Double getNowpay() {
        return nowpay;
    }

    public void setNowpay(Double nowpay) {
        this.nowpay = nowpay;
    }

    public String getPayproject() {
        return payproject;
    }

    public void setPayproject(String payproject) {
        this.payproject = payproject;
    }

    public Date getPaydate() {
        return paydate;
    }

    public void setPaydate(Date paydate) {
        this.paydate = paydate;
    }

    public String getPersoninchargeaccount() {
        return personinchargeaccount;
    }

    public void setPersoninchargeaccount(String personinchargeaccount) {
        this.personinchargeaccount = personinchargeaccount;
    }

    public String getPaytext() {
        return paytext;
    }

    public void setPaytext(String paytext) {
        this.paytext = paytext;
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

