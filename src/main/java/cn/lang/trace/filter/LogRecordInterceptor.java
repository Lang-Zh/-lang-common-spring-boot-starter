package cn.lang.trace.filter;

import cn.hutool.json.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Controller请求信息打印拦截器
 *
 * @author Lang 1102076808@qq.com
 * @date 2022-06-17
 */
public class LogRecordInterceptor implements HandlerInterceptor {

    Logger log = LoggerFactory.getLogger(LogRecordInterceptor.class);

    private static final ThreadLocal<SimpleDateFormat> sdf = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (handler instanceof HandlerMethod) {
            report(request, response, (HandlerMethod) handler);
        }
        return true;
    }

    public void report(HttpServletRequest request, HttpServletResponse response, HandlerMethod handler) {
        String method = request.getMethod();
        String uri = request.getRequestURI();
        Class<?> cc = handler.getBeanType();
        String paraString = JSONUtil.toJsonStr(request.getParameterMap());
        StringBuilder sbRequest = new StringBuilder("\nLang-common request report ---------")
                .append((sdf.get()).format(new Date())).append(" --------------------------\n");
        sbRequest.append("Url         : ").append(method).append("  ").append(uri).append("\n");
        sbRequest.append("Controller  : ").append(cc.getName()).append(".(").append(cc.getSimpleName()).append(".java:1)");
        sbRequest.append("Method      : ").append(handler.getMethod().getName()).append("\n");
        sbRequest.append("UrlPara     : ").append(paraString).append("\n");
        sbRequest.append("----------------------------------------------------------------------------------\n");
        log.info(sbRequest.toString());
    }

}
