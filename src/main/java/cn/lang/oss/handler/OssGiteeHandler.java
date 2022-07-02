package cn.lang.oss.handler;


import cn.hutool.core.codec.Base64;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.lang.oss.properties.OssGiteeProperties;
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

public class OssGiteeHandler extends OssHandler {

    @Autowired
    private OssGiteeProperties ossGiteeProperties;
    /**
     * 上传api地址
     */
    private final String API_CREATE_POST = "https://gitee.com/api/v5/repos/%s/%s/contents/%s";

    private Logger logger = LoggerFactory.getLogger(OssGiteeHandler.class);

    public OssGiteeHandler(OssGiteeProperties ossGiteeProperties) {
        ossPropertiesInit(ossGiteeProperties);
        this.ossGiteeProperties = ossGiteeProperties;
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
        String targetUrl = String.format(this.API_CREATE_POST,
                this.ossGiteeProperties.getOwner(),
                this.ossGiteeProperties.getRepo(),
                this.ossGiteeProperties.getBaseDisc() + resourcesName);
        //请求体封装
        Map<String, Object> uploadBodyMap = this.getUploadBodyMap(data);
        String ret = HttpUtil.post(targetUrl, uploadBodyMap);
        JSONObject jsonObject = JSONUtil.parseObj(ret);
        if (ObjectUtil.isEmpty(jsonObject)) {
            logger.error("Gitee上传失败");
            return null;
        }
        String url = jsonObject.getJSONObject("content").getStr("download_url");
        logger.info("Gitee上传成功:{}", url);
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
        bodyMap.put("access_token", this.ossGiteeProperties.getAccessToken());
        bodyMap.put("message", ":art: add file!");
        bodyMap.put("content", Base64.encode(multipartFile));
        return bodyMap;
    }

}
