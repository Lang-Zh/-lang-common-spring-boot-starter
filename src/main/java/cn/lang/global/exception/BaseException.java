package cn.lang.global.exception;


import cn.lang.global.ret.RetCode;

/**
 * @author Lang 1102076808@qq.com
 * @description 基础异常类
 * @date 2020-06-22 22:14
 */
public abstract class BaseException extends RuntimeException {

    private RetCode retCode;

    public BaseException() {
    }

    public BaseException(RetCode retCode) {
        super(retCode.getMessage());
        this.retCode = retCode;
    }

    public BaseException(String messageDetail){
        super(messageDetail);
    }

    public BaseException(RetCode retCode,String messageDetail){
        super(messageDetail);
        this.retCode = retCode;
    }

    public void setRetCode(RetCode retCode) {
        this.retCode = retCode;
    }

    public RetCode getRetCode() {
        return retCode;
    }

}
