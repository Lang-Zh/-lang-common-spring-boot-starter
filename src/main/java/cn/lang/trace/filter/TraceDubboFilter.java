package cn.lang.trace.filter;


import cn.lang.trace.Trace;
import cn.lang.trace.TraceHelper;
import org.apache.dubbo.rpc.*;

import java.util.Objects;

/**
 * TraceDubboInterceptor
 * dubbo3 过滤器
 * @author Lang 1102076808@qq.com
 * @date 2022-06-19
 */
public class TraceDubboFilter implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {

        String traceId = RpcContext.getServerContext().getAttachment(Trace.TRACE_HEADER);
        TraceHelper.setTrace(traceId);

        Trace trace = TraceHelper.getTrace();
        if (Objects.nonNull(trace) && trace.getTraceId() != null){
            RpcContext.getServerContext().setAttachment(Trace.TRACE_HEADER,trace.getTraceId());
        }
        // 纯dubbo服务时 未清除trace 有内存泄露风险
        return invoker.invoke(invocation);
    }

}
