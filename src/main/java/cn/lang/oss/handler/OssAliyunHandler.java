package cn.lang.oss.handler;

import cn.lang.oss.properties.OssAliyunProperties;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;

/**
 * ClassName : QiniuOss
 * Description : 七牛云
 *
 * @author : Lang
 * date: 2020-03-07 08:54
 */
public class OssAliyunHandler extends OssHandler {

    private OssAliyunProperties ossAliyunProperties;

    private OSS ossClient;

    private Logger logger = LoggerFactory.getLogger(OssAliyunHandler.class);

    public OssAliyunHandler(OssAliyunProperties ossAliyunProperties) {
        ossPropertiesInit(ossAliyunProperties);
        this.ossAliyunProperties = ossAliyunProperties;
        this.ossClient = createOssClient();
    }


    @Override
    public String upload(File targetFile, String resourcesName) {
        try {
            PutObjectRequest putObjectRequest = new PutObjectRequest(ossAliyunProperties.getBucket(), resourcesName, targetFile);
            ossClient.putObject(putObjectRequest);
            logger.info("阿里云对象存储上传成功:{}", resourcesName);
            return resourcesName;
        } catch (Exception e) {
            logger.error("阿里云对象存储上传异常", e);
        }
        return null;
    }

    @Override
    public String upload(String targetName, String resourcesName) {
        return upload(new File(targetName), resourcesName);
    }

    @Override
    public String upload(byte[] data, String resourcesName) {
        return upload(new ByteArrayInputStream(data), resourcesName);
    }

    @Override
    public String upload(InputStream inputStream, String resourcesName) {
        try {
            PutObjectRequest putObjectRequest = new PutObjectRequest(ossAliyunProperties.getBucket(), resourcesName, inputStream);
            ossClient.putObject(putObjectRequest);
            logger.info("阿里云对象存储上传成功:{}", resourcesName);
            return resourcesName;
        } catch (Exception e) {
            logger.error("阿里云对象存储上传异常", e);
        }
        return null;
    }

    @Override
    public String getUrl(String resourcesName) {
        return ossAliyunProperties.getEndpoint() + separator + resourcesName;
    }

    @Override
    public Oss setBucket(String bucket) {
        this.ossAliyunProperties.setBucket(bucket);
        this.ossClient = createOssClient();
        return this;
    }

    private OSS createOssClient() {
        return new OSSClientBuilder().build(ossAliyunProperties.getEndpoint(),
                ossAliyunProperties.getAccessKeyId(), ossAliyunProperties.getAccessKeySecret());
    }
}
