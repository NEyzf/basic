package com.fredal.demo.grpc;

import com.fredal.demo.interceptor.HeaderServerInterceptor;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.ServerInterceptors;
import io.grpc.protobuf.services.ProtoReflectionService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class GrpcServer implements Runnable {

    private final Integer port;
    private final String echo;
    private final String version;

    public GrpcServer(@Value("${grpc.port}") Integer port, @Value("${spring.application.name}") String echo,
                      @Value("${spring.application.version}") String version) {
        this.port = port;
        this.echo = echo;
        this.version = version;
    }

    @PostConstruct
    public void start() {
        new Thread(this).start();
    }

    @Override
    @SneakyThrows
    public void run() {
        int port = this.port;
        Server server;
        server = ServerBuilder.forPort(port)
                .addService(ServerInterceptors.intercept(new  GrpcEcho(echo, version), new HeaderServerInterceptor()))
                .addService(ServerInterceptors.intercept(ProtoReflectionService.newInstance()))
                .build()
                .start();
        log.info("Listening on port " + port);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                // Start graceful shutdown
                server.shutdown();
                try {
                    // Wait for RPCs to complete processing
                    if (!server.awaitTermination(5, TimeUnit.SECONDS)) {
                        // That was plenty of time. Let's cancel the remaining RPCs
                        server.shutdownNow();
                        // shutdownNow isn't instantaneous, so give a bit of time to clean resources up
                        // gracefully. Normally this will be well under a second.
                        server.awaitTermination(1, TimeUnit.SECONDS);
                    }
                } catch (InterruptedException ex) {
                    server.shutdownNow();
                } finally {
                    server.shutdownNow();
                }
            }
        });
        server.awaitTermination();
    }
}
