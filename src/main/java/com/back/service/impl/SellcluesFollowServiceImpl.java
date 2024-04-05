package com.back.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.back.dao.SellcluesDao;
import com.back.dao.SellcluesFollowDao;
import com.back.entity.Sellclues;
import com.back.entity.SellcluesFollow;
import com.back.service.SellcluesFollowService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (SellcluesFollow)表服务实现类
 *
 * @author songjie
 * @since 2022-11-28 21:18:08
 */
@Service("sellcluesFollowService")
public class SellcluesFollowServiceImpl extends ServiceImpl<SellcluesFollowDao, SellcluesFollow> implements SellcluesFollowService {

    @Resource
    private SellcluesFollowDao sellcluesFollowDao;
    @Resource
    private SellcluesDao sellcluesDao;

    @Override
    public void saveSellCluesFollow(SellcluesFollow sellcluesFollow) {
        sellcluesFollowDao.insert(sellcluesFollow);
        String phone=sellcluesFollow.getPhone();
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("phone",phone);
        Sellclues sellclues = sellcluesDao.selectOne(wrapper);
        String grade=sellcluesFollow.getGrade();
        sellclues.setGrade(grade);
        if (sellcluesFollow.isIscustomer()){
            sellclues.setAdminaccount(sellcluesFollow.getAdminaccount());
            sellclues.setState("已分配");
        }
        sellclues.setCount(sellclues.getCount()+1);
        sellcluesDao.updateById(sellclues);
    }

    @Override
    public Map getFollowsByPhone(String phone) {
        Map map=new HashMap();
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("phone",phone);
        List list = sellcluesFollowDao.selectList(wrapper);
        map.put("list",list);
        return map;
    }
}

