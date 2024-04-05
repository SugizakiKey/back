package com.back.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.back.dao.*;
import com.back.entity.*;
import com.back.service.CarInventoryService;
import com.back.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (CarInventory)表服务实现类
 *
 * @author songjie
 * @since 2022-11-11 17:47:35
 */
@Slf4j
@Service("carInventoryService")
public class CarInventoryServiceImpl extends ServiceImpl<CarInventoryDao, CarInventory> implements CarInventoryService {

    @Resource
    private CarInventoryDao carInventoryDao;
    @Resource
    private CarAssessmentDao carAssessmentDao;
    @Resource
    private TransferDao transferDao;
    @Resource
    private CarPayDao carPayDao;
    @Resource
    private CustomerDao customerDao;
    @Resource
    private EquipDao equipDao;
    @Resource
    private IncidentalsDao incidentalsDao;
    @Resource
    private CarCollectionDao carCollectionDao;
    @Resource
    private CustomerService customerService;


    @Override
    public Map saveCarInventory(CarInventory carInventory) {

        Map map=new HashMap();
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("inventoryid",carInventory.getInventoryid());
        Integer integer = carInventoryDao.selectCount(wrapper);
        if (integer==0){
            if (carInventory.getNowpay()!=null){
                carInventory.setHadpay(carInventory.getHadpay()+carInventory.getNowpay());
                carInventory.setLeftpay(carInventory.getLeftpay()-carInventory.getNowpay());
            }
            carInventoryDao.insert(carInventory);
            map.put("code",1);
            map.put("message","提交入库申请成功！！！");
        }else {
            map.put("code",0);
            map.put("message","该车辆已提交入库申请，请不要重复提交！！！");
        }
        return map;
    }

    @Override
    public Map getAllCarInventory() {
        //List<CarInventory> carInventories = carInventoryDao.selectList(null);

        //获取所有数据，包括已经逻辑删除掉的
        List<CarInventory> carInventories=carInventoryDao.getRealList();
        Map map=new HashMap();
        map.put("list",carInventories);
        return map;
    }

