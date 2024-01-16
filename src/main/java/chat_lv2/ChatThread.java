package chat_lv2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChatThread extends Thread{
    private String name;
    private BufferedReader br;
    private PrintWriter pw;
    private Socket socket;
    private List<ChatThread> list;
    
    public ChatThread(Socket socket, List<ChatThread> list) throws Exception {
        this.socket = socket;
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        this.br = br;
        this.pw = pw;
        this.name = br.readLine();
        this.list = list;
        this.list.add(this);
    }
     
    private void sendMessage (String msg) {
        pw.println(msg);
        pw.flush();
    }
     
    @Override
    public void run() {
        // ChatThread는 사용자가 보낸 메세지를 읽어들여서
        // 접속된 모든 클라이언트에게 메세지를 보낸다(boradcast).
        try {
            // 나를 제외한 모든 사용자에게 "XX 님이 연결되었습니다." 출력
            broadcast(name + "님이 연결되었습니다.", false);
            
            // 입력한 메세지를 모든 사용자에게 출력하도록 전달한다.
            String line = null;
            while ((line = br.readLine()) != null) {
                if(line.equals("/quit")) {
                    throw new RuntimeException("접속 종료");
                }
                // 나를 포함한 ChatThread에 msg를 보낸다.
                broadcast(name + ": " + line, true);
            }
        } catch (Exception e) { 
            e.printStackTrace();
        } finally { // ChatThread의 연결이 끊어짐
            broadcast(name + "님의 연결이 해제되었습니다.", false);
            this.list.remove(this);

            try {
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                pw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    private void broadcast(String msg, boolean includeMe) {
        // 중간에 리스트에서 제거가 되더라도 멈추지 않고 메세지를 보내도록 처리하기 위함.
        List<ChatThread> chatThreads = new ArrayList<>(this.list);
        
        try {
            for(int i = 0; i < list.size(); i++) {
                ChatThread ct = chatThreads.get(i);
                if(!includeMe && (ct == this)) {
                    continue;
                }
                ct.sendMessage(msg);
            }
        } catch (Exception e) {
            System.out.println("----");
        }
    }
}
