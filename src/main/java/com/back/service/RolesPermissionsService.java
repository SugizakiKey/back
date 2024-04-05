package com.back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.back.entity.RolesPermissions;

import java.util.Map;
import java.util.Set;

/**
 * (RolesPermissions)表服务接口
 *
 * @author songjie
 * @since 2022-10-14 17:37:09
 */
public interface RolesPermissionsService extends IService<RolesPermissions> {

    Set<String>getPermissionsByRoleName(Set<String> roles);
    Map saveRolesPermissions(String roleName,Integer permissionids[]);
    Map getAllRoleNames();
    Map getPermissionsByRoleName(String roleName);
    void updateRolesPermissions(String roleName,Integer permissionids[]);
    void deleteRolesByRoleName(String roleName);
    Map getHeadInfo(String roleName);
}

