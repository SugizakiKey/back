package com.back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.back.entity.AdminRoles;

import java.util.Map;

/**
 * (AdminRoles)表服务接口
 *
 * @author songjie
 * @since 2022-10-14 17:36:51
 */
public interface AdminRolesService extends IService<AdminRoles> {

    Map getRolesByAccount(String account);
    Map getRolesByAccountList(String accountList[]);
}

