package cn.lang.oss.handler;


import cn.hutool.core.util.StrUtil;
import cn.lang.oss.properties.OssProperties;

import java.util.Objects;

/**
 * 对象存储处理器抽象类
 *
 * @author Lang 1102076808@qq.com
 * @date 2022-1-6 11:03
 */

public abstract class OssHandler implements Oss {

    protected final String separator = "/";

    public OssHandler() {

    }

    public OssHandler(OssProperties ossProperties) {

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
