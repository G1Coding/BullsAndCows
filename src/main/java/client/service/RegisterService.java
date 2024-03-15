package client.service;

import client.AlertClass;
import client.MainClass;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalDate;
import java.util.ArrayList;

public class RegisterService {

  // 회원가입 서비스를 수행하는 메서드
  public boolean registerUser(ArrayList<String> registerData, Socket sock) {
    // 아이디 중복 확인
    //회원 가입 성공 여부
    try {

      // 데이터를 주고받을 스트림 생성
      DataOutputStream outputStream = new DataOutputStream(sock.getOutputStream());
      DataInputStream inputStream = new DataInputStream(sock.getInputStream());
      // ArrayList를 서버로 전송
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
      objectOutputStream.writeObject(registerData);
      System.out.print(registerData);
      objectOutputStream.flush();

      if (MainClass.sock.isClosed()) {
        System.out.println("소켓이 닫혔습니다.");
      } else if (MainClass.sock.isConnected()) {
        System.out.println("소켓이 닫히지 않았고 연결되어 있습니다.");

      }

      System.out.printf("소켓 보내기 완료");


      // 서버로부터 인증 결과를 받음(아직 테스트 전)
      int resultMsg = inputStream.readInt();
      //확인
      System.out.printf("인증 결과 = " + resultMsg);

      boolean isRegisterAvailable = resultMsg == 1;

      // return isAuthenticated; (테스트 전: 서버로부터 받아야할 값)
      return isRegisterAvailable;
    } catch (IOException e) {
      e.printStackTrace();
      return false; // 오류 발생 시 인증 실패로 처리
    }
  }
  public boolean registerCheckID(ArrayList<String> checkIdData, Socket sock) {
    // 아이디 중복 확인
    //회원 가입 성공 여부
    try {

      // 데이터를 주고받을 스트림 생성
      DataOutputStream outputStream = new DataOutputStream(sock.getOutputStream());
      DataInputStream inputStream = new DataInputStream(sock.getInputStream());
      // ArrayList를 서버로 전송
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
      objectOutputStream.writeObject(checkIdData);
      System.out.print(checkIdData);
      objectOutputStream.flush();

      // 서버로부터 인증 결과를 받음(아직 테스트 전)
      int resultMsg = inputStream.readInt();
      //확인
      System.out.printf("인증 결과 = " + resultMsg);

      boolean isRegisterAvailable = resultMsg == 1;

      // return isAuthenticated; (테스트 전: 서버로부터 받아야할 값)
      return isRegisterAvailable;
    } catch (IOException e) {
      e.printStackTrace();
      return false; // 오류 발생 시 인증 실패로 처리
    }
  }
  public boolean registerCheckName(ArrayList<String> checkNameData, Socket sock) {
    // 아이디 중복 확인
    //회원 가입 성공 여부
    try {

      // 데이터를 주고받을 스트림 생성
      DataOutputStream outputStream = new DataOutputStream(sock.getOutputStream());
      DataInputStream inputStream = new DataInputStream(sock.getInputStream());
      // ArrayList를 서버로 전송
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
      objectOutputStream.writeObject(checkNameData);
      System.out.print(checkNameData);
      objectOutputStream.flush();

      // 서버로부터 인증 결과를 받음(아직 테스트 전)
      int resultMsg = inputStream.readInt();
      //확인
      System.out.printf("인증 결과 = " + resultMsg);

      boolean isRegisterAvailable = resultMsg == 1;

      // return isAuthenticated; (테스트 전: 서버로부터 받아야할 값)
      return isRegisterAvailable;
    } catch (IOException e) {
      e.printStackTrace();
      return false; // 오류 발생 시 인증 실패로 처리
    }
  }





}
