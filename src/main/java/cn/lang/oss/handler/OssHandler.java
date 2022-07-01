package cn.lang.oss.handler;


import cn.hutool.core.util.StrUtil;
import cn.lang.oss.properties.OssProperties;

import java.io.File;
import java.io.InputStream;
import java.util.Objects;
import java.util.UUID;

/**
 * 对象存储处理器抽象类
 *
 * @author Lang 1102076808@qq.com
 * date 2022-1-6 11:03
 */
public abstract class OssHandler implements Oss {

    protected final String separator = "/";

    @Override
    public String upload(File targetFile) {
        return upload(targetFile, generateRandomResourcesName());
    }

    @Override
    public String upload(String targetName) {
        return upload(targetName, generateRandomResourcesName());
    }

    @Override
    public String upload(byte[] data) {
        return upload(data, generateRandomResourcesName());
    }

    @Override
    public String upload(InputStream inputStream) {
        return upload(inputStream, generateRandomResourcesName());
    }

    public String generateRandomResourcesName() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }


    protected void ossPropertiesInit(OssProperties ossProperties) {
        String baseDisc = ossProperties.getBaseDisc();
        if (baseDisc == null) {
            baseDisc = "";
        }
        if (StrUtil.isNotBlank(baseDisc) && !baseDisc.endsWith(separator)) {
            baseDisc = baseDisc + separator;
        }
        ossProperties.setBaseDisc(baseDisc);
        String domain = ossProperties.getDomain();
        if (!Objects.isNull(domain) && !domain.endsWith(separator)) {
            domain = domain + separator;
        }
        ossProperties.setDomain(domain);
    }

}
