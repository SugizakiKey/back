package com.back.shiro;


import com.back.dao.AdminDao;
import com.back.shiro.token.JwtToken;
import com.back.utils.StringUtil;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;

import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;


/**
 * @PackageName:com.ye.back.config
 * @ClassName:MyCredentialsMatcher
 * @Description:
 * @author:何进业
 * @date:2021/6/1 7:32
 */
@Slf4j
@Component
public class MyCredentialsMatcher extends SimpleCredentialsMatcher {

    @Resource
    private AdminDao adminDao;

    //使用自定义token
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        log.info("自定义密码验证器之doCredentialsMatch..............................................................................................");
        JwtToken jwtToken=(JwtToken) token;
        if (jwtToken.getPassword() == null){
            return true;
        }

        //获得用户输入的密码:(可以采用加盐(salt)的方式去检验)
        String inPassword = new String(jwtToken.getPassword());
        log.info("inpassword:"+inPassword);
        String account = String.valueOf(info.getPrincipals());
        //获得数据库中的密码
        String dbPassword=(String) info.getCredentials();
        log.info("db:"+dbPassword);
        //获取盐
        String slat = adminDao.selectByAccount(account).getSalty();
        log.info("颜值："+slat);
        log.info("自定义密码验证器调用！！！");
        log.info("md5加密:"+StringUtil.getStringUtil().md5(inPassword + slat));
        //进行密码的比对
        return this.equals(StringUtil.getStringUtil().md5(inPassword + slat), dbPassword);
    }

}
