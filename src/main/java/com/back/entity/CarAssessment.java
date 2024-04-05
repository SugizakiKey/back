package com.back.entity;


import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.util.Date;

/**
 * (CarAssessment)表实体类
 *
 * @author songjie
 * @since 2022-10-31 19:51:04
 */
@SuppressWarnings("serial")
public class CarAssessment extends Model<CarAssessment> {
    @TableId(type = IdType.AUTO)
    private Integer id;
    
    private String inventoryid;
    
    private String cartype;
    
    private String assessmentstate;
    
    private String branddept;
    
    private String vin;
    
    private String motorid;
    
    private String oldcarnumber;
    
    private String gearbox;
    
    private String carcolor;
    
    private String exhaust;
    
    private String mileage;
    
    private Date createyear;
    
    private Date firstnumber;
    
    private boolean isnumber;
    
    private String numberprovince;

    private String numbercity;
    
    private String carprovince;

    private String carcity;
    
    private Double userprice;
    
    private Double appraiserprice;
    
    private Double standprice;
    
    private Double buyprice;
    
    private String username;
    
    private String phone;
    
    private String grade;
    
    private String cartext;
    
    private String maintenancetext;
    
    private String carurl;
    
    private String createconfig;
    
    private String carconfig;
    
    private String adminaccount;
    
    private String appraiseraccount;
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

    public String getCartype() {
        return cartype;
    }

    public void setCartype(String cartype) {
        this.cartype = cartype;
    }

    public String getNumbercity() {
        return numbercity;
    }

    public void setNumbercity(String numbercity) {
        this.numbercity = numbercity;
    }

    public String getCarcity() {
        return carcity;
    }

    public void setCarcity(String carcity) {
        this.carcity = carcity;
    }

    public String getAssessmentstate() {
        return assessmentstate;
    }

    public void setAssessmentstate(String assessmentstate) {
        this.assessmentstate = assessmentstate;
    }

    public String getBranddept() {
        return branddept;
    }

    public void setBranddept(String branddept) {
        this.branddept = branddept;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getMotorid() {
        return motorid;
    }

    public void setMotorid(String motorid) {
        this.motorid = motorid;
    }

    public String getOldcarnumber() {
        return oldcarnumber;
    }

    public void setOldcarnumber(String oldcarnumber) {
        this.oldcarnumber = oldcarnumber;
    }

    public String getGearbox() {
        return gearbox;
    }

    public void setGearbox(String gearbox) {
        this.gearbox = gearbox;
    }

    public String getCarcolor() {
        return carcolor;
    }

    public void setCarcolor(String carcolor) {
        this.carcolor = carcolor;
    }

    public String getExhaust() {
        return exhaust;
    }

    public void setExhaust(String exhaust) {
        this.exhaust = exhaust;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public Date getCreateyear() {
        return createyear;
    }

    public void setCreateyear(Date createyear) {
        this.createyear = createyear;
    }

    public Date getFirstnumber() {
        return firstnumber;
    }

    public void setFirstnumber(Date firstnumber) {
        this.firstnumber = firstnumber;
    }

    public boolean isIsnumber() {
        return isnumber;
    }

    public void setIsnumber(boolean isnumber) {
        this.isnumber = isnumber;
    }

    public String getNumberprovince() {
        return numberprovince;
    }

    public void setNumberprovince(String numberprovince) {
        this.numberprovince = numberprovince;
    }

    public String getCarprovince() {
        return carprovince;
    }

    public void setCarprovince(String carprovince) {
        this.carprovince = carprovince;
    }

    public Double getUserprice() {
        return userprice;
    }

    public void setUserprice(Double userprice) {
        this.userprice = userprice;
    }

    public Double getAppraiserprice() {
        return appraiserprice;
    }

    public void setAppraiserprice(Double appraiserprice) {
        this.appraiserprice = appraiserprice;
    }

    public Double getStandprice() {
        return standprice;
    }

    public void setStandprice(Double standprice) {
        this.standprice = standprice;
    }

    public Double getBuyprice() {
        return buyprice;
    }

    public void setBuyprice(Double buyprice) {
        this.buyprice = buyprice;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getCartext() {
        return cartext;
    }

    public void setCartext(String cartext) {
        this.cartext = cartext;
    }

    public String getMaintenancetext() {
        return maintenancetext;
    }

    public void setMaintenancetext(String maintenancetext) {
        this.maintenancetext = maintenancetext;
    }

    public String getCarurl() {
        return carurl;
    }

    public void setCarurl(String carurl) {
        this.carurl = carurl;
    }

    public String getCreateconfig() {
        return createconfig;
    }

    public void setCreateconfig(String createconfig) {
        this.createconfig = createconfig;
    }

    public String getCarconfig() {
        return carconfig;
    }

    public void setCarconfig(String carconfig) {
        this.carconfig = carconfig;
    }

    public String getAdminaccount() {
        return adminaccount;
    }

    public void setAdminaccount(String adminaccount) {
        this.adminaccount = adminaccount;
    }

    public String getAppraiseraccount() {
        return appraiseraccount;
    }

    public void setAppraiseraccount(String appraiseraccount) {
        this.appraiseraccount = appraiseraccount;
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

