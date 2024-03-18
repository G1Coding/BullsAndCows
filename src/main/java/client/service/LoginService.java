package client.service;

import client.MainClass;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class LoginService {
    // 로그인 서비스를 수행하는 메서드
    public static boolean authenticateUser(ArrayList<String> loginData, Socket sock) {

        try {
            // 데이터를 주고받을 스트림 생성
            DataOutputStream outputStream = new DataOutputStream(sock.getOutputStream());
            DataInputStream inputStream = new DataInputStream(sock.getInputStream());
            // ArrayList를 서버로 전송
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(loginData);
            System.out.print(loginData);
            objectOutputStream.flush();
            int resultMsg = inputStream.readInt();

            //확인
            System.out.printf("인증 결과 = " + resultMsg);
            boolean isAuthentication = resultMsg == 1;

            return isAuthentication;
        } catch (IOException e) {
            e.printStackTrace();
            return false; // 오류 발생 시 인증 실패로 처리
        }
    }
}

