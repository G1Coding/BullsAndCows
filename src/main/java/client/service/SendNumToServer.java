package client.service;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

public class SendNumToServer {

    public static ArrayList<String> ResultNumber(ArrayList<Integer> resultNum, Socket sock) throws IOException {

        ArrayList<String> resultMsg = null;
        try {
            // 데이터를 주고받을 스트림 생성
            DataOutputStream outputStream = new DataOutputStream(sock.getOutputStream());
            DataInputStream inputStream = new DataInputStream(sock.getInputStream());
            // ArrayList를 서버로 전송
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(resultNum);
            System.out.print(resultNum);
            objectOutputStream.flush();
            // 서버로부터 인증 결과를 받음(아직 테스트 전)
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

            resultMsg = (ArrayList<String>) objectInputStream.readObject();
            System.out.println(resultMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return resultMsg;
    }

}
