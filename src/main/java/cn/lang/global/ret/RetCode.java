package cn.lang.global.ret;

import org.springframework.http.HttpStatus;

/**
 * @author Lang 1102076808@qq.com
 * @title ErrorCode
 * @description 异常相应
 * @date 2020/6/22 22:10
 */
public enum RetCode {

    /**
     * 成功
     */
    SUCCESS(200, HttpStatus.OK, "操作成功"),

    /**
     * 404
     */
    RESOURCE_NOT_FOUND(10404, HttpStatus.NOT_FOUND, "未找到资源"),

    /**
     * 请求数据格式验证失败
     */
    REQUEST_VALIDATION_FAILED(10411, HttpStatus.BAD_REQUEST, "请求数据格式验证失败"),

    /**
     * 请求参数异常
     */
    PARAMETER_ERROR(10412, HttpStatus.BAD_REQUEST, "请求参数异常"),

    REQUEST_ERROR(10413, HttpStatus.BAD_REQUEST, "请求错误"),

    REQUEST_AUTHORIZATION_ERROR(10414, HttpStatus.BAD_REQUEST, "暂无权限"),

    REQUEST_AUTHENTICATION_ERROR(10415, HttpStatus.BAD_REQUEST, "鉴权失败"),

    REQUEST_SHIRO_ERROR(10416, HttpStatus.BAD_REQUEST, "授权失败"),

    /**
     * 系统异常
     */
    SYS_ERROR(10512, HttpStatus.INTERNAL_SERVER_ERROR, "系统异常");

    /**
     * 唯一标示异常的 code
     */
    private final int code;

    /**
     * HTTP 状态码
     */
    private final HttpStatus status;

    /**
     * 错误的具体信息
     */
    private final String message;

    RetCode(int code, HttpStatus status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ErrorCode{" +
                "code=" + code +
                ", status=" + status +
                ", message='" + message + '\'' +
                '}';
    }
}
