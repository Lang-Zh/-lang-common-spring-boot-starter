package cn.lang.trace;

/**
 * Trace
 *
 * @author Lang 1102076808@qq.com
 * date 2022-6-06
 */
public class Trace {

    private String traceId;

    private String spanId;

    public static final String TRACE_ID = "X-LANG-TRACE";

    public static final String SPAN_ID = "X-LANG-SPAN";

    public static final String TRACE_HEADER = "X-TRACE";

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getSpanId() {
        return spanId;
    }

    public void setSpanId(String spanId) {
        this.spanId = spanId;
    }

    @Override
    public String toString() {
        return "Trace{" +
                "traceId='" + traceId + '\'' +
                ", spanId='" + spanId + '\'' +
                '}';
    }
}
