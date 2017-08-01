package com.tck.common;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.*;
import com.tck.base.BaseData;
import com.tck.base.BaseDataUtils;
import com.tck.base.StatusCode;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URL;
import java.util.Date;

/**
 * 上传文件
 * http://www.cnblogs.com/baizhanshi/p/5593431.html
 * Created by tck on 2017/7/23.
 */
public class OSSClientUtil {

    private static String endpoint = "http://oss-cn-shanghai.aliyuncs.com";
    private static String accessKeyId = "LTAIcmokAJfitWW0";
    private static String accessKeySecret = "cSHauhT5I7tR70CkY05Pza1DosVAn9";
    private static String bucketName = "tck";


    private OSSClient ossClient;

    public OSSClientUtil() {
        ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
    }

    public BaseData<String> upImage(MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            String success = upImage(inputStream);
            if (success.equals("")||getUrl().equals("")) {
                return BaseDataUtils.getInstance().<String>getBaseData(StatusCode.WEB_ERROR_CODE, "上传图片失败", "");
            } else {
                return BaseDataUtils.getInstance().<String>getBaseData(StatusCode.SUCCESS_CODE, "上传图片成功", getUrl());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return BaseDataUtils.getInstance().<String>getBaseData(StatusCode.WEB_ERROR_CODE, "上传图片失败", "");
        }
    }

    private String getUrl() {
        // 设置URL过期时间为1年  3600l* 1000*24*365*1
        Date expiration = new Date(new Date().getTime() + 3600l * 1000 * 24 * 365);
        URL url = ossClient.generatePresignedUrl(bucketName, "image/", expiration);
        if (url != null) {
            return url.toString();
        }
        return "";
    }

    private String upImage(InputStream inputStream) {
        String str = "";
        try {
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, "image/" + String.valueOf(System.currentTimeMillis()), inputStream);
            PutObjectResult putObjectResult = ossClient.putObject(putObjectRequest);
            str = putObjectResult.getETag();
        } catch (OSSException oe) {
        } catch (ClientException ce) {
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            ossClient.shutdown();
        }

        return str;
    }


}
