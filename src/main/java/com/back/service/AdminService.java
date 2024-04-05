package com.back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.back.entity.Admin;
import org.springframework.web.multipart.MultipartFile;


import java.util.Map;

/**
 * (Admin)表服务接口
 *
 * @author makejava
 * @since 2022-10-08 20:17:50
 */
public interface AdminService extends IService<Admin> {
    //登录
    Map checkAccount(Admin admin);
    //Set<String> getAdminPermissons(String account);
    Map register(Admin admin);
    Map updatePassword(Admin admin);
    Map updateInfo(MultipartFile imgFile,String account,String name,String phone,String sex,String email);
    Map getAllAccount();
    Map getAllAdminInfo();
    Map addAdmin(Admin admin,String roleName);
    void resetPassword(Integer id);
    Map getAdminById(Integer id);
    void deleteAdminById(Integer id);
    void updateAdmin(Admin admin,String roleName);
}

