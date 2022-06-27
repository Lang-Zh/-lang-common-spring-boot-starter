package cn.lang.global.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "lang.global-exception")
public class GlobalExceptionProperties {

    private Boolean enabled;
}
