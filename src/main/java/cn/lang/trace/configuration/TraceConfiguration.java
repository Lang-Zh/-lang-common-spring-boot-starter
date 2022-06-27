package cn.lang.trace.configuration;

import cn.lang.trace.filter.LogRecordInterceptor;
import cn.lang.trace.filter.TraceInterceptor;
import cn.lang.trace.properties.TraceProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * TraceConfiguration
 *
 * @author Lang 1102076808@qq.com
 * @date 2022-06-17
 */
@Configuration
@EnableConfigurationProperties({TraceProperties.class})
public class TraceConfiguration implements WebMvcConfigurer {

    @Autowired
    private TraceProperties traceProperties;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        if (traceProperties.getEnabled() != null && traceProperties.getEnabled()) {
            registry.addInterceptor(new TraceInterceptor()).addPathPatterns("/**");
        }
        if (traceProperties.getRequestLogEnabled() != null && traceProperties.getRequestLogEnabled()) {
            registry.addInterceptor(new LogRecordInterceptor()).addPathPatterns("/**");
        }
    }

}
