## Interceptor vs Filter

Spring的Interceptor(拦截器)与Servlet的Filter有相似之处，都能实现权限检查、日志记录等。不同的是：

| Filter                                                       | Interceptor                                                  | Summary                                                      |
| :----------------------------------------------------------- | :----------------------------------------------------------- | :----------------------------------------------------------- |
| Filter 接口定义在 javax.servlet 包中                         | 接口 HandlerInterceptor 定义在org.springframework.web.servlet 包中 |                                                              |
| Filter 定义在 web.xml 中                                     |                                                              |                                                              |
| Filter在只在 Servlet 前后起作用。Filters 通常将 请求和响应（request/response） 当做黑盒子，Filter 通常不考虑servlet 的实现。 | 拦截器能够深入到方法前后、异常抛出前后等，因此拦截器的使用具有更大的弹性。允许用户介入（hook into）请求的生命周期，在请求过程中获取信息，Interceptor 通常和请求更加耦合。 | 在Spring构架的程序中，要优先使用拦截器。几乎所有 Filter 能够做的事情， interceptor 都能够轻松的实现[1](http://einverne.github.io/post/2017/08/spring-interceptor-vs-filter.html#fn:top) |
| Filter 是 Servlet 规范规定的。                               | 而拦截器既可以用于Web程序，也可以用于Application、Swing程序中。 | 使用范围不同                                                 |
| Filter 是在 Servlet 规范中定义的，是 Servlet 容器支持的。    | 而拦截器是在 Spring容器内的，是Spring框架支持的。            | 规范不同                                                     |
| Filter 不能够使用 Spring 容器资源                            | 拦截器是一个Spring的组件，归Spring管理，配置在Spring文件中，因此能使用Spring里的任何资源、对象，例如 Service对象、数据源、事务管理等，通过IoC注入到拦截器即可 | Spring 中使用 interceptor 更容易                             |
| Filter 是被 Server(like Tomcat) 调用                         | Interceptor 是被 Spring 调用                                 | 因此 Filter 总是优先于 Interceptor 执行                      |



### 使用场景

#### Request Filters 

1. 执行安全检查 perform security checks
2. 格式化请求头和主体 reformat request headers or bodies
3. 审查或者记录日志 audit or log requests
4. 根据请求内容授权或者限制用户访问 Authentication-Blocking requests based on user identity.
5. 根据请求频率限制用户访问

#### Response Filters

1. 压缩响应内容,比如让下载的内容更小 Compress the response stream
2. 追加或者修改响应 append or alter the response stream
3. 创建或者整体修改响应 create a different response altogether
4. 根据地方不同修改响应内容 Localization-Targeting the request and response to a particular locale.



### 参考文章

[http://einverne.github.io/post/2017/08/spring-interceptor-vs-filter.html](http://einverne.github.io/post/2017/08/spring-interceptor-vs-filter.html)