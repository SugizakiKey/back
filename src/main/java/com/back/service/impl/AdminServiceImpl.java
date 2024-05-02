package com.back.service.impl;

import com.aliyuncs.exceptions.ClientException;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.back.dao.AdminDao;
import com.back.dao.AdminRolesDao;
import com.back.entity.Admin;
import com.back.entity.AdminRoles;
import com.back.service.AdminRolesService;
import com.back.service.AdminService;
import com.back.service.RolesPermissionsService;
import com.back.shiro.token.JwtToken;
import com.back.utils.JwtUtil;
import com.back.utils.OSSUtil;
import com.back.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;


import java.io.FileNotFoundException;
import java.util.*;

/**
 * (Admin)表服务实现类
 *
 * @author makejava
 * @since 2022-10-08 20:17:50
 */
@Slf4j
@Service("adminService")
public class AdminServiceImpl extends ServiceImpl<AdminDao, Admin> implements AdminService {

    @Resource
    private AdminDao adminDao;
    @Resource
    private AdminRolesService adminRolesService;
    @Resource
    private RolesPermissionsService rolesPermissionsService;
    @Resource
    private AdminRolesDao adminRolesDao;




    @Override
    public Map checkAccount(Admin admin) {


        Map map=new HashMap();
        if (admin.getAccount() == null || admin.getPassword() == null) {
            map.put("code","500");
            map.put("message","账号密码不能为空！");
            return map;
        }
        Subject subject = SecurityUtils.getSubject();
        String token = JwtUtil.getJwtUtil().createJWT(admin.getAccount(), "hziee", "songjie", 1000 *60 * 60 * 24);
        JwtToken jwtToken = new JwtToken(token, admin.getPassword());
        try {
            subject.login(jwtToken);
        } catch (UnknownAccountException e) {
            map.put("code","401");
            map.put("message","账号不存在！！！");
            return map;
        } catch (IncorrectCredentialsException e) {
            map.put("code","402");
            map.put("message","密码错误！！！");
            return map;
        }
        Admin backAdmin=adminDao.selectByAccount(admin.getAccount());
        Set<String> rolesByAccount = (Set<String>)adminRolesService.getRolesByAccount(backAdmin.getAccount()).get("roles");
        Set<String> permissionsByRoleName = rolesPermissionsService.getPermissionsByRoleName(rolesByAccount);
        map.put("code","200");
        map.put("message","登录成功！！！");
        map.put("admin", backAdmin);
        map.put("token", token);
        map.put("roles",rolesByAccount);
        map.put("permissions",permissionsByRoleName);

        log.info(token);


        return map;
    }

    @Override
    //哪怕数据库中的账号已经被逻辑删除，只要存在，就不能注册！
    public Map register(Admin admin) {
        Map map=new HashMap();

        Admin admin1=adminDao.selectByAccount1(admin.getAccount());

        /*QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("account",admin.getAccount());
        log.info(admin.getAccount());
        Integer count = adminDao.selectCount(wrapper);
        log.info(""+count);*/
        if (admin1==null){
            String salty= StringUtil.getStringUtil().salty();
            admin.setSalty(salty);
            admin.setPassword(StringUtil.getStringUtil().md5(admin.getPassword()+salty));
            //添加
            adminDao.insert(admin);
            map.put("code",201);
            map.put("message","注册成功！！！");
        }else {
            map.put("code",404);
            map.put("message","已存在该账号，请重新注册！！！");
        }

        return map;
    }



