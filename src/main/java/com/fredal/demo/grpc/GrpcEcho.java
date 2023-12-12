package com.fredal.demo.grpc;

import com.fredal.demo.interceptor.HeaderClientInterceptor;
import com.fredal.demo.proto.Echo;
import com.fredal.demo.proto.EchoServiceGrpc;
import io.grpc.*;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
public class GrpcEcho extends EchoServiceGrpc.EchoServiceImplBase {
  private final String message;
  private final String lane;
  InetAddress address = InetAddress.getLocalHost();
  String hostname = address.getHostName();

  public GrpcEcho(String echo, String version) throws UnknownHostException {
    this.message = "name: " + echo + " version: " + version + "  host: " + hostname;
    this.lane = echo + " " + version;
  }

  @Override
  public void echo(Echo.EchoRequest request, StreamObserver<Echo.EchoResponse> responseObserver) {
    log.info("got echo request");
    try {
      Thread.sleep(request.getDelay());
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    Echo.EchoResponse.Builder newBuilder = Echo.EchoResponse.newBuilder();
    newBuilder.setMessage(message);
    responseObserver.onNext(newBuilder.build());
    responseObserver.onCompleted();
    log.info("finish echo request");
  }

  @Override
  public void forwardEcho(
      Echo.ForwardEchoRequest request, StreamObserver<Echo.ForwardEchoResponse> responseObserver) {
    int count = 1;
    int delay = 0;
    String targetURL = request.getUrl();
    ClientInterceptor interceptor = new HeaderClientInterceptor();
    Echo.ForwardEchoResponse.Builder builder = Echo.ForwardEchoResponse.newBuilder();
    ChannelCredentials credentials = InsecureChannelCredentials.create();
    Channel channelTemp = Grpc.newChannelBuilder(targetURL, credentials).build();
    Channel channel = ClientInterceptors.intercept(channelTemp, interceptor);
    EchoServiceGrpc.EchoServiceBlockingStub blockingStub = EchoServiceGrpc.newBlockingStub(channel);

    try {
      if (request.hasField(request.getDescriptorForType().findFieldByName("count"))) {
        count = request.getCount();
        log.info("loop {}", request.getCount());
      }
      for (int i = 0; i < count; i++) {
        Echo.EchoRequest echoRequest;
        log.info("execute order {}", i);
        log.info("Forwarded");
        if (request.hasField(request.getDescriptorForType().findFieldByName("delay"))) {
          delay = request.getDelay();
        }
        echoRequest = Echo.EchoRequest.newBuilder().setDelay(delay).build();
        Echo.EchoResponse echo = blockingStub.echo(echoRequest);
        builder.addOutput(echo.getMessage());
      }
      log.info("Forwarded");
      responseObserver.onNext(builder.build());
      responseObserver.onCompleted();

    } catch (Exception e) {
      Status status = Status.INTERNAL.withDescription(e.getMessage());
      responseObserver.onError(status.asRuntimeException());
    } finally {
      log.info("shout down channel");
    }
  }

  @Override
  public void laneEcho(
      Echo.EchoRequest request, StreamObserver<Echo.EchoResponse> responseObserver) {
    log.info("lane node of: " + this.lane);
    String targetURL = System.getenv("Target");
    ClientInterceptor interceptor = new HeaderClientInterceptor();
    Echo.EchoResponse.Builder builder = Echo.EchoResponse.newBuilder();
    ChannelCredentials credentials = InsecureChannelCredentials.create();
    String nextLanes = "";
    if (targetURL != null) {
      Channel channelTemp = Grpc.newChannelBuilder(targetURL, credentials).build();
      Channel channel = ClientInterceptors.intercept(channelTemp, interceptor);
      EchoServiceGrpc.EchoServiceBlockingStub blockingStub =
          EchoServiceGrpc.newBlockingStub(channel);
      Echo.EchoRequest laneRequest = Echo.EchoRequest.newBuilder().setLane(this.lane).build();
      Echo.EchoResponse laneResp = blockingStub.laneEcho(laneRequest);
      nextLanes = nextLanes + laneResp.getLane();
    } else {
      nextLanes = " end";
    }

    builder.setLane(this.lane + " - " + nextLanes);
    responseObserver.onNext(builder.build());
    responseObserver.onCompleted();
  }
}
