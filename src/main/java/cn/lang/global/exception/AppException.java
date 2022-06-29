package cn.lang.global.exception;

import cn.lang.global.ret.RetBaseCode;
import cn.lang.global.ret.RetCode;

/**
 * @author Lang 1102076808@qq.com
 * @description 应用程序异常
 * @date 2022-06-16
 */
public class AppException extends BaseException {

    public AppException() {
        super(RetBaseCode.SYS_ERROR);
    }

    public AppException(String messageDetail) {
        super(RetBaseCode.SYS_ERROR, messageDetail);
    }

    public AppException(RetCode retCode) {
        super(retCode);
    }
}
