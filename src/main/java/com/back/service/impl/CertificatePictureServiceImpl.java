package com.back.service.impl;

import com.aliyuncs.exceptions.ClientException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.back.dao.CertificatePictureDao;
import com.back.entity.CertificatePicture;
import com.back.service.CertificatePictureService;
import com.back.utils.OSSUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (CertificatePicture)表服务实现类
 *
 * @author songjie
 * @since 2022-11-08 18:12:32
 */
@Slf4j
@Service("certificatePictureService")
public class CertificatePictureServiceImpl extends ServiceImpl<CertificatePictureDao, CertificatePicture> implements CertificatePictureService {

    @Resource
    private CertificatePictureDao certificatePictureDao;

    @Override
    public void saveCetificatePictures(String inventoryid, String[] names, MultipartFile[] files,Boolean states[]) {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("inventoryid",inventoryid);
        List<CertificatePicture> list = certificatePictureDao.selectList(wrapper);
        CertificatePicture certificatePicture=new CertificatePicture();
        certificatePicture.setInventoryid(inventoryid);

        if (names!=null){
            int count=0;//记录要上传的文件的下标
            for (int i=0;i<names.length;i++){
                if (states[i]){
                    //需要保存的
                    boolean flag=false;//用于判断数据库是否已存在，存在则修改，不存在则插入
                    for (int j=0;j<list.size();j++){
                        if (list.get(j).getName().equals(names[i])){

                            String objectKey="hello/"+inventoryid+"/"+names[i]+"/"+"1."+(files[count].getContentType().substring(files[count].getContentType().lastIndexOf('/')+1));
                            try {
                                String url = OSSUtil.uploadFile(objectKey, files[count]);
                                list.get(i).setUrl(url);
                            } catch (ClientException e) {
                                e.printStackTrace();
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                            //修改
                            certificatePictureDao.updateById(list.get(i));
                            flag=true;
                            list.remove(j);
                            count++;
                            break;
                        }
                    }
                    if (!flag){
                        certificatePicture.setName(names[i]);
                        String objectKey="hello/"+inventoryid+"/"+names[i]+"/"+"1."+(files[count].getContentType().substring(files[count].getContentType().lastIndexOf('/')+1));
                        try {
                            String url = OSSUtil.uploadFile(objectKey, files[count]);
                            certificatePicture.setUrl(url);
                        } catch (ClientException e) {
                            e.printStackTrace();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        //保存
                        certificatePictureDao.insert(certificatePicture);
                        count++;
                    }
                }else {
                    for (int j=0;j<list.size();j++){
                        if (list.get(j).getName().equals(names[i])){
                            list.remove(j);
                            break;
                        }
                    }
                }

            }
        }
        for (int i=0;i<list.size();i++){
            list.get(i).setUrl("");
            certificatePictureDao.updateById(list.get(i));
        }
    }

    @Override
    public Map getCetificatePicturesById(String inventoryid) {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("inventoryid",inventoryid);
        List<CertificatePicture> certificatePictures = certificatePictureDao.selectList(wrapper);
        Map map=new HashMap();
        map.put("list",certificatePictures);
        return map;
    }
}

