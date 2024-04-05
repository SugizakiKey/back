package com.back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.back.entity.UserBuyInfo;

import java.util.Map;

/**
 * (UserBuyInfo)表服务接口
 *
 * @author songjie
 * @since 2022-11-28 15:37:12
 */
public interface UserBuyInfoService extends IService<UserBuyInfo> {
 Map saveUserBuyInfo(UserBuyInfo userBuyInfo);
 Map getAllUserBuyInfo();
 Map getNoUserBuyInfo();
 Map getDeleteUserBuyInfo();

 void AllToNo(Integer id);
 void AllToDelete(Integer id);
 Map NoToNo(Integer id);

 void deleteOne(Integer id);
 void deleteByIds(Integer ids[]);
}

