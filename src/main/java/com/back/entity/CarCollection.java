package com.back.entity;


import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.util.Date;

/**
 * (CarCollection)表实体类
 *
 * @author songjie
 * @since 2022-11-29 20:19:39
 */
@SuppressWarnings("serial")
public class CarCollection extends Model<CarCollection> {
    @TableId(type = IdType.AUTO)
    private Integer id;
    
    private Integer selloutid;
    
    private String inventoryid;
    
    private String branddept;
    
    private Double mustcollection;
    
    private Double hadcollection;
    
    private Double leftcollection;
    
    private Double nowcollection;
    
    private String collectionproject;
    
    private Date collectiondate;
    
    private String personinchargeaccount;
    
    private String collectiontext;

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

    public Integer getSelloutid() {
        return selloutid;
    }

    public void setSelloutid(Integer selloutid) {
        this.selloutid = selloutid;
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

    public Double getMustcollection() {
        return mustcollection;
    }

    public void setMustcollection(Double mustcollection) {
        this.mustcollection = mustcollection;
    }

    public Double getHadcollection() {
        return hadcollection;
    }

    public void setHadcollection(Double hadcollection) {
        this.hadcollection = hadcollection;
    }

    public Double getLeftcollection() {
        return leftcollection;
    }

    public void setLeftcollection(Double leftcollection) {
        this.leftcollection = leftcollection;
    }

    public Double getNowcollection() {
        return nowcollection;
    }

    public void setNowcollection(Double nowcollection) {
        this.nowcollection = nowcollection;
    }

    public String getCollectionproject() {
        return collectionproject;
    }

    public void setCollectionproject(String collectionproject) {
        this.collectionproject = collectionproject;
    }

    public Date getCollectiondate() {
        return collectiondate;
    }

    public void setCollectiondate(Date collectiondate) {
        this.collectiondate = collectiondate;
    }

    public String getPersoninchargeaccount() {
        return personinchargeaccount;
    }

    public void setPersoninchargeaccount(String personinchargeaccount) {
        this.personinchargeaccount = personinchargeaccount;
    }

    public String getCollectiontext() {
        return collectiontext;
    }

    public void setCollectiontext(String collectiontext) {
        this.collectiontext = collectiontext;
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

