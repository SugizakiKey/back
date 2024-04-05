package com.back.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.back.dao.SellcluesDao;
import com.back.entity.Sellclues;
import com.back.service.SellcluesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (Sellclues)表服务实现类
 *
 * @author songjie
 * @since 2022-11-28 20:22:49
 */
@Slf4j
@Service("sellcluesService")
public class SellcluesServiceImpl extends ServiceImpl<SellcluesDao, Sellclues> implements SellcluesService {

    @Resource
    private SellcluesDao sellcluesDao;

    @Override
    public Map saveSellClues(Sellclues sellclues) {
        Map map=new HashMap();
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("phone",sellclues.getPhone());
        if (sellcluesDao.selectCount(wrapper)==0){
            sellcluesDao.insert(sellclues);
            map.put("code",1);
            map.put("message","新增线索成功！！！");
        }else {
            map.put("code",2);
            map.put("message","已存在该手机号的线索，新增失败！！！");
        }
        return map;
    }

    @Override
    public Map getSellClues(char flag) {
        //flag 0全部 1跟进中 2已分配 3已失效
        Map map=new HashMap();
        List<Sellclues> sellclues;
        QueryWrapper wrapper=new QueryWrapper();
        switch (flag){
            case '0':
                break;
            case '1':
                wrapper.eq("state","跟进中");
                break;
            case '2':
                wrapper.eq("state","已分配");
                break;
            case '3':
                wrapper.eq("state","已失效");
                break;
            default:
                wrapper.eq("grade",flag);
        }
        sellclues=sellcluesDao.selectList(wrapper);
        map.put("list",sellclues);
        return map;
    }

    @Override
    public Map setState(String phone, String state) {
        Map map=new HashMap();
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("phone",phone);
        Sellclues sellclues = sellcluesDao.selectOne(wrapper);
        if (sellclues.getState().equals("跟进中")){
            sellclues.setState(state);
            sellcluesDao.updateById(sellclues);
            map.put("code",1);
            map.put("message","已将该条记录设为无效！！！");
        }else {
            map.put("code",0);
            map.put("message","不能修改不是跟进中的线索状态！！！");
        }
        return map;
    }

    @Override
    public Map deleteSellClues(Integer id) {
        Map map=new HashMap();
        Sellclues sellclues = sellcluesDao.selectById(id);
        if (sellclues.getState().equals("已失效")){
            sellcluesDao.deleteById(id);
            map.put("code",1);
            map.put("message","删除该条线索成功！！！");
        }else {
            map.put("code",0);
            map.put("message","不能删除未失效的线索！！！");
        }
        return map;
    }

    @Override
    public Map getOne(Integer id) {
        Map map=new HashMap();
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("id",id);
        Sellclues sellclues = sellcluesDao.selectOne(wrapper);
        map.put("sellclues",sellclues);
        return map;
    }
}

