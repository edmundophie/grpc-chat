# gRPC-chat
CLI Chat Based on gRPC &amp; Protobuf

## Requirements
 - JRE >= 1.7
 - [Maven](https://maven.apache.org/download.cgi) installed
 - [Protobuf v3.0.0-beta-1](https://github.com/google/protobuf/releases/tag/v3.0.0-beta-1) installed


## How to Build
1. Resolve maven dependency  

	 ```
	 $ mvn dependency:copy-dependencies
	 ```
2. Build `jar` using maven `mvn`  

	 ```
	 $ mvn package
	 ```

## How to Run	 
1. Run `ChatServer` from the generated `jar` in `target` folder  

	 ```
	 $ java -cp target/dependency/*:target/grpc-chat-1.0-SNAPSHOT.jar if4031.grpc.chat.ChatServer
	 ```
2. Run `ChatClient` from the generated `jar` in `target` folder  

	 ```
	 $ java -cp target/dependency/*:target/grpc-chat-1.0-SNAPSHOT.jar if4031.grpc.chat.ChatClient
	 ```

## Testing
Coming soon...
![alt text](https://github.com/edmundophie/grpc-chat/blob/master/testing-screenshot/comingsoon.png "Screenshoot Caption Goes Here!!!")
![alt text](https://github.com/edmundophie/grpc-chat/blob/master/blob/tes_1_prak_3_PAT_.png "Screenshot tes 1")
![alt text](https://github.com/edmundophie/grpc-chat/blob/master/blob/tes_2_prak_3_PAT.png "Screenshot tes 2")

## Team Member
- Edmund Ophie 13512095
- Kevin 13512097

## [Github Link](https://github.com/edmundophie/grpc-chat.git) 
