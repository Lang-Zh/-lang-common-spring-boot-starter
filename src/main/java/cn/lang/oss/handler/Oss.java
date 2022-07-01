package cn.lang.oss.handler;

import java.io.File;
import java.io.InputStream;

/**
 * ClassName : Oss
 * description : 对象存储
 * @author : Lang
 * date: 2020-03-07 08:41
 */
public interface Oss {

    /**
     * 文件上传
     * @param targetFile 要上传的文件
     * @return 外链地址
     */
    String upload(File targetFile);
    /**
     * description 文件上传
     * date 2020-03-14 11:10
     * @param targetFile 要上传的文件
     * @param resourcesName 上传到OSS上存储的文件名
     * @return 不包含域名的外链地址
     */
    String upload(File targetFile, String resourcesName);

    /**
     * 文件上传
     * @param targetName 要上传的文件路径
     * @return 不包含域名的外链地址
     */
    String upload(String targetName);

    /**
     * 文件上传
     * @param targetName 要上传的文件路径
     * @param resourcesName 上传到OSS上存储的文件名
     * @return 不包含域名的外链地址
     */
    String upload(String targetName, String resourcesName);

    /**
     * 文件上传
     * @param data 要上传的文件字节数组
     * @return 不包含域名的外链地址
     */
    String upload(byte[] data);

    /**
     * 文件上传
     * @param data 要上传的文件字节数组
     * @param resourcesName 上传到OSS上存储的文件名
     * @return 不包含域名的外链地址
     */
    String upload(byte[] data,String resourcesName);

    /**
     * 文件上传
     * @param inputStream 要上传的文件输入流
     * @return 不包含域名的外链地址
     */
    String upload(InputStream inputStream);

    /**
     * 文件上传
     * @param inputStream 要上传的文件输入流
     * @param resourcesName 上传到OSS上存储的文件名
     * @return 不包含域名的外链地址
     */
    String upload(InputStream inputStream,String resourcesName);

    /**
     * 获取完整外链地址
     * @param resourcesName 上传到OSS上存储的文件名
     * @return 完整外链地址
     */
    String getUrl(String resourcesName);

}
