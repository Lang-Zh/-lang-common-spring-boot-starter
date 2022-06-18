package cn.lang.trace;

import org.slf4j.MDC;

import java.util.UUID;

/**
 * TraceHelper
 *
 * @author Lang 1102076808@qq.com
 * @date 2022-6-06
 */
public class TraceHelper {

    public static final ThreadLocal<Trace> TRACE_CONTEXT = new ThreadLocal<Trace>();

    /**
     * 生成traceId
     *
     * @return
     */
    public static String genTraceId() {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 24);
    }

    /**
     * 生成spanId
     *
     * @return
     */
    public static String genSpanId() {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 16);
    }

    public static void genTrace() {
        setTrace(null);
    }

    /**
     * 设置Trace到 上下文对象 以及logback MDC
     *
     * @param traceId
     */
    public static void setTrace(String traceId) {
        if (traceId == null) {
            traceId = genTraceId();
        }
        Trace trace = new Trace();
        trace.setTraceId(traceId);
        trace.setSpanId(genSpanId());
        TRACE_CONTEXT.set(trace);
        MDC.put(Trace.TRACE_ID, trace.getTraceId());
        MDC.put(Trace.SPAN_ID, trace.getSpanId());
    }

    /**
     * 获取trace对象
     *
     * @return
     */
    public static Trace getTrace() {
        return TRACE_CONTEXT.get();
    }

    /**
     * 用完清空上下文对象 避免内存泄露
     */
    public static void clearTrace() {
        TRACE_CONTEXT.set(null);
        TRACE_CONTEXT.remove();
    }

}
