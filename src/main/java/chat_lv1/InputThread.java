package chat_lv1;

import java.io.BufferedReader;

public class InputThread extends Thread{
    BufferedReader in;
    public InputThread(BufferedReader in) {
        this.in = in;
    }
    
    @Override
    public void run() {
        try{
            String line;
            while((line = in.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
