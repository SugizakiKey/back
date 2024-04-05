package com.back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.back.entity.CarInventory;

import java.util.Map;

/**
 * (CarInventory)表服务接口
 *
 * @author songjie
 * @since 2022-11-11 17:47:35
 */
public interface CarInventoryService extends IService<CarInventory> {
    Map saveCarInventory(CarInventory carInventory);
    Map getAllCarInventory();
    void setIsPass(String inventoryid,String flag);
    Map deleteRealCarInventoryById(Integer id);
    Map getAllCarInventoryIsPass();
    void updateCarInventoryState(CarInventory carInventory);
    void updateCarInventoryPrice(CarInventory carInventory);
    Map getRealCarInventoryById(Integer id);
    void updateCarStand(CarInventory carInventory);
    Map getCarInventoryIn();
    Map getCarInventoryOut();
}

