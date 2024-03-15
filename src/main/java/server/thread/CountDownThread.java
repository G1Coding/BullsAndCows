package server.thread;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import static server.ServerMain.users;

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
                dos.writeUTF("게임시작");
                System.out.println(users.indexOf(user) + " : 게임시작 전송 완료");
            }catch(Exception e){
                e.printStackTrace();
            }
    }
}
