package client.service;

import main.MainClass;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import static client.MainClass.gameStartSign;
public class InGame {
  public static String startGame(ArrayList<String> startSign, Socket sock) {
    try {
      // 데이터를 주고받을 스트림 생성
      DataOutputStream outputStream = new DataOutputStream(sock.getOutputStream());
      DataInputStream inputStream = new DataInputStream(sock.getInputStream());
      // ArrayList를 서버로 전송
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
      objectOutputStream.writeObject(startSign);
      System.out.print(startSign);
      objectOutputStream.flush();

      gameStartSign = inputStream.readUTF();

      if(gameStartSign.equals("상대방을 찾는 중...")) {
        return gameStartSign;
      }

      gameStartSign = inputStream.readUTF();
      if (gameStartSign.equals("상대방을 찾는 중...")) {
        return gameStartSign;
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
    return "오류";
  }
}