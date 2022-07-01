package cn.lang.trace.filter;

import cn.lang.trace.Trace;
import cn.lang.trace.TraceHelper;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * TraceInterceptor
 *
 * @author Lang 1102076808@qq.com
 * @date 2022-06-16
 */
public class TraceInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        TraceHelper.setTrace(request.getHeader(Trace.TRACE_HEADER));
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex)  {
        TraceHelper.clearTrace();
    }

}
