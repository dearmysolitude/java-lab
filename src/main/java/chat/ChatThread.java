package chat;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class ChatThread extends Thread {

    private Socket socket;
    private List<PrintWriter> outList;
    private PrintWriter out;
    private BufferedReader in;

    public ChatThread(Socket socket, List<PrintWriter> outList) {
        this.socket = socket;
        this.outList = outList;

        // 1. socket으로부터 읽어들일 수 있는 객체를 얻는다.
        // 2. socket에 쓰기 위한 객체를 얻는다.(현재 연결된 클라이언트에 쓰는 객체)
        try {
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            outList.add(out);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    @Override
    public void run() {
        String line = null;
        try {
            while ((line = in.readLine()) != null) {
                for(int i = 0; i < outList.size(); i++) { // 접속한 모든 클라이언트에 입력받은 라인을 써서 보낸다.
                    PrintWriter o = outList.get(i);
                    o.println(line);
                    o.flush();
                }
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } finally { // 접속이 끊어질 때
            try {
                outList.remove(out);
            } catch (Exception e) {
                e.printStackTrace();
            }
            // 모든 연결이 끊어졌을 때 메세지를 보낼 필요가 있다.
            
            for(int i = 0; i < outList.size(); i++) {
                PrintWriter o = outList.get(i);
                o.println("어떤 클라이언트의 접속이 끊어졌습니다.");
                o.flush();
            }

            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 3. 클라이언트가 보낸 메세지를 읽는다.
        // 4. 접속한 모든 클라이언트에게 메세지를 보낸다. (현재 접속된 모든 클라이언트에 쓸 수 있는 객체가 필요하다.)
    }
}
