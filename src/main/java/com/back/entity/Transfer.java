package com.back.entity;


import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.util.Date;

/**
 * (Transfer)表实体类
 *
 * @author songjie
 * @since 2022-11-25 22:49:00
 */
@SuppressWarnings("serial")
public class Transfer extends Model<Transfer> {
    @TableId(type = IdType.AUTO)
    private Integer id;
    
    private String type;
    
    private String inventoryid;
    
    private String branddept;
    
    private String olduser;
    
    private String olduserphone;
    
    private String oldcarnumber;
    
    private String newuser;
    
    private String newuserphone;
    
    private String newcarnumber;
    
    private String transfertype;
    
    private String transferarea;
    
    private Date submittime;
    
    private Date finishtime;
    
    private String transferstate;
    
    private String isurgent;
    
    private String appraiseraccount;
    
    private String transfercommissioner;
    
    private String materials;
    
    private Integer carkeys;
    
    private String remarks;
    
    private String carexamination;
    
    private String licenseplate;
    
    private String showcards;
    
    private String violationhandling;
    
    private String dataprocessing;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getOlduser() {
        return olduser;
    }

    public void setOlduser(String olduser) {
        this.olduser = olduser;
    }

    public String getOlduserphone() {
        return olduserphone;
    }

    public void setOlduserphone(String olduserphone) {
        this.olduserphone = olduserphone;
    }

    public String getOldcarnumber() {
        return oldcarnumber;
    }

    public void setOldcarnumber(String oldcarnumber) {
        this.oldcarnumber = oldcarnumber;
    }

    public String getNewuser() {
        return newuser;
    }

    public void setNewuser(String newuser) {
        this.newuser = newuser;
    }

    public String getNewuserphone() {
        return newuserphone;
    }

    public void setNewuserphone(String newuserphone) {
        this.newuserphone = newuserphone;
    }

    public String getNewcarnumber() {
        return newcarnumber;
    }

    public void setNewcarnumber(String newcarnumber) {
        this.newcarnumber = newcarnumber;
    }

    public String getTransfertype() {
        return transfertype;
    }

    public void setTransfertype(String transfertype) {
        this.transfertype = transfertype;
    }

    public String getTransferarea() {
        return transferarea;
    }

    public void setTransferarea(String transferarea) {
        this.transferarea = transferarea;
    }

    public Date getSubmittime() {
        return submittime;
    }

    public void setSubmittime(Date submittime) {
        this.submittime = submittime;
    }

    public Date getFinishtime() {
        return finishtime;
    }

    public void setFinishtime(Date finishtime) {
        this.finishtime = finishtime;
    }

    public String getTransferstate() {
        return transferstate;
    }

    public void setTransferstate(String transferstate) {
        this.transferstate = transferstate;
    }

    public String getIsurgent() {
        return isurgent;
    }

    public void setIsurgent(String isurgent) {
        this.isurgent = isurgent;
    }

    public String getAppraiseraccount() {
        return appraiseraccount;
    }

    public void setAppraiseraccount(String appraiseraccount) {
        this.appraiseraccount = appraiseraccount;
    }

    public String getTransfercommissioner() {
        return transfercommissioner;
    }

    public void setTransfercommissioner(String transfercommissioner) {
        this.transfercommissioner = transfercommissioner;
    }

    public String getMaterials() {
        return materials;
    }

    public void setMaterials(String materials) {
        this.materials = materials;
    }

    public Integer getCarkeys() {
        return carkeys;
    }

    public void setCarkeys(Integer carkeys) {
        this.carkeys = carkeys;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getCarexamination() {
        return carexamination;
    }

    public void setCarexamination(String carexamination) {
        this.carexamination = carexamination;
    }

    public String getLicenseplate() {
        return licenseplate;
    }

    public void setLicenseplate(String licenseplate) {
        this.licenseplate = licenseplate;
    }

    public String getShowcards() {
        return showcards;
    }

    public void setShowcards(String showcards) {
        this.showcards = showcards;
    }

    public String getViolationhandling() {
        return violationhandling;
    }

    public void setViolationhandling(String violationhandling) {
        this.violationhandling = violationhandling;
    }

    public String getDataprocessing() {
        return dataprocessing;
    }

    public void setDataprocessing(String dataprocessing) {
        this.dataprocessing = dataprocessing;
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

