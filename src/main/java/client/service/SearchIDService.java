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

public class SearchIDService {

  // 아이디 찾기 서비스를 수행하는 메서드
  public static String searchId(ArrayList<String> searchingId, Socket sock) {
    // 서버 응답에 따라 아이디를 찾았는지 확인
    String resultMsg = "1";
    try {
      // 데이터를 주고받을 스트림 생성
      DataOutputStream outputStream = new DataOutputStream(sock.getOutputStream());
      DataInputStream inputStream = new DataInputStream(sock.getInputStream());
      // ArrayList를 서버로 전송
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
      objectOutputStream.writeObject(searchingId);
      System.out.print(searchingId);
      objectOutputStream.flush();
      // 서버로부터 인증 결과를 받음(아직 테스트 전)
      resultMsg = inputStream.readUTF();

      //확인
      System.out.printf("인증 결과 = " + resultMsg);

      if (!resultMsg.equals("x")) {
        return resultMsg;
      }else {
        return "없음";
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
    return resultMsg;
  }
}
