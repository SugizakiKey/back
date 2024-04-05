package com.back.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.back.dao.AdminRolesDao;
import com.back.entity.AdminRoles;
import com.back.service.AdminRolesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * (AdminRoles)表服务实现类
 *
 * @author songjie
 * @since 2022-10-14 17:36:51
 */
@Service("adminRolesService")
public class AdminRolesServiceImpl extends ServiceImpl<AdminRolesDao, AdminRoles> implements AdminRolesService {

    @Resource
    private AdminRolesDao adminRolesDao;

    @Override
    public Map getRolesByAccount(String account) {

        Set<String> roles = new HashSet<String>();
        QueryWrapper<AdminRoles> rolesWrapper=new QueryWrapper<>();
        rolesWrapper.eq("account",account);
        ArrayList<AdminRoles> adminRoles= (ArrayList<AdminRoles>) adminRolesDao.selectList(rolesWrapper);
        for (int i=0;i<adminRoles.size();i++){
            roles.add(adminRoles.get(i).getRoleName());
        }

        Map map=new HashMap();
        map.put("roles",roles);
        return map;
    }

    @Override
    public Map getRolesByAccountList(String[] accountList) {
        List<Set<String>> adminRoles=new ArrayList<Set<String>>();
        for (int i=0;i<accountList.length;i++){
            Set<String> rolesByAccount = (Set<String>) getRolesByAccount(accountList[i]).get("roles");
            adminRoles.add(rolesByAccount);
        }
        Map map=new HashMap();
        map.put("adminRoles",adminRoles);
        return map;
    }
}

