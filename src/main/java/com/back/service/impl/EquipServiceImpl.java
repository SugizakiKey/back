package com.back.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.back.dao.EquipDao;
import com.back.entity.Equip;
import com.back.service.EquipService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * (Equip)表服务实现类
 *
 * @author songjie
 * @since 2022-11-27 14:12:17
 */
@Service("equipService")
public class EquipServiceImpl extends ServiceImpl<EquipDao, Equip> implements EquipService {

    @Resource
    private EquipDao equipDao;

    @Override
    public void saveEquip(String inventoryid, String branddept, String carstand, Integer rows, String[] costitems, Double[] amounts,
                          String[] remarks, String[] startdates, String[] enddates, String[] types,String adminaccount) {

        for (int i=0;i<rows;i++){
            Equip equip = new Equip();
            equip.setInventoryid(inventoryid);
            equip.setBranddept(branddept);
            equip.setCarstand(carstand);
            equip.setRows(rows);
            equip.setCostitems(costitems[i]);
            equip.setAmounts(amounts[i]);
            equip.setRemarks(remarks[i]);
            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String start = startdates[i];
            String end=enddates[i];
            try {
                Date starttime = ft.parse(start);
                Date endtime=ft.parse(end);
                equip.setStartdates(starttime);
                equip.setEnddates(endtime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            equip.setTypes(types[i]);
            equip.setAdminaccount(adminaccount);
            equipDao.insert(equip);
        }

    }

    @Override
    public Map getEquipByInventoryids(String inventoryid[]) {
        Map map=new HashMap();
        List<List<Equip>>list=new ArrayList<>();
        List<Double>totals=new ArrayList<>();
        for (int i=0;i<inventoryid.length;i++){
            LambdaQueryWrapper<Equip> equipLambdaQueryWrapper=new LambdaQueryWrapper<>();
            equipLambdaQueryWrapper.eq(Equip::getInventoryid,inventoryid[i]);
            List<Equip> equips = equipDao.selectList(equipLambdaQueryWrapper);
            list.add(equips);

            Double totalByInventoryid = equipDao.getTotalByInventoryid(inventoryid[i]);
            totals.add(totalByInventoryid);

        }
        map.put("list",list);
        map.put("totals",totals);
        return map;
    }

    @Override
    public Map getEquipById(Integer id) {
        Equip equip = equipDao.selectById(id);
        Map map=new HashMap();
        map.put("equip",equip);
        return map;
    }

    @Override
    public void updateEquip(Equip equip) {
        Equip equip1 = equipDao.selectById(equip.getId());
        equip1.setCostitems(equip.getCostitems());
        equip1.setAmounts(equip.getAmounts());
        equip1.setRemarks(equip.getRemarks());
        equip1.setStartdates(equip.getStartdates());
        equip1.setEnddates(equip.getEnddates());
        equip1.setTypes(equip.getTypes());
        equip1.setAdminaccount(equip.getAdminaccount());
        equipDao.updateById(equip1);
    }
}

