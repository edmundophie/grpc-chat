package if4031.grpc.chat;


import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

/**
 * Created by edmundophie on 9/26/15.
 */
public class ChatServer {
    private static final Logger logger = Logger.getLogger(ChatServer.class.getName());

    private int port = 50051;
    private Server server;
    private Map<String, User> userMap;
    private Map<String, Channel> channelMap;
    private Map<String, List<Message>> messageListMap;

    private static final int MAX_GENERATED_RANDOM_ACCOUNT_INT = 99999;

    private void start() throws IOException {
        server = ServerBuilder.forPort(port)
                .addService(ChatGrpc.bindService(new ChatImpl()))
                .build()
                .start();
        userMap =  new HashMap<String, User>();
        channelMap =  new HashMap<String, Channel>();
        messageListMap = new HashMap<String, List<Message>>();
        logger.info("Server started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.err.println("*** shutting down gRPC server since JVM is shutting down");
                ChatServer.this.stop();
                System.err.println("*** server shut down");
            }
        });
    }

    private void stop() {
        if(server != null) {
            server.shutdown();
        }
    }

    private void blockUntilShutdown() throws InterruptedException {
        if(server != null) {
            server.awaitTermination();
        }
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        final ChatServer server = new ChatServer();
        server.start();
        server.blockUntilShutdown();
    }

    private class ChatImpl implements ChatGrpc.Chat {

        @Override
        public void login(ChatRequest req, StreamObserver<ChatResponse> responseObserver) {
            System.out.println("- Login method invoked");
            String nickname = req.getNickname();
            StringBuilder message = new StringBuilder();

            if(nickname==null || nickname.isEmpty() || userMap.containsKey(nickname)) {
                if(userMap.containsKey(nickname)) message.append("* Username exist!\n");
                nickname = generateRandomNickname();
                message.append("* Random user generated\n");
            }
            message.append("* Successfully logged in as " + nickname);

            userMap.put(nickname, new User(nickname));

            ChatResponse reply = ChatResponse.newBuilder()
                    .setStatus(true)
                    .setMessage(message.toString())
                    .addValues(nickname)
                    .build();
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }

        @Override
        public void join(ChatRequest req, StreamObserver<ChatResponse> responseObserver) {
            String nickname = req.getNickname();
            String channelName= req.getChannelName();
            System.out.println("- " + nickname + " requested to join #" + channelName);

            List userChannelList = userMap.get(nickname).getJoinedChannel();
            StringBuilder message = new StringBuilder();
            ChatResponse.Builder replyBuilder = ChatResponse.newBuilder();

            if(userChannelList.contains(channelName)) {
                message.append("* You are already a member of #" + channelName);
                replyBuilder.setStatus(false);
            } else {
                if(!channelMap.containsKey(channelName)) {
                    channelMap.put(channelName, new Channel(channelName));
                    messageListMap.put(channelName, new ArrayList<Message>());
                    message.append("* Created new channel #" + channelName + "\n");
                }

                userChannelList.add(channelName);
                message.append("* #" + channelName + " joined successfully");
                replyBuilder.setStatus(true);
            }

            replyBuilder.setMessage(message.toString());
            ChatResponse reply = replyBuilder.build();
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }

        @Override
        public void leave(ChatRequest req, StreamObserver<ChatResponse> responseObserver) {
            String nickname = req.getNickname();
            String channelName= req.getChannelName();
            System.out.println("- " + nickname + " request to leave #" + channelName);

            StringBuilder message = new StringBuilder();
            ChatResponse.Builder replyBuilder = ChatResponse.newBuilder();
            if(!userMap.get(nickname).getJoinedChannel().contains(channelName)) {
                System.err.println("- Failed to leave channel. " + nickname + " is not a member of #" + channelName);
                message.append("* Failed to leave.\n* You are not a member of #" + channelName);
                replyBuilder.setStatus(false);
            } else {
                userMap.get(nickname).getJoinedChannel().remove(channelName);
                replyBuilder.setStatus(true);
                message.append("* You are no longer a member of #" + channelName);
            }

            replyBuilder.setMessage(message.toString());
            responseObserver.onNext(replyBuilder.build());
            responseObserver.onCompleted();
        }

        @Override
        public void logout(ChatRequest req, StreamObserver<ChatResponse> responseObserver) {
            System.out.println("- " + req.getNickname() + " requested to logout");
            userMap.remove(req.getNickname());
            ChatResponse reply = ChatResponse.newBuilder()
                    .setStatus(true)
                    .setMessage("* " + req.getNickname() + " have been logged out")
                    .build();
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }

        @Override
        public void sendMessageToChannel(ChatRequest req, StreamObserver<ChatResponse> responseObserver) {
            String nickname = req.getNickname();
            String channelName= req.getChannelName();
            String message = req.getMessage();
            StringBuilder returnedMessage = new StringBuilder();
            ChatResponse.Builder replyBuilder = ChatResponse.newBuilder();
            System.out.println("- " + nickname + " sends a message to #" + channelName);

            List<String> userChannelList = userMap.get(nickname).getJoinedChannel();
            if(!userChannelList.contains(channelName)) {
                System.err.println("- Failed to send " + nickname + " message to #" + channelName + ". User is not a member of the channel.");
                returnedMessage.append("* You are not a member of #" + channelName);
                replyBuilder.setStatus(false);
            } else {
                Message msg = new Message(nickname, message);
                channelMap.get(channelName).getMessages().add(msg); // TODO is channelMap really needed?
                msg.setText("@" + channelName + " " + nickname + ": " + msg.getText());
                messageListMap.get(channelName).add(msg);
                replyBuilder.setStatus(true);
            }

            replyBuilder.setMessage(returnedMessage.toString());
            responseObserver.onNext(replyBuilder.build());
            responseObserver.onCompleted();
        }

        @Override
        public void broadcastMessage(ChatRequest req, StreamObserver<ChatResponse> responseObserver) {
            String nickname = req.getNickname();
            String message = req.getMessage();
            StringBuilder returnedMessage = new StringBuilder();
            ChatResponse.Builder replyBuilder = ChatResponse.newBuilder();
            System.out.println("- " + nickname + " broadcasts a message");

            List<String> userChannelList = userMap.get(nickname).getJoinedChannel();
            if(userChannelList.size()==0) {
                System.err.println("- Failed to send " + nickname + " message. No channel found.");
                returnedMessage.append("* Failed to send the message\n* You haven't join any channel yet");
                replyBuilder.setStatus(false);
            } else {
                Message msg = new Message(nickname, message);
                for(String channelName:userChannelList) { // TODO async
                    channelMap.get(channelName).getMessages().add(msg);
                    Message temp = new Message(nickname, "@" + channelName + " " + nickname + ": " + msg.getText());
                    messageListMap.get(channelName).add(temp);
                }
                replyBuilder.setStatus(true);
            }

            replyBuilder.setMessage(returnedMessage.toString());
            responseObserver.onNext(replyBuilder.build());
            responseObserver.onCompleted();
        }

        @Override
        public void fetchMessages(ChatRequest req, StreamObserver<FetchMessagesResponse> responseObserver) {
            String nickname = req.getNickname();
            long lastReceivedMessageTimestamp = req.getTimestamp();
            List<FetchMessagesResponse.Message> messages = new ArrayList<FetchMessagesResponse.Message>();
            FetchMessagesResponse.Builder replyBuilder = FetchMessagesResponse.newBuilder();

            if(nickname.isEmpty()) {
                replyBuilder.setStatus(false);
                responseObserver.onNext(replyBuilder.build());
                responseObserver.onCompleted();
            }

            if(userMap.get(nickname)!=null) {
                for (String channelName : userMap.get(nickname).getJoinedChannel()) {
                    List<Message> messageList = messageListMap.get(channelName);

                    if (!messageList.isEmpty()) {
                        int low = 0;
                        int high = messageList.size() - 1;

                        while (low != high) {
                            int mid = (low + high) / 2;
                            if (messageList.get(mid).getTimestamp() <= lastReceivedMessageTimestamp)
                                low = mid + 1;
                            else
                                high = mid;
                        }

                        if (messageList.get(low).getTimestamp() > lastReceivedMessageTimestamp) {
                            FetchMessagesResponse.Message.Builder msgBuilder = FetchMessagesResponse.Message.newBuilder();
                            for (Message message : messageList.subList(low, messageList.size())) {
                                msgBuilder.setSender(message.getSender());
                                msgBuilder.setText(message.getText());
                                msgBuilder.setTimestamp(message.getTimestamp());
                                messages.add(msgBuilder.build());
                            }
                        }
                    }
                }
            }

            if(!messages.isEmpty()) {
                replyBuilder.setStatus(true);
                replyBuilder.addAllMessages(messages);
            } else {
                replyBuilder.setStatus(false);
            }

            responseObserver.onNext(replyBuilder.build());
            responseObserver.onCompleted();
        }

        private String generateRandomNickname() {
            String newNickname;
            Random random = new Random();
            do {
                newNickname = "user" + random.nextInt(MAX_GENERATED_RANDOM_ACCOUNT_INT);
            }while(userMap.containsKey(newNickname));

            return newNickname;
        }
    }
}
