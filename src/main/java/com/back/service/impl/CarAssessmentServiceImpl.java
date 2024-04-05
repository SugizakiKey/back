package com.back.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.back.dao.*;
import com.back.entity.CarAssessment;
import com.back.entity.CarCollection;
import com.back.entity.CarInventory;
import com.back.service.CarAssessmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * (CarAssessment)表服务实现类
 *
 * @author songjie
 * @since 2022-10-31 19:51:04
 */
@Slf4j
@Service("carAssessmentService")
public class CarAssessmentServiceImpl extends ServiceImpl<CarAssessmentDao, CarAssessment> implements CarAssessmentService {

    @Resource
    private CarAssessmentDao carAssessmentDao;
    @Resource
    private CarInventoryDao carInventoryDao;
    @Resource
    private IncidentalsDao incidentalsDao;
    @Resource
    private EquipDao equipDao;
    @Resource
    private CarCollectionDao carCollectionDao;

    @Override
    public Map getInventoryid() {
        Map map=new HashMap();
        List<CarAssessment> carAssessments = carAssessmentDao.selectList(null);
        if (carAssessments.size()==0){
            map.put("inventoryid","S00000001");
        }else {
            String lastInventoryid=carAssessments.get(carAssessments.size()-1).getInventoryid();
            String head=lastInventoryid.substring(0,1);
            String body=lastInventoryid.substring(1);
            String newBody= String.valueOf((Integer.valueOf(body)+1));
            int length=newBody.toCharArray().length;
            if (length<=8){
                for (int i=0;i<8-length;i++){
                    newBody="0"+newBody;
                }
            }else {
                System.out.println("库存满了！！！");
            }
            String newInventoryid=head+newBody;
            map.put("inventoryid",newInventoryid);
        }
        return map;
    }

    @Override
    public Map saveCarAssessment(CarAssessment carAssessment) {
        Map map=new HashMap();
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("inventoryid",carAssessment.getInventoryid());
        Integer count = carAssessmentDao.selectCount(wrapper);
        if (count==0){
            carAssessmentDao.insert(carAssessment);
            map.put("code",1);
            map.put("message","新建车辆评估成功！！！");
        }else {
            map.put("code",0);
            map.put("message","已存在该库存编号，请重试！！！");
        }

        return map;
    }

    @Override
    public Map getAllCarAssessment() {
        List<CarAssessment> carAssessments = carAssessmentDao.selectList(null);
        Map map =new HashMap();
        map.put("carAssessments",carAssessments);
        return map;
    }
    @Override
    public void setCarurl(String inventoryid) {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("inventoryid",inventoryid);
        CarAssessment carAssessment = carAssessmentDao.selectOne(wrapper);
        carAssessment.setCarurl("");
        carAssessmentDao.updateById(carAssessment);
    }

    @Override
    public Map getCarAssessmentById(String inventoryid) {
        Map map=new HashMap();
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("inventoryid",inventoryid);
        CarAssessment carAssessment = carAssessmentDao.selectOne(wrapper);
        map.put("carAssessment",carAssessment);
        return map;
    }

    @Override
    public Map setAssessmentState(String inventoryid) {
        Map map=new HashMap();
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("inventoryid",inventoryid);
        Integer integer = carInventoryDao.selectCount(wrapper);
        if (integer!=0){
            map.put("code",0);
            map.put("message","采购订单中已存在，请先去删除再来设为无效！！！");
        }else {
            CarAssessment carAssessment = carAssessmentDao.selectOne(wrapper);
            carAssessment.setAssessmentstate("战败");
            carAssessmentDao.updateById(carAssessment);
            map.put("code",1);
            map.put("message","已设为战败！！！");
        }
        return map;
    }

    @Override
    public Map deleteCarAssessmentById(String inventoryid) {
        Map map=new HashMap();
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("inventoryid",inventoryid);
        CarAssessment carAssessment = carAssessmentDao.selectOne(wrapper);
        if (carAssessment.getAssessmentstate().equals("战败")){
            carAssessmentDao.delete(wrapper);
            map.put("code",1);
            map.put("message","删除成功！！！");
        }else {
            map.put("code",0);
            map.put("message","不能删除未战败的车辆评估信息！！！");
        }
        return map;
    }

    @Override
    public Map getCarAssessmentByIds(String[] inventoryid) {
        ArrayList<CarAssessment> list=new ArrayList<CarAssessment>();
        for (int i=0;i<inventoryid.length;i++){
            QueryWrapper wrapper=new QueryWrapper();
            wrapper.eq("inventoryid",inventoryid[i]);
            list.add(carAssessmentDao.selectOne(wrapper));
        }
        Map map=new HashMap();
        map.put("list",list);
        return map;
    }

    @Override
    public Map getIndexInfo() {
        Map map=new HashMap();

        //评估量
        Integer carAssessmentCount = carAssessmentDao.selectCount(null);

        //入库量
        LambdaQueryWrapper<CarInventory>carInventoryLambdaQueryWrapper=new LambdaQueryWrapper<>();
        carInventoryLambdaQueryWrapper.eq(CarInventory::getState,"在库");
        Integer carInventoryCount = carInventoryDao.selectCount(carInventoryLambdaQueryWrapper);

        //成交量
        List<CarInventory> carInventories = carInventoryDao.getRealList();
        Integer bargainCount = carInventories.size() - carInventoryCount;

        //总成本
        Double allchengben=0.0;
        Double allBuy=carInventoryDao.getTotalPay();
        Double zhichu=incidentalsDao.getTotal1();
        Double shouru=incidentalsDao.getTotal2();
        Double equipCount = equipDao.getTotal();
        if (allBuy==null){
            allBuy=0.0;
        }
        if (zhichu==null){
            zhichu=0.0;
        }
        if (shouru==null){
            shouru=0.0;
        }
        if (equipCount==null){
            equipCount=0.0;
        }

        allchengben=allBuy+zhichu-shouru+equipCount;

        //成交额
        List<String> inventoryids = carCollectionDao.getInventoryids();
        Double allSell=0.0;
        for (int i=0;i<inventoryids.size();i++){
            LambdaQueryWrapper<CarCollection>carCollectionLambdaQueryWrapper=new LambdaQueryWrapper<>();
            carCollectionLambdaQueryWrapper.eq(CarCollection::getInventoryid,inventoryids.get(i));
            List<CarCollection> carCollections = carCollectionDao.selectList(carCollectionLambdaQueryWrapper);
            CarCollection carCollection = carCollections.get(carCollections.size() - 1);
            allSell+=carCollection.getMustcollection();
        }

        map.put("carAssessmentCount",carAssessmentCount);
        map.put("carInventoryCount",carInventoryCount);
        map.put("bargainCount",bargainCount);
        map.put("allchengben",allchengben);
        map.put("allSell",allSell);

        return map;
    }

}

