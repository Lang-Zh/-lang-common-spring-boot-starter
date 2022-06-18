package cn.lang.security.aspect;

import cn.hutool.core.util.StrUtil;
import cn.lang.global.exception.PreAuthorizeException;
import cn.lang.security.annotation.PreAuthorize;
import cn.lang.security.handler.PreAuthorizeHandler;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Method;

import static cn.lang.security.handler.PreAuthorizeHandler.ARRAY_EMPTY;

/**
 * 自定义权限实现
 *
 * @author lang
 */
@Aspect
public class PreAuthorizeAspect {

    @Autowired
    private PreAuthorizeHandler preAuthorizeHandler;

    @Around("@annotation(cn.lang.security.annotation.PreAuthorize)")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Signature signature = point.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        PreAuthorize annotation = method.getAnnotation(PreAuthorize.class);
        if (annotation == null) {
            return point.proceed();
        }

        if (!StrUtil.isEmpty(annotation.hasPermission())) {
            if (preAuthorizeHandler.hasPermission(annotation.hasPermission())) {
                return point.proceed();
            }
            throw new PreAuthorizeException();
        } else if (!StrUtil.isEmpty(annotation.lacksPermission())) {
            if (preAuthorizeHandler.lacksPermission(annotation.lacksPermission())) {
                return point.proceed();
            }
            throw new PreAuthorizeException();
        } else if (ARRAY_EMPTY < annotation.hasAnyPermission().length) {
            if (preAuthorizeHandler.hasAnyPermission(annotation.hasAnyPermission())) {
                return point.proceed();
            }
            throw new PreAuthorizeException();
        } else if (!StrUtil.isEmpty(annotation.hasRole())) {
            if (preAuthorizeHandler.hasRole(annotation.hasRole())) {
                return point.proceed();
            }
            throw new PreAuthorizeException();
        } else if (!StrUtil.isEmpty(annotation.lacksRole())) {
            if (preAuthorizeHandler.lacksRole(annotation.lacksRole())) {
                return point.proceed();
            }
            throw new PreAuthorizeException();
        } else if (ARRAY_EMPTY < annotation.hasAnyRoles().length) {
            if (preAuthorizeHandler.hasAnyRoles(annotation.hasAnyRoles())) {
                return point.proceed();
            }
            throw new PreAuthorizeException();
        }

        return point.proceed();
    }


}
