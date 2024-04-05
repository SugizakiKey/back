package com.back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.back.entity.CarPay;

import java.util.Map;

/**
 * (CarPay)表服务接口
 *
 * @author songjie
 * @since 2022-11-30 16:42:10
 */
public interface CarPayService extends IService<CarPay> {
Map getCarPays();
Map getCarPayByInventoryid(String inventoryid[]);
}

