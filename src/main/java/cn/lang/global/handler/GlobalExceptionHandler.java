package cn.lang.global.handler;


import cn.lang.global.exception.BaseException;
import cn.lang.global.ret.Ret;
import cn.lang.global.ret.RetBaseCode;
import cn.lang.global.ret.RetCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

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
            StringBuilder msgSb = new StringBuilder();
            bindResult.getAllErrors().forEach(e -> msgSb.append(e.getDefaultMessage()).append(";"));
            msg = msgSb.toString();
        } else {
            msg = "系统繁忙，请稍后重试...";
        }
        log.error("全局异常:验证框架异常", exception);
        return Ret.error(RetBaseCode.PARAMETER_ERROR, msg);
    }

    @ExceptionHandler(BaseException.class)
    public Ret<Object> handleAppException(BaseException exception) {
        log.error("全局异常:BaseException", exception);
        RetCode retCode = exception.getRetCode();
        if (Objects.isNull(retCode)) {
            return Ret.error(exception.getMessage());
        }
        return Ret.error(retCode, exception.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public Ret<Object> handleAppException(RuntimeException exception) {
        log.error("全局异常:RuntimeException", exception);
        return Ret.error(RetBaseCode.SYS_ERROR, exception.getMessage());
    }

    @ExceptionHandler(Throwable.class)
    public Ret<Object> handleAppException(Throwable throwable) {
        log.error("全局异常:Throwable", throwable);
        return Ret.error(RetBaseCode.SYS_ERROR, throwable.getMessage());
    }

}

