package server;

import server.service.MainService;
import server.thread.*;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerMain {
    public static ArrayList<Socket> users = new ArrayList<>();
    public static boolean gameStatus = false;
    public static int turn = 0;
    public static int tryTurn = 0;
    public static int[] correctNum = new int[4];

    public static void main(String[] args) throws Exception {
        ServerMain main = new ServerMain();
    }
    public ServerMain() throws Exception {

        ServerSocket server = new ServerSocket(7979);

        while(true){
            // 소켓으로 접속한 유저의 정보를 user 변수로 저장
            Socket user = server.accept();

            // 입력되는 모든 값을 처리하는 쓰레드
            TotalInputThread totalInputThread = new TotalInputThread(user);

            // 소켓에 클라이언트 접속하여 배열로 해당 소켓 정보 저장
            users.add(user);

            if(users.size()==2) {
                System.out.println("유저 2명 저장");
                break;
            }
        }

        /* 2 명이 접속한 상태, 게임 시작 전 카운트 다운 */
        CountDownThread countUser1 = new CountDownThread(users.get(0));
        CountDownThread countUser2 = new CountDownThread(users.get(1));
        countUser1.start();
        countUser2.start();

        // 난수 생성 메소드
        MainService service = new MainService();
        service.mkCorrectNum();

        // 카운트 다운 시간초 6초 대기 후 턴 시작
        Thread.sleep(6000);

        TurnThread turn = new TurnThread();

        while(true){
            Thread.sleep(1000 * 60 * 30);
            break;
        }
    }
}