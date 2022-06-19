package cn.lang.global.handler;


import cn.lang.global.exception.BaseException;
import cn.lang.global.ret.Ret;
import cn.lang.global.ret.RetCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Objects;

/**
 * @author Lang 1102076808@qq.com
 * @description 全局异常处理器
 * @date 2020-06-22 22:07
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(RuntimeException.class)
    public Ret<Object> handleAppException(RuntimeException exception) {
        if (exception instanceof BaseException) {
            BaseException baseException = (BaseException) exception;
            log.error("全局异常", exception);
            RetCode retCode = baseException.getRetCode();
            if (Objects.isNull(retCode)) {
                return Ret.error(exception.getMessage());
            }
            return Ret.error(retCode);
        }
        log.error("未知异常", exception);
        return Ret.error(RetCode.SYS_ERROR);
    }

    /**
     * 所有验证框架异常捕获处理
     *
     * @return
     */
    @ExceptionHandler(value = {BindException.class, MethodArgumentNotValidException.class})
    public Object validationExceptionHandler(Exception exception) {
        BindingResult bindResult = null;

        if (exception instanceof MethodArgumentNotValidException) {
            bindResult = ((MethodArgumentNotValidException) exception).getBindingResult();
        } else if (exception instanceof BindException) {
            bindResult = ((BindException) exception).getBindingResult();
        }
        String msg;
        if (bindResult != null && bindResult.hasErrors()) {
            msg = bindResult.getAllErrors().get(0).getDefaultMessage();
            if (msg != null && msg.contains("NumberFormatException")) {
                msg = "参数类型错误！";
            }
        } else {
            msg = "系统繁忙，请稍后重试...";
        }
        log.info("验证参数异常：{}", msg);
        return Ret.error(msg);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public Ret noHandlerFoundException(NoHandlerFoundException exception) {
        Ret ret = Ret.error(RetCode.RESOURCE_NOT_FOUND);
        log.error(RetCode.RESOURCE_NOT_FOUND.getMessage());
        log.error("未找到异常处理类", exception);
        return ret;
    }
}

