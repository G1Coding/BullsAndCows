package client.service;

import client.MainClass;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class LoginService {
  // 로그인 서비스를 수행하는 메서드

    public static boolean authenticateUser(ArrayList<String> loginData, Socket sock) {


//      if (MainClass.sock.isClosed()) {
//        System.out.println("소켓이 닫혔습니다.");
//      } else if (MainClass.sock.isConnected()){
//        System.out.println("소켓이 닫히지 않았고 연결되어 있습니다.");
//
//      }
//
//      System.out.printf("소켓 test 완료");

      
      try {
     //  Socket sock = new Socket("19s2.168.0.23", 7979);

      // 데이터를 주고받을 스트림 생성
      DataOutputStream outputStream = new DataOutputStream(sock.getOutputStream());
      DataInputStream inputStream = new DataInputStream(sock.getInputStream());
      // ArrayList를 서버로 전송
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
      objectOutputStream.writeObject(loginData);
      System.out.print(loginData);
      objectOutputStream.flush();

        if (MainClass.sock.isClosed()) {
          System.out.println("소켓이 닫혔습니다.");
        } else if (MainClass.sock.isConnected()){
          System.out.println("소켓이 닫히지 않았고 연결되어 있습니다.");

        }

        System.out.printf("소켓 보내기 완료");


        
        
        // 서버로부터 인증 결과를 받음(아직 테스트 전)
      int resultMsg= inputStream.readInt();

      //확인
      System.out.printf("인증 결과 = " + resultMsg);


        if (MainClass.sock.isClosed()) {
          System.out.println("소켓이 닫혔습니다.");
        } else if (MainClass.sock.isConnected()){
          System.out.println("소켓이 닫히지 않았고 연결되어 있습니다.");

        }

        System.out.printf("소켓 보내기 완료22");



        boolean isAuthentication = resultMsg==1;

      // 소켓과 스트림 닫기
//      outputStream outputStream.close();
//      inputStream.close();();
//      inputStream.close();
      //sock.close();


        if (MainClass.sock.isClosed()) {
          System.out.println("소켓이 닫혔습니다.");
        } else if (MainClass.sock.isConnected()){
          System.out.println("소켓이 닫히지 않았고 연결되어 있습니다.");

        }

        System.out.printf("소켓 보내기 완료33");







        // return isAuthenticated; (테스트 전: 서버로부터 받아야할 값)
      return isAuthentication;
    } catch (IOException e) {
      e.printStackTrace();
      return false; // 오류 발생 시 인증 실패로 처리
    }
  }
}

