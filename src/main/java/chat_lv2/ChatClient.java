package chat_lv2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClient extends Thread{
    public static void main(String[] args) throws Exception {
        
        if(args.length != 1) {
            System.out.println("사용법: java chat_lv2.ChatClient <닉네임>");
            return;
        }
        
        String name = args[0];
        Socket socket = new Socket("127.0.0.1", 8888);
        
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

        pw.println(name);
        pw.flush();

        // 백그라운드에서 서버가 보낸 메세지를 읽어들여 화면에 출력한다.
        InputThread inputThread = new InputThread(br);
        inputThread.start();
        
        // 읽어들인 메세지를 서버에 전송한다.
        try {
            String line = null;
            while ((line = keyboard.readLine()) != null) {
                if("/quit".equals(line)) {
                    pw.println(line);
                    pw.flush();
                    break;
                }
                pw.println(line);
                pw.flush();
            }
        } catch (Exception exception) { // 접속이 끊어지면 예외가 발생
            System.out.println("-----");
        }

        try {
            br.close();
        } catch (Exception e) {

        }
        
        try {
            pw.close();
        } catch (Exception e) {
            
        }
        
        try {
            socket.close();
        } catch (Exception e) {
            
        }
    }
}
