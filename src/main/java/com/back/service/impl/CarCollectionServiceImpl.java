package com.back.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.back.dao.CarCollectionDao;
import com.back.dao.CustomerDao;
import com.back.dao.SelloutDao;
import com.back.entity.CarCollection;
import com.back.entity.Customer;
import com.back.entity.Sellout;
import com.back.service.CarCollectionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (CarCollection)表服务实现类
 *
 * @author songjie
 * @since 2022-11-29 20:19:39
 */
@Service("carCollectionService")
public class CarCollectionServiceImpl extends ServiceImpl<CarCollectionDao, CarCollection> implements CarCollectionService {

    @Resource
    private CarCollectionDao carCollectionDao;
    @Resource
    private CustomerDao customerDao;
    @Resource
    private SelloutDao selloutDao;

    @Override
    public Map getCarCollectionByInventoryid(String[] inventoryid) {
        List<CarCollection>list=new ArrayList<>();
        for (int i=0;i<inventoryid.length;i++){
            LambdaQueryWrapper<CarCollection>carCollectionLambdaQueryWrapper=new LambdaQueryWrapper<>();
            carCollectionLambdaQueryWrapper.eq(CarCollection::getInventoryid,inventoryid[i]);
            List<CarCollection> list1 = carCollectionDao.selectList(carCollectionLambdaQueryWrapper);
            if (list1.size()!=0){
                CarCollection carCollection = list1.get(list1.size() - 1);
                list.add(carCollection);
            }else {
                list.add(null);
            }
        }
        Map map=new HashMap();
        map.put("list",list);
        return map;
    }

    @Override
    public Map getCarCollection(String inventoryid) {
        LambdaQueryWrapper<CarCollection>carCollectionLambdaQueryWrapper=new LambdaQueryWrapper<>();
        carCollectionLambdaQueryWrapper.eq(CarCollection::getInventoryid,inventoryid);
        List<CarCollection> list = carCollectionDao.selectList(carCollectionLambdaQueryWrapper);
        Map map=new HashMap();
        map.put("list",list);
        return map;
    }

    @Override
    public void deleteCarCollection(CarCollection carCollection) {
        LambdaQueryWrapper<CarCollection>wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(CarCollection::getInventoryid,carCollection.getInventoryid());
        List<CarCollection> list = carCollectionDao.selectList(wrapper);
        CarCollection lastCarCollection = list.get(list.size() - 1);
        if (lastCarCollection.getId()==carCollection.getId()){
            //是最后一个直接删就好了
            carCollectionDao.deleteById(carCollection.getId());
        }else {
            lastCarCollection.setHadcollection(lastCarCollection.getHadcollection()-carCollection.getNowcollection());
            lastCarCollection.setLeftcollection(lastCarCollection.getLeftcollection()+carCollection.getNowcollection());
            carCollectionDao.updateById(lastCarCollection);
            carCollectionDao.deleteById(carCollection.getId());
        }
    }

    @Override
    public Map getCarCollections() {
        Map map=new HashMap();
        List<List<CarCollection>>list=new ArrayList<>();
        List<Customer> customers=new ArrayList<>();
        List<Sellout> sellouts=new ArrayList<>();
        List<String> inventoryids = carCollectionDao.getInventoryids();
        for (int i=0;i<inventoryids.size();i++){
            LambdaQueryWrapper<CarCollection>carCollectionLambdaQueryWrapper=new LambdaQueryWrapper<>();
            carCollectionLambdaQueryWrapper.eq(CarCollection::getInventoryid,inventoryids.get(i));
            List<CarCollection> carCollections = carCollectionDao.selectList(carCollectionLambdaQueryWrapper);
            list.add(carCollections);

            String customerid = carCollections.get(0).getCustomerid();
            LambdaQueryWrapper<Customer>customerLambdaQueryWrapper=new LambdaQueryWrapper<>();
            customerLambdaQueryWrapper.eq(Customer::getCustomerid,customerid);
            Customer customer = customerDao.selectOne(customerLambdaQueryWrapper);
            customers.add(customer);

            Integer selloutid = carCollections.get(i).getSelloutid();
            Sellout sellOutById = selloutDao.getSellOutById(selloutid);
            sellouts.add(sellOutById);
        }
        map.put("list",list);
        map.put("customers",customers);
        map.put("sellouts",sellouts);
        return map;
    }


}

