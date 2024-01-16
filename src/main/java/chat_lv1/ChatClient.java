package chat_lv1;

import java.io.*;
import java.net.Socket;

// java chat.ChatClient kim  이름: kim으로 클라이언트 실행
public class ChatClient {
    public static void main(String[] args) throws Exception {
        String name = args[0];

        Socket socket = new Socket("127.0.0.1", 9999);

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in)); // 키보드로부터 입력


        String line = null;
        InputThread inputThread = new InputThread(in);
        inputThread.start();

        while ((line = keyboard.readLine()) != null) {
            out.println(name + ": " + line);
            out.flush();
        }

    }
}


