package com.back.service.impl;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.back.dao.BrandDao;
import com.back.dao.DeptDao;
import com.back.entity.Brand;

import com.back.entity.Dept;
import com.back.service.BrandService;

import com.back.utils.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * (Brand)表服务实现类
 *
 * @author songjie
 * @since 2022-12-04 15:40:30
 */
@Slf4j
@Service("brandService")
public class BrandServiceImpl extends ServiceImpl<BrandDao, Brand> implements BrandService {

    private final String APPKEY="469c07826eed16b6";

    @Resource
    private BrandDao brandDao;
    @Resource
    private DeptDao deptDao;

    @Override
    public void saveAllBrand() {

        String url = "https://api.jisuapi.com/car/brand";
        Map<String, Object> map = new HashMap<>();
        String appkey=APPKEY;

        map.put("appkey",appkey);
        try {
            String str = HttpClientUtil.doGet(url, map);
            List<Brand> brands = JSON.parseArray(JSON.parseObject(str).getString("result"), Brand.class);
            for (int i=0;i<brands.size();i++){
                Brand brand = brands.get(i);
                brand.setVersion(0);
                brand.setDeleted(0);
                brandDao.saveBrand(brand);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }




    }

    @Override
    public Map getAllBrandInfo() {

        List<List<Dept>>list=new ArrayList<>();

        List<Brand> brands = brandDao.selectList(null);
        for (int i=0;i<brands.size();i++){
            Integer parentid=brands.get(i).getId();
            LambdaQueryWrapper<Dept>deptLambdaQueryWrapper=new LambdaQueryWrapper<>();
            deptLambdaQueryWrapper.eq(Dept::getParentid, parentid);
            List<Dept> depts = deptDao.selectList(deptLambdaQueryWrapper);
            if (depts==null){
                list.add(null);
            }else {
                list.add(depts);
            }
        }
        Map map=new HashMap();
        map.put("brands",brands);
        map.put("list",list);

        return map;
    }
}

