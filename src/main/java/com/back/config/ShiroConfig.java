package com.back.config;


import com.back.shiro.JwtFilter;
import com.back.shiro.MyCredentialsMatcher;
import com.back.shiro.realm.AdminRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import javax.annotation.Resource;
import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Resource
    private MyCredentialsMatcher myCredentialsMatcher;




    //工厂
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager")DefaultWebSecurityManager defaultWebSecurityManager,JwtFilter jwtFilter){
        ShiroFilterFactoryBean bean=new ShiroFilterFactoryBean();

        //设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);

        //设置jwt过滤器
        Map<String , Filter> filterMap=new HashMap<>();
        filterMap.put("jwt",jwtFilter);
        bean.setFilters(filterMap);


        //添加shiro内置过滤器
        /*
            anon:无需认证就可访问
            authc:必须认证了才能访问
            user:必须拥有 记住我 功能才能用
            perms: 拥有对某个资源的权限才能访问
            role: 拥有某个角色权限才能访问

        */
        Map<String ,String > map=new LinkedHashMap();
        //第一个参数  接口  第二个参数 过滤器

        //让swagger通过过滤器
        map.put("/swagger-ui.html**", "anon");
        map.put("/v2/api-docs", "anon");
        map.put("/swagger-resources/**", "anon");
        map.put("/**","jwt");

        bean.setLoginUrl("/admin/login");
        bean.setUnauthorizedUrl("/admin/login");
        bean.setFilterChainDefinitionMap(map);


        return bean;
    }

    //jwt过滤器
    @Bean
    public JwtFilter getJwtFilter() {
        return new JwtFilter();
    }



    //安全管理器
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("adminRealm")AdminRealm adminRealm){
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
        adminRealm.setCredentialsMatcher(myCredentialsMatcher);
        //关联realm
        securityManager.setRealm(adminRealm);
        return securityManager;
    }



    //realm对象
    @Bean
    public AdminRealm adminRealm(){
        return new AdminRealm();
    }



    //开启Shiro的注解(如@RequiresRoles,@RequiresPermissions)
    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }


    //开启AOP注解支持
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }






}
