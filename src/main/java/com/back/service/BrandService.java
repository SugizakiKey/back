package com.back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.back.entity.Brand;

import java.util.Map;

/**
 * (Brand)表服务接口
 *
 * @author songjie
 * @since 2022-12-04 15:40:30
 */
public interface BrandService extends IService<Brand> {

    void saveAllBrand();

    Map getAllBrandInfo();

}

