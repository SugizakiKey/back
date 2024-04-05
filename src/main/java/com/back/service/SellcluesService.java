package com.back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.back.entity.Sellclues;

import java.util.Map;

/**
 * (Sellclues)表服务接口
 *
 * @author songjie
 * @since 2022-11-28 20:22:49
 */
public interface SellcluesService extends IService<Sellclues> {
    Map saveSellClues(Sellclues sellclues);
    Map getSellClues(char flag);
    Map setState(String phone,String state);
    Map deleteSellClues(Integer id);
    Map getOne(Integer id);
}

