package cn.lang.oss.handler;

import cn.lang.oss.properties.OssUpyProperties;
import com.UpYun;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.InputStream;

/**
 * ClassName : YoupyOss
 * description : 又拍云
 *
 * @author : Lang
 * date: 2020-03-14 12:00
 */
public class OssUpyHandler extends OssHandler {

    /**
     * 又拍云实例
     */
    private UpYun upyun;

    private OssUpyProperties ossUpyProperties;

    Logger logger = LoggerFactory.getLogger(OssUpyHandler.class);

    public OssUpyHandler(OssUpyProperties ossUpyProperties) {
        ossPropertiesInit(ossUpyProperties);
        this.ossUpyProperties = ossUpyProperties;
        this.upyun = getUpYunManager();
    }

    @Override
    public String upload(File targetFile, String resourcesName) {
        try {
            resourcesName = ossUpyProperties.getBaseDisc() + resourcesName;
            upyun.writeFile(resourcesName, targetFile, true);
            logger.info("又拍云对象存储上传成功:{}", resourcesName);
            return resourcesName;
        } catch (Exception e) {
            logger.error("又拍云对象存储上传异常", e);
        }
        return null;
    }

    @Override
    public String upload(String targetName, String resourcesName) {
        try {
            resourcesName = ossUpyProperties.getBaseDisc() + resourcesName;
            upyun.writeFile(resourcesName, targetName, true);
            logger.info("又拍云对象存储上传成功:{}", resourcesName);
            return resourcesName;
        } catch (Exception e) {
            logger.error("又拍云对象存储上传异常", e);
        }
        return null;
    }

    @Override
    public String upload(byte[] data, String resourcesName) {
        try {
            resourcesName = ossUpyProperties.getBaseDisc() + resourcesName;
            upyun.writeFile(resourcesName, data, true);
            logger.info("又拍云对象存储上传成功:{}", resourcesName);
            return resourcesName;
        } catch (Exception e) {
            logger.error("又拍云对象存储上传异常", e);
        }
        return null;
    }

    @Override
    public String upload(InputStream inputStream, String resourcesName) {
        try {
            resourcesName = ossUpyProperties.getBaseDisc() + resourcesName;
            upyun.writeFile(resourcesName, inputStream, false, null);
            logger.info("又拍云对象存储上传成功:{}", resourcesName);
            return resourcesName;
        } catch (Exception e) {
            logger.error("又拍云对象存储上传异常", e);
        }
        return null;
    }

    @Override
    public String getUrl(String resourcesName) {
        return ossUpyProperties.getDomain() + resourcesName;
    }

    /**
     * description 获取又拍云实例
     * date 2020-03-14 13:42
     */
    private UpYun getUpYunManager() {
        // 创建实例
        UpYun upyun = new UpYun(this.ossUpyProperties.getBucket(), this.ossUpyProperties.getUsername(), this.ossUpyProperties.getPassword());
        // 可选属性1，是否开启 debug 模式，默认不开启
        upyun.setDebug(false);
        // 可选属性2，超时时间，默认 30s
        upyun.setTimeout(30);
        return upyun;
    }
}
