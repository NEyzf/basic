FROM monica-cn-beijing.cr.volces.com/monica/client:v1

ENV WORKDIR /app
WORKDIR "$WORKDIR"

COPY basic/demo-with-nacos.jar "$WORKDIR"/
COPY basic/demo-with-nacos2.x.jar "$WORKDIR"/
COPY basic/demo-with-nacos2.x.jar "$WORKDIR"/
COPY basic/demo-with-nacos-zj.jar "$WORKDIR"/
COPY basic/demo-with-tomcat.war /opt/tomcat/webapps/


CMD ["bash"]

