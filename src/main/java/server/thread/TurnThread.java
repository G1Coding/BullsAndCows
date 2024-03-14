package server.thread;

import server.ServerMain;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import static server.ServerMain.*;

public class TurnThread extends Thread{
    public OutputStream out;
    public DataOutputStream dos;
    public Socket user;
    public TurnThread(){
        start();
    }

    @Override
    public void run() {
        gameStatus = true;
        while(true){
            try {
                turn = 1;
                System.out.println("1설정");
                for(Socket user : users){
                    this.user = user;
                    out = user.getOutputStream();
                    dos = new DataOutputStream(out);
                    dos.writeUTF(turn + "님 차례");
                }
                Thread.sleep(30000);

                turn = 2;
                System.out.println("2설정");
                for(Socket user : users){
                    this.user = user;
                    out = user.getOutputStream();
                    dos = new DataOutputStream(out);
                    dos.writeUTF(turn + "님 차례");
                }
                Thread.sleep(30000);
            }catch (Exception e){
                users.remove(user);
                for(Socket user : users){
                    System.out.println(user);
                }
            }
        }
    }
}
