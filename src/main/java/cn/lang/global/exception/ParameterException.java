package cn.lang.global.exception;

import cn.lang.global.ret.RetBaseCode;
import cn.lang.global.ret.RetCode;

/**
 * @author Lang 1102076808@qq.com
 * @description 请求参数异常
 * @date 2020-06-22 22:15
 */
public class ParameterException extends BaseException {

    public ParameterException() {
        super(RetBaseCode.PARAMETER_ERROR);
    }

    public ParameterException(String messageDetail) {
        super(RetBaseCode.PARAMETER_ERROR, messageDetail);
    }

    public ParameterException(RetCode retCode) {
        super(retCode);
    }

}
