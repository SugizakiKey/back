package com.back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.back.entity.Sellschedule;

import java.util.Map;

/**
 * (Sellschedule)表服务接口
 *
 * @author songjie
 * @since 2022-11-17 23:22:31
 */
public interface SellscheduleService extends IService<Sellschedule> {
    Map saveSellschedule(Sellschedule sellschedule);
    Map getSellscheduleDate(String inventoryid);
}

