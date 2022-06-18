package cn.lang.trace.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "lang.trace")
public class TraceProperties {

    /**
     * trace open
     */
    private Boolean enabled;

    /**
     * trace gateway open
     */
    private Boolean gatewayEnabled;

    /**
     * trace feign open
     */
    private Boolean feignEnabled;

    /**
     * request log
     */
    private Boolean requestLogEnabled;

    public Boolean getEnabled() {
        return enabled;
    }

    public Boolean getGatewayEnabled() {
        return gatewayEnabled;
    }

    public Boolean getFeignEnabled() {
        return feignEnabled;
    }

    public Boolean getRequestLogEnabled() {
        return requestLogEnabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public void setGatewayEnabled(Boolean gatewayEnabled) {
        this.gatewayEnabled = gatewayEnabled;
    }

    public void setFeignEnabled(Boolean feignEnabled) {
        this.feignEnabled = feignEnabled;
    }

    public void setRequestLogEnabled(Boolean requestLogEnabled) {
        this.requestLogEnabled = requestLogEnabled;
    }
}
