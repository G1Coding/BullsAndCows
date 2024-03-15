package server.thread;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class CountDownThread extends Thread{
    private Socket user;
    public CountDownThread(Socket user){
        this.user = user;
    }

    @Override
    public void run() {
            try{
                OutputStream os = user.getOutputStream();
                DataOutputStream dos = new DataOutputStream(os);
                dos.writeUTF("모든 유저가 접속했습니다.");
                dos.writeUTF("===== 5초 후 게임이 시작됩니다 =====");

                for(int i=5; i>=1; i--){
                    dos.writeUTF("⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️" + i + "⭐️⭐️⭐️⭐️⭐️⭐️⭐️⭐️");
                    Thread.sleep(1000);
                }

                dos.writeUTF("!!! 게임 시작 !!!");
            }catch(Exception e){
                e.printStackTrace();
            }
    }
}
