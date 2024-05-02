package com.back.utils;


import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.aliyuncs.exceptions.ClientException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
@Slf4j
public class OSSUtil{

    private static OSSUtil ossUtil;

    private OSSUtil() {
    }

    // 阿里云API的内或外网域名
    private static final String endpoint="oss-cn-shanghai.aliyuncs.com";

    // 阿里云API的密钥Access Key ID
    private static final String accessKeyId="LTAI4GJ7sFi4CdmeS6ihNwTJ";

    // 阿里云API的密钥Access Key Secret
    private static final String accessKeySecret="jYSmsnqJLILtuf4e4VVAY9VMFJDY5S";

    // 阿里云API的bucket名称
    private static final String bucketName="songjieai";

    static long a=1000;//毫秒
    static long b=60;//秒
    static long c=60;//分
    static long d=24;//小时
    static long e=365;//天
    private static final long ONE_YEAR=a*b*c*d*e;


    public static synchronized OSSUtil getOssUtil(){
        if (ossUtil == null){
            ossUtil = new OSSUtil();
        }
        return ossUtil;
    }
    public String uploadFile(String objectKey, MultipartFile multipartFile)
            throws OSSException, ClientException, FileNotFoundException {

        // 创建OSSClient的实例
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        String res = "";
        try {
            if(multipartFile.getSize() != 0 && !"".equals(multipartFile.getName())){
                // getInputStream()返回一个InputStream以从中读取文件的内容。通过此方法就可以获取到流
                InputStream multipartFileInputStream = multipartFile.getInputStream();
                PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectKey, multipartFileInputStream);

                PutObjectResult putObjectResult = ossClient.putObject(putObjectRequest);


                res = putObjectResult.getETag();
                // 生成URL   expiration设置失效时间   365天
                Date expiration = new Date(System.currentTimeMillis()+ONE_YEAR);
                URL url = ossClient.generatePresignedUrl(bucketName, objectKey, expiration);
                if (url != null) {
                    res = url.toString();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭流
            ossClient.shutdown();
        }
        return res;
    }
}


