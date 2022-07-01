package cn.lang.oss.handler;

import cn.lang.oss.properties.OssTencentProperties;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.UploadResult;
import com.qcloud.cos.transfer.TransferManager;
import com.qcloud.cos.transfer.TransferManagerConfiguration;
import com.qcloud.cos.transfer.Upload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName : QiniuOss
 * @Description : 七牛云
 * @Author : Lang
 * @Date: 2020-03-07 08:54
 */
public class OssTencentHandler extends OssHandler {


    private String domain;

    private OssTencentProperties ossTencentProperties;

    private TransferManager transferManager;

    private Logger logger = LoggerFactory.getLogger(OssTencentHandler.class);

    public OssTencentHandler(OssTencentProperties ossTencentProperties) {
        super(ossTencentProperties);
        this.ossTencentProperties = ossTencentProperties;
        this.domain = ossTencentProperties.getDomain();
        this.transferManager = createTransferManager();
    }


    @Override
    public String upload(File targetFile, String resourcesName) {
        return null;
    }

    @Override
    public String upload(File targetFile) {
        return null;
    }

    @Override
    public String upload(String targetName, String resourcesName) {
        return null;
    }

    @Override
    public String upload(byte[] data, String resourcesName) {

        return null;
    }

    @Override
    public String upload(InputStream inputStream, String resourcesName) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        PutObjectRequest putObjectRequest = new PutObjectRequest(ossTencentProperties.getBucket(), resourcesName, inputStream, objectMetadata);
        Upload upload = transferManager.upload(putObjectRequest);
        try {
            UploadResult uploadResult = upload.waitForUploadResult();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 创建 TransferManager 实例，这个实例用来后续调用高级接口
     * @return TransferManager
     */
    public TransferManager createTransferManager() {
        // 创建一个 COSClient 实例，这是访问 COS 服务的基础实例。
        // 详细代码参见本页: 简单操作 -> 创建 COSClient
        COSClient cosClient = createCOSClient();

        // 自定义线程池大小，建议在客户端与 COS 网络充足（例如使用腾讯云的 CVM，同地域上传 COS）的情况下，设置成16或32即可，可较充分的利用网络资源
        // 对于使用公网传输且网络带宽质量不高的情况，建议减小该值，避免因网速过慢，造成请求超时。
        ExecutorService threadPool = Executors.newFixedThreadPool(32);

        // 传入一个 threadpool, 若不传入线程池，默认 TransferManager 中会生成一个单线程的线程池。
        TransferManager transferManager = new TransferManager(cosClient, threadPool);

        // 设置高级接口的配置项
        // 分块上传阈值和分块大小分别为 5MB 和 1MB
        TransferManagerConfiguration transferManagerConfiguration = new TransferManagerConfiguration();
        transferManagerConfiguration.setMultipartUploadThreshold(5 * 1024 * 1024);
        transferManagerConfiguration.setMinimumUploadPartSize(1024 * 1024);
        transferManager.setConfiguration(transferManagerConfiguration);

        return transferManager;
    }

    public COSClient createCOSClient() {
        // 1 初始化用户身份信息（secretId, secretKey）。
        // SECRETID和SECRETKEY请登录访问管理控制台 https://console.cloud.tencent.com/cam/capi 进行查看和管理
        String secretId = ossTencentProperties.getSecretId();
        String secretKey = ossTencentProperties.getSecretKey();
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        // 2 设置 bucket 的地域, COS 地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        // clientConfig 中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者常见问题 Java SDK 部分。
        ClientConfig clientConfig = new ClientConfig();
        // 这里建议设置使用 https 协议
        // 从 5.6.54 版本开始，默认使用了 https
        clientConfig.setHttpProtocol(HttpProtocol.https);
        // 3 生成 cos 客户端。
        return new COSClient(cred, clientConfig);
    }

    public void shutdownTransferManager() {
        // 指定参数为 true, 则同时会关闭 transferManager 内部的 COSClient 实例。
        // 指定参数为 false, 则不会关闭 transferManager 内部的 COSClient 实例。
        transferManager.shutdownNow(true);
    }



    
}
