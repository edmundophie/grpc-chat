package if4031.grpc.chat;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;

@javax.annotation.Generated("by gRPC proto compiler")
public class ChatGrpc {

  private ChatGrpc() {}

  public static final String SERVICE_NAME = "chat.Chat";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<if4031.grpc.chat.ChatRequest,
      if4031.grpc.chat.ChatResponse> METHOD_LOGIN =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "chat.Chat", "login"),
          io.grpc.protobuf.ProtoUtils.marshaller(if4031.grpc.chat.ChatRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(if4031.grpc.chat.ChatResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<if4031.grpc.chat.ChatRequest,
      if4031.grpc.chat.ChatResponse> METHOD_JOIN =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "chat.Chat", "join"),
          io.grpc.protobuf.ProtoUtils.marshaller(if4031.grpc.chat.ChatRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(if4031.grpc.chat.ChatResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<if4031.grpc.chat.ChatRequest,
      if4031.grpc.chat.ChatResponse> METHOD_LEAVE =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "chat.Chat", "leave"),
          io.grpc.protobuf.ProtoUtils.marshaller(if4031.grpc.chat.ChatRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(if4031.grpc.chat.ChatResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<if4031.grpc.chat.ChatRequest,
      if4031.grpc.chat.ChatResponse> METHOD_LOGOUT =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "chat.Chat", "logout"),
          io.grpc.protobuf.ProtoUtils.marshaller(if4031.grpc.chat.ChatRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(if4031.grpc.chat.ChatResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<if4031.grpc.chat.ChatRequest,
      if4031.grpc.chat.ChatResponse> METHOD_SEND_MESSAGE_TO_CHANNEL =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "chat.Chat", "sendMessageToChannel"),
          io.grpc.protobuf.ProtoUtils.marshaller(if4031.grpc.chat.ChatRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(if4031.grpc.chat.ChatResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<if4031.grpc.chat.ChatRequest,
      if4031.grpc.chat.ChatResponse> METHOD_BROADCAST_MESSAGE =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "chat.Chat", "broadcastMessage"),
          io.grpc.protobuf.ProtoUtils.marshaller(if4031.grpc.chat.ChatRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(if4031.grpc.chat.ChatResponse.getDefaultInstance()));
  @io.grpc.ExperimentalApi
  public static final io.grpc.MethodDescriptor<if4031.grpc.chat.ChatRequest,
      if4031.grpc.chat.FetchMessagesResponse> METHOD_FETCH_MESSAGES =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "chat.Chat", "fetchMessages"),
          io.grpc.protobuf.ProtoUtils.marshaller(if4031.grpc.chat.ChatRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(if4031.grpc.chat.FetchMessagesResponse.getDefaultInstance()));

  public static ChatStub newStub(io.grpc.Channel channel) {
    return new ChatStub(channel);
  }

  public static ChatBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new ChatBlockingStub(channel);
  }

  public static ChatFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new ChatFutureStub(channel);
  }

  public static interface Chat {

    public void login(if4031.grpc.chat.ChatRequest request,
        io.grpc.stub.StreamObserver<if4031.grpc.chat.ChatResponse> responseObserver);

    public void join(if4031.grpc.chat.ChatRequest request,
        io.grpc.stub.StreamObserver<if4031.grpc.chat.ChatResponse> responseObserver);

    public void leave(if4031.grpc.chat.ChatRequest request,
        io.grpc.stub.StreamObserver<if4031.grpc.chat.ChatResponse> responseObserver);

    public void logout(if4031.grpc.chat.ChatRequest request,
        io.grpc.stub.StreamObserver<if4031.grpc.chat.ChatResponse> responseObserver);

    public void sendMessageToChannel(if4031.grpc.chat.ChatRequest request,
        io.grpc.stub.StreamObserver<if4031.grpc.chat.ChatResponse> responseObserver);

    public void broadcastMessage(if4031.grpc.chat.ChatRequest request,
        io.grpc.stub.StreamObserver<if4031.grpc.chat.ChatResponse> responseObserver);

    public void fetchMessages(if4031.grpc.chat.ChatRequest request,
        io.grpc.stub.StreamObserver<if4031.grpc.chat.FetchMessagesResponse> responseObserver);
  }

  public static interface ChatBlockingClient {

    public if4031.grpc.chat.ChatResponse login(if4031.grpc.chat.ChatRequest request);

    public if4031.grpc.chat.ChatResponse join(if4031.grpc.chat.ChatRequest request);

    public if4031.grpc.chat.ChatResponse leave(if4031.grpc.chat.ChatRequest request);

    public if4031.grpc.chat.ChatResponse logout(if4031.grpc.chat.ChatRequest request);

    public if4031.grpc.chat.ChatResponse sendMessageToChannel(if4031.grpc.chat.ChatRequest request);

    public if4031.grpc.chat.ChatResponse broadcastMessage(if4031.grpc.chat.ChatRequest request);

    public if4031.grpc.chat.FetchMessagesResponse fetchMessages(if4031.grpc.chat.ChatRequest request);
  }

  public static interface ChatFutureClient {

    public com.google.common.util.concurrent.ListenableFuture<if4031.grpc.chat.ChatResponse> login(
        if4031.grpc.chat.ChatRequest request);

    public com.google.common.util.concurrent.ListenableFuture<if4031.grpc.chat.ChatResponse> join(
        if4031.grpc.chat.ChatRequest request);

    public com.google.common.util.concurrent.ListenableFuture<if4031.grpc.chat.ChatResponse> leave(
        if4031.grpc.chat.ChatRequest request);

    public com.google.common.util.concurrent.ListenableFuture<if4031.grpc.chat.ChatResponse> logout(
        if4031.grpc.chat.ChatRequest request);

    public com.google.common.util.concurrent.ListenableFuture<if4031.grpc.chat.ChatResponse> sendMessageToChannel(
        if4031.grpc.chat.ChatRequest request);

    public com.google.common.util.concurrent.ListenableFuture<if4031.grpc.chat.ChatResponse> broadcastMessage(
        if4031.grpc.chat.ChatRequest request);

    public com.google.common.util.concurrent.ListenableFuture<if4031.grpc.chat.FetchMessagesResponse> fetchMessages(
        if4031.grpc.chat.ChatRequest request);
  }

  public static class ChatStub extends io.grpc.stub.AbstractStub<ChatStub>
      implements Chat {
    private ChatStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ChatStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ChatStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ChatStub(channel, callOptions);
    }

    @java.lang.Override
    public void login(if4031.grpc.chat.ChatRequest request,
        io.grpc.stub.StreamObserver<if4031.grpc.chat.ChatResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_LOGIN, getCallOptions()), request, responseObserver);
    }

    @java.lang.Override
    public void join(if4031.grpc.chat.ChatRequest request,
        io.grpc.stub.StreamObserver<if4031.grpc.chat.ChatResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_JOIN, getCallOptions()), request, responseObserver);
    }

    @java.lang.Override
    public void leave(if4031.grpc.chat.ChatRequest request,
        io.grpc.stub.StreamObserver<if4031.grpc.chat.ChatResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_LEAVE, getCallOptions()), request, responseObserver);
    }

    @java.lang.Override
    public void logout(if4031.grpc.chat.ChatRequest request,
        io.grpc.stub.StreamObserver<if4031.grpc.chat.ChatResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_LOGOUT, getCallOptions()), request, responseObserver);
    }

    @java.lang.Override
    public void sendMessageToChannel(if4031.grpc.chat.ChatRequest request,
        io.grpc.stub.StreamObserver<if4031.grpc.chat.ChatResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_SEND_MESSAGE_TO_CHANNEL, getCallOptions()), request, responseObserver);
    }

    @java.lang.Override
    public void broadcastMessage(if4031.grpc.chat.ChatRequest request,
        io.grpc.stub.StreamObserver<if4031.grpc.chat.ChatResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_BROADCAST_MESSAGE, getCallOptions()), request, responseObserver);
    }

    @java.lang.Override
    public void fetchMessages(if4031.grpc.chat.ChatRequest request,
        io.grpc.stub.StreamObserver<if4031.grpc.chat.FetchMessagesResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_FETCH_MESSAGES, getCallOptions()), request, responseObserver);
    }
  }

  public static class ChatBlockingStub extends io.grpc.stub.AbstractStub<ChatBlockingStub>
      implements ChatBlockingClient {
    private ChatBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ChatBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ChatBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ChatBlockingStub(channel, callOptions);
    }

    @java.lang.Override
    public if4031.grpc.chat.ChatResponse login(if4031.grpc.chat.ChatRequest request) {
      return blockingUnaryCall(
          getChannel().newCall(METHOD_LOGIN, getCallOptions()), request);
    }

    @java.lang.Override
    public if4031.grpc.chat.ChatResponse join(if4031.grpc.chat.ChatRequest request) {
      return blockingUnaryCall(
          getChannel().newCall(METHOD_JOIN, getCallOptions()), request);
    }

    @java.lang.Override
    public if4031.grpc.chat.ChatResponse leave(if4031.grpc.chat.ChatRequest request) {
      return blockingUnaryCall(
          getChannel().newCall(METHOD_LEAVE, getCallOptions()), request);
    }

    @java.lang.Override
    public if4031.grpc.chat.ChatResponse logout(if4031.grpc.chat.ChatRequest request) {
      return blockingUnaryCall(
          getChannel().newCall(METHOD_LOGOUT, getCallOptions()), request);
    }

    @java.lang.Override
    public if4031.grpc.chat.ChatResponse sendMessageToChannel(if4031.grpc.chat.ChatRequest request) {
      return blockingUnaryCall(
          getChannel().newCall(METHOD_SEND_MESSAGE_TO_CHANNEL, getCallOptions()), request);
    }

    @java.lang.Override
    public if4031.grpc.chat.ChatResponse broadcastMessage(if4031.grpc.chat.ChatRequest request) {
      return blockingUnaryCall(
          getChannel().newCall(METHOD_BROADCAST_MESSAGE, getCallOptions()), request);
    }

    @java.lang.Override
    public if4031.grpc.chat.FetchMessagesResponse fetchMessages(if4031.grpc.chat.ChatRequest request) {
      return blockingUnaryCall(
          getChannel().newCall(METHOD_FETCH_MESSAGES, getCallOptions()), request);
    }
  }

  public static class ChatFutureStub extends io.grpc.stub.AbstractStub<ChatFutureStub>
      implements ChatFutureClient {
    private ChatFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ChatFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ChatFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ChatFutureStub(channel, callOptions);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<if4031.grpc.chat.ChatResponse> login(
        if4031.grpc.chat.ChatRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_LOGIN, getCallOptions()), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<if4031.grpc.chat.ChatResponse> join(
        if4031.grpc.chat.ChatRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_JOIN, getCallOptions()), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<if4031.grpc.chat.ChatResponse> leave(
        if4031.grpc.chat.ChatRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_LEAVE, getCallOptions()), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<if4031.grpc.chat.ChatResponse> logout(
        if4031.grpc.chat.ChatRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_LOGOUT, getCallOptions()), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<if4031.grpc.chat.ChatResponse> sendMessageToChannel(
        if4031.grpc.chat.ChatRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_SEND_MESSAGE_TO_CHANNEL, getCallOptions()), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<if4031.grpc.chat.ChatResponse> broadcastMessage(
        if4031.grpc.chat.ChatRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_BROADCAST_MESSAGE, getCallOptions()), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<if4031.grpc.chat.FetchMessagesResponse> fetchMessages(
        if4031.grpc.chat.ChatRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_FETCH_MESSAGES, getCallOptions()), request);
    }
  }

  public static io.grpc.ServerServiceDefinition bindService(
      final Chat serviceImpl) {
    return io.grpc.ServerServiceDefinition.builder(SERVICE_NAME)
      .addMethod(
        METHOD_LOGIN,
        asyncUnaryCall(
          new io.grpc.stub.ServerCalls.UnaryMethod<
              if4031.grpc.chat.ChatRequest,
              if4031.grpc.chat.ChatResponse>() {
            @java.lang.Override
            public void invoke(
                if4031.grpc.chat.ChatRequest request,
                io.grpc.stub.StreamObserver<if4031.grpc.chat.ChatResponse> responseObserver) {
              serviceImpl.login(request, responseObserver);
            }
          }))
      .addMethod(
        METHOD_JOIN,
        asyncUnaryCall(
          new io.grpc.stub.ServerCalls.UnaryMethod<
              if4031.grpc.chat.ChatRequest,
              if4031.grpc.chat.ChatResponse>() {
            @java.lang.Override
            public void invoke(
                if4031.grpc.chat.ChatRequest request,
                io.grpc.stub.StreamObserver<if4031.grpc.chat.ChatResponse> responseObserver) {
              serviceImpl.join(request, responseObserver);
            }
          }))
      .addMethod(
        METHOD_LEAVE,
        asyncUnaryCall(
          new io.grpc.stub.ServerCalls.UnaryMethod<
              if4031.grpc.chat.ChatRequest,
              if4031.grpc.chat.ChatResponse>() {
            @java.lang.Override
            public void invoke(
                if4031.grpc.chat.ChatRequest request,
                io.grpc.stub.StreamObserver<if4031.grpc.chat.ChatResponse> responseObserver) {
              serviceImpl.leave(request, responseObserver);
            }
          }))
      .addMethod(
        METHOD_LOGOUT,
        asyncUnaryCall(
          new io.grpc.stub.ServerCalls.UnaryMethod<
              if4031.grpc.chat.ChatRequest,
              if4031.grpc.chat.ChatResponse>() {
            @java.lang.Override
            public void invoke(
                if4031.grpc.chat.ChatRequest request,
                io.grpc.stub.StreamObserver<if4031.grpc.chat.ChatResponse> responseObserver) {
              serviceImpl.logout(request, responseObserver);
            }
          }))
      .addMethod(
        METHOD_SEND_MESSAGE_TO_CHANNEL,
        asyncUnaryCall(
          new io.grpc.stub.ServerCalls.UnaryMethod<
              if4031.grpc.chat.ChatRequest,
              if4031.grpc.chat.ChatResponse>() {
            @java.lang.Override
            public void invoke(
                if4031.grpc.chat.ChatRequest request,
                io.grpc.stub.StreamObserver<if4031.grpc.chat.ChatResponse> responseObserver) {
              serviceImpl.sendMessageToChannel(request, responseObserver);
            }
          }))
      .addMethod(
        METHOD_BROADCAST_MESSAGE,
        asyncUnaryCall(
          new io.grpc.stub.ServerCalls.UnaryMethod<
              if4031.grpc.chat.ChatRequest,
              if4031.grpc.chat.ChatResponse>() {
            @java.lang.Override
            public void invoke(
                if4031.grpc.chat.ChatRequest request,
                io.grpc.stub.StreamObserver<if4031.grpc.chat.ChatResponse> responseObserver) {
              serviceImpl.broadcastMessage(request, responseObserver);
            }
          }))
      .addMethod(
        METHOD_FETCH_MESSAGES,
        asyncUnaryCall(
          new io.grpc.stub.ServerCalls.UnaryMethod<
              if4031.grpc.chat.ChatRequest,
              if4031.grpc.chat.FetchMessagesResponse>() {
            @java.lang.Override
            public void invoke(
                if4031.grpc.chat.ChatRequest request,
                io.grpc.stub.StreamObserver<if4031.grpc.chat.FetchMessagesResponse> responseObserver) {
              serviceImpl.fetchMessages(request, responseObserver);
            }
          })).build();
  }
}