    @Override
    public Map updatePassword(Admin admin) {
        Map map=new HashMap();
        Admin admin1 = adminDao.selectByAccount(admin.getAccount());
        admin1.setPassword(StringUtil.getStringUtil().md5(admin.getPassword()+admin1.getSalty()));
        adminDao.updateById(admin1);
        map.put("code",202);
        map.put("message","修改密码成功！！！");
        map.put("admin",admin1);
        return map;
    }
    @Override
    public Map updateInfo(MultipartFile imgFile, String account, String name, String phone, String sex, String email){
        Map map=new HashMap();
        Admin admin = adminDao.selectByAccount(account);
        admin.setName(name);
        admin.setPhone(phone);
        if (!(sex.isEmpty())){
            admin.setSex(sex);
        }
        if (!(email.isEmpty())){
            admin.setEmail(email);
        }
        if (!(imgFile==null)){
            //阿里云里的名字
            String objectKey="hello/"+admin.getId()+"/"+"1."+(imgFile.getContentType().substring(imgFile.getContentType().lastIndexOf('/')+1));
            try {
                String url = OSSUtil.getOssUtil().uploadFile(objectKey, imgFile);
                admin.setCircleurl(url);
            } catch (ClientException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
        adminDao.updateById(admin);
        map.put("code",203);
        map.put("message","修改信息成功！！！");
        map.put("admin",admin);
        return map;
    }

    @Override
    public Map getAllAccount() {
        Map map=new HashMap();
        List<String> accountlist = adminDao.getAllAccount();
        map.put("accountlist",accountlist);
        return map;
    }

    @Override
    public Map getAllAdminInfo() {
        List<Admin> admins = adminDao.selectList(null);
        Map map=new HashMap();
        map.put("admins",admins);
        return map;
    }
    //哪怕数据库中的账号已经被逻辑删除，只要存在，就不能注册！
    @Override
    public Map addAdmin(Admin admin, String roleName) {

        Map map=new HashMap();
        Admin admin1=adminDao.selectByAccount1(admin.getAccount());
        if (admin1==null){
            String salty= StringUtil.getStringUtil().salty();
            admin.setSalty(salty);
            admin.setPassword(StringUtil.getStringUtil().md5(admin.getPassword()+salty));
            adminDao.insert(admin);

            AdminRoles adminRoles = new AdminRoles();
            adminRoles.setAccount(admin.getAccount());
            adminRoles.setRoleName(roleName);
            adminRolesDao.insert(adminRoles);
            map.put("code",1);
            map.put("message","新增员工成功！！！");

        }else {
            map.put("code",0);
            map.put("message","已存在该账号！！！");
        }
        return map;
    }

    @Override
    public void resetPassword(Integer id) {
        Admin admin = adminDao.selectById(id);
        String salty = admin.getSalty();
        admin.setPassword(StringUtil.getStringUtil().md5("123456"+salty));
        adminDao.updateById(admin);
    }

    @Override
    public Map getAdminById(Integer id) {

        LambdaQueryWrapper<Admin>adminLambdaQueryWrapper=new LambdaQueryWrapper<>();
        adminLambdaQueryWrapper.eq(Admin::getId,id);
        Admin admin = adminDao.selectOne(adminLambdaQueryWrapper);
        Map map=new HashMap();
        map.put("admin",admin);
        return map;
    }

    @Override
    public void deleteAdminById(Integer id) {
        Admin admin = adminDao.selectById(id);
        adminDao.deleteById(id);
        LambdaQueryWrapper<AdminRoles>adminRolesLambdaQueryWrapper=new LambdaQueryWrapper<>();
        adminRolesLambdaQueryWrapper.eq(AdminRoles::getAccount,admin.getAccount());
        adminRolesDao.delete(adminRolesLambdaQueryWrapper);
    }

    @Override
    public void updateAdmin(Admin admin, String roleName) {
        Admin admin1 = adminDao.selectById(admin.getId());
        admin1.setName(admin.getName());
        admin1.setPhone(admin.getPhone());
        adminDao.updateById(admin1);
        LambdaQueryWrapper<AdminRoles>adminRolesLambdaQueryWrapper=new LambdaQueryWrapper<>();
        adminRolesLambdaQueryWrapper.eq(AdminRoles::getAccount,admin.getAccount());
        AdminRoles adminRoles = adminRolesDao.selectOne(adminRolesLambdaQueryWrapper);
        if (adminRoles==null){
            AdminRoles adminRoles1 = new AdminRoles();
            adminRoles1.setAccount(admin.getAccount());
            adminRoles1.setRoleName(roleName);
            adminRolesDao.insert(adminRoles1);
        }else {
            adminRoles.setRoleName(roleName);
            adminRolesDao.updateById(adminRoles);
        }
    }


}

