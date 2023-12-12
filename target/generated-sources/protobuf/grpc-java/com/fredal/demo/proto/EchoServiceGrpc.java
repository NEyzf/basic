package com.fredal.demo.proto;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.51.0)",
    comments = "Source: echo.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class EchoServiceGrpc {

  private EchoServiceGrpc() {}

  public static final String SERVICE_NAME = "proto.EchoService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.fredal.demo.proto.Echo.EchoRequest,
      com.fredal.demo.proto.Echo.EchoResponse> getEchoMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Echo",
      requestType = com.fredal.demo.proto.Echo.EchoRequest.class,
      responseType = com.fredal.demo.proto.Echo.EchoResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.fredal.demo.proto.Echo.EchoRequest,
      com.fredal.demo.proto.Echo.EchoResponse> getEchoMethod() {
    io.grpc.MethodDescriptor<com.fredal.demo.proto.Echo.EchoRequest, com.fredal.demo.proto.Echo.EchoResponse> getEchoMethod;
    if ((getEchoMethod = EchoServiceGrpc.getEchoMethod) == null) {
      synchronized (EchoServiceGrpc.class) {
        if ((getEchoMethod = EchoServiceGrpc.getEchoMethod) == null) {
          EchoServiceGrpc.getEchoMethod = getEchoMethod =
              io.grpc.MethodDescriptor.<com.fredal.demo.proto.Echo.EchoRequest, com.fredal.demo.proto.Echo.EchoResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Echo"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.fredal.demo.proto.Echo.EchoRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.fredal.demo.proto.Echo.EchoResponse.getDefaultInstance()))
              .setSchemaDescriptor(new EchoServiceMethodDescriptorSupplier("Echo"))
              .build();
        }
      }
    }
    return getEchoMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.fredal.demo.proto.Echo.ForwardEchoRequest,
      com.fredal.demo.proto.Echo.ForwardEchoResponse> getForwardEchoMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ForwardEcho",
      requestType = com.fredal.demo.proto.Echo.ForwardEchoRequest.class,
      responseType = com.fredal.demo.proto.Echo.ForwardEchoResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.fredal.demo.proto.Echo.ForwardEchoRequest,
      com.fredal.demo.proto.Echo.ForwardEchoResponse> getForwardEchoMethod() {
    io.grpc.MethodDescriptor<com.fredal.demo.proto.Echo.ForwardEchoRequest, com.fredal.demo.proto.Echo.ForwardEchoResponse> getForwardEchoMethod;
    if ((getForwardEchoMethod = EchoServiceGrpc.getForwardEchoMethod) == null) {
      synchronized (EchoServiceGrpc.class) {
        if ((getForwardEchoMethod = EchoServiceGrpc.getForwardEchoMethod) == null) {
          EchoServiceGrpc.getForwardEchoMethod = getForwardEchoMethod =
              io.grpc.MethodDescriptor.<com.fredal.demo.proto.Echo.ForwardEchoRequest, com.fredal.demo.proto.Echo.ForwardEchoResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ForwardEcho"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.fredal.demo.proto.Echo.ForwardEchoRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.fredal.demo.proto.Echo.ForwardEchoResponse.getDefaultInstance()))
              .setSchemaDescriptor(new EchoServiceMethodDescriptorSupplier("ForwardEcho"))
              .build();
        }
      }
    }
    return getForwardEchoMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.fredal.demo.proto.Echo.EchoRequest,
      com.fredal.demo.proto.Echo.EchoResponse> getLaneEchoMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "LaneEcho",
      requestType = com.fredal.demo.proto.Echo.EchoRequest.class,
      responseType = com.fredal.demo.proto.Echo.EchoResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.fredal.demo.proto.Echo.EchoRequest,
      com.fredal.demo.proto.Echo.EchoResponse> getLaneEchoMethod() {
    io.grpc.MethodDescriptor<com.fredal.demo.proto.Echo.EchoRequest, com.fredal.demo.proto.Echo.EchoResponse> getLaneEchoMethod;
    if ((getLaneEchoMethod = EchoServiceGrpc.getLaneEchoMethod) == null) {
      synchronized (EchoServiceGrpc.class) {
        if ((getLaneEchoMethod = EchoServiceGrpc.getLaneEchoMethod) == null) {
          EchoServiceGrpc.getLaneEchoMethod = getLaneEchoMethod =
              io.grpc.MethodDescriptor.<com.fredal.demo.proto.Echo.EchoRequest, com.fredal.demo.proto.Echo.EchoResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "LaneEcho"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.fredal.demo.proto.Echo.EchoRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.fredal.demo.proto.Echo.EchoResponse.getDefaultInstance()))
              .setSchemaDescriptor(new EchoServiceMethodDescriptorSupplier("LaneEcho"))
              .build();
        }
      }
    }
    return getLaneEchoMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static EchoServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<EchoServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<EchoServiceStub>() {
        @java.lang.Override
        public EchoServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new EchoServiceStub(channel, callOptions);
        }
      };
    return EchoServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static EchoServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<EchoServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<EchoServiceBlockingStub>() {
        @java.lang.Override
        public EchoServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new EchoServiceBlockingStub(channel, callOptions);
        }
      };
    return EchoServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static EchoServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<EchoServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<EchoServiceFutureStub>() {
        @java.lang.Override
        public EchoServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new EchoServiceFutureStub(channel, callOptions);
        }
      };
    return EchoServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class EchoServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void echo(com.fredal.demo.proto.Echo.EchoRequest request,
        io.grpc.stub.StreamObserver<com.fredal.demo.proto.Echo.EchoResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getEchoMethod(), responseObserver);
    }

    /**
     */
    public void forwardEcho(com.fredal.demo.proto.Echo.ForwardEchoRequest request,
        io.grpc.stub.StreamObserver<com.fredal.demo.proto.Echo.ForwardEchoResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getForwardEchoMethod(), responseObserver);
    }

    /**
     */
    public void laneEcho(com.fredal.demo.proto.Echo.EchoRequest request,
        io.grpc.stub.StreamObserver<com.fredal.demo.proto.Echo.EchoResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getLaneEchoMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getEchoMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.fredal.demo.proto.Echo.EchoRequest,
                com.fredal.demo.proto.Echo.EchoResponse>(
                  this, METHODID_ECHO)))
          .addMethod(
            getForwardEchoMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.fredal.demo.proto.Echo.ForwardEchoRequest,
                com.fredal.demo.proto.Echo.ForwardEchoResponse>(
                  this, METHODID_FORWARD_ECHO)))
          .addMethod(
            getLaneEchoMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.fredal.demo.proto.Echo.EchoRequest,
                com.fredal.demo.proto.Echo.EchoResponse>(
                  this, METHODID_LANE_ECHO)))
          .build();
    }
  }

  /**
   */
  public static final class EchoServiceStub extends io.grpc.stub.AbstractAsyncStub<EchoServiceStub> {
    private EchoServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EchoServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new EchoServiceStub(channel, callOptions);
    }

    /**
     */
    public void echo(com.fredal.demo.proto.Echo.EchoRequest request,
        io.grpc.stub.StreamObserver<com.fredal.demo.proto.Echo.EchoResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getEchoMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void forwardEcho(com.fredal.demo.proto.Echo.ForwardEchoRequest request,
        io.grpc.stub.StreamObserver<com.fredal.demo.proto.Echo.ForwardEchoResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getForwardEchoMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void laneEcho(com.fredal.demo.proto.Echo.EchoRequest request,
        io.grpc.stub.StreamObserver<com.fredal.demo.proto.Echo.EchoResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getLaneEchoMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class EchoServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<EchoServiceBlockingStub> {
    private EchoServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EchoServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new EchoServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.fredal.demo.proto.Echo.EchoResponse echo(com.fredal.demo.proto.Echo.EchoRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getEchoMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.fredal.demo.proto.Echo.ForwardEchoResponse forwardEcho(com.fredal.demo.proto.Echo.ForwardEchoRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getForwardEchoMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.fredal.demo.proto.Echo.EchoResponse laneEcho(com.fredal.demo.proto.Echo.EchoRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getLaneEchoMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class EchoServiceFutureStub extends io.grpc.stub.AbstractFutureStub<EchoServiceFutureStub> {
    private EchoServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EchoServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new EchoServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.fredal.demo.proto.Echo.EchoResponse> echo(
        com.fredal.demo.proto.Echo.EchoRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getEchoMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.fredal.demo.proto.Echo.ForwardEchoResponse> forwardEcho(
        com.fredal.demo.proto.Echo.ForwardEchoRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getForwardEchoMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.fredal.demo.proto.Echo.EchoResponse> laneEcho(
        com.fredal.demo.proto.Echo.EchoRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getLaneEchoMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_ECHO = 0;
  private static final int METHODID_FORWARD_ECHO = 1;
  private static final int METHODID_LANE_ECHO = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final EchoServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(EchoServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_ECHO:
          serviceImpl.echo((com.fredal.demo.proto.Echo.EchoRequest) request,
              (io.grpc.stub.StreamObserver<com.fredal.demo.proto.Echo.EchoResponse>) responseObserver);
          break;
        case METHODID_FORWARD_ECHO:
          serviceImpl.forwardEcho((com.fredal.demo.proto.Echo.ForwardEchoRequest) request,
              (io.grpc.stub.StreamObserver<com.fredal.demo.proto.Echo.ForwardEchoResponse>) responseObserver);
          break;
        case METHODID_LANE_ECHO:
          serviceImpl.laneEcho((com.fredal.demo.proto.Echo.EchoRequest) request,
              (io.grpc.stub.StreamObserver<com.fredal.demo.proto.Echo.EchoResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class EchoServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    EchoServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.fredal.demo.proto.Echo.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("EchoService");
    }
  }

  private static final class EchoServiceFileDescriptorSupplier
      extends EchoServiceBaseDescriptorSupplier {
    EchoServiceFileDescriptorSupplier() {}
  }

  private static final class EchoServiceMethodDescriptorSupplier
      extends EchoServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    EchoServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (EchoServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new EchoServiceFileDescriptorSupplier())
              .addMethod(getEchoMethod())
              .addMethod(getForwardEchoMethod())
              .addMethod(getLaneEchoMethod())
              .build();
        }
      }
    }
    return result;
  }
}
