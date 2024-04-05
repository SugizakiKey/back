package com.back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.back.entity.UserSellInfo;

import java.util.Map;

/**
 * (UserSellInfo)表服务接口
 *
 * @author songjie
 * @since 2022-10-21 18:57:06
 */
public interface UserSellInfoService extends IService<UserSellInfo> {

    Map saveUserSellInfo(UserSellInfo userSellInfo);
    Map getAllUserSellInfo();
    Map getNoUserSellInfo();
    Map getDeleteUserSellInfo();
    void AllToNo(Integer id);
    void AllToDelete(Integer id);
    Map NoToNo(Integer id);
    void deleteOne(Integer id);
    void deleteByIds(Integer ids[]);
}

