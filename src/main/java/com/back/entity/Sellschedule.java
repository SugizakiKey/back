package com.back.entity;


import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.util.Date;

/**
 * (Sellschedule)表实体类
 *
 * @author songjie
 * @since 2022-11-17 23:22:31
 */
@SuppressWarnings("serial")
public class Sellschedule extends Model<Sellschedule> {
    @TableId(type = IdType.AUTO)
    private Integer id;
    
    private String inventoryid;
    
    private String branddept;
    
    private String selltype;
    
    private String isinfull;
    
    private Date scheduledate;
    
    private String scheduleadminaccount;
    
    private Double webprice;
    
    private Double sellminprice;
    
    private Double managerminprice;
    
    private Double bargainprice;
    
    private Double deposit;
    
    private Integer mortgagecycle;
    
    private Double downpaymentratio;
    
    private Double lendingrate;
    
    private Double lendingprice;
    
    private Double totalrepaymentprice;
    
    private Double monthrepayprice;
    
    private Double servicechargeratio;
    
    private Double servicechargeprice;
    
    private Double returnpointratio;
    
    private Double returnpointprice;
    
    private Date startdate;
    
    private Date enddate;
    
    private String scheduleuseraccount;
    
    private String userfrom;
    
    private String buyuserphone;
    
    private String buyuseridcard;

    private Boolean isinsurance;
    
    private String remarks;

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

    public Boolean getIsinsurance() {
        return isinsurance;
    }

    public void setIsinsurance(Boolean isinsurance) {
        this.isinsurance = isinsurance;
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

    public String getSelltype() {
        return selltype;
    }

    public void setSelltype(String selltype) {
        this.selltype = selltype;
    }

    public String getIsinfull() {
        return isinfull;
    }

    public void setIsinfull(String isinfull) {
        this.isinfull = isinfull;
    }

    public Date getScheduledate() {
        return scheduledate;
    }

    public void setScheduledate(Date scheduledate) {
        this.scheduledate = scheduledate;
    }

    public String getScheduleadminaccount() {
        return scheduleadminaccount;
    }

    public void setScheduleadminaccount(String scheduleadminaccount) {
        this.scheduleadminaccount = scheduleadminaccount;
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

    public Double getManagerminprice() {
        return managerminprice;
    }

    public void setManagerminprice(Double managerminprice) {
        this.managerminprice = managerminprice;
    }

    public Double getBargainprice() {
        return bargainprice;
    }

    public void setBargainprice(Double bargainprice) {
        this.bargainprice = bargainprice;
    }

    public Double getDeposit() {
        return deposit;
    }

    public void setDeposit(Double deposit) {
        this.deposit = deposit;
    }

    public Integer getMortgagecycle() {
        return mortgagecycle;
    }

    public void setMortgagecycle(Integer mortgagecycle) {
        this.mortgagecycle = mortgagecycle;
    }

    public Double getDownpaymentratio() {
        return downpaymentratio;
    }

    public void setDownpaymentratio(Double downpaymentratio) {
        this.downpaymentratio = downpaymentratio;
    }

    public Double getLendingrate() {
        return lendingrate;
    }

    public void setLendingrate(Double lendingrate) {
        this.lendingrate = lendingrate;
    }

    public Double getLendingprice() {
        return lendingprice;
    }

    public void setLendingprice(Double lendingprice) {
        this.lendingprice = lendingprice;
    }

    public Double getTotalrepaymentprice() {
        return totalrepaymentprice;
    }

    public void setTotalrepaymentprice(Double totalrepaymentprice) {
        this.totalrepaymentprice = totalrepaymentprice;
    }

    public Double getMonthrepayprice() {
        return monthrepayprice;
    }

    public void setMonthrepayprice(Double monthrepayprice) {
        this.monthrepayprice = monthrepayprice;
    }

    public Double getServicechargeratio() {
        return servicechargeratio;
    }

    public void setServicechargeratio(Double servicechargeratio) {
        this.servicechargeratio = servicechargeratio;
    }

    public Double getServicechargeprice() {
        return servicechargeprice;
    }

    public void setServicechargeprice(Double servicechargeprice) {
        this.servicechargeprice = servicechargeprice;
    }

    public Double getReturnpointratio() {
        return returnpointratio;
    }

    public void setReturnpointratio(Double returnpointratio) {
        this.returnpointratio = returnpointratio;
    }

    public Double getReturnpointprice() {
        return returnpointprice;
    }

    public void setReturnpointprice(Double returnpointprice) {
        this.returnpointprice = returnpointprice;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public String getScheduleuseraccount() {
        return scheduleuseraccount;
    }

    public void setScheduleuseraccount(String scheduleuseraccount) {
        this.scheduleuseraccount = scheduleuseraccount;
    }

    public String getUserfrom() {
        return userfrom;
    }

    public void setUserfrom(String userfrom) {
        this.userfrom = userfrom;
    }

    public String getBuyuserphone() {
        return buyuserphone;
    }

    public void setBuyuserphone(String buyuserphone) {
        this.buyuserphone = buyuserphone;
    }

    public String getBuyuseridcard() {
        return buyuseridcard;
    }

    public void setBuyuseridcard(String buyuseridcard) {
        this.buyuseridcard = buyuseridcard;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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

