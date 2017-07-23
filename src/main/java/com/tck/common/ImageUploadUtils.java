package com.tck.common;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.*;

import java.io.*;

/**
 * 上传文件
 * Created by tck on 2017/7/23.
 */
public class ImageUploadUtils {

    private static String endpoint = "http://oss-cn-shanghai.aliyuncs.com";
    private static String accessKeyId = "LTAIcmokAJfitWW0";
    private static String accessKeySecret = "cSHauhT5I7tR70CkY05Pza1DosVAn9";
    private static String bucketName = "tck";

    private static volatile ImageUploadUtils singleton;

    private ImageUploadUtils() {
    }

    public static ImageUploadUtils getInstance() {
        if (singleton == null) {
            synchronized (ImageUploadUtils.class) {
                if (singleton == null) {
                    singleton = new ImageUploadUtils();
                }
            }
        }
        return singleton;
    }

    public void upImage(String fileName, InputStream inputStream) {
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        try {
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, "image/" + fileName, inputStream);
            ossClient.putObject(putObjectRequest);
        } catch (OSSException oe) {
        } catch (ClientException ce) {
            System.out.println("Error Message: " + ce.getMessage());
        } finally {
            ossClient.shutdown();
        }
    }
}
