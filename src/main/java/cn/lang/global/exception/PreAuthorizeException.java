package cn.lang.global.exception;


import cn.lang.global.ret.RetBaseCode;
import cn.lang.global.ret.RetCode;

/**
 * 鉴权异常
 *
 * @author rabbit
 */
public class PreAuthorizeException extends BaseException {

    public PreAuthorizeException() {
        super(RetBaseCode.REQUEST_AUTHENTICATION_ERROR);
    }

    public PreAuthorizeException(String messageDetail) {
        super(RetBaseCode.REQUEST_AUTHENTICATION_ERROR, messageDetail);
    }

    public PreAuthorizeException(RetCode retCode) {
        super(retCode);
    }
}
