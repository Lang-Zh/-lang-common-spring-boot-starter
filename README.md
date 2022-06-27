# lang-common-spring-boot-starter

#### 介绍
通用的工具

#### 软件架构
- global 全局异常处理
- oss 整合的对象存储对接
- security 接口鉴权处理
- trace 路由唯一id跟踪

#### 使用说明


```yml

## trace模块
## 日志xml配置文件中加入%X{X-LANG-TRACE}-%X{X-LANG-SPAN} 用于打印traceid和spanid
## 可参考日志MDC用法
lang:
  trace:
    request-log-enabled: true ## 是否开启请求日志打印 开启后可以打印Controlelr的接口的请求参数
    enabled: true      ## 是否开启traceid记录
    feign-enabled: true  ## 是否开启feign传递traceid
    gateway-enabled: true  ## 是否开启gateway网关传递traceid
    ## 如果使用的是dubbo 会自动传递 

## global模块
lang:
  global-exception:
    enabled: false  关闭全局异常处理 （默认是开启的）


## oss模块


  
```




#### 参与贡献

1.  Fork 本仓库
2.  新建 Feat_xxx 分支
3.  提交代码
4.  新建 Pull Request


#### 特技

1.  使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2.  Gitee 官方博客 [blog.gitee.com](https://blog.gitee.com)
3.  你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解 Gitee 上的优秀开源项目
4.  [GVP](https://gitee.com/gvp) 全称是 Gitee 最有价值开源项目，是综合评定出的优秀开源项目
5.  Gitee 官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6.  Gitee 封面人物是一档用来展示 Gitee 会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)
