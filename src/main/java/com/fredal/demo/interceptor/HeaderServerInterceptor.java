package com.fredal.demo.interceptor;

import com.google.common.annotations.VisibleForTesting;
import io.grpc.*;

import java.util.logging.Logger;

public class HeaderServerInterceptor implements ServerInterceptor {
    private static final Logger logger = Logger.getLogger(HeaderServerInterceptor.class.getName());
public static Metadata serverHeaders;

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(
            ServerCall<ReqT, RespT> call,
            final Metadata requestHeaders,
            ServerCallHandler<ReqT, RespT> next) {
        serverHeaders=requestHeaders;
        // @1 打印从客户端设置的header信息
        logger.info("header received from client:" + requestHeaders);
        return next.startCall(new ForwardingServerCall.SimpleForwardingServerCall<ReqT, RespT>(call) {
            @Override
            public void
            sendHeaders(Metadata responseHeaders) {
                // @2 响应客户端设置服务端Header信息
                super.sendHeaders(responseHeaders);
            }
        }, requestHeaders);
    }
}
