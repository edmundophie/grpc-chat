package if4031.grpc.chat;

import com.fasterxml.jackson.core.type.TypeReference;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by edmundophie on 9/26/15.
 */
public class ChatClient {
    private static final Logger logger = Logger.getLogger(ChatClient.class.getName());

    private final ManagedChannel channel;
    private final ChatGrpc.ChatBlockingStub blockingStub;

    private boolean isLoggedIn;
    private String nickname;
    private Thread messageListenerThread;
    private Long lastReceivedMessageTimestamp;


    public ChatClient(String host, int port) {
        channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext(true)
                .build();
        blockingStub = ChatGrpc.newBlockingStub(channel);
        isLoggedIn = false;
        nickname = "";
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        ChatClient client = new ChatClient("localhost", 50051);

        perform(client);

        client.shutdown();
    }

    private static void perform(ChatClient client) throws IOException, InterruptedException {
        System.out.println("Client started");

        String command=null;
        do {
//            String consoleNickname = (!isLoggedIn)?"$ ":nickname+"$ ";
//            System.out.print(consoleNickname);

            String input = new BufferedReader(new InputStreamReader(System.in)).readLine().trim();

            if(input.isEmpty())
                printInvalidCommand();
            else {
                String parameter = "";
                int i = input.indexOf(" ");

                if (i > -1) {
                    command = input.substring(0, i);
                    parameter = input.substring(i + 1);
                } else
                    command = input;

                if (command.equalsIgnoreCase("NICK")) {
                    client.login(parameter);
                } else if (command.equalsIgnoreCase("JOIN")) {
                    client.join(parameter);
                } else if (command.equalsIgnoreCase("LEAVE")) {
                    client.leave(parameter);
                } else if (command.equalsIgnoreCase("LOGOUT")) {
                    client.logout();
                } else if (command.equalsIgnoreCase("EXIT")) {
                    client.exit();
                } else if (command.charAt(0) == '@') {
                    client.sendMessage(command.substring(1), parameter);
                } else {
                    client.sendMessage(input);
                }
            }
        } while (!command.equalsIgnoreCase("EXIT"));
    }

    private static void printInvalidCommand() {
        System.err.println("Invalid Command!");
    }

    private void login(String parameter) {
        if(isLoggedIn)
            System.err.println("Please logout first!");
        else {
            ChatRequest request = ChatRequest.newBuilder().setNickname(parameter).build();
            ChatResponse response = blockingStub.login(request);
            String message = response.getMessage();
            if(response.getStatus()) {
                nickname = response.getValues(0);
                isLoggedIn = true;
                lastReceivedMessageTimestamp = System.currentTimeMillis() - 1;
                System.out.println(message);

                Runnable messageListener = new Runnable() {
                    public void run() {
                        System.out.println("* Message listener started...");
                        while (isLoggedIn) {
                            List<FetchMessagesResponse.Message> messages = getMessages();
                            if (messages != null && !messages.isEmpty()) {
                                lastReceivedMessageTimestamp = messages.get(messages.size() - 1).getTimestamp();
                                for(FetchMessagesResponse.Message msg:messages)
                                    System.out.println(msg.getText());
                            }
                        }
                    }
                };

                messageListenerThread = new Thread(messageListener);
                messageListenerThread.start();
            } else {
                System.err.println(message);
            }
        }
    }


    private void join(String parameter) {
        if(!isLoggedIn) System.err.println("Please login first!");
        else if(parameter==null || parameter.isEmpty()) printInvalidCommand();
        else {
            ChatRequest request = ChatRequest.newBuilder().setNickname(nickname).setChannelName(parameter).build();
            ChatResponse response = blockingStub.join(request);
            String message = response.getMessage();
            if(response.getStatus())
                System.out.println(message);
            else
                System.err.println(message);
        }
    }

    private void leave(String parameter) {
        if(!isLoggedIn) System.err.println("Please login first!");
        else if(parameter==null || parameter.isEmpty()) printInvalidCommand();
        else  {
            ChatRequest request = ChatRequest.newBuilder().setNickname(nickname).setChannelName(parameter).build();
            ChatResponse response = blockingStub.leave(request);
            String message = response.getMessage();
            if(response.getStatus())
                System.out.println(message);
            else
                System.err.println(message);
        }
    }

    private void logout() throws InterruptedException {
        if(!isLoggedIn) System.err.println("Please login first!");
        else {
            ChatRequest request = ChatRequest.newBuilder().setNickname(nickname).build();
            ChatResponse response = blockingStub.logout(request);
            String message = response.getMessage();

            if(response.getStatus()) {
                isLoggedIn = false;
                nickname = "";
                System.out.println(message);
            } else {
                System.err.println(message);
            }
        }
    }

    private void exit() throws InterruptedException {
        if(isLoggedIn)
            logout();

        System.out.println("* Program exited");
    }

    private void sendMessage(String channelName, String message) {
        if(!isLoggedIn) System.err.println("Please login first!");
        else if(message==null || message.isEmpty()) printInvalidCommand();
        else {
            ChatRequest request = ChatRequest.newBuilder()
                    .setNickname(nickname)
                    .setChannelName(channelName)
                    .setMessage(message)
                    .build();
            ChatResponse response = blockingStub.sendMessageToChannel(request);

            if(!response.getStatus()) {
                System.err.println(response.getMessage());
            }
        }
    }

    private void sendMessage(String message) {
        if(!isLoggedIn) System.err.println("Please login first!");
        else {
            ChatRequest request = ChatRequest.newBuilder()
                .setNickname(nickname)
                .setMessage(message)
                .build();
            ChatResponse response = blockingStub.broadcastMessage(request);

            if(!response.getStatus()) {
                System.err.println(response.getMessage());
            }
        }
    }

    private List<FetchMessagesResponse.Message> getMessages() {
        ChatRequest request = ChatRequest.newBuilder()
                .setNickname(nickname)
                .setTimestamp(lastReceivedMessageTimestamp)
                .build();
        FetchMessagesResponse response = blockingStub.fetchMessages(request);
        if(response.getStatus()) {
            return response.getMessagesList();
        }
        return null;
    }
}
