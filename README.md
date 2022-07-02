## lang-common-spring-boot-starter 使用说明


### trace 调用链路id

通过在访问日志和业务日志里记录的traceid、spanid  能完整的还原出整个请求的调用链路视图,对错误排查能起到很大的帮助；

- traceid: 整个请求链路中的唯一id，不同服务间会携带传递;
- spanid：当前服务的请求唯一id;

是借助日志MDC实现+springmvc拦截器实现。


#### 使用方式

1. 引入maven依赖

后续模块同样，不做额外说明。
```xml
<dependency>
  <groupId>io.github.lang-zh</groupId>
  <artifactId>lang-common-spring-boot-starter</artifactId>
  <version>1.0.2</version>
</dependency>
```

2. yml配置

```yaml
lang:
  trace:
    request-log-enabled: true ## 是否开启请求日志打印 开启后可以打印Controlelr的接口的请求参数
    enabled: true  ## 是否开启traceid记录
    gateway-enabled: true  ## 是否开启gateway网关传递traceid 默认开启
    feign-enabled: true  ## 是否开启feign传递traceid  默认开启
    ## 如果使用的是dubbo调用 会自动传递
```

- 单体springboot应用：只需要配置 `lang.trace.enbled=ture` 即可；
- 微服务应用：`lang.trace.enbled=ture`开启的同时，如果使用了gateway网关则 `lang.trace.gateway-enabled=ture` 开启，traceid将在网关中生成；服务与服务之间如果是 feign 调用 则开启 `lang.trace.feign-enabled=ture` 会自动在服务之前自动传递traceid。



`lang.trace.request-log-enabled=ture` 这个配置是开启接口请求参数打印，与trace无关。

打印效果：
```
Lang-common request report ---------2022-06-30 22:37:22 --------------------------
Url         : GET  /test
Controller  : cn.lang.producer.controller.TestController.(TestController.java:1)Method      : test
UrlPara     : {"name":["zhangsan"],"age":["11"]}
----------------------------------------------------------------------------------
```

3. 日志文件配置

日志xml配置文件中加入%X{X-LANG-TRACE}-%X{X-LANG-SPAN} 用于在指定位置打印traceid和spanid。

配置参考：
```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration scan="true" scanPeriod="60 seconds" debug="false">
    <property name="log.pattern" value="%d{yyyy-MM-dd HH:mm:ss.SSS} [${appName}-%X{X-LANG-TRACE}-%X{X-LANG-SPAN}] [%thread] %-5level %logger{20} - [%method,%line] - %msg%n"/>
    ...
</configuration>
```

#### 效果

