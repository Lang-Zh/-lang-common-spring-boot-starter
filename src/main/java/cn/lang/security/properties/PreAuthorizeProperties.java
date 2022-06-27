package cn.lang.security.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "lang.authorize")
public class PreAuthorizeProperties {

    /**
     * open  PreAuthorize
     */
    private Boolean enabled;

}
