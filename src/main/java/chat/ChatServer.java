package chat;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChatServer {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(9999);
        // 공유 객체에서 스레드에 안전한 리스트를 만든다.
        List<PrintWriter> outList = Collections.synchronizedList(new ArrayList<>());
        while(true) {
            Socket socket = serverSocket.accept();

            ChatThread chatThread = new ChatThread(socket, outList);
            chatThread.start();
        }
        
    }
}

