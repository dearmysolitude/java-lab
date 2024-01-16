package chat_lv2;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChatServer {
    public static void main(String[] args) throws Exception{
        ServerSocket serverSocket = new ServerSocket(8888);
        
        List<ChatThread> list = Collections.synchronizedList(new ArrayList<>()); //동시성 문제를 예방: list에 동시에 접근하지 못하도록 해 줌
        
        while(true) {
            Socket socket = serverSocket.accept(); // 클라이언트의 접속을 받는다: 접속할 때마다 while문이 돈다.
            
            ChatThread chatThread = new ChatThread(socket, list);
            chatThread.start();
        }
    }


}
