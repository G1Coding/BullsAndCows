package server.thread;

import server.service.LoginRegisterFindResetService;

import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;

public class LoginRegisterFindResetThread extends Thread{
    Socket user;
    public LoginRegisterFindResetThread(Socket user){
        this.user = user;
        start();
    }

    public LoginRegisterFindResetService service = new LoginRegisterFindResetService();

    @Override
    public void run() {
        while(true) {
            try {
                ObjectInputStream ois = new ObjectInputStream(user.getInputStream());

                ArrayList<String> data = (ArrayList<String>)ois.readObject();

                // ArrayList 의 첫번째 값을 추출
                String item = data.get(0);

                ois.close(); user.getInputStream().close();

                if(item.equals("로그인")){
                    System.out.println("로그인 로직 작동");
                    service.login(data, user);
                } else if (item.equals("회원가입")) {
                    service.register(data, user);
                } else if (item.equals("찾기")) {
                    service.login(data, user);
                } else if (item.equals("초기화")) {
                    service.reset(data, user);
                }

            }catch (Exception e){

            }
        }
    }
}
