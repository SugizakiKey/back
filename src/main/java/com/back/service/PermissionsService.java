package com.back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.back.entity.Permissions;

import java.util.Map;

/**
 * (Permissions)表服务接口
 *
 * @author songjie
 * @since 2022-12-02 15:25:33
 */
public interface PermissionsService extends IService<Permissions> {
    Map getAllPermissions();
}

