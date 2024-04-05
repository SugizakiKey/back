package com.back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.back.entity.CarCollection;

import java.util.Map;

/**
 * (CarCollection)表服务接口
 *
 * @author songjie
 * @since 2022-11-29 20:19:39
 */
public interface CarCollectionService extends IService<CarCollection> {
Map getCarCollectionByInventoryid(String inventoryid[]);
Map getCarCollection(String inventoryid);
void deleteCarCollection(CarCollection carCollection);
Map getCarCollections();

}

