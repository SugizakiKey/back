package com.back.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.back.dao.BuycluesDao;
import com.back.entity.Buyclues;
import com.back.service.BuycluesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (Buyclues)表服务实现类
 *
 * @author songjie
 * @since 2022-10-25 23:02:46
 */
@Service("buycluesService")
public class BuycluesServiceImpl extends ServiceImpl<BuycluesDao, Buyclues> implements BuycluesService {

    @Resource
    private BuycluesDao buycluesDao;

    @Override
    public Map saveBuyClues(Buyclues buyclues) {
        Map map=new HashMap();
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("phone",buyclues.getPhone());
        if (buycluesDao.selectCount(wrapper)==0){
            buycluesDao.insert(buyclues);
            map.put("code",1);
            map.put("message","新增线索成功！！！");
        }else {
            map.put("code",2);
            map.put("message","已存在该手机号的线索，新增失败！！！");
        }
        return map;
    }

    @Override
    public Map getBuyClues(char flag) {
        //flag 0全部 1跟进中 2已分配 3已失效
        Map map=new HashMap();
        List<Buyclues> buyclues;
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
        buyclues=buycluesDao.selectList(wrapper);
        map.put("list",buyclues);
        return map;
    }

    @Override
    public Map setState(String phone,String state) {
        Map map=new HashMap();
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("phone",phone);
        Buyclues buyclues=(Buyclues) buycluesDao.selectList(wrapper).get(0);
        if (buyclues.getState().equals("跟进中")){
            buyclues.setState(state);
            buycluesDao.updateById(buyclues);
            map.put("code",1);
            map.put("message","已将该条记录设为无效！！！");
        }else {
            map.put("code",0);
            map.put("message","不能修改不是跟进中的线索状态！！！");
        }
        return map;
    }

    @Override
    public Map deleteBuyClues(Integer id) {
        Map map=new HashMap();
        Buyclues buyclues = buycluesDao.selectById(id);
        if (buyclues.getState().equals("已失效")){
            buycluesDao.deleteById(id);
            map.put("code",1);
            map.put("message","删除该条线索成功！！！");
        }else {
            map.put("code",0);
            map.put("message","不能删除未失效的线索！！！");
        }
        return map;
    }
    @Override
    public Map getOne(Integer id){
        Map map=new HashMap();
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("id",id);
        Buyclues buyclues = buycluesDao.selectOne(wrapper);
        map.put("buyclues",buyclues);
        return map;
    }
}

