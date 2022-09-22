package cn.lang.global.validator;

import cn.hutool.core.lang.Assert;
import cn.lang.global.exception.AppException;
import cn.lang.global.ret.RetCode;

/**
 * 断言工具类
 *
 * @author zhangren
 * @date 2022/09/03
 */
public class AssertUtil extends Assert {

    public static void isFalse(Boolean flag, RetCode errorCode, AssertFuture assertFuture) {
        assertFuture.accept();
        isFalse(flag, () -> new AppException(errorCode));
    }

    /**
     * flag为ture 则抛出异常
     *
     * @param flag      flag;
     * @param errorCode 错误代码
     */
    public static void isFalse(Boolean flag, RetCode errorCode) {
        isFalse(flag, () -> new AppException(errorCode));
    }

    public static void isFalse(Boolean flag, String messageDetail) {
        isFalse(flag, () -> new AppException(messageDetail));
    }

    /**
     * 断言未来
     *
     * @author zhangren
     * @date 2022/09/05
     */
    @FunctionalInterface
    public interface AssertFuture {
        void accept();
    }
}
