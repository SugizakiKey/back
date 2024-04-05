package com.back;

import com.back.dao.*;
import com.back.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class HelloBackApplicationTests {
    @Resource
    private AdminDao adminDao;
    @Resource
    private AdminService adminService;
    @Resource
    private AdminRolesService adminRolesService;
    @Resource
    private RolesPermissionsService rolesPermissionsService;
    @Resource
    private CustomerService customerService;
    @Resource
    private CarPayDao carPayDao;
    @Resource
    private CarInventoryDao carInventoryDao;
    @Resource
    private IncidentalsDao incidentalsDao;
    @Resource
    private  BrandService brandService;
    @Test
    void contextLoads() {
/*
        Double t1 = incidentalsDao.getTotal1ByInventoryid("S00000001");
        Double t2 = incidentalsDao.getTotal2ByInventoryid("S00000001");
        System.out.println(t2-t1);*/

       // brandService.saveAllBrand();
       // System.out.println(1000*60*60*24*365);

    }


}
