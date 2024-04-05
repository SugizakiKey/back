package com.back.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.back.dao.CarCollectionDao;
import com.back.dao.CarInventoryDao;
import com.back.dao.ReturncarDao;
import com.back.dao.SelloutDao;
import com.back.entity.CarCollection;
import com.back.entity.Returncar;
import com.back.service.ReturncarService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (Returncar)表服务实现类
 *
 * @author songjie
 * @since 2022-11-29 16:53:27
 */
@Service("returncarService")
public class ReturncarServiceImpl extends ServiceImpl<ReturncarDao, Returncar> implements ReturncarService {

    @Resource
    private ReturncarDao returncarDao;
    @Resource
    private CarInventoryDao carInventoryDao;
    @Resource
    private SelloutDao selloutDao;
    @Resource
    private CarCollectionDao carCollectionDao;

    @Override
    public void saveReturnCar(Returncar returncar) {
        //新增回库车辆
        returncarDao.insert(returncar);
        //改变在库车辆的状态
        carInventoryDao.realUpdateStateDeleted(returncar.getInventoryid());
        //删除出库车辆  真删
        selloutDao.realDeleteById(returncar.getSelloutid());
        //删除收款信息
        LambdaQueryWrapper<CarCollection>carCollectionLambdaQueryWrapper=new LambdaQueryWrapper<>();
        carCollectionLambdaQueryWrapper.eq(CarCollection::getInventoryid,returncar.getInventoryid());
        carCollectionDao.delete(carCollectionLambdaQueryWrapper);

    }
}

