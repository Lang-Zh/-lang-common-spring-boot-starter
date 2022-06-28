package cn.lang.global.validator;

/**
 * @author Lang 1102076808@qq.com
 * @description 全局异常处理器
 * @date 2020-06-28 22:07
 */

import cn.lang.global.exception.BaseException;
import cn.lang.global.exception.ParameterException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * hibernate-validator校验工具类
 *
 * @author Lang 1102076808@qq.com
 * @date 2020-06-28 16:13
 */
public class ValidatorUtil {

    private static Validator validator;

    static {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    /**
     * 校验对象
     *
     * @param object 待校验对象
     * @param groups 待校验的组
     * @throws BaseException 校验不通过，则报BaseException异常
     */
    public static void validate(Object object, Class<?>... groups) throws ParameterException {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
        if (!constraintViolations.isEmpty()) {
            StringBuilder msg = new StringBuilder();
            for (ConstraintViolation<Object> constraint : constraintViolations) {
                msg.append(constraint.getMessage()).append(";");
            }
            throw new ParameterException(msg.toString());
        }
    }
}