    @Override
    public void setIsPass(String inventoryid, String flag) {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("inventoryid",inventoryid);
        CarInventory carInventory = carInventoryDao.selectOne(wrapper);
        carInventory.setIspass(flag);
        carInventoryDao.updateById(carInventory);
        if (flag.equals("已通过")){
            CarAssessment carAssessment = carAssessmentDao.selectOne(wrapper);
            carAssessment.setAssessmentstate("成功");
            carAssessmentDao.updateById(carAssessment);

            //生成第一笔付款记录
            if (carInventory.getNowpay()!=null){
                CarPay carPay = new CarPay();
                carPay.setUserphone(carAssessment.getPhone());
                carPay.setInventoryid(inventoryid);
                carPay.setBranddept(carAssessment.getBranddept());
                carPay.setMustpay(carInventory.getMustpay());
                carPay.setHadpay(carInventory.getHadpay());
                carPay.setLeftpay(carInventory.getLeftpay());
                carPay.setNowpay(carInventory.getNowpay());
                carPay.setPayproject("首次付款");
                carPay.setPaydate(carInventory.getGmtModified());
                carPay.setPersoninchargeaccount(carInventory.getPersoninchargeaccount());
                carPay.setPaytext("首次付款记录");
                LambdaQueryWrapper<Customer>customerLambdaQueryWrapper=new LambdaQueryWrapper<>();
                customerLambdaQueryWrapper.eq(Customer::getCustomerphone,carAssessment.getPhone());
                Customer customer = customerDao.selectOne(customerLambdaQueryWrapper);
                if (customer==null){
                    //新建一个客户
                    Customer customer1 = new Customer();
                    Map customeridMap = customerService.getCustomerid();
                    String  customerid = (String) customeridMap.get("customerid");
                    customer1.setCustomerid(customerid);
                    customer1.setCustomername(carAssessment.getUsername());
                    customer1.setCustomerphone(carAssessment.getPhone());
                    customer1.setGrade(carAssessment.getGrade());
                    customerDao.insert(customer1);
                    carPay.setCustomerid(customerid);
                }else {
                    carPay.setCustomerid(customer.getCustomerid());
                }
                carPayDao.insert(carPay);
            }


            //默认生成采购过户
            LambdaQueryWrapper<Transfer>lambdaQueryWrapper=new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(Transfer::getInventoryid,inventoryid);
            lambdaQueryWrapper.eq(Transfer::getType,"采购过户");
            Integer integer = transferDao.selectCount(lambdaQueryWrapper);
            if (integer==0){
                Transfer transfer=new Transfer();
                transfer.setType("采购过户");
                transfer.setInventoryid(inventoryid);
                transfer.setBranddept(carAssessment.getBranddept());
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

        }
        if (flag.equals("未通过")){
            carInventoryDao.delete(wrapper);
        }
    }

    @Override
    public Map deleteRealCarInventoryById(Integer id) {
        Map map=new HashMap();
        CarInventory carInventory = carInventoryDao.selectById(id);
        if (carInventory==null){
            carInventoryDao.realDeleteCarInventoryById(id);
            map.put("code",1);
            map.put("message","删除成功！！！");
        }else {
            map.put("code",0);
            map.put("message","不能删除不是未通过的记录！！！");
        }
        return map;
    }

    @Override
    public Map getAllCarInventoryIsPass() {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("ispass","已通过");
        List list = carInventoryDao.selectList(wrapper);
        Map map=new HashMap();
        map.put("list",list);
        return map;
    }

    @Override
    public void updateCarInventoryState(CarInventory carInventory) {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("inventoryid",carInventory.getInventoryid());
        CarInventory carInventory1 = carInventoryDao.selectOne(wrapper);
        carInventory1.setState(carInventory.getState());
        carInventory1.setIsshow(carInventory.getIsshow());
        carInventory1.setStatetext(carInventory.getStatetext());
        carInventoryDao.updateById(carInventory1);
    }

    @Override
    public void updateCarInventoryPrice(CarInventory carInventory) {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("inventoryid",carInventory.getInventoryid());
        CarInventory carInventory1 = carInventoryDao.selectOne(wrapper);
        carInventory1.setShowroomprice(carInventory.getShowroomprice());
        carInventory1.setWebprice(carInventory.getWebprice());
        carInventory1.setSellminprice(carInventory.getSellminprice());
        carInventory1.setManagerminprice(carInventory.getManagerminprice());
        carInventory1.setChangepricetext(carInventory.getChangepricetext());
        carInventoryDao.updateById(carInventory1);
    }

    @Override
    public Map getRealCarInventoryById(Integer id) {
        Map map=new HashMap();
        CarInventory carInventory = carInventoryDao.getReal(id);
        map.put("carinventory",carInventory);
        return map;
    }

    @Override
    public void updateCarStand(CarInventory carInventory) {
        CarInventory carInventory1 = carInventoryDao.selectById(carInventory.getId());
        carInventory1.setCarstand(carInventory.getCarstand());
        carInventoryDao.updateById(carInventory1);
    }

    @Override
    public Map getCarInventoryIn() {
        LambdaQueryWrapper<CarInventory>carInventoryLambdaQueryWrapper=new LambdaQueryWrapper<>();
        carInventoryLambdaQueryWrapper.eq(CarInventory::getState,"在库");
        List<CarInventory> carInventories = carInventoryDao.selectList(carInventoryLambdaQueryWrapper);
        Map map=new HashMap();
        map.put("carInventories",carInventories);
        return map;
    }

    @Override
    public Map getCarInventoryOut() {
        Map map=new HashMap();
        List<CarInventory> realOut = carInventoryDao.getRealOut();
        List<Double>totalequips=new ArrayList<>();
        List<Double>totalincidentals=new ArrayList<>();
        List<CarCollection>carCollections=new ArrayList<>();
        List<CarPay>carPays=new ArrayList<>();
        for (int i=0;i<realOut.size();i++){
            String inventoryid=realOut.get(i).getInventoryid();

            Double totalByInventoryid = equipDao.getTotalByInventoryid(inventoryid);
            if (totalByInventoryid==null){
                totalByInventoryid=0.0;
            }
            totalequips.add(totalByInventoryid);

            Double total1ByInventoryid = incidentalsDao.getTotal1ByInventoryid(inventoryid);
            Double total2ByInventoryid = incidentalsDao.getTotal2ByInventoryid(inventoryid);
            if (total1ByInventoryid==null){
                total1ByInventoryid=0.0;
            }
            if (total2ByInventoryid==null){
                total2ByInventoryid=0.0;
            }
            Double total21=total2ByInventoryid-total1ByInventoryid;
            totalincidentals.add(total21);

            LambdaQueryWrapper<CarCollection>carCollectionLambdaQueryWrapper=new LambdaQueryWrapper<>();
            carCollectionLambdaQueryWrapper.eq(CarCollection::getInventoryid,inventoryid);
            List<CarCollection> carCollections1 = carCollectionDao.selectList(carCollectionLambdaQueryWrapper);
            if (carCollections1.size()==0){
                carCollections.add(null);
            }else {
                CarCollection carCollection = carCollections1.get(carCollections1.size() - 1);
                carCollections.add(carCollection);
            }

            LambdaQueryWrapper<CarPay>carPayLambdaQueryWrapper=new LambdaQueryWrapper<>();
            carPayLambdaQueryWrapper.eq(CarPay::getInventoryid,inventoryid);
            List<CarPay> carPays1 = carPayDao.selectList(carPayLambdaQueryWrapper);
            if (carPays1.size()==0){
                carPays.add(null);
            }else {
                CarPay carPay = carPays1.get(carPays1.size() - 1);
                carPays.add(carPay);
            }
        }
        map.put("carinventorys",realOut);
        map.put("totalequips",totalequips);
        map.put("totalincidentals",totalincidentals);
        map.put("carCollections",carCollections);
        map.put("carPays",carPays);


        return map;
    }


}

