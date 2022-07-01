package cn.lang.oss.handler;

import java.io.File;
import java.io.InputStream;

/**
 * @ClassName : Oss
 * @Description : 对象存储
 * @Author : Lang
 * @Date: 2020-03-07 08:41
 */
public interface Oss {

    /**
     * @description 文件上传
     * @date 2020-03-14 11:10
     * @param targetFile 要上传的文件
     * @param resourcesName 上传到OSS上存储的文件名
     * @return 外链地址
     */
    String upload(File targetFile, String resourcesName);
    String upload(File targetFile);
    String upload(String targetName, String resourcesName);
    String upload(byte[] data,String resourcesName);
    String upload(InputStream inputStream,String resourcesName);

}
