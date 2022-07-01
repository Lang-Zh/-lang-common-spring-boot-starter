package cn.lang.global.exception;

import cn.lang.global.ret.RetCode;

/**
 * @author Lang 1102076808@qq.com
 * @description 应用程序异常
 * @date 2022-06-16
 */
public class AppException extends BaseException {

    public AppException() {
        super(RetCode.SYS_ERROR);
    }

    public AppException(String message) {
        super(RetCode.SYS_ERROR, message);
    }

    public AppException(RetCode retCode) {
        super(retCode);
    }
}
