package com.back.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.back.dao.UserBuyInfoDao;
import com.back.entity.UserBuyInfo;
import com.back.service.UserBuyInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (UserBuyInfo)表服务实现类
 *
 * @author songjie
 * @since 2022-11-28 15:37:12
 */
@Service("userBuyInfoService")
public class UserBuyInfoServiceImpl extends ServiceImpl<UserBuyInfoDao, UserBuyInfo> implements UserBuyInfoService {

    @Resource
    private UserBuyInfoDao userBuyInfoDao;

    @Override
    public Map saveUserBuyInfo(UserBuyInfo userBuyInfo) {

        Map map=new HashMap();
        LambdaQueryWrapper<UserBuyInfo> userBuyInfoLambdaQueryWrapper=new LambdaQueryWrapper<>();
        userBuyInfoLambdaQueryWrapper.eq(UserBuyInfo::getPhone,userBuyInfo.getPhone());
        userBuyInfoLambdaQueryWrapper.eq(UserBuyInfo::getInventoryid,userBuyInfo.getInventoryid());
        Integer integer = userBuyInfoDao.selectCount(userBuyInfoLambdaQueryWrapper);
        if (integer==0){
            userBuyInfoDao.insert(userBuyInfo);
            map.put("code",1);
            map.put("message","提交成功！！！");
        }else {
            map.put("code",0);
            map.put("message","该手机号已预约过该车！！！");
        }

        return map;
    }

    @Override
    public Map getAllUserBuyInfo() {
        Map map=new HashMap();
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("state",0);
        List<UserBuyInfo> list=userBuyInfoDao.selectList(wrapper);
        map.put("userBuyInfo",list);
        return map;
    }

    @Override
    public Map getNoUserBuyInfo() {
        Map map=new HashMap();
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.ne("state",0);
        List<UserBuyInfo> list=userBuyInfoDao.selectList(wrapper);
        map.put("userBuyInfo",list);
        return map;
    }

    @Override
    public Map getDeleteUserBuyInfo() {
        Map map=new HashMap();
        List<UserBuyInfo>list=userBuyInfoDao.getDeleteUserInfo();
        map.put("userBuyInfo",list);
        return map;
    }

    @Override
    public void AllToNo(Integer id) {
        UserBuyInfo userBuyInfo = userBuyInfoDao.selectById(id);
        userBuyInfo.setState(1);
        userBuyInfoDao.updateById(userBuyInfo);
    }

    @Override
    public void AllToDelete(Integer id) {
        userBuyInfoDao.deleteById(id);
    }

    @Override
    public Map NoToNo(Integer id) {
        Map map=new HashMap();
        UserBuyInfo userBuyInfo = userBuyInfoDao.selectById(id);
        Integer state=userBuyInfo.getState();
        if (state>=3){
            userBuyInfoDao.deleteReallyById(id);
            map.put("code",2);
            map.put("message","超过三次未接，已删除该条信息！！！");
        }else {
            userBuyInfo.setState(state+1);
            userBuyInfoDao.updateById(userBuyInfo);
            map.put("code",1);
            map.put("message","成功！！！");
        }
        return map;
    }

    @Override
    public void deleteOne(Integer id) {
        userBuyInfoDao.deleteReallyById(id);
    }

    @Override
    public void deleteByIds(Integer[] ids) {
        for (int i=0;i<ids.length;i++){
            userBuyInfoDao.deleteReallyById(ids[i]);
        }
    }
}

