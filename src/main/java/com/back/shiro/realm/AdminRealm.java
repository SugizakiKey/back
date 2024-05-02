package com.back.shiro.realm;

import com.back.dao.AdminDao;
import com.back.entity.Admin;
import com.back.service.AdminRolesService;
import com.back.service.RolesPermissionsService;
import com.back.shiro.token.JwtToken;
import com.back.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;


import javax.annotation.Resource;
import java.util.Set;

@Slf4j
public class AdminRealm extends AuthorizingRealm {

    @Resource
    private AdminDao adminDao;
    @Resource
    private AdminRolesService adminRolesService;
    @Resource
    private RolesPermissionsService rolesPermissionsService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("执行了授权~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        String account=(String) principalCollection.iterator().next();
        Set<String>roles= (Set<String>) adminRolesService.getRolesByAccount(account).get("roles");
        Set<String>permissions=rolesPermissionsService.getPermissionsByRoleName(roles);
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        /*log.info(roles.toString());
        log.info(permissions.toString());*/
        info.setRoles(roles);
        info.setStringPermissions(permissions);
        return info;
    }


    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("执行了认证！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
        JwtToken jwtToken=(JwtToken) authenticationToken;
        String token=jwtToken.getToken();
        Claims claims = JwtUtil.getJwtUtil().parseJWT(token);
        String account=claims.getId();
        Admin admin=adminDao.selectByAccount(account);
        log.info("realm里调用！！！");
        if (admin==null){
            return null;
        }
        return new SimpleAuthenticationInfo(account,admin.getPassword(),getName());
    }


    //替换成jwttoken
    @Override
    public boolean supports(AuthenticationToken authenticationToken){
        return authenticationToken instanceof JwtToken;
    }

}
