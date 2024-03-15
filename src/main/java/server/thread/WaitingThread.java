package server.thread;

import server.ServerMain;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

import static server.ServerMain.gameStatus;
import static server.ServerMain.users;

public class WaitingThread extends Thread{
    Socket user;
    public WaitingThread(Socket user){
        this.user = user;
        start(); // start 메소드 실행 시 run 메소드 자동으로 실행이 되므로 run 메소드 오버라이딩이 필요
    }

    @Override
    public void run(){

            // 클라이언트가 1명만 접속되었을 경우
            /*while(true){
                try {
                    if(gameStatus.size() == 2) {
                        break;
                    }

                    OutputStream out = users.get(0).getOutputStream();
                    DataOutputStream dos = new DataOutputStream(out);
                    Thread.sleep(3000);
                    System.out.println("상대방 찾는 중 보냄");

                }catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("WaitingThread 에서 문제 발생");
                    return;
                }
            }*/

        OutputStream out = null;
        try {
            out = user.getOutputStream();
            DataOutputStream dos = new DataOutputStream(out);
            dos.writeUTF("상대방을 찾는 중...");
            System.out.println(users.indexOf(user) + " : 상대방을 찾는 중... 전송 완료");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
