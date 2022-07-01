package cn.lang.global.ret;

import org.springframework.http.HttpStatus;

/**
 * @author Lang 1102076808@qq.com
 * description 自定义的异常错误码枚举需要实现该接口
 * date 2020/6/28 18:10
 */
public interface RetCode {

    int getCode();

    HttpStatus getStatus();

    String getMessage();
}
