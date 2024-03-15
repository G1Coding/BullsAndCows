package server.thread;

import server.DAO.ServerDAO;
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
        while(true){

            try {
                turn = 1;
                System.out.println("1설정");
                for(Socket user : users){
                    this.user = user;
                    out = user.getOutputStream();
                    dos = new DataOutputStream(out);
                    String user1ip = users.get(0).getInetAddress().toString().replaceAll("/", "");
                    ServerDAO dao = new ServerDAO();
                    String user1 = dao.getInetAddress(user1ip);
                    dos.writeUTF( user1 + "님 차례");
                }
                for(int i=1; i<=30; i++){
                    if(turn == 2){
                        break;
                    }
                    Thread.sleep(1000);
                }

                turn = 2;
                System.out.println("2설정");
                for(Socket user : users){
                    this.user = user;
                    out = user.getOutputStream();
                    dos = new DataOutputStream(out);
                    String user2ip = users.get(1).getInetAddress().toString().replaceAll("/", "");
                    ServerDAO dao = new ServerDAO();
                    String user2 = dao.getInetAddress(user2ip);
                    dos.writeUTF( user2 + "님 차례");
                }

                for(int i=1; i<=30; i++){
                    if(turn == 1){
                        break;
                    }
                    Thread.sleep(1000);
                }

            }catch (Exception e){
                e.printStackTrace();
                System.out.println("TurnThread 에서 문제 발생");
            }
        }
    }
}
