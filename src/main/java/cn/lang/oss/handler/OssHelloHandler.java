package cn.lang.oss.handler;


import cn.hutool.core.codec.Base64;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.lang.oss.properties.OssHelloProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class OssHelloHandler extends OssHandler {

    @Autowired
    private OssHelloProperties ossHelloProperties;
    /**
     * 上传api地址
     */
    private final String API_CREATE_POST = "https://www.helloimg.com/newapi/2/upload/?format=json";

    private Logger logger = LoggerFactory.getLogger(OssHelloHandler.class);

    public OssHelloHandler(OssHelloProperties ossHelloProperties) {
        ossPropertiesInit(ossHelloProperties);
        this.ossHelloProperties = ossHelloProperties;
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

    @Override
    public String upload(String targetName, String resourcesName) {

        if (StrUtil.isBlank(targetName)) {
            logger.error("文件地址targetName不能为空：{}", targetName);
            return null;
        }
        Path filePath = Paths.get(targetName);
        if (!Files.isRegularFile(filePath)) {
            logger.error(targetName + " not a regular file");
            return null;
        }
        return upload(filePath.toFile(), resourcesName);
    }

    @Override
    public String upload(byte[] data, String resourcesName) {
        //请求体封装
        Map<String, Object> uploadBodyMap = this.getUploadBodyMap(data);
        String ret = HttpUtil.post(API_CREATE_POST, uploadBodyMap);
        JSONObject jsonObject = JSONUtil.parseObj(ret);
        logger.info("Hello响应：{}", ret);
        if (ObjectUtil.isEmpty(jsonObject)) {
            logger.error("Hello上传失败");
            return null;
        }
        if (ObjectUtil.notEqual(200, jsonObject.getInt("status_code"))) {
            logger.error("Hello上传失败");
            return null;
        }
        String url = jsonObject.getJSONObject("image").getStr("url");
        logger.info("Hello上传成功:{}", url);
        return url;

    }

    @Override
    public String upload(InputStream inputStream, String resourcesName) {
        byte[] data = IoUtil.readBytes(inputStream);
        return upload(data, resourcesName);
    }

    @Override
    public String getUrl(String resourcesName) {
        return resourcesName;
    }

    @Override
    public Oss setBucket(String bucket) {
        logger.error("暂时不支持");
        return null;
    }


    /**
     * 获取创建文件的请求体map集合：access_token、message、content
     *
     * @param multipartFile 文件字节数组
     * @return 封装成map的请求体集合
     */
    private Map<String, Object> getUploadBodyMap(byte[] multipartFile) {
        HashMap<String, Object> bodyMap = new HashMap<>(3);
        bodyMap.put("login-subject", this.ossHelloProperties.getLoginSubject());
        bodyMap.put("password", ossHelloProperties.getPassword());
        bodyMap.put("source", Base64.encode(multipartFile));
        return bodyMap;
    }

}
