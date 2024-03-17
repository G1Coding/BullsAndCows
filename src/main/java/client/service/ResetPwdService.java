package client.service;

import client.AlertClass;
import javafx.scene.control.TextInputDialog;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class ResetPwdService {

  // 비밀번호 초기화 서비스를 수행하는 메서드
  public boolean resetPassword(ArrayList<String> resetPwd, Socket sock) {
    //회원 가입 성공 여부
    try {
      // 데이터를 주고받을 스트림 생성
      DataOutputStream outputStream = new DataOutputStream(sock.getOutputStream());
      DataInputStream inputStream = new DataInputStream(sock.getInputStream());
      // ArrayList를 서버로 전송
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
      objectOutputStream.writeObject(resetPwd);
      System.out.print(resetPwd);
      objectOutputStream.flush();

      // 서버로부터 인증 결과를 받음(아직 테스트 전)
      int resultMsg = inputStream.readInt();
      //확인
      System.out.printf("인증 결과 = " + resultMsg);

      boolean resetPwdAvailable = resultMsg == 1;

      // return isAuthenticated; (테스트 전: 서버로부터 받아야할 값)
      return resetPwdAvailable;
    } catch (IOException e) {
      e.printStackTrace();
      return false; // 오류 발생 시 인증 실패로 처리
    }
  }
  }
