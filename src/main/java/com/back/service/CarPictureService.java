package com.back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.back.entity.CarPicture;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * (CarPicture)表服务接口
 *
 * @author songjie
 * @since 2022-10-31 19:51:18
 */
public interface CarPictureService extends IService<CarPicture> {
void saveCarPicture(String inventoryid, MultipartFile files[],String firsturl);
Map getCarPictureById(String inventoryid);
void deleteCarPictureByIds(Integer id[]);
void deleteAllCarPictureByInventoryid(String inventoryid);
}

