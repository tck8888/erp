package com.tck.common;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.*;
import com.tck.base.BaseData;
import com.tck.base.BaseDataUtils;
import com.tck.base.StatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URL;
import java.util.Date;

/**
 * 上传文件
 * http://www.cnblogs.com/baizhanshi/p/5593431.html
 * Created by tck on 2017/7/23.
 */
@Service
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
           String filename =  String.valueOf(System.currentTimeMillis())+file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            String success = upImage(filename,inputStream);
            if (success.equals("")) {
                return BaseDataUtils.getInstance().<String>getBaseData(StatusCode.WEB_ERROR_CODE, "上传图片失败", "");
            } else {
                return BaseDataUtils.getInstance().<String>getBaseData(StatusCode.SUCCESS_CODE, "上传图片成功", "http://tck.oss-cn-shanghai.aliyuncs.com/image/" + filename);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return BaseDataUtils.getInstance().<String>getBaseData(StatusCode.WEB_ERROR_CODE, "上传图片失败", "");
        }
    }

    private String upImage(String fileName,InputStream inputStream) {

        String str = "";
        try {
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, "image/" + fileName, inputStream);
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
