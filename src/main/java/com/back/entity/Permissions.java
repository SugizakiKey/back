package com.back.entity;


import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.util.Date;

/**
 * (Permissions)表实体类
 *
 * @author songjie
 * @since 2022-12-02 15:25:33
 */
@SuppressWarnings("serial")
public class Permissions extends Model<Permissions> {
    @TableId(type = IdType.AUTO)
    private Integer id;
    
    private String permisson;
    
    private Integer fatherid;

    private String children;

    private String permissionindex;

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

    public String getPermissionindex() {
        return permissionindex;
    }

    public void setPermissionindex(String permissionindex) {
        this.permissionindex = permissionindex;
    }

    public String getChildren() {
        return children;
    }

    public void setChildren(String children) {
        this.children = children;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPermisson() {
        return permisson;
    }

    public void setPermisson(String permisson) {
        this.permisson = permisson;
    }

    public Integer getFatherid() {
        return fatherid;
    }

    public void setFatherid(Integer fatherid) {
        this.fatherid = fatherid;
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

