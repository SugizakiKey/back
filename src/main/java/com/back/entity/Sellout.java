package com.back.entity;


import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.util.Date;

/**
 * (Sellout)表实体类
 *
 * @author songjie
 * @since 2022-11-25 19:51:57
 */
@SuppressWarnings("serial")
public class Sellout extends Model<Sellout> {
    @TableId(type = IdType.AUTO)
    private Integer id;
    
    private String inventoryid;
    
    private String branddept;
    
    private String outtype;
    
    private Date bargaindate;
    
    private String operationadminaccount;
    
    private Date acquisitiondate;
    
    private Double showroomprice;
    
    private Double sellminprice;
    
    private Double managerminprice;
    
    private Double bargainprice;
    
    private String selltype;
    
    private String isinfull;
    
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
    
    private Double deposit;
    
    private Double balance;
    
    private Double commission;
    
    private Double otherexpenses;
    
    private String bargainuseraccount;
    
    private String userfrom;
    
    private String buyuserphone;
    
    private String buyuseridcard;
    
    private Boolean isinsurance;
    
    private String outremarks;
    
    private Double mustcollection;
    
    private Double hadcollection;
    
    private Double leftcollection;
    
    private Double nowcollection;
    
    private String personinchargeaccount;
    
    private String collectiontext;
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

    public String getOuttype() {
        return outtype;
    }

    public void setOuttype(String outtype) {
        this.outtype = outtype;
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

    public Date getAcquisitiondate() {
        return acquisitiondate;
    }

    public void setAcquisitiondate(Date acquisitiondate) {
        this.acquisitiondate = acquisitiondate;
    }

    public Double getShowroomprice() {
        return showroomprice;
    }

    public void setShowroomprice(Double showroomprice) {
        this.showroomprice = showroomprice;
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

    public Double getDeposit() {
        return deposit;
    }

    public void setDeposit(Double deposit) {
        this.deposit = deposit;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getCommission() {
        return commission;
    }

    public void setCommission(Double commission) {
        this.commission = commission;
    }

    public Double getOtherexpenses() {
        return otherexpenses;
    }

    public void setOtherexpenses(Double otherexpenses) {
        this.otherexpenses = otherexpenses;
    }

    public String getBargainuseraccount() {
        return bargainuseraccount;
    }

    public void setBargainuseraccount(String bargainuseraccount) {
        this.bargainuseraccount = bargainuseraccount;
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

    public Boolean getIsinsurance() {
        return isinsurance;
    }

    public void setIsinsurance(Boolean isinsurance) {
        this.isinsurance = isinsurance;
    }

    public String getOutremarks() {
        return outremarks;
    }

    public void setOutremarks(String outremarks) {
        this.outremarks = outremarks;
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

