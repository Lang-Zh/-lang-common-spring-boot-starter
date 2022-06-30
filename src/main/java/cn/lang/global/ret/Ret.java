package cn.lang.global.ret;


import cn.lang.trace.Trace;
import cn.lang.trace.TraceHelper;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * @author Lang 1102076808@qq.com
 * description 统一返回参数
 * date 2020-06-22 22:13
 */
public class Ret<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 唯一标示异常的 code
     */
    private int code;
    /**
     * HTTP 状态码
     */
    private Integer status;
    /**
     * 错误的具体信息
     */
    private String message;
    /**
     * 开发错误信息
     */
    private String messageDetail;
    /**
     * 发生错误的时间戳
     */
    private Instant timestamp;
    /**
     * 返回数据
     */
    private T data;

    /**
     * 全局traceId
     */
    private String traceId;

    /**
     * 当前服务spanId
     */
    private String spanId;

    public Boolean isOk() {
        return this.code == 200;
    }

    public static <T> Ret<T> success() {
        return new Ret<>(RetBaseCode.SUCCESS);
    }
    /**
     * 成功时候的调用
     * @param data 返回值
     * @param messageDetail 错误描述
     * @return 统一返回包装
     */
    public static <T> Ret<T> success(T data, String messageDetail) {
        Ret<T> ret = new Ret<>(RetBaseCode.SUCCESS);
        if (Objects.nonNull(data)){
            ret.setData(data);
        }
        if (Objects.nonNull(messageDetail)){
            ret.setMessageDetail(messageDetail);
        }
        return ret;
    }

    /**
     * 成功时候的调用
     *
     * @param <T>  data
     * @return Ret
     */
    public static <T> Ret<T> success(T data) {
        return success(data, null);
    }

    /**
     * 不重载success 避免success(T data)中传入String类型值 而进入了当前方法
     *
     * @param messageDetail 错误描述
     * @return Ret
     */
    public static <T> Ret<T> successMsg(String messageDetail) {
        return success(null, messageDetail);
    }


    /**
     * 失败时候的调用
     *
     * @param retCode 错误码
     * @param messageDetail  错误描述
     * @param <T> 返回值
     * @return 统一返回包装
     */
    public static <T> Ret<T> error(RetCode retCode, String messageDetail) {
        Ret<T> ret = new Ret<>(retCode);
        ret.setMessageDetail(messageDetail);
        return ret;
    }

    /**
     * 失败时候的调用
     *
     * @param retCode retCode
     * @return Ret 统一返回包装
     */
    public static <T> Ret<T> error(RetCode retCode) {
        return new Ret<>(retCode);
    }

    /**
     * description 失败时候的调用
     * @author Lang
     * date 2020/6/22 23:19
     * @param messageDetail messageDetail
     * @return Ret
     */
    public static <T> Ret<T> error(String messageDetail) {
        return error(RetBaseCode.REQUEST_ERROR, messageDetail);
    }

    /**
     * 失败时候的调用
     * @param data 可以传RetCode
     * @param messageDetail  错误描述
     * @param <T>  返回值
     * @return 统一返回包装
     */
    public static <T> Ret<T> error(T data, String messageDetail) {
        Ret<T> ret = error(messageDetail);
        ret.setData(data);
        return ret;
    }

    public Ret() {
        initTrace();
    }

    /**
     * 成功的构造函数
     *
     * @param data data
     */
    public Ret(T data) {
        //默认200是成功
        this.code = RetBaseCode.SUCCESS.getCode();
        this.status = RetBaseCode.SUCCESS.getStatus().value();
        this.message = RetBaseCode.SUCCESS.getMessage();
        this.data = data;
        this.timestamp = Instant.now();
        initTrace();
    }

    private Ret(RetCode retCode) {
        if (retCode != null) {
            this.code = retCode.getCode();
            this.status = retCode.getStatus().value();
            this.message = retCode.getMessage();
            this.timestamp = Instant.now();
        }
        initTrace();
    }

    public Ret(int code, int status, String message, T data) {
        this.code = code;
        this.status = status;
        this.message = message;
        this.timestamp = Instant.now();
        this.data = data;
        initTrace();
    }

    private void initTrace() {
        Trace trace = TraceHelper.getTrace();
        if (Objects.nonNull(trace)) {
            this.traceId = trace.getTraceId();
            this.spanId = trace.getSpanId();
        }
    }

    @Override
    public String toString() {
        return "Ret{" +
                "code=" + code +
                ", status=" + status +
                ", message='" + message + '\'' +
                ", timestamp=" + timestamp +
                ", data=" + data +
                '}';
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageDetail() {
        return messageDetail;
    }

    public void setMessageDetail(String messageDetail) {
        this.messageDetail = messageDetail;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getSpanId() {
        return spanId;
    }

    public void setSpanId(String spanId) {
        this.spanId = spanId;
    }
}
