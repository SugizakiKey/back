package com.back.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.back.dao.UserSellInfoDao;
import com.back.entity.UserSellInfo;
import com.back.service.UserSellInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (UserSellInfo)表服务实现类
 *
 * @author songjie
 * @since 2022-10-21 18:57:06
 */
@Slf4j
@Service("userSellInfoService")
public class UserSellInfoServiceImpl extends ServiceImpl<UserSellInfoDao, UserSellInfo> implements UserSellInfoService {

    @Resource
    private UserSellInfoDao userSellInfoDao;

    @Override
    public Map saveUserSellInfo(UserSellInfo userSellInfo) {
        Map map=new HashMap();
        userSellInfoDao.insert(userSellInfo);
        map.put("code",204);
        map.put("message","信息提交成功！！！");
        return map;
    }

    @Override
    public Map getAllUserSellInfo() {
        Map map=new HashMap();
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("state",0);
        List<UserSellInfo>list=userSellInfoDao.selectList(wrapper);
        map.put("userSellInfo",list);
        return map;
    }

    @Override
    public Map getNoUserSellInfo() {
        Map map=new HashMap();
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.ne("state",0);
        List<UserSellInfo>list=userSellInfoDao.selectList(wrapper);
        map.put("userSellInfo",list);
        return map;
    }

    @Override
    public Map getDeleteUserSellInfo() {
        Map map=new HashMap();
        List<UserSellInfo>list=userSellInfoDao.getDeleteUserInfo();
        map.put("userSellInfo",list);
        return map;
    }

    @Override
    public void AllToNo(Integer id) {
        UserSellInfo userSellInfo=userSellInfoDao.selectById(id);
        userSellInfo.setState(1);
        userSellInfoDao.updateById(userSellInfo);
    }

    @Override
    public void AllToDelete(Integer id) {
        userSellInfoDao.deleteById(id);
    }

    @Override
    public Map NoToNo(Integer id) {
        Map map=new HashMap();
        UserSellInfo userSellInfo = userSellInfoDao.selectById(id);
        Integer state=userSellInfo.getState();
        if (state>=3){
            userSellInfoDao.deleteReallyById(id);
            map.put("code",2);
            map.put("message","超过三次未接，已删除该条信息！！！");
        }else {
            userSellInfo.setState(state+1);
            userSellInfoDao.updateById(userSellInfo);
            map.put("code",1);
            map.put("message","成功！！！");
        }
        return map;
    }

    @Override
    public void deleteOne(Integer id) {
        userSellInfoDao.deleteReallyById(id);
    }

    @Override
    public void deleteByIds(Integer[] ids) {
        for (int i=0;i<ids.length;i++){
            userSellInfoDao.deleteReallyById(ids[i]);
        }
    }

}

