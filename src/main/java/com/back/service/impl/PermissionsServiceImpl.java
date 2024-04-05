package com.back.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.back.dao.PermissionsDao;
import com.back.entity.Permissions;
import com.back.service.PermissionsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (Permissions)表服务实现类
 *
 * @author songjie
 * @since 2022-12-02 15:25:33
 */
@Service("permissionsService")
public class PermissionsServiceImpl extends ServiceImpl<PermissionsDao, Permissions> implements PermissionsService {

    @Resource
    private PermissionsDao permissionsDao;

    @Override
    public Map getAllPermissions() {
        Map map=new HashMap();
        List<List<Permissions>>list=new ArrayList<>();
        LambdaQueryWrapper<Permissions>fatherWrapper=new LambdaQueryWrapper<>();
        fatherWrapper.eq(Permissions::getFatherid,0);
        List<Permissions> fatherPermissions = permissionsDao.selectList(fatherWrapper);

        for (int i=0;i<fatherPermissions.size();i++){
            Integer fatherid=fatherPermissions.get(i).getId();
            LambdaQueryWrapper<Permissions>wrapper=new LambdaQueryWrapper<>();
            wrapper.eq(Permissions::getFatherid,fatherid);
            List<Permissions> permissions = permissionsDao.selectList(wrapper);
            list.add(permissions);
        }


        map.put("fatherPermissions",fatherPermissions);
        map.put("list",list);

        return map;
    }
}

