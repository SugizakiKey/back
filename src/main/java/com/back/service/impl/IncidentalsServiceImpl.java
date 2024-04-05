package com.back.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.back.dao.CarAssessmentDao;
import com.back.dao.CarInventoryDao;
import com.back.dao.IncidentalsDao;
import com.back.dao.SelloutDao;
import com.back.entity.*;
import com.back.service.IncidentalsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * (Incidentals)表服务实现类
 *
 * @author songjie
 * @since 2022-11-26 16:48:37
 */
@Service("incidentalsService")
public class IncidentalsServiceImpl extends ServiceImpl<IncidentalsDao, Incidentals> implements IncidentalsService {

    @Resource
    private IncidentalsDao incidentalsDao;
    @Resource
    private CarInventoryDao carInventoryDao;
    @Resource
    private SelloutDao selloutDao;
    @Resource
    private CarAssessmentDao carAssessmentDao;


    @Override
    public void saveIncidentals(String inventoryid, String branddept, String personinchargeaccount, Integer rows, String[] costitems,
                                Double[] amounts, String[] types, String[] remarks, String[] dates) {
        for (int i=0;i<rows;i++){
            Incidentals incidentals = new Incidentals();
            incidentals.setInventoryid(inventoryid);
            incidentals.setBranddept(branddept);
            incidentals.setPersoninchargeaccount(personinchargeaccount);
            incidentals.setRows(rows);
            incidentals.setCostitems(costitems[i]);
            incidentals.setAmounts(amounts[i]);
            incidentals.setTypes(types[i]);
            incidentals.setRemarks(remarks[i]);

            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = dates[i];
            try {
                Date date = ft.parse(time);
                incidentals.setDates(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            incidentalsDao.insert(incidentals);
        }
    }

    @Override
    public Map getAllIncidentalsInfo() {
        Map map=new HashMap();
        List<List<Incidentals>>list=new ArrayList<>();
        List<CarInventory>carInventories=new ArrayList<>();
        List<Sellout> sellouts=new ArrayList<>();
        List<Double> totals=new ArrayList<>();
        List<CarAssessment>carAssessments=new ArrayList<>();
        List<String> inventoryids = carInventoryDao.getAllRealInventoryid();
        for (int i=0;i<inventoryids.size();i++){
            LambdaQueryWrapper<Incidentals> incidentalsLambdaQueryWrapper=new LambdaQueryWrapper<>();
            incidentalsLambdaQueryWrapper.eq(Incidentals::getInventoryid,inventoryids.get(i));
            List<Incidentals> incidentals = incidentalsDao.selectList(incidentalsLambdaQueryWrapper);
            list.add(incidentals);


            Double shouru = incidentalsDao.getTotal2ByInventoryid(inventoryids.get(i));
            Double zhichu = incidentalsDao.getTotal1ByInventoryid(inventoryids.get(i));
            if (shouru==null){
                shouru=0.0;
            }
            if (zhichu==null){
                zhichu=0.0;
            }
            Double total = shouru - zhichu;
            totals.add(total);


            CarInventory realByInventoryid = carInventoryDao.getRealByInventoryid(inventoryids.get(i));
            carInventories.add(realByInventoryid);

            Sellout realSelloutByInventoryid = selloutDao.getRealSelloutByInventoryid(inventoryids.get(i));
            sellouts.add(realSelloutByInventoryid);

            LambdaQueryWrapper<CarAssessment>carAssessmentLambdaQueryWrapper=new LambdaQueryWrapper<>();
            carAssessmentLambdaQueryWrapper.eq(CarAssessment::getInventoryid,inventoryids.get(i));
            CarAssessment carAssessment = carAssessmentDao.selectOne(carAssessmentLambdaQueryWrapper);
            carAssessments.add(carAssessment);

        }
        map.put("list",list);
        map.put("carInventories",carInventories);
        map.put("sellouts",sellouts);
        map.put("totals",totals);
        map.put("carAssessments",carAssessments);
        return map;
    }
}

