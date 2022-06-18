package cn.lang.trace.filter;

import cn.lang.trace.Trace;
import cn.lang.trace.TraceHelper;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;

/**
 * TraceFeignInterceptor
 *
 * @author Lang 1102076808@qq.com
 * @date 2022-06-17
 */
public class TraceFeignInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        Trace trace = TraceHelper.getTrace();
        requestTemplate.header(Trace.TRACE_HEADER, trace.getTraceId());
    }
}
