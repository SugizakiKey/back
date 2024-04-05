package com.back.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.back.dao.PermissionsDao;
import com.back.dao.RolesPermissionsDao;
import com.back.entity.Permissions;
import com.back.entity.RolesPermissions;
import com.back.service.RolesPermissionsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * (RolesPermissions)表服务实现类
 *
 * @author songjie
 * @since 2022-10-14 17:37:10
 */
@Service("rolesPermissionsService")
public class RolesPermissionsServiceImpl extends ServiceImpl<RolesPermissionsDao, RolesPermissions> implements RolesPermissionsService {

    @Resource
    private RolesPermissionsDao rolesPermissionsDao;
    @Resource
    private PermissionsDao permissionsDao;

    @Override
    public Set<String> getPermissionsByRoleName(Set<String> roles) {
        Set<String> permissions= new HashSet<String>();

        ArrayList<String>rolesList=new ArrayList<>(roles);
        for (int i=0;i<rolesList.size();i++){
            String roleName=rolesList.get(i);
            QueryWrapper<RolesPermissions> permissionWrapper=new QueryWrapper<>();
            permissionWrapper.eq("role_name",roleName);
            ArrayList<RolesPermissions> permissionsList=(ArrayList<RolesPermissions>)rolesPermissionsDao.selectList(permissionWrapper);
            for (int j=0;j<permissionsList.size();j++){
                permissions.add(permissionsList.get(j).getPermisson());
            }
        }
        return permissions;
    }

    @Override
    public Map saveRolesPermissions(String roleName, Integer[] permissionids) {

        Map map=new HashMap();
        LambdaQueryWrapper<RolesPermissions>wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(RolesPermissions::getRoleName,roleName);
        Integer integer = rolesPermissionsDao.selectCount(wrapper);
        if (integer==0){
            //保存
            for (int i=0;i<permissionids.length;i++){
                RolesPermissions rolesPermissions = new RolesPermissions();
                rolesPermissions.setRoleName(roleName);

                LambdaQueryWrapper<Permissions>permissionsLambdaQueryWrapper=new LambdaQueryWrapper<>();
                permissionsLambdaQueryWrapper.eq(Permissions::getId,permissionids[i]);
                Permissions permissions = permissionsDao.selectOne(permissionsLambdaQueryWrapper);

                if (permissions.getFatherid()!=0){
                    rolesPermissions.setPermissionid(permissionids[i]);
                    rolesPermissions.setPermisson(permissions.getPermisson());
                    rolesPermissionsDao.insert(rolesPermissions);
                }
            }
            map.put("code",1);
            map.put("message","新增角色成功！！！");
        }else {
            map.put("code",0);
            map.put("message","已存在该角色名，请重新提交！！！");
        }

        return map;
    }

    @Override
    public Map getAllRoleNames() {
        Map map=new HashMap();

        List<String> allRoleNames = rolesPermissionsDao.getAllRoleNames();
        map.put("allRoleNames",allRoleNames);

        return map;
    }

    @Override
    public Map getPermissionsByRoleName(String roleName) {
        Map map=new HashMap();
        LambdaQueryWrapper<RolesPermissions>rolesPermissionsLambdaQueryWrapper=new LambdaQueryWrapper<>();
        rolesPermissionsLambdaQueryWrapper.eq(RolesPermissions::getRoleName,roleName);
        List<RolesPermissions> rolesPermissions = rolesPermissionsDao.selectList(rolesPermissionsLambdaQueryWrapper);
        map.put("rolesPermissions",rolesPermissions);
        return map;
    }

    @Override
    public void updateRolesPermissions(String roleName, Integer[] permissionids) {

        ArrayList<Integer> integers = new ArrayList<Integer>(Arrays.asList(permissionids));

        LambdaQueryWrapper<RolesPermissions>rolesPermissionsLambdaQueryWrapper=new LambdaQueryWrapper<>();
        rolesPermissionsLambdaQueryWrapper.eq(RolesPermissions::getRoleName,roleName);
        List<RolesPermissions> rolesPermissions = rolesPermissionsDao.selectList(rolesPermissionsLambdaQueryWrapper);

        for (int i=0;i<rolesPermissions.size();i++){
            RolesPermissions rolesPermissions1 = rolesPermissions.get(i);
            Integer permissionid=rolesPermissions1.getPermissionid();
            Boolean flag=false;
            for (int j=0;j<integers.size();j++){
                if (permissionid==integers.get(j)){
                    integers.remove(j);
                    flag=true;
                    break;
                }
            }
            if (!flag){
                rolesPermissionsDao.deleteById(rolesPermissions1.getId());
            }
        }
        for (int i=0;i<integers.size();i++){
            RolesPermissions rolesPermissions1 = new RolesPermissions();
            rolesPermissions1.setRoleName(roleName);
            rolesPermissions1.setPermissionid(integers.get(i));
            Permissions permissions = permissionsDao.selectById(integers.get(i));
            if (permissions.getFatherid()!=0){
                rolesPermissions1.setPermisson(permissions.getPermisson());
                rolesPermissionsDao.insert(rolesPermissions1);
            }
        }

    }

    @Override
    public void deleteRolesByRoleName(String roleName) {
        LambdaQueryWrapper<RolesPermissions>rolesPermissionsLambdaQueryWrapper=new LambdaQueryWrapper<>();
        rolesPermissionsLambdaQueryWrapper.eq(RolesPermissions::getRoleName,roleName);
        rolesPermissionsDao.delete(rolesPermissionsLambdaQueryWrapper);
    }

    @Override
    public Map getHeadInfo(String roleName) {

        LambdaQueryWrapper<RolesPermissions>rolesPermissionsLambdaQueryWrapper=new LambdaQueryWrapper<>();
        rolesPermissionsLambdaQueryWrapper.eq(RolesPermissions::getRoleName,roleName);
        List<RolesPermissions> rolesPermissions = rolesPermissionsDao.selectList(rolesPermissionsLambdaQueryWrapper);
        List<Permissions>list1=new ArrayList<>();
        List<Permissions>list2=new ArrayList<>();
        List<Permissions>list3=new ArrayList<>();
        List<Permissions>list4=new ArrayList<>();
        List<Permissions>list5=new ArrayList<>();
        List<Permissions>list6=new ArrayList<>();
        List<Permissions>list7=new ArrayList<>();
        for (int i=0;i<rolesPermissions.size();i++){
            Integer permissionid = rolesPermissions.get(i).getPermissionid();
            Permissions permissions = permissionsDao.selectById(permissionid);
            switch (permissions.getFatherid()){
                case 1:
                    list1.add(permissions);
                    break;
                case 2:
                    list2.add(permissions);
                    break;
                case 3:
                    list3.add(permissions);
                    break;
                case 4:
                    list4.add(permissions);
                    break;
                case 5:
                    list5.add(permissions);
                    break;
                case 6:
                    list6.add(permissions);
                    break;
                case 7:
                    list7.add(permissions);
                    break;
            }
        }
        Map map=new HashMap();
        map.put("list1",list1);
        map.put("list2",list2);
        map.put("list3",list3);
        map.put("list4",list4);
        map.put("list5",list5);
        map.put("list6",list6);
        map.put("list7",list7);

        return map;
    }
}

