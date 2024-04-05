package com.back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.back.entity.SellcluesFollow;

import java.util.Map;

/**
 * (SellcluesFollow)表服务接口
 *
 * @author songjie
 * @since 2022-11-28 21:18:08
 */
public interface SellcluesFollowService extends IService<SellcluesFollow> {
    void saveSellCluesFollow(SellcluesFollow sellcluesFollow);
    Map getFollowsByPhone(String phone);
}

