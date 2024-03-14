package server.thread;

import server.ServerMain;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

import static server.ServerMain.users;

public class WaitingThread extends Thread{
    public WaitingThread(){
        start(); // start 메소드 실행 시 run 메소드 자동으로 실행이 되므로 run 메소드 오버라이딩이 필요
    }

    @Override
    public void run(){

            // 클라이언트가 1명만 접속되었을 경우
            while(true){
                try {
                    for(Socket user : users) {
                        // 접속 유저가 2명이면 쓰레드 종료
                        if(users.size() == 2) {
                            System.out.println("유저 2명 접속!!!");
                            return;
                        }

                            System.out.println("스트림 시작!!!!");
                            OutputStream out = user.getOutputStream();
                            DataOutputStream dos = new DataOutputStream(out);
                            dos.writeUTF("상대방 찾는 중....");
                            System.out.println("스트림 종료!!!!");
                            System.out.println("유저 1명 접속!!!");
                            Thread.sleep(3000);

                        System.out.println("상대방 찾는 중 문구 출력 완료");
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("WaitingThread 에서 문제 발생");
                    return;
                    // 반복문 도는 중에 users 에 user 정보가 추가되면 Exception 발생....
                }
        }
    }
}
