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



//
//      gameStartSign = inputStream.readUTF();
//      if(gameStartSign.equals("게임시작")){
//        return gameStartSign;
//      }

    } catch (Exception e) {
      e.printStackTrace();
    }
    return "오류";
  }
}


//
//      while(true) {
//        DataInputStream inputStream = new DataInputStream(sock.getInputStream());
//        gameStartSign = inputStream.readUTF();
//
//        //System.out.println(gameStartSign);
//        if (gameStartSign.equals("게임시작")) {
//          break;
//        }
//
////        System.out.println("받은 데이터 " + gameStartSign);
//      }
//
//
//      //확인
//      //System.out.printf("인증 결과 = " + gameStartSign);
//
////      if (gameStartSign.equals("게임시작")) {
////        return gameStartSign;
////      } else {
////        return "찾는중";
////      }
//
//      // return isAuthenticated; (테스트 전: 서버로부터 받아야할 값)
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
//    return gameStartSign;
//  }
//}