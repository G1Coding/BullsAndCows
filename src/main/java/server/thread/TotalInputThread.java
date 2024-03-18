package server.thread;

import server.ServerMain;
import server.service.LoginRegisterFindResetService;
import server.service.MainService;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

import static server.ServerMain.*;
import static server.ServerMain.tryTurn;

public class TotalInputThread extends Thread{
    Socket user;
    public TotalInputThread(Socket user){
        this.user = user;
        start();
    }

    @Override
    public void run() {

        try{

            while(true){
                InputStream inputStream = user.getInputStream();
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                // 데이터의 형식을 판별하여 처리
                Object data = objectInputStream.readObject();

                // @@@@@@@@@@@@@@@@@@@@@@@@@@@@ test 용 데이터 출력 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@
                System.out.println(data);


                Object firstElemnt = ((ArrayList<?>)data).get(0);

                // ArrayList 일 경우
                if(firstElemnt instanceof Integer){
                    // 정답 추측 숫자를 담은 ArrayList 넘어옴
                    ArrayList<Integer> receiveNum = (ArrayList<Integer>) data;
                    if(turn == users.indexOf(user)+1) {
                        System.out.println(receiveNum);

                        MainService service = new MainService();

                        // Strike, Ball, Out 카운트 체크 후 객체에 담음
                        ArrayList<Integer> strikeBallOut = service.compareNum(receiveNum);

                        // User 닉네임 String 형태로 받음
                        String userName = service.getUserName(user);

                        // 현재 몇번째 도전인지 횟수 늘려가며 설정
                        ServerMain.tryTurn++;

                        // DB 에 넘겨 받은 내용 저장
                        service.saveReceiveResult(receiveNum, strikeBallOut, userName, tryTurn);

                        // 다시 클라이언트에게 n번째 도전, 닉네임, 도전한 수(xxxx), 스트라이크, 볼, 아웃 을 전송
                        service.sendResult(tryTurn, userName, receiveNum, strikeBallOut, user);

                        // 턴을 상대 턴으로 조정
                        if(turn == 1){
                            turn = 2;
                        } else if (turn == 2) {
                            turn = 1;
                        }
                    } else if (turn == 0) {
                        OutputStream os = user.getOutputStream();
                        ObjectOutputStream oos = new ObjectOutputStream(os);

                        ArrayList<String> noUser = new ArrayList<>();
                        noUser.add("상대가 입장하지 않았습니다.");

                        oos.writeObject(noUser);
                    } else{
                        OutputStream os = user.getOutputStream();
                        ObjectOutputStream oos = new ObjectOutputStream(os);

                        ArrayList<String> noTurn = new ArrayList<>();
                        noTurn.add("턴아님");

                        oos.writeObject(noTurn);
                    }
                } else if (firstElemnt instanceof  String) {
                    LoginRegisterFindResetService service = new LoginRegisterFindResetService();

                    // 로그인, 회원가입, 찾기, 초기화
                    ArrayList<String> loginRegisterFindReset = (ArrayList<String>) data;
                    String option = loginRegisterFindReset.get(0);
                    if(option.equals("로그인")){
                        service.login(loginRegisterFindReset, user);
                    } else if (option.equals("회원가입")) {
                        service.register(loginRegisterFindReset, user);
                    } else if (option.equals("찾기")) {
                        service.findId(loginRegisterFindReset, user);
                    } else if (option.equals("초기화")) {
                        service.reset(loginRegisterFindReset, user);
                    } else if (option.equals("닉네임")){
                        service.chkName(loginRegisterFindReset, user);
                    } else if (option.equals("아이디")){
                        service.chkId(loginRegisterFindReset, user);
                    } else if (option.equals("비밀번호초기화")) {
                        service.resetPwd(loginRegisterFindReset, user);
                    } else if (option.equals("게임시작")){
                        gameStatus.add("게임시작");
                        WaitingThread waitingThread = new WaitingThread(user);
                    }
                }
            }
        }catch (EOFException e){
            System.out.println("데이터 읽기 완료");
        }catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
