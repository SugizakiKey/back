package com.back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.back.entity.Equip;

import java.util.Map;

/**
 * (Equip)表服务接口
 *
 * @author songjie
 * @since 2022-11-27 14:12:17
 */
public interface EquipService extends IService<Equip> {
    void saveEquip(String inventoryid, String branddept,String carstand,Integer rows,String costitems[], Double amounts[],
                   String remarks[], String startdates[],String enddates[],String types[],String adminaccount);

    Map getEquipByInventoryids(String inventoryid[]);
    Map getEquipById(Integer id);
    void updateEquip(Equip equip);
}

