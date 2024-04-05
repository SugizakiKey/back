package com.back.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.back.dao.CarInventoryDao;
import com.back.dao.CustomerDao;
import com.back.dao.SellscheduleDao;
import com.back.entity.CarInventory;
import com.back.entity.Customer;
import com.back.entity.Sellschedule;
import com.back.service.SellscheduleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * (Sellschedule)表服务实现类
 *
 * @author songjie
 * @since 2022-11-17 23:22:31
 */
@Slf4j
@Service("sellscheduleService")
public class SellscheduleServiceImpl extends ServiceImpl<SellscheduleDao, Sellschedule> implements SellscheduleService {

    @Resource
    private SellscheduleDao sellscheduleDao;
    @Resource
    private CarInventoryDao carInventoryDao;
    @Resource
    private CustomerDao customerDao;

    @Override
    public Map saveSellschedule(Sellschedule sellschedule) {
        Map map=new HashMap();
        LambdaQueryWrapper<CarInventory> carInventoryLambdaQueryWrapper=new LambdaQueryWrapper<>();
        carInventoryLambdaQueryWrapper.eq(CarInventory::getInventoryid,sellschedule.getInventoryid());
        CarInventory carInventory = carInventoryDao.selectOne(carInventoryLambdaQueryWrapper);
        if (carInventory.getState().equals("预定")){
            map.put("code",0);
            map.put("message","该车辆已被预定！！！");
        }else {
            LambdaQueryWrapper<Customer>customerLambdaQueryWrapper=new LambdaQueryWrapper<>();
            customerLambdaQueryWrapper.eq(Customer::getCustomerphone,sellschedule.getBuyuserphone());
            Customer customer = customerDao.selectOne(customerLambdaQueryWrapper);
            sellschedule.setCustomerid(customer.getCustomerid());
            sellscheduleDao.insert(sellschedule);
            map.put("code",1);
            map.put("message","新增预定成功！！！");
            carInventory.setState("预定");
            carInventoryDao.updateById(carInventory);
        }
        return map;
    }

    @Override
    public Map getSellscheduleDate(String inventoryid) {
        LambdaQueryWrapper<Sellschedule> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Sellschedule::getInventoryid,inventoryid);
        Sellschedule sellschedule = sellscheduleDao.selectOne(wrapper);
        Map map=new HashMap();
        map.put("date",sellschedule.getScheduledate());
        return map;
    }
}

