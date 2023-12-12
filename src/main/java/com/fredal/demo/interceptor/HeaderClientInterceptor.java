package com.fredal.demo.interceptor;

import com.google.common.annotations.VisibleForTesting;
import io.grpc.*;

import java.util.logging.Logger;

public class HeaderClientInterceptor implements ClientInterceptor {
    private static final Logger logger = Logger.getLogger(HeaderClientInterceptor.class.getName());

    @Override
    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(
            MethodDescriptor<ReqT, RespT> method, CallOptions callOptions, Channel next) {
        return new ForwardingClientCall.SimpleForwardingClientCall<ReqT, RespT>(
                next.newCall(method, callOptions)) {

            @Override
            public void start(Listener<RespT> responseListener, Metadata headers) {
                // @1 在Header中设置需要透传的值
                Metadata serverHeaders = HeaderServerInterceptor.serverHeaders;
                for (String existingKey : serverHeaders.keys()) {
                    if (!existingKey.equals("content-type") & !existingKey.equals("user-agent")
                            &!existingKey.equals("baggage")& !existingKey.equals("grpc-accept-encoding")
                            &!existingKey.equals("date")& !existingKey.equals("server")
                            & !existingKey.equals("grpc-encoding=")& !existingKey.equals("x-envoy-upstream-service-time")){;
                        Metadata.Key<String> metadataKey = Metadata.Key.of(existingKey, Metadata.ASCII_STRING_MARSHALLER);
                        headers.put(metadataKey,serverHeaders.get(metadataKey));
                    }
                }
                super.start(
                        new ForwardingClientCallListener.SimpleForwardingClientCallListener<RespT>(
                                responseListener) {
                            @Override
                            public void onHeaders(Metadata headers) {
                                // @2 获取从服务端返回的Header信息
                                logger.info("header received from server:" + headers);
                                super.onHeaders(headers);
                            }
                        }, headers);
            }
        };
    }
}
