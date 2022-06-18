package cn.lang.global.ret;

/**
 * @author Lang 1102076808@qq.com
 * @description
 * @date 2020-06-22 22:13
 */

import cn.lang.trace.Trace;
import cn.lang.trace.TraceHelper;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

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

    public boolean isSuccess() {
        return this.code == 200;
    }

    public static <T> Ret<T> success() {
        return new Ret<>(RetCode.SUCCESS);
    }

    /**
     * 成功时候的调用
     *
     * @param data data
     * @param <T>  t
     * @return Ret
     */
    public static <T> Ret<T> success(T data) {
        Ret<T> ret = new Ret<>(RetCode.SUCCESS);
        ret.setData(data);
        return ret;
    }

    public static <T> Ret<T> success(String message) {
        Ret<T> ret = new Ret<>(RetCode.SUCCESS);
        ret.setMessage(message);
        return ret;
    }

    /**
     * 失败时候的调用
     *
     * @param retCode retCode
     * @return Ret
     */
    public static <T> Ret<T> error(RetCode retCode) {
        return new Ret<>(retCode);
    }

    /**
     * @Description 失败时候的调用
     * @author Lang
     * @Date 2020/6/22 23:19
     */
    public static <T> Ret<T> error(String message) {
        Ret<T> ret = new Ret<>(RetCode.REQUEST_ERROR);
        ret.setMessage(message);
        return ret;
    }

    public static <T> Ret<T> error(T data, String message) {
        RetCode retCode = RetCode.REQUEST_ERROR;
        if (data instanceof RetCode) {
            retCode = (RetCode) data;
        }
        Ret<T> ret = new Ret<>(retCode);
        ret.setMessage(message);
        ret.setData(data);
        return ret;
    }

    public Ret() {
    }

    /**
     * 成功的构造函数
     *
     * @param data data
     */
    public Ret(T data) {
        //默认200是成功
        this.code = 200;
        this.status = HttpStatus.OK.value();
        this.message = "操作成功";
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
        if (!Objects.isNull(trace)) {
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

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
