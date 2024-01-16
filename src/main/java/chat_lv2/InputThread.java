package chat_lv2;

import java.io.BufferedReader;

public class InputThread extends Thread{
    private BufferedReader br;
    public InputThread(BufferedReader br) {
        this.br = br;
    }
    
    @Override
    public void run() {
        try {
            String line = null;
            while ((line = br.readLine())!= null) {
                System.out.println(line);
            }
        } catch (Exception exception) { // 접속이 끊어지면 예외가 발생
            System.out.println("-----");
        }
    }
}
