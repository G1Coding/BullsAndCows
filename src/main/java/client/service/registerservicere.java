//package client.service;
//
//
//import client.MainClass;
//
//import java.io.*;
//import java.net.Socket;
//import java.util.ArrayList;
//
//public class RegisterService {
//
//  // 회원가입 서비스를 수행하는 메서드
//  public static boolean registerUser(ArrayList<String> registerData, Socket sock) {
//
//    //회원 가입 성공 여부
//    try {
//
//      // 데이터를 주고받을 스트림 생성
//      DataOutputStream outputStream = new DataOutputStream(sock.getOutputStream());
//      DataInputStream inputStream = new DataInputStream(sock.getInputStream());
//      // ArrayList를 서버로 전송
//      ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
//      objectOutputStream.writeObject(registerData);
//      System.out.print(registerData);
//      objectOutputStream.flush();
//
//      if (MainClass.sock.isClosed()) {
//        System.out.println("소켓이 닫혔습니다.");
//      } else if (MainClass.sock.isConnected()) {
//        System.out.println("소켓이 닫히지 않았고 연결되어 있습니다.");
//
//      }
//
//      System.out.printf("소켓 보내기 완료");
//
//
//      // 서버로부터 인증 결과를 받음(아직 테스트 전)
//      int resultMsg = inputStream.readInt();
//      //확인
//      System.out.printf("인증 결과 = " + resultMsg);
//
//      boolean isRegisterAvailable = resultMsg == 1;
//
//      // return isAuthenticated; (테스트 전: 서버로부터 받아야할 값)
//      return isRegisterAvailable;
//    } catch (IOException e) {
//      e.printStackTrace();
//      return false; // 오류 발생 시 인증 실패로 처리
//    }
//  }
//
//
////
////
//
//
//  //아이디 중복 확인
//  public boolean checkID(ArrayList<String> registerName, Socket sock) {
//    try {
//      //  Socket sock = new Socket("19s2.168.0.23", 7979);
//
//      // 데이터를 주고받을 스트림 생성
//      DataOutputStream outputStream = new DataOutputStream(sock.getOutputStream());
//      DataInputStream inputStream = new DataInputStream(sock.getInputStream());
//      // ArrayList를 서버로 전송
//      ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
//      objectOutputStream.writeObject(registerName);
//      System.out.print(registerName);
//      objectOutputStream.flush();
//
//
//      // 서버로부터 인증 결과를 받음(아직 테스트 전)
//      int resultMsg = inputStream.readInt();
//      //확인
//      System.out.printf("인증 결과 = " + resultMsg);
//
//      boolean resultCheckID = resultMsg == 1;
//
//      // return isAuthenticated; (테스트 전: 서버로부터 받아야할 값)
//      return resultCheckID;
//    } catch (IOException e) {
//      e.printStackTrace();
//      return false; // 오류 발생 시 인증 실패로 처리
//    }
//  }
//}
//
//public boolean checkName(ArrayList<String> registerID, Socket sock) {
//  try {
//    //  Socket sock = new Socket("19s2.168.0.23", 7979);
//
//    // 데이터를 주고받을 스트림 생성
//    DataOutputStream outputStream = new DataOutputStream(sock.getOutputStream());
//    DataInputStream inputStream = new DataInputStream(sock.getInputStream());
//    // ArrayList를 서버로 전송
//    ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
//    objectOutputStream.writeObject(registerID);
//    System.out.print(registerId);
//    objectOutputStream.flush();
//
//
//    // 서버로부터 인증 결과를 받음(아직 테스트 전)
//    int resultMsg = inputStream.readInt();
//    //확인
//    System.out.printf("인증 결과 = " + resultMsg);
//
//    boolean resultCheckID = resultMsg == 1;
//
//    // return isAuthenticated; (테스트 전: 서버로부터 받아야할 값)
//    return resultCheckID;
//  } catch (IOException e) {
//    e.printStackTrace();
//    return false; // 오류 발생 시 인증 실패로 처리
//  }
//}