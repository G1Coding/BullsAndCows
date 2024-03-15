package server.service;

import server.DAO.ServerDAO;
import server.DTO.ReceiveResultDTO;
import server.DTO.SendResultDTO;
import server.ServerMain;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

import static server.ServerMain.users;

public class MainService {

    public void mkCorrectNum(){
        Random ran = new Random();
        int[] mkCorrectNum = new int[4];
        for(int i=0; i<4; i++){
            mkCorrectNum[i] = ran.nextInt(10);
            for(int j=0; j<i; j++){
                if(mkCorrectNum[i] == mkCorrectNum[j]) i--;
            }
        }
        ServerMain.correctNum = mkCorrectNum;
    }

    public ArrayList<Integer> compareNum(ArrayList<Integer> receiveNum){

        // 스트라이크, 볼, 아웃 카운트 체크 후 ArrayList 에 담아서 반환
        int strike = 0;
        int ball = 0;
        int out = 0;
        ArrayList<Integer> strikeBallOut = new ArrayList<>();
        for(int i=0; i<4; i++){
            if (ServerMain.correctNum[i] == receiveNum.get(i)){
                strike++;
            }
            for(int j=0; j<4; j++){
                if(ServerMain.correctNum[i] == receiveNum.get(j)){
                    ball++;
                }
            }
        }
        if(strike == 0 || ball == 0) out++;

        strikeBallOut.add(strike);
        strikeBallOut.add(ball);
        strikeBallOut.add(out);

        return strikeBallOut;
    }

    public String getUserName(Socket user){
        String userIp = user.getInetAddress().toString();

        // DAO 에 접근해서 IP 에 해당하는 닉네임 받아오기
        ServerDAO DAO = new ServerDAO();
        String userName = DAO.getName(userIp);

        return userName;
    }

    public void saveReceiveResult(ArrayList<Integer> receiveNum, ArrayList<Integer> strikeBallOut, String userName, int tryTurn){
        // DB 에 저장할 값 DTO 에 모두 저장
        ReceiveResultDTO receiveResultDTO = new ReceiveResultDTO();
        receiveResultDTO.setList_num(tryTurn);
        receiveResultDTO.setFirst_num(receiveNum.get(0));
        receiveResultDTO.setSecond_num(receiveNum.get(1));
        receiveResultDTO.setThird_num(receiveNum.get(2));
        receiveResultDTO.setFourth_num(receiveNum.get(3));
        receiveResultDTO.setUser_name(userName);
        receiveResultDTO.setStrike(strikeBallOut.get(0));
        receiveResultDTO.setBall(strikeBallOut.get(1));
        receiveResultDTO.setOut(strikeBallOut.get(2));

        // DAO 로 저장할 값이 담긴 DTO 전달
        ServerDAO DAO = new ServerDAO();
        DAO.saveReceiveResult(receiveResultDTO);
    }

    public void sendResult(int tryTurn, String userName, ArrayList<Integer> receiveNum, ArrayList<Integer> strikeBallOut){
        String sendNum = "" + receiveNum.get(0) + receiveNum.get(1) + receiveNum.get(2) + receiveNum.get(3);
        SendResultDTO sendResultDTO = new SendResultDTO();
        sendResultDTO.setTryTurn(tryTurn);
        sendResultDTO.setUserName(userName);
        sendResultDTO.setSendNum(sendNum);
        sendResultDTO.setStrike(strikeBallOut.get(0));
        sendResultDTO.setBall(strikeBallOut.get(1));
        sendResultDTO.setOut(strikeBallOut.get(2));

        try {
            for(Socket user : users) {
                ObjectOutputStream oos = new ObjectOutputStream(user.getOutputStream());
                oos.writeObject(sendResultDTO);
            }
            System.out.println("MainService - sendResult 메소드에서 결과 정보 담긴 객체 전송 완료");
        } catch (Exception e) {
            System.out.println("MainService 의 sendResult 메소드에서 오류 발생");;
        }
    }
}
