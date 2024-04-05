package com.back.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.back.dao.CarCollectionDao;
import com.back.dao.CarPayDao;
import com.back.dao.CustomerDao;
import com.back.dao.SellscheduleDao;
import com.back.entity.CarCollection;
import com.back.entity.CarPay;
import com.back.entity.Customer;
import com.back.entity.Sellschedule;
import com.back.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * (Customer)表服务实现类
 *
 * @author songjie
 * @since 2022-11-20 15:33:26
 */
@Slf4j
@Service("customerService")
public class CustomerServiceImpl extends ServiceImpl<CustomerDao, Customer> implements CustomerService {

    @Resource
    private CustomerDao customerDao;
    @Resource
    private CarCollectionDao carCollectionDao;
    @Resource
    private CarPayDao carPayDao;
    @Resource
    private SellscheduleDao sellscheduleDao;

    @Override
    public Map getCustomerid() {
        Map map=new HashMap();
        List<Customer> customers = customerDao.selectList(null);
        if (customers.size()==0){
            map.put("customerid","U00000001");
        }else {
            String lastCustomerid=customers.get(customers.size()-1).getCustomerid();
            String head=lastCustomerid.substring(0,1);
            String body=lastCustomerid.substring(1);
            String newBody= String.valueOf((Integer.valueOf(body)+1));
            int length=newBody.toCharArray().length;
            if (length<=8){
                for (int i=0;i<8-length;i++){
                    newBody="0"+newBody;
                }
            }else {
                System.out.println("库存满了！！！");
            }
            String newCustoemrid=head+newBody;
            map.put("customerid",newCustoemrid);
        }
        return map;
    }

    @Override
    public Map saveCustomer(Customer customer) {
        Map map=new HashMap();
        LambdaQueryWrapper<Customer> queryWrapper=new LambdaQueryWrapper();
        queryWrapper.eq(Customer::getCustomerphone,customer.getCustomerphone());
        Integer integer = customerDao.selectCount(queryWrapper);
        if (integer==0){
            customerDao.insert(customer);
            map.put("code",1);
            map.put("message","新增客户成功！！！");
        }else {
            map.put("code",0);
            map.put("message","已存在该手机号的用户，请重新添加！！！");
        }
        return map;
    }

    @Override
    public Map getAllCustomer() {
        Map map=new HashMap();
        List<Customer> customers = customerDao.selectList(null);
        map.put("customers",customers);
        return map;
    }

    @Override
    public Map getCustomerByPhones(String[] phones) {
        List<Customer>list=new ArrayList<>();
        for (int i=0;i<phones.length;i++){
            LambdaQueryWrapper<Customer> customerLambdaQueryWrapper=new LambdaQueryWrapper<>();
            customerLambdaQueryWrapper.eq(Customer::getCustomerphone,phones[i]);
            Customer customer = customerDao.selectOne(customerLambdaQueryWrapper);
            list.add(customer);
        }
        Map map=new HashMap();
        map.put("list",list);
        return map;
    }

    @Override
    public Map getCustomerById(Integer id) {
        Customer customer = customerDao.selectById(id);
        Map map=new HashMap();
        map.put("customer",customer);
        return map;
    }

    @Override
    public Map updateCustomer(Customer customer) {
        Map map=new HashMap();
        Customer backCustomer = customerDao.selectById(customer.getId());
        if (customer.getCustomerphone().equals(backCustomer.getCustomerphone())){
            //没改手机号，直接修改
            customerDao.updateById(customer);
            map.put("code",1);
            map.put("message","修改成功！！！");
        }else {
            LambdaQueryWrapper<Customer>customerLambdaQueryWrapper=new LambdaQueryWrapper<>();
            customerLambdaQueryWrapper.eq(Customer::getCustomerphone,customer.getCustomerphone());
            Integer integer = customerDao.selectCount(customerLambdaQueryWrapper);
            if (integer==0){
                customerDao.updateById(customer);
                map.put("code",1);
                map.put("message","修改成功！！！");
            }else {
                map.put("code",0);
                map.put("message","已存在该手机号的客户，修改失败！！！");
            }
        }
        return map;
    }

    @Override
    public Map setFail(Integer id) {
        Map map=new HashMap();
        if (isdelete(id)){
            customerDao.deleteById(id);
            map.put("code",1);
            map.put("message","设置成功！！！");
        }else {
            map.put("code",0);
            map.put("message","该客户存在订单，不能设为战败！！！");
        }
        return map;
    }

    @Override
    public Map realDeleteById(Integer id) {
        Map map=new HashMap();
        if (isdelete(id)){
            customerDao.realDeleteById(id);
            map.put("code",1);
            map.put("message","删除成功！！！");
        }else {
            map.put("code",0);
            map.put("message","该客户存在订单，不能删除！！！");
        }
        return map;
    }

    @Override
    public Map getFailCustomer() {
        Map map=new HashMap();
        List<Customer> failCustomer = customerDao.getFailCustomer();
        map.put("failCustomer",failCustomer);
        return map;
    }

