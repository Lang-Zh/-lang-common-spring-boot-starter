package cn.lang.oss.handler;

import cn.lang.oss.properties.OssMinioProperties;
import io.minio.MinioClient;
import io.minio.PutObjectOptions;
import io.minio.errors.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URLConnection;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * ClassName : MinioOssHandler
 * description : Minio
 *
 * @author : Lang
 * date 2021-10-06 11:33
 */
public class OssMinioHandler extends OssHandler {

    Logger logger = LoggerFactory.getLogger(OssMinioHandler.class);

    private final MinioClient minioClient;

    private final OssMinioProperties ossMinioProperties;


    public OssMinioHandler(OssMinioProperties ossMinioProperties) {
        ossPropertiesInit(ossMinioProperties);
        this.ossMinioProperties = ossMinioProperties;
        this.minioClient = getMinioClient();
    }


    private MinioClient getMinioClient() {
        MinioClient minioClient;
        try {
            minioClient = new MinioClient(this.ossMinioProperties.getDomain(), this.ossMinioProperties.getAccessKey(),
                    this.ossMinioProperties.getSecretKey());
            if (minioClient.bucketExists(this.ossMinioProperties.getBucket())) {
                logger.info("桶已初始化:{}", this.ossMinioProperties.getBucket());
            } else {
                logger.error("Minio桶不存在：{}", this.ossMinioProperties.getBucket());
            }
            return minioClient;
        } catch (Exception e) {
            logger.error("Minio对象存储初始化异常", e);
        }
        return null;
    }

    public String upload(InputStream targetInputStream, String resourcesName, String contentType) {
        try {
            PutObjectOptions options = new PutObjectOptions(targetInputStream.available(), -1);
            options.setContentType(contentType);
            resourcesName = ossMinioProperties.getBaseDisc() + resourcesName;
            minioClient.putObject(this.ossMinioProperties.getBucket(), resourcesName, targetInputStream, options);
            logger.info("Minio对象存储上传成功:{}", resourcesName);
            return resourcesName;
        } catch (Exception e) {
            logger.error("Minio对象存储上传异常", e);
        }
        return null;
    }

    @Override
    public String upload(InputStream targetInputStream, String resourcesName) {

        try {
            PutObjectOptions options = new PutObjectOptions(targetInputStream.available(), -1);
            String contentType = URLConnection.guessContentTypeFromStream(targetInputStream);
            if (contentType != null && !contentType.equals("")) {
                options.setContentType(contentType);
            }
            resourcesName = ossMinioProperties.getBaseDisc() + resourcesName;
            minioClient.putObject(this.ossMinioProperties.getBucket(), resourcesName, targetInputStream, options);
            logger.info("Minio对象存储上传成功:{}", resourcesName);
            return resourcesName;
        } catch (Exception e) {
            logger.error("Minio对象存储上传异常", e);
        }
        return null;
    }

    @Override
    public String getUrl(String resourcesName) {
        try {
            return minioClient.getObjectUrl(this.ossMinioProperties.getBucket(), resourcesName);
        } catch (Exception e) {
            logger.error("Minio对象存储获取外链地址异常", e);
        }
        return null;
    }

    @Override
    public String upload(byte[] data, String resourcesName) {
        ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(data);
        return upload(arrayInputStream, resourcesName);
    }

    @Override
    public String upload(String targetName, String resourcesName) {
        try {
            resourcesName = ossMinioProperties.getBaseDisc() + resourcesName;
            minioClient.putObject(this.ossMinioProperties.getBucket(), resourcesName, targetName, null);
            logger.info("Minio对象存储上传成功:{}", resourcesName);
            return resourcesName;
        } catch (Exception e) {
            logger.error("Minio对象存储上传异常", e);
        }
        return null;
    }

    @Override
    public String upload(File targetFile, String resourcesName) {
        try {
            FileInputStream fileInputStream = new FileInputStream(targetFile);
            return upload(fileInputStream, resourcesName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
