package com.back.service.impl;

import com.aliyuncs.exceptions.ClientException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.back.dao.CarAssessmentDao;
import com.back.dao.CarPictureDao;
import com.back.entity.CarAssessment;
import com.back.entity.CarPicture;
import com.back.service.CarPictureService;
import com.back.utils.OSSUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * (CarPicture)表服务实现类
 *
 * @author songjie
 * @since 2022-10-31 19:51:18
 */
@Slf4j
@Service("carPictureService")
public class CarPictureServiceImpl extends ServiceImpl<CarPictureDao, CarPicture> implements CarPictureService {

    @Resource
    private CarPictureDao carPictureDao;
    @Resource
    private CarAssessmentDao carAssessmentDao;

    @Override
    public void saveCarPicture(String inventoryid, MultipartFile[] files,String firsturl) {
        CarPicture carPicture=new CarPicture();
        carPicture.setInventoryid(inventoryid);
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("inventoryid",inventoryid);
        CarAssessment carAssessment = carAssessmentDao.selectOne(wrapper);

        if (firsturl!=null){
            carAssessment.setCarurl(firsturl);
            carAssessmentDao.updateById(carAssessment);
        }
        if (!(files==null)){
            for (int i=0;i<files.length;i++){
                String id= UUID.randomUUID().toString().replace("-","");
                String objectKey="hello/"+inventoryid+"/"+id+"."+(files[i].getContentType().substring(files[i].getContentType().lastIndexOf('/')+1));
                try {
                    String url = OSSUtil.uploadFile(objectKey, files[i]);
                    carPicture.setUrl(url);

                    if (i==0 &&firsturl==null){
                        carAssessment.setCarurl(url);
                        carAssessmentDao.updateById(carAssessment);
                    }

                } catch (ClientException e) {

                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                carPictureDao.insert(carPicture);
            }
        }
    }

    @Override
    public Map getCarPictureById(String inventoryid) {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("inventoryid",inventoryid);
        List<CarPicture> list = carPictureDao.selectList(wrapper);
        Map map=new HashMap();
        map.put("list",list);
        return map;
    }

    @Override
    public void deleteCarPictureByIds(Integer[] id) {
        for (int i=0;i<id.length;i++){
            carPictureDao.deleteById(id[i]);
        }
    }

    @Override
    public void deleteAllCarPictureByInventoryid(String inventoryid) {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("inventoryid",inventoryid);
        carPictureDao.delete(wrapper);
    }

}

