package client.service;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class LoginService {
  // 로그인 서비스를 수행하는 메서드


  public static boolean authenticateUser(ArrayList<String> loginData, Socket sock) {
    try {
      // 서버의 IP 주소와 포트 번호를 설정
//      Socket sock = new Socket("19s2.168.0.23", 7979);

      // 데이터를 주고받을 스트림 생성
      DataOutputStream outputStream = new DataOutputStream(sock.getOutputStream());
      DataInputStream inputStream = new DataInputStream(sock.getInputStream());
      // ArrayList를 서버로 전송
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
      objectOutputStream.writeObject(loginData);
      System.out.print(loginData);
      objectOutputStream.flush();

      // 서버로부터 인증 결과를 받음(아직 테스트 전)
      String resultMsg = inputStream.readUTF();

      //확인
      System.out.printf("인증 결과 = " + resultMsg);

      boolean isAuthentication = resultMsg.equals("성공");

      // 소켓과 스트림 닫기
      outputStream.close();
      inputStream.close();
      //sock.close();

     // return isAuthenticated; (테스트 전: 서버로부터 받아야할 값)
      return isAuthentication;
    } catch (IOException e) {
      e.printStackTrace();
      return false; // 오류 발생 시 인증 실패로 처리
    }
  }
}

