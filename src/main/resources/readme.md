
```yml

## trace模块
## 日志xml配置文件中加入%X{X-LANG-TRACE}-%X{X-LANG-SPAN} 用于打印traceid和spanid
## 可参考日志MDC用法
lang:
  trace:
    request-log-enabled: true ## 是否开启请求日志打印
    enabled: true      ## 是否开启traceid记录
    feign-enabled: true  ## 是否开启feign传递traceid
    gateway-enabled: true  ## 是否开启网关传递traceid
  
  global-exception:
     enabled: false #关闭默认全局异常处理
  
```