package cn.lang.oss.handler;

import cn.lang.oss.properties.OssMinioProperties;
import io.minio.MinioClient;
import io.minio.PutObjectOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URLConnection;

/**
 * @ClassName : MinioOssHandler
 * @Description : Minio
 * @Author : Lang
 * @Date 2021-10-06 11:33
 */
public class OssMinioHandler extends OssHandler {

    Logger logger = LoggerFactory.getLogger(OssMinioHandler.class);

    private final MinioClient minioClient;

    private final OssMinioProperties ossMinioProperties;


    public OssMinioHandler(OssMinioProperties ossMinioProperties) {
        super(ossMinioProperties);
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
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String upload(File targetFile) {
        return this.upload(targetFile, targetFile.getName());
    }

    public String upload(InputStream targetInputStream, String resourcesName, String contentType) {
        try {
            PutObjectOptions options = new PutObjectOptions(targetInputStream.available(), -1);
            options.setContentType(contentType);
            resourcesName = ossMinioProperties.getBaseDisc()+resourcesName;
            minioClient.putObject(this.ossMinioProperties.getBucket(), resourcesName, targetInputStream, options);
            return minioClient.getObjectUrl(this.ossMinioProperties.getBucket(), resourcesName);
        } catch (Exception e) {
            e.printStackTrace();
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
            resourcesName = ossMinioProperties.getBaseDisc()+resourcesName;
            minioClient.putObject(this.ossMinioProperties.getBucket(), resourcesName, targetInputStream, options);
            return minioClient.getObjectUrl(this.ossMinioProperties.getBucket(), resourcesName);
        } catch (Exception e) {
            e.printStackTrace();
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
            minioClient.putObject(this.ossMinioProperties.getBucket(), resourcesName, targetName, null);
            return minioClient.getObjectUrl(this.ossMinioProperties.getBucket(), resourcesName);
        } catch (Exception e) {
            e.printStackTrace();
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