    @Override
    public Map setActive(Integer id) {
        Map map=new HashMap();
        Customer realById = customerDao.getRealById(id);
        LambdaQueryWrapper<Customer>customerLambdaQueryWrapper=new LambdaQueryWrapper<>();
        customerLambdaQueryWrapper.eq(Customer::getCustomerphone,realById.getCustomerphone());
        Integer integer = customerDao.selectCount(customerLambdaQueryWrapper);
        if (integer==0){
            customerDao.updateDeleted(id);
            map.put("code",1);
            map.put("message","战败激活成功！！！");
        }else {
            map.put("code",0);
            map.put("message","已存在该手机号的客户，激活失败！！！");
        }
        return map;
    }

    @Override
    public Map getSuccessCustomer() {
        Set<String> customerids=new HashSet<>();
        //收款
        List<CarCollection> list = carCollectionDao.selectList(null);
        for (int i=0;i<list.size();i++){
            customerids.add(list.get(i).getCustomerid());
        }
        //付款
        List<CarPay> carPays = carPayDao.selectList(null);
        for (int i=0;i<carPays.size();i++){
            customerids.add(carPays.get(i).getCustomerid());
        }
        //预约表
        List<Sellschedule> sellschedules = sellscheduleDao.selectList(null);
        for (int i=0;i<sellschedules.size();i++){
            customerids.add(sellschedules.get(i).getCustomerid());
        }
        ArrayList<String> strings = new ArrayList<>(customerids);
        List<Customer>customers=new ArrayList<>();
        for (int i=0;i<strings.size();i++){
            LambdaQueryWrapper<Customer>customerLambdaQueryWrapper=new LambdaQueryWrapper<>();
            customerLambdaQueryWrapper.eq(Customer::getCustomerid,strings.get(i));
            Customer customer = customerDao.selectOne(customerLambdaQueryWrapper);
            customers.add(customer);
        }
        Map map=new HashMap();
        map.put("list",customers);
        return map;
    }

    @Override
    public Map getCustomerCars(String[] customerids) {
        Map map=new HashMap();
        List<List<CarCollection>>list1=new ArrayList<>();
        List<List<CarPay>>list2=new ArrayList<>();
        List<List<Sellschedule>>list3=new ArrayList<>();
        for (int i=0;i<customerids.length;i++){
            List<CarCollection>carCollectionList=new ArrayList<>();
            List<CarPay>carPayList=new ArrayList<>();
            List<Sellschedule>sellscheduleList=new ArrayList<>();
            Set<String>inventoryids=new HashSet<>();
            QueryWrapper wrapper=new QueryWrapper();
            wrapper.eq("customerid",customerids[i]);
            List<CarCollection> collections = carCollectionDao.selectList(wrapper);
            for (int j=0;j<collections.size();j++){
                inventoryids.add(collections.get(j).getInventoryid());
            }
            ArrayList<String> inventoryidStrings = new ArrayList<>(inventoryids);
            QueryWrapper wrapper1=new QueryWrapper();
            for (int j=0;j<inventoryidStrings.size();j++){
                wrapper1.eq("inventoryid",inventoryidStrings.get(j));
                List<CarCollection> list = carCollectionDao.selectList(wrapper1);
                CarCollection carCollection = list.get(list.size() - 1);
                carCollectionList.add(carCollection);
            }
            list1.add(carCollectionList);
            Set<String>inventoryids1=new HashSet<>();
            List<CarPay>carPays=carPayDao.selectList(wrapper);
            for (int j=0;j<carPays.size();j++){
                inventoryids1.add(carPays.get(j).getInventoryid());
            }
            ArrayList<String> inventoryidStrings1 = new ArrayList<>(inventoryids1);
            QueryWrapper wrapper2=new QueryWrapper();
            for (int j=0;j<inventoryidStrings1.size();j++){
                wrapper1.eq("inventoryid",inventoryidStrings1.get(j));
                List<CarPay> list = carPayDao.selectList(wrapper1);
                CarPay carPay = list.get(list.size() - 1);
                carPayList.add(carPay);
            }
            list2.add(carPayList);
            sellscheduleList=sellscheduleDao.selectList(wrapper);
            list3.add(sellscheduleList);

        }
        map.put("list1",list1);
        map.put("list2",list2);
        map.put("list3",list3);

        return map;
    }


    //判断能否删除客户
    public Boolean isdelete(Integer id){
        Customer customer = customerDao.getRealById(id);
        String customerid=customer.getCustomerid();
        //收款表
        LambdaQueryWrapper<CarCollection>carCollectionLambdaQueryWrapper=new LambdaQueryWrapper<>();
        carCollectionLambdaQueryWrapper.eq(CarCollection::getCustomerid,customerid);
        Integer integer = carCollectionDao.selectCount(carCollectionLambdaQueryWrapper);
        //付款表
        LambdaQueryWrapper<CarPay>carPayLambdaQueryWrapper=new LambdaQueryWrapper<>();
        carPayLambdaQueryWrapper.eq(CarPay::getCustomerid,customerid);
        Integer integer1 = carPayDao.selectCount(carPayLambdaQueryWrapper);
        //预约表
        LambdaQueryWrapper<Sellschedule>sellscheduleLambdaQueryWrapper=new LambdaQueryWrapper<>();
        sellscheduleLambdaQueryWrapper.eq(Sellschedule::getCustomerid,customerid);
        Integer integer2 = sellscheduleDao.selectCount(sellscheduleLambdaQueryWrapper);

        if (integer+integer1+integer2==0){
            return true;
        }else {
            return false;
        }
    }

}

