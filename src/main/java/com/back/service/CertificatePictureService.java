package com.back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.back.entity.CertificatePicture;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * (CertificatePicture)表服务接口
 *
 * @author songjie
 * @since 2022-11-08 18:12:32
 */
public interface CertificatePictureService extends IService<CertificatePicture> {
    void saveCetificatePictures(String inventoryid, String names[], MultipartFile files[],Boolean states[]);
    Map getCetificatePicturesById(String inventoryid);
}

