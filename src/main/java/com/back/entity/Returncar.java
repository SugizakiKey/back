package com.back.entity;


import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.util.Date;

/**
 * (Returncar)表实体类
 *
 * @author songjie
 * @since 2022-11-29 16:53:27
 */
@SuppressWarnings("serial")
public class Returncar extends Model<Returncar> {
    @TableId(type = IdType.AUTO)
    private Integer id;
    
    private String inventoryid;
    
    private String branddept;
    
    private Date bargaindate;
    
    private String operationadminaccount;
    
    private Double bargainprice;
    
    private Double mustcollection;
    
    private Double deposit;
    
    private Double hadcollection;
    
    private Double carbackservicecharge;
    
    private Double carbackprice;
    
    private Date carbackdate;
    
    private String carbacktext;

    private Integer selloutid;
    @Version
    private Integer version;
    @TableLogic
    private Integer deleted;
    @TableField(fill= FieldFill.INSERT)
    private Date gmtCreate;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

    public Integer getSelloutid() {
        return selloutid;
    }

    public void setSelloutid(Integer selloutid) {
        this.selloutid = selloutid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Date getBargaindate() {
        return bargaindate;
    }

    public void setBargaindate(Date bargaindate) {
        this.bargaindate = bargaindate;
    }

    public String getOperationadminaccount() {
        return operationadminaccount;
    }

    public void setOperationadminaccount(String operationadminaccount) {
        this.operationadminaccount = operationadminaccount;
    }

    public Double getBargainprice() {
        return bargainprice;
    }

    public void setBargainprice(Double bargainprice) {
        this.bargainprice = bargainprice;
    }

    public Double getMustcollection() {
        return mustcollection;
    }

    public void setMustcollection(Double mustcollection) {
        this.mustcollection = mustcollection;
    }

    public Double getDeposit() {
        return deposit;
    }

    public void setDeposit(Double deposit) {
        this.deposit = deposit;
    }

    public Double getHadcollection() {
        return hadcollection;
    }

    public void setHadcollection(Double hadcollection) {
        this.hadcollection = hadcollection;
    }

    public Double getCarbackservicecharge() {
        return carbackservicecharge;
    }

    public void setCarbackservicecharge(Double carbackservicecharge) {
        this.carbackservicecharge = carbackservicecharge;
    }

    public Double getCarbackprice() {
        return carbackprice;
    }

    public void setCarbackprice(Double carbackprice) {
        this.carbackprice = carbackprice;
    }

    public Date getCarbackdate() {
        return carbackdate;
    }

    public void setCarbackdate(Date carbackdate) {
        this.carbackdate = carbackdate;
    }

    public String getCarbacktext() {
        return carbacktext;
    }

    public void setCarbacktext(String carbacktext) {
        this.carbacktext = carbacktext;
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

