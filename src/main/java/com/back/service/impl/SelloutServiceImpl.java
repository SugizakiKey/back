package com.back.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.back.dao.*;
import com.back.entity.*;
import com.back.service.CustomerService;
import com.back.service.SelloutService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (Sellout)表服务实现类
 *
 * @author songjie
 * @since 2022-11-25 19:51:57
 */
@Slf4j
@Service("selloutService")
public class SelloutServiceImpl extends ServiceImpl<SelloutDao, Sellout> implements SelloutService {

    @Resource
    private SelloutDao selloutDao;
    @Resource
    private CarInventoryDao carInventoryDao;
    @Resource
    private TransferDao transferDao;
    @Resource
    private CarCollectionDao carCollectionDao;
    @Resource
    private CustomerDao customerDao;
    @Resource
    private CustomerService customerService;


    @Override
    public Map saveSellOut(Sellout sellout) {
        Map map=new HashMap();
        LambdaQueryWrapper<Sellout> selloutLambdaQueryWrapper=new LambdaQueryWrapper<>();
        selloutLambdaQueryWrapper.eq(Sellout::getInventoryid,sellout.getInventoryid());
        Sellout sellout1 = selloutDao.selectOne(selloutLambdaQueryWrapper);
        if (sellout1==null){
            selloutDao.insert(sellout);
            map.put("code",1);
            map.put("message","出库成功！！！");

            LambdaQueryWrapper<CarInventory> carInventoryLambdaQueryWrapper=new LambdaQueryWrapper<>();
            carInventoryLambdaQueryWrapper.eq(CarInventory::getInventoryid,sellout.getInventoryid());
            CarInventory carInventory = carInventoryDao.selectOne(carInventoryLambdaQueryWrapper);
            carInventory.setState("出库");
            carInventoryDao.updateById(carInventory);
            carInventoryDao.delete(carInventoryLambdaQueryWrapper);


            //将第一次收款记录生成到collection
            if (sellout.getNowcollection()!=null){
                CarCollection carCollection = new CarCollection();
                carCollection.setSelloutid(sellout.getId());
                carCollection.setInventoryid(sellout.getInventoryid());
                carCollection.setBranddept(sellout.getBranddept());
                carCollection.setMustcollection(sellout.getMustcollection());
                carCollection.setHadcollection(sellout.getHadcollection());
                carCollection.setLeftcollection(sellout.getLeftcollection());
                carCollection.setNowcollection(sellout.getNowcollection());
                carCollection.setCollectionproject("首次付款");
                carCollection.setCollectiondate(sellout.getGmtCreate());
                carCollection.setPersoninchargeaccount(sellout.getPersoninchargeaccount());
                carCollection.setCollectiontext(sellout.getCollectiontext());
                LambdaQueryWrapper<Customer>customerLambdaQueryWrapper=new LambdaQueryWrapper<>();
                customerLambdaQueryWrapper.eq(Customer::getCustomerphone,sellout.getBuyuserphone());
                Customer customer = customerDao.selectOne(customerLambdaQueryWrapper);

                if (customer==null){
                    //新建一个客户
                    Customer customer1 = new Customer();
                    Map customeridMap = customerService.getCustomerid();
                    String  customerid = (String) customeridMap.get("customerid");
                    customer1.setCustomerid(customerid);
                    customer1.setCustomername(sellout.getBargainuseraccount());
                    customer1.setCustomerphone(sellout.getBuyuserphone());
                    customer1.setGrade("H");
                    customerDao.insert(customer1);
                    carCollection.setCustomerid(customerid);
                }else {
                    carCollection.setCustomerid(customer.getCustomerid());
                }


                carCollection.setCustomerid(customer.getCustomerid());
                carCollectionDao.insert(carCollection);
            }


            //自动生成默认过户表单！！！
            LambdaQueryWrapper<Transfer> queryWrapper=new LambdaQueryWrapper();
            queryWrapper.eq(Transfer::getInventoryid,sellout.getInventoryid());
            queryWrapper.eq(Transfer::getType,"销售过户");
            Integer integer = transferDao.selectCount(queryWrapper);
            if (integer==0){
                Transfer transfer=new Transfer();
                transfer.setType("销售过户");
                transfer.setInventoryid(sellout.getInventoryid());
                transfer.setBranddept(sellout.getBranddept());
                transfer.setTransfertype("过户");
                transfer.setTransferarea("本地");
                transfer.setTransferstate("过户中");
                transfer.setIsurgent("普通");
                transfer.setCarexamination("待处理");
                transfer.setLicenseplate("待处理");
                transfer.setShowcards("待处理");
                transfer.setViolationhandling("待处理");
                transfer.setDataprocessing("待处理");
                transferDao.insert(transfer);
            }



        }else {
            map.put("code",0);
            map.put("message","该车辆已出库，请勿重复出库！！！");
        }
        return map;
    }

    @Override
    public Map getAllSellOut() {
        List<Sellout> sellouts = selloutDao.getRealSellouts();
        Map map=new HashMap();
        map.put("list",sellouts);
        return map;
    }

    @Override
    public void setIsPass(Integer id, Boolean flag) {
        if (flag){
            ////通过  逻辑删除
            selloutDao.deleteById(id);
        }else {
            //未通过  真删
            Sellout sellout = selloutDao.selectById(id);
            String inventoryid=sellout.getInventoryid();
            LambdaQueryWrapper<CarCollection>carCollectionLambdaQueryWrapper=new LambdaQueryWrapper<>();
            carCollectionLambdaQueryWrapper.eq(CarCollection::getInventoryid,inventoryid);
            carCollectionDao.delete(carCollectionLambdaQueryWrapper);
            carInventoryDao.realUpdateStateDeleted(inventoryid);
            selloutDao.realDeleteById(id);
        }
    }

    @Override
    public Map getPassSellOut() {
        Map map=new HashMap();
        List<Sellout> passSellouts = selloutDao.getPassSellouts();
        map.put("list",passSellouts);
        return map;
    }

    @Override
    public Map getSellOutById(Integer id) {
        Sellout sellOutById = selloutDao.getSellOutById(id);
        LambdaQueryWrapper<CarCollection>wrapper=new LambdaQueryWrapper<>();
        List<CarCollection> list = carCollectionDao.selectList(wrapper);
        CarCollection carCollection = list.get(list.size() - 1);
        sellOutById.setHadcollection(carCollection.getHadcollection());
        sellOutById.setLeftcollection(carCollection.getLeftcollection());
        Map map=new HashMap();
        map.put("sellout",sellOutById);
        return map;
    }

    @Override
    public void RealDeleteById(Integer id) {
        selloutDao.realDeleteById(id);
    }


}

