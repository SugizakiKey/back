package com.back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.back.entity.Buyclues;

import java.util.Map;

/**
 * (Buyclues)表服务接口
 *
 * @author songjie
 * @since 2022-10-25 23:02:46
 */
public interface BuycluesService extends IService<Buyclues> {
Map saveBuyClues(Buyclues buyclues);
Map getBuyClues(char flag);
Map setState(String phone,String state);
Map deleteBuyClues(Integer id);
Map getOne(Integer id);
}

