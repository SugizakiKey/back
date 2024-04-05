package com.back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.back.entity.BuycluesFollow;

import java.util.Map;

/**
 * (BuycluesFollow)表服务接口
 *
 * @author songjie
 * @since 2022-10-27 19:01:38
 */
public interface BuycluesFollowService extends IService<BuycluesFollow> {
void saveBuyCluesFollow(BuycluesFollow buycluesFollow);
Map getFollowsByPhone(String phone);
}

