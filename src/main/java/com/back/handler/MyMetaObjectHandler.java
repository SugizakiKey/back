package com.back.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 字段自动填充功能
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    private static final String GMT_CRATE = "gmtCreate";
    private static final String GMT_MODIFIED = "gmtModified";
    private static final String VERSION="version";
    private static final String DELETED="deleted";

    @Override
    public void insertFill(MetaObject metaObject) {
        if (metaObject.hasGetter(GMT_CRATE) && metaObject.hasGetter(GMT_MODIFIED)) {
            Date now = new Date();
            setFieldValByName(GMT_CRATE, now, metaObject);
            setFieldValByName(GMT_MODIFIED, now, metaObject);

            //添加乐观锁默认值是1
            setFieldValByName(VERSION,1,metaObject);
            //添加逻辑删除的默认值0
            setFieldValByName(DELETED,0,metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        if (metaObject.hasGetter(GMT_MODIFIED)) {
            setFieldValByName(GMT_MODIFIED, new Date(), metaObject);
        }
    }
}
