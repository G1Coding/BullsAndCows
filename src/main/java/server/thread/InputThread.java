package server.thread;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.Socket;

import static server.ServerMain.users;

public class InputThread extends Thread{
    Socket user;
    public InputThread(Socket user){
        this.user = user;
        start();
    }

    @Override
    public void run() {
        while(true){
            try {
                InputStream in = user.getInputStream();
                DataInputStream dis = new DataInputStream(in);
                String msg = dis.readUTF();
                System.out.println(user + " : " + msg);

                 dis.close(); in.close();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("문자열이 넘어오지 않음");
                break;
            }

        }
    }
}
