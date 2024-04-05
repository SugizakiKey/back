package com.back.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.back.dao.BuycluesDao;
import com.back.dao.BuycluesFollowDao;
import com.back.entity.Buyclues;
import com.back.entity.BuycluesFollow;
import com.back.service.BuycluesFollowService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (BuycluesFollow)表服务实现类
 *
 * @author songjie
 * @since 2022-10-27 19:01:38
 */
@Service("buycluesFollowService")
public class BuycluesFollowServiceImpl extends ServiceImpl<BuycluesFollowDao, BuycluesFollow> implements BuycluesFollowService {

    @Resource
    private BuycluesDao buycluesDao;
    @Resource
    private BuycluesFollowDao buycluesFollowDao;

    @Override
    public void saveBuyCluesFollow(BuycluesFollow buycluesFollow) {
        buycluesFollowDao.insert(buycluesFollow);
        String phone=buycluesFollow.getPhone();
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("phone",phone);
        Buyclues buyclues = (Buyclues) buycluesDao.selectList(wrapper).get(0);
        String grade=buycluesFollow.getGrade();
        buyclues.setGrade(grade);
        if (buycluesFollow.isIscustomer()){
            buyclues.setAdminaccount(buycluesFollow.getAdminaccount());
            //buyclues.setState("已分配");
        }
        buyclues.setCount(buyclues.getCount()+1);
        buycluesDao.updateById(buyclues);
    }

    @Override
    public Map getFollowsByPhone(String phone) {
        Map map=new HashMap();
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("phone",phone);
        List<BuycluesFollow>list=buycluesFollowDao.selectList(wrapper);
        map.put("list",list);
        return map;
    }

}

