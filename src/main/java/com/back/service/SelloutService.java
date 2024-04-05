package com.back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.back.entity.Sellout;

import java.util.Map;

/**
 * (Sellout)表服务接口
 *
 * @author songjie
 * @since 2022-11-25 19:51:57
 */
public interface SelloutService extends IService<Sellout> {
 Map saveSellOut(Sellout sellout);
 Map getAllSellOut();
 void setIsPass(Integer id,Boolean flag);
 Map getPassSellOut();
 Map getSellOutById(Integer id);
 void RealDeleteById(Integer id);
}

