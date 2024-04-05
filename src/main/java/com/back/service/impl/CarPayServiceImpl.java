package com.back.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.back.dao.CarPayDao;
import com.back.entity.CarPay;
import com.back.service.CarPayService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (CarPay)表服务实现类
 *
 * @author songjie
 * @since 2022-11-30 16:42:10
 */
@Service("carPayService")
public class CarPayServiceImpl extends ServiceImpl<CarPayDao, CarPay> implements CarPayService {

    @Resource
    private CarPayDao carPayDao;

    @Override
    public Map getCarPays() {
        Map map=new HashMap();
        List<List<CarPay>>list=new ArrayList<>();
        List<String> inventoryids = carPayDao.getInventoryids();
        for (int i=0;i<inventoryids.size();i++){
            LambdaQueryWrapper<CarPay>carPayLambdaQueryWrapper=new LambdaQueryWrapper<>();
            carPayLambdaQueryWrapper.eq(CarPay::getInventoryid,inventoryids.get(i));
            List<CarPay> carPays = carPayDao.selectList(carPayLambdaQueryWrapper);
            list.add(carPays);
        }
        map.put("list",list);
        return map;
    }

    @Override
    public Map getCarPayByInventoryid(String[] inventoryid) {
        List<CarPay>list=new ArrayList<>();
        for (int i=0;i<inventoryid.length;i++){
            LambdaQueryWrapper<CarPay> carPayLambdaQueryWrapper=new LambdaQueryWrapper<>();
            carPayLambdaQueryWrapper.eq(CarPay::getInventoryid,inventoryid[i]);
            List<CarPay> carPays = carPayDao.selectList(carPayLambdaQueryWrapper);
            CarPay carPay = carPays.get(carPays.size() - 1);
            list.add(carPay);
        }
        Map map=new HashMap();
        map.put("list",list);
        return map;
    }
}

