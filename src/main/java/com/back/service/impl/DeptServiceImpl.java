package com.back.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.back.dao.DeptDao;
import com.back.entity.Dept;
import com.back.service.DeptService;
import org.springframework.stereotype.Service;

/**
 * (Dept)表服务实现类
 *
 * @author songjie
 * @since 2022-12-04 18:44:36
 */
@Service("deptService")
public class DeptServiceImpl extends ServiceImpl<DeptDao, Dept> implements DeptService {

}

