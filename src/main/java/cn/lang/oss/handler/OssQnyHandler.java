package cn.lang.oss.handler;

import cn.lang.oss.properties.OssQnyProperties;
import com.qiniu.common.QiniuException;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.InputStream;

/**
 * @ClassName : QiniuOss
 * @Description : 七牛云
 * @Author : Lang
 * @Date: 2020-03-07 08:54
 */
public class OssQnyHandler extends OssHandler {

    private UploadManager uploadManager;

    private BucketManager bucketManager;

    private String token;

    private String domain;

    private OssQnyProperties ossQnyProperties;

    Logger logger = LoggerFactory.getLogger(OssQnyHandler.class);

    public OssQnyHandler(OssQnyProperties ossQnyProperties) {
        super(ossQnyProperties);
        this.ossQnyProperties = ossQnyProperties;
        this.token = getToken();
        this.uploadManager = getUploadManager();
        this.bucketManager = getBucketManager();
        this.domain = ossQnyProperties.getDomain();
    }

    @Override
    public String upload(File targetFile) {
        return this.upload(targetFile, targetFile.getName());
    }

    @Override
    public String upload(File targetFile, String resourcesName) {
        try {
            uploadManager.put(targetFile, resourcesName, this.token);
        } catch (QiniuException e) {
            logger.info("上传失败");
            e.printStackTrace();
        }
        String url = this.domain + resourcesName;
        logger.info("上传成功:{}",url);
        return url;
    }

    @Override
    public String upload(String targetName, String resourcesName) {
        try {
            resourcesName = ossQnyProperties.getBaseDisc() + resourcesName;
            uploadManager.put(targetName, resourcesName, this.token);
        } catch (QiniuException e) {
            e.printStackTrace();
        }
        String url = this.domain + resourcesName;
        logger.info("上传成功:{}",url);
        return url;
    }

    @Override
    public String upload(byte[] data, String resourcesName) {
        try {
            resourcesName = ossQnyProperties.getBaseDisc() + resourcesName;
            uploadManager.put(data, resourcesName, this.token);
        } catch (QiniuException e) {
            e.printStackTrace();
        }
        String url = this.domain + resourcesName;
        logger.info("上传成功:{}",url);
        return url;
    }

    @Override
    public String upload(InputStream inputStream, String resourcesName) {
        try {
            resourcesName = ossQnyProperties.getBaseDisc() + resourcesName;
            uploadManager.put(inputStream, resourcesName, this.token, null, null);
        } catch (QiniuException e) {
            e.printStackTrace();
        }
        String url = this.domain + resourcesName;
        logger.info("上传成功:{}",url);
        return url;
    }

    /**
     * @description 权限
     * @date 2020-03-14 11:47
     */
    public Auth getAuth() {
        return Auth.create(this.ossQnyProperties.getAccessKey(), this.ossQnyProperties.getSecretKey());
    }

    public String getToken() {
        return getAuth().uploadToken(this.ossQnyProperties.getBucket());
    }

    public BucketManager getBucketManager() {
        Auth auth = getAuth();
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Region.autoRegion());
        return new BucketManager(auth, cfg);
    }

    /**
     * @description 上传
     * @date 2020-03-14 11:46
     */
    public UploadManager getUploadManager() {
        Configuration cfg = new Configuration(Region.autoRegion());
        return new UploadManager(cfg);
    }


}
