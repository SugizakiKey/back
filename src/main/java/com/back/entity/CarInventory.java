package com.back.entity;


import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.util.Date;

/**
 * (CarInventory)表实体类
 *
 * @author songjie
 * @since 2022-11-11 17:47:35
 */
@SuppressWarnings("serial")
public class CarInventory extends Model<CarInventory> {
    @TableId(type = IdType.AUTO)
    private Integer id;
    
    private String inventoryid;
    
    private Double showroomprice;
    
    private Double webprice;
    
    private Double sellminprice;

    private Double managerminprice;

    private String changepricetext;
    
    private Date inventorydate;
    
    private String carstand;
    
    private String isshow;
    
    private String inventorytext;
    
    private Double mustpay;
    
    private Double hadpay;
    
    private Double leftpay;
    
    private Double nowpay;
    
    private String personinchargeaccount;
    
    private String selltext;
    
    private String ispass;

    private String state;

    private String statetext;

    private String passaccount;
    @Version
    private Integer version;
    @TableLogic
    private Integer deleted;
    @TableField(fill= FieldFill.INSERT)
    private Date gmtCreate;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

    public Double getManagerminprice() {
        return managerminprice;
    }

    public void setManagerminprice(Double managerminprice) {
        this.managerminprice = managerminprice;
    }

    public String getChangepricetext() {
        return changepricetext;
    }

    public void setChangepricetext(String changepricetext) {
        this.changepricetext = changepricetext;
    }

    public String getStatetext() {
        return statetext;
    }

    public void setStatetext(String statetext) {
        this.statetext = statetext;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPassaccount() {
        return passaccount;
    }

    public void setPassaccount(String passaccount) {
        this.passaccount = passaccount;
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

    public Double getShowroomprice() {
        return showroomprice;
    }

    public void setShowroomprice(Double showroomprice) {
        this.showroomprice = showroomprice;
    }

    public Double getWebprice() {
        return webprice;
    }

    public void setWebprice(Double webprice) {
        this.webprice = webprice;
    }

    public Double getSellminprice() {
        return sellminprice;
    }

    public void setSellminprice(Double sellminprice) {
        this.sellminprice = sellminprice;
    }

    public Date getInventorydate() {
        return inventorydate;
    }

    public void setInventorydate(Date inventorydate) {
        this.inventorydate = inventorydate;
    }

    public String getCarstand() {
        return carstand;
    }

    public void setCarstand(String carstand) {
        this.carstand = carstand;
    }

    public String getIsshow() {
        return isshow;
    }

    public void setIsshow(String isshow) {
        this.isshow = isshow;
    }

    public String getInventorytext() {
        return inventorytext;
    }

    public void setInventorytext(String inventorytext) {
        this.inventorytext = inventorytext;
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

    public String getPersoninchargeaccount() {
        return personinchargeaccount;
    }

    public void setPersoninchargeaccount(String personinchargeaccount) {
        this.personinchargeaccount = personinchargeaccount;
    }

    public String getSelltext() {
        return selltext;
    }

    public void setSelltext(String selltext) {
        this.selltext = selltext;
    }

    public String getIspass() {
        return ispass;
    }

    public void setIspass(String ispass) {
        this.ispass = ispass;
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

