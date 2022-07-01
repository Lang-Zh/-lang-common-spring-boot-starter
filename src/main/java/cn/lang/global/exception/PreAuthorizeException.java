package cn.lang.global.exception;


import cn.lang.global.ret.RetCode;

/**
 * 鉴权异常
 *
 * @author rabbit
 */
public class PreAuthorizeException extends BaseException {

    public PreAuthorizeException() {
        super(RetCode.REQUEST_AUTHENTICATION_ERROR);
    }

    public PreAuthorizeException(String message) {
        super(RetCode.REQUEST_AUTHENTICATION_ERROR, message);
    }

    public PreAuthorizeException(RetCode retCode) {
        super(retCode);
    }
}
