# `mse k8s+java` ——测试工具箱

## 打包相关
```
# 拦截注册或双注册场景配置spring.profiles.active=nacos 
mvn package
mv ./target/basic-1.0-SNAPSHOT.jar ./demo-with-nacos.jar
# 自动注册场景配置spring.profiles.active=none
mvn package
mv ./target/basic-1.0-SNAPSHOT.jar ./demo-without-nacos.jar
# nacos-discovery 2.x 版本
mvn package -f pomnn20.xml
mv ./target/basic-1.0-SNAPSHOT.jar ./demo-with-nacos2.x.jar
# 启动方式为tomcat
mvn package -f pomwar.xml
mv ./target/basic-1.0-SNAPSHOT.war ./demo-with-tomcat.war
# 智己场景，spring-cloud-starter-openfeign 为2.x
mvn package -f pomzj.xml
mv ./target/basic-1.0-SNAPSHOT.jar ./demo-with-nacos-zj.jar
```
## 构建镜像
``
docker build -f ../server.Dockerfile  -t  monica-cn-beijing.cr.volces.com/monica/mse-tools:v1
``
## 环境变量

## 框架&API
```
# feigh 9.x & feigh 10.x
/feign/self/getBpost
/feign/self/getBdelete
/feign/self/getBhead
/feign/self/getBoptions
/feign/self/getBput
/feign/self/getBheadertest
/feign/self/getBrequestparamtest
/feign/self/getBpathvariabletest
/feign/self/getBbodytest

# feigh with ribbon
/feign/ribbon/getBpost
/feign/ribbon/getBdelete
/feign/ribbon/getBhead
/feign/ribbon/getBoptions
/feign/ribbon/getBput
/feign/ribbon/getBheadertest
/feign/ribbon/getBrequestparamtest
/feign/ribbon/getBpathvariabletest
/feign/ribbon/getBbodytest
/feign/ribbon/getBUser

# feigh with springboot
/feign/springboot/getBpost
/feign/springboot/getBdelete
/feign/springboot/getBhead
/feign/springboot/getBoptions
/feign/springboot/getBput
/feign/springboot/getBheadertest
/feign/springboot/getBrequestparamtest
/feign/springboot/getBpathvariabletest
/feign/springboot/getBbodytest
/feign/springboot/getBUser
/feign/springboot/AtoB1
/feign/springboot/AtoB2
/feign/springboot/passthrough //覆盖passthrough常见，默认http://www.baidu.com，自定义常见可通过环境变量ExternalAddr传入
/feign/springboot/getBupload  //上传下载场景，上传时需自己指定本地存在的文件，支持上传任意类型文件，图片、视频、文本等，单文件上线为5GB
/feign/springboot/getBdownload/?fileName={}

# httpclient
/httpclient/getBpost
/httpclient/getBdelete
/httpclient/getBhead
/httpclient/getBoptions
/httpclient/getBput
/httpclient/getBpatch
/httpclient/getBtrace
/httpclient/getBheadertest
/httpclient/getBrequestparamtest
/httpclient/getBpathvariabletest
/httpclient/getBbodytest
/httpclient/passthrough

# okhttp
/okhttp/getBpost
/okhttp/getBdelete
/okhttp/getBhead
/okhttp/getBput
/okhttp/getBpatch
/okhttp/getBheadertest
/okhttp/getBrequestparamtest
/okhttp/getBpathvariabletest
/okhttp/getBbodytest

# restTemplate with apache
/rest/apache/getBpost
/rest/apache/getBdelete
/rest/apache/getBhead
/rest/apache/getBoptions
/rest/apache/getBput
/rest/apache/getBpatch
/rest/apache/getBheadertest
/rest/apache/getBrequestparamtest
/rest/apache/getBpathvariabletest
/rest/apache/getBbodytest
/rest/apache/getBUser
/rest/apache/passthrough

# restTemplate with ribbon
/rest/ribbon/getBpost
/rest/ribbon/getBdelete
/rest/ribbon/getBhead
/rest/ribbon/getBoptions
/rest/ribbon/getBput
/rest/ribbon/getBpatch
/rest/ribbon/getBheadertest
/rest/ribbon/getBrequestparamtest
/rest/ribbon/getBpathvariabletest
/rest/ribbon/getBbodytest
/rest/ribbon/getBUser

# restTemplate with springboot
/rest/springboot/getBpost
/rest/springboot/getBdelete
/rest/springboot/getBhead
/rest/springboot/getBoptions
/rest/springboot/getBput
/rest/springboot/getBheadertest
/rest/springboot/getBrequestparamtest
/rest/springboot/getBpathvariabletest
/rest/springboot/getBbodytest
/rest/springboot/getBUser

# retrofit
/retrofit/getBpost
/retrofit/getBdelete
/retrofit/getBhead
/retrofit/getBoptions
/retrofit/getBput
/retrofit/getBpatch
/retrofit/getBheadertest
/retrofit/getBrequestparamtest
/retrofit/getBpathvariabletest
/retrofit/getBbodytest
/retrofit/getBUser
/retrofit/AtoB1
/retrofit/AtoB2
/retrofit/passthrough
/retrofit/asynchronous/getBUser

# retrofit with ribbon
/retrofit/ribbon/getBpost
/retrofit/ribbon/getBdelete
/retrofit/ribbon/getBhead
/retrofit/ribbon/getBoptions
/retrofit/ribbon/getBput
/retrofit/ribbon/getBpatch
/retrofit/ribbon/getBheadertest
/retrofit/ribbon/getBrequestparamtest
/retrofit/ribbon/getBpathvariabletest
/retrofit/ribbon/getBbodytest
/retrofit/ribbon/getBUser
/retrofit/ribbon/AtoB1
/retrofit/ribbon/AtoB2
/retrofit/ribbon/asynchronous/getBUser //异步场景

# webclient
/webclient/getBpost
/webclient/getBdelete
/webclient/getBhead
/webclient/getBoptions
/webclient/getBput
/webclient/getBpatch
/webclient/getBheadertest
/webclient/getBrequestparamtest
/webclient/getBpathvariabletest
/webclient/getBUser
/webclient/AtoB1
/webclient/AtoB2
/webclient/passthrough

# webclient with ribbon
/webclient/ribbon/getBpost
/webclient/ribbon/getBdelete
/webclient/ribbon/getBhead
/webclient/ribbon/getBoptions
/webclient/ribbon/getBput
/webclient/ribbon/getBpatch
/webclient/ribbon/getBheadertest
/webclient/ribbon/getBrequestparamtest
/webclient/ribbon/getBpathvariabletest
/webclient/ribbon/getBUser
/webclient/ribbon/AtoB1
/webclient/ribbon/AtoB2
```

