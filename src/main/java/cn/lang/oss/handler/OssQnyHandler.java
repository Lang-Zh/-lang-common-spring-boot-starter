package cn.lang.oss.handler;

import cn.lang.oss.properties.OssQnyProperties;
import com.qiniu.common.QiniuException;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.InputStream;

/**
 * ClassName : QiniuOss
 * description : 七牛云
 *
 * @author : Lang
 * date: 2020-03-07 08:54
 */
public class OssQnyHandler extends OssHandler {

    private UploadManager uploadManager;

    private String token;

    private OssQnyProperties ossQnyProperties;

    Logger logger = LoggerFactory.getLogger(OssQnyHandler.class);

    public OssQnyHandler(OssQnyProperties ossQnyProperties) {
        ossPropertiesInit(ossQnyProperties);
        this.ossQnyProperties = ossQnyProperties;
        this.token = getToken();
        this.uploadManager = getUploadManager();
    }

    @Override
    public String upload(File targetFile, String resourcesName) {
        try {
            resourcesName = ossQnyProperties.getBaseDisc() + resourcesName;
            uploadManager.put(targetFile, resourcesName, this.token);
            logger.info("七牛云对象存储上传成功:{}", resourcesName);
            return resourcesName;
        } catch (QiniuException e) {
            logger.info("七牛云对象存储上传异常", e);
        }
        return null;
    }

    @Override
    public String upload(String targetName, String resourcesName) {
        try {
            resourcesName = ossQnyProperties.getBaseDisc() + resourcesName;
            uploadManager.put(targetName, resourcesName, this.token);
            logger.info("七牛云对象存储上传成功:{}", resourcesName);
            return resourcesName;
        } catch (QiniuException e) {
            logger.info("七牛云对象存储上传异常", e);
        }
        return null;
    }

    @Override
    public String upload(byte[] data, String resourcesName) {
        try {
            resourcesName = ossQnyProperties.getBaseDisc() + resourcesName;
            uploadManager.put(data, resourcesName, this.token);
            logger.info("七牛云对象存储上传成功:{}", resourcesName);
            return resourcesName;
        } catch (QiniuException e) {
            logger.info("七牛云对象存储上传异常", e);
        }
        return null;
    }

    @Override
    public String upload(InputStream inputStream, String resourcesName) {
        try {
            resourcesName = ossQnyProperties.getBaseDisc() + resourcesName;
            uploadManager.put(inputStream, resourcesName, this.token, null, null);
            logger.info("七牛云对象存储上传成功:{}", resourcesName);
            return resourcesName;
        } catch (QiniuException e) {
            logger.info("七牛云对象存储上传异常", e);
        }
        return null;
    }

    @Override
    public String getUrl(String resourcesName) {
        return ossQnyProperties.getDomain() + resourcesName;
    }

    @Override
    public Oss setBucket(String bucket) {
        this.ossQnyProperties.setBucket(bucket);
        this.token = getToken();
        return this;
    }

    /**
     * description 权限
     * date 2020-03-14 11:47
     *
     * @return Auth
     */
    public Auth getAuth() {
        return Auth.create(this.ossQnyProperties.getAccessKey(), this.ossQnyProperties.getSecretKey());
    }

    public String getToken() {
        return getAuth().uploadToken(this.ossQnyProperties.getBucket());
    }

    /**
     * description 上传
     * date 2020-03-14 11:46
     *
     * @return UploadManager
     */
    public UploadManager getUploadManager() {
        Configuration cfg = new Configuration(Region.autoRegion());
        return new UploadManager(cfg);
    }


}
