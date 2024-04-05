package com.back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.back.entity.Customer;

import java.util.Map;

/**
 * (Customer)表服务接口
 *
 * @author songjie
 * @since 2022-11-20 15:33:26
 */
public interface CustomerService extends IService<Customer> {
Map getCustomerid();
Map saveCustomer(Customer customer);
Map getAllCustomer();
Map getCustomerByPhones(String phones[]);
Map getCustomerById(Integer id);
Map updateCustomer(Customer customer);
Map setFail(Integer id);
Map realDeleteById(Integer id);
Map getFailCustomer();
Map setActive(Integer id);
Map getSuccessCustomer();
Map getCustomerCars(String customerids[]);
}