## 重要接口
| 接口  | 作用                                                                   | 示例                                                                                                                                                                                                                                                                                      |
|-----|----------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| /forward?target={} | 透传client的method、header、body等，并通过target参数指定目的地址                       | ``curl  -H 'h1:v1' -d 'id=1' "localhost/forward/?target=http://serviceb/post"``                                                                                                                                                                                                         |
| /getBPost | 返回server详细信息，报告名称、版本、及hostname                                       |                                                                                                                                                                                                                                                                                         |
| /getBstatus/?status={} | 指定sever返回码                                                           | ``curl  -o /dev/null -s -w %{http_code} -X GET "localhost/getBstatus/?status=504"``                                                                                                                                                                                                     |
| /getBsleep/{time} | 制定server返回延迟时间                                                       |                                                                                                                                                                                                                                                                                         |
| /getnext | 配合环境变量CurServiceName、NextServiceName使用构造复杂调用链路，实现除baggage以外的header透传 ||
| Echo| 返回server详细信息，报告名称、版本、及hostname                                       | grpcurl -plaintext :17171 proto.EchoService/Echo                                                                                                                                                                                                                                        |
| ForwardEcho| 指定下一跳地址，同时可通过count配置访问次数，delay配置返回时延                                 | ``grpcurl -plaintext -d '{"url":"echob.test-public-default-group.svc.cluster.local"}' :17171 proto.EchoService/ForwardEcho``<br/>``grpcurl -plaintext -d '{"url":"echob.test-public-default-group.svc.cluster.local","count":3}' :17171 proto.EchoService/ForwardEcho``<br/>`time grpcurl -plaintext -d '{"url":"echob.test-public-default-group.svc.cluster.local","delay":5000}' :17171 proto.EchoService/ForwardEcho` |
| LaneEcho| 配合环境变量Target，使用构造复杂调用链路，实现除baggage以外的header透传                        ||