![](https://files.catbox.moe/yf8y5a.png)



### global-exception 全局异常


正常的Web应用开发时，需要考虑到应用运行发生异常时或出现错误时如何来被处理，例如捕获必要的异常信息，记录日志方便日后排错，友好的用户响应输出等等。而应用程序发生错误有可能是应用自身的问题，也有可能是客户端操作的问题，在我们的项目中全局异常处理非常重要。

#### 使用方式

```yaml
lang:
  global-exception:
    enabled: true  关闭全局异常处理 （默认是开启的）
```

建议使用 `cn.lang.global.ret.Ret` 作为统一的返回参数参数；如果开启了 `trace` 配置会自动添加 `traceid` 与 `spanid` 参数。

```java
Ret.success(data);
Ret.error(RetBaseCode.SYS_ERROR);
```

系统中任意位置抛出 `Exception` 都会被 `GlobalExceptionHandler` 捕获并统一返回。
所以可以结合`hibernate-validator` 参数验证框架使用。

- @Validated
- ValidatorUtil.validate();

两种方式都行

另外默认提供的异常错误码枚举类 `RetBaseCode` 只提供了常见错误，如果需要扩展可以实现 `RetCode` 接口扩展错误码。

#### 效果

```json
{
    "code": 10412,
    "status": 400,
    "message": "请求参数异常",
    "messageDetail": "姓名不能为空;年龄不能为空;",
    "timestamp": "2022-06-30T15:17:53.406Z",
    "data": null,
    "traceId": "d7a5b5e72836444a890960bc",
    "spanId": "b05519f66f8a4e66",
    "ok": false
}
```


### OSS 对象存储

集成了多个供应商对象存储上传功能。

当前已集成：

- 腾讯云
- 阿里云
- 七牛云
- 又拍云
- MinIo
- Gitee


#### 使用方式

1. 引入maven依赖

为避免依赖冗余，腾讯云，阿里云，七牛云，又拍云，MinIo的SDK的maven依赖需要单独引入（使用哪个引入哪个）。
```xml
<dependency>
    <groupId>com.qcloud</groupId>
    <artifactId>cos_api</artifactId>
    <version>5.6.89</version>
</dependency>

<dependency>
    <groupId>com.aliyun.oss</groupId>
    <artifactId>aliyun-sdk-oss</artifactId>
    <version>3.10.2</version>
</dependency>

<dependency>
    <groupId>com.qiniu</groupId>
    <artifactId>qiniu-java-sdk</artifactId>
    <version>[7.2.0, 7.2.99]</version>
</dependency>

<dependency>
    <groupId>com.upyun</groupId>
    <artifactId>java-sdk</artifactId>
    <version>4.0.1</version>
</dependency>

<dependency>
    <groupId>io.minio</groupId>
    <artifactId>minio</artifactId>
    <version>7.0.2</version>
</dependency>

```

2. yml 配置

```yml
lang:
  oss:
    tencent:
      enabled: true
      secretId: xxx
      secretKey: xxx
      bucket: southwind-1255331426
    aliyun:
      enabled: true
      accessKeyId: xxx
      accessKeySecret: xx
      endpoint: https://oss-cn-hangzhou.aliyuncs.com
      bucket: langzh
    qny:
      enabled: true
      access-key:
      secret-key:
      bucket:
    upy： 
      enabled: true
      username：
      password：
      bucket：
    minio：
      enabled: true
      accessKey： 
      secretKey：
      bucket：
    gitee：
      enabled：true
      accessToken:  
      owner:  
      repo: 
```

使用

```java
@Autowired
private OssTencentHandler oss;

String key = oss.upload(new File("E:/desk/tz/forme/bg/bg1.jpeg"),"disk/a.png");
// 获取完整外链
oss.getUrl(key);

```

### security 接口鉴权模块

权限设计中一般遵循RBAC模型，以下只是一个权限设计的一个思路，借鉴于ruoyi。该模块非完整实现。

实际数据库设计一般五张表

- 用户表 USER
- 角色表 ROLE
- 菜单表/资源表 MENU/RESOURCES
- 用户角色关联表 USER_ROLE
- 角色菜单关联表 ROLE_MENU

这里可以访问的资源实际就是菜单

用户分配角色  角色拥有权限（拥有哪些资源可以访问，这里即哪些菜单按钮可以访问）

##### 表设计DEMO：
用户表 USER
```sql
CREATE TABLE `sys_user`  (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `user_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户账号',
  `nick_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户昵称',
  `user_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '用户类型（0系统用户 1注册用户）',
  `uuid` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '第三方授权用户uuid',
  `oauth_type` char(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '授权类型',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '用户邮箱',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '手机号码',
  `sex` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '用户性别（1男 0女 -1未知）',
  `avatar` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '头像地址',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '密码',
  `salt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '加密盐',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `login_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '最后登陆IP',
  `login_date` datetime(0) NULL DEFAULT NULL COMMENT '最后登陆时间',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 36 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户信息表' ROW_FORMAT = Compact;

```

角色表 ROLE
```sql
CREATE TABLE `sys_role`  (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `role_key` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色权限字符串',
  `role_sort` int(4) NOT NULL COMMENT '显示顺序',
  `data_scope` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色' ROW_FORMAT = Compact;

```
菜单表/资源表 MENU/RESOURCES
```sql
CREATE TABLE `sys_menu`  (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单名称',
  `parent_id` bigint(20) NULL DEFAULT 0 COMMENT '父菜单ID',
  `order_num` int(4) NULL DEFAULT 0 COMMENT '显示顺序',
  `path` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '路由地址',
  `component` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组件路径',
  `is_frame` int(1) NULL DEFAULT 1 COMMENT '是否为外链（0是 1否）',
  `menu_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
  `perms` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '#' COMMENT '菜单图标',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2022 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单权限表' ROW_FORMAT = Compact;
```
用户角色关联表 USER_ROLE
```sql
CREATE TABLE `sys_user_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户与角色对应关系' ROW_FORMAT = Compact;

```
角色菜单关联表 ROLE_MENU
```sql
CREATE TABLE `sys_role_menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NULL DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 868 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色与菜单对应关系' ROW_FORMAT = Compact;

```


菜单权限表中每条数据有唯一的perms字符串代表该 按钮或者菜单
角色表每天数据有唯一的role_key字符串代表改角色

当用户登录后 我们应当查出该用户所有的权限  即perms字符串列表以及role_key列表存储到缓存。

然后每个接口上加注解 `@hasPermission("system:role:edit")` 代表具有 `system:role:edit` 改perms权限的用户可以访问

接口加注解 `@hasRole("admin")` 代表具有管理员角色才能访问该接口

这个模块具体的是否拥有权限的方法需要调用者自己实现。实现 `PreAuthorizeHandler` 接口，实现对应是否拥有hasPermission，hasRole 方法。