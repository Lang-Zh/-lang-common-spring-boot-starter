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
 * @author : Lang
 * date: 2020-03-14 12:00
 */
public class OssUpyHandler extends OssHandler {

    /**
     * 外链域名
     */
    private String domain;

    /**
     * 又拍云实例
     */
    private UpYun upyun;

    private OssUpyProperties ossUpyProperties;

    Logger logger = LoggerFactory.getLogger(OssUpyHandler.class);

    public OssUpyHandler() {
    }

    public OssUpyHandler(OssUpyProperties ossUpyProperties) {
        super(ossUpyProperties);
        this.ossUpyProperties = ossUpyProperties;
        this.domain = ossUpyProperties.getDomain();
        this.upyun = getUpYunManager();
    }

    @Override
    public String upload(File targetFile) {
        return this.upload(targetFile, targetFile.getName());
    }

    @Override
    public String upload(File targetFile, String resourcesName) {
        try {
            resourcesName = ossUpyProperties.getBaseDisc() + resourcesName;
            upyun.writeFile(resourcesName, targetFile, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String url = this.domain + resourcesName;
        logger.info("上传成功:{}",url);
        return url;
    }

    @Override
    public String upload(String targetName, String resourcesName) {
        try {
            resourcesName = ossUpyProperties.getBaseDisc() + resourcesName;
            upyun.writeFile(resourcesName, targetName, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String url = this.domain + resourcesName;
        logger.info("上传成功:{}",url);
        return url;
    }

    @Override
    public String upload(byte[] data, String resourcesName) {
        try {
            resourcesName = ossUpyProperties.getBaseDisc() + resourcesName;
            upyun.writeFile(resourcesName, data, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String url = this.domain + resourcesName;
        logger.info("上传成功:{}",url);
        return url;
    }

    @Override
    public String upload(InputStream inputStream, String resourcesName) {
        try {
            resourcesName = ossUpyProperties.getBaseDisc() + resourcesName;
            upyun.writeFile(resourcesName, inputStream, false, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String url = this.domain + resourcesName;
        logger.info("上传成功:{}",url);
        return url;
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
