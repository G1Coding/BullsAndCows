package server.service;

import server.DAO.ServerDAO;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class LoginRegisterFindResetService {
    public void login(ArrayList<String> data, Socket user){
        String id, pwd;
        id = data.get(1);
        pwd = data.get(2);

        ServerDAO DAO = new ServerDAO();
        int result = DAO.login(id, pwd);

        if(result == 1){
            // 로그인 성공
            try {
                OutputStream os = user.getOutputStream();
                DataOutputStream dos = new DataOutputStream(os);
                dos.writeInt(1);

                dos.close(); os.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("LoginRegisterFindResetService 클래스의 login 메소드에서 문제 발생");
            }
        }else {
            // 로그인 실패
            try {
                OutputStream os = user.getOutputStream();
                DataOutputStream dos = new DataOutputStream(os);
                dos.writeInt(0);

                dos.close(); os.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("LoginRegisterFindResetService 클래스의 login 메소드에서 문제 발생");
            }
        }
    }
    public void register(ArrayList<String> data, Socket user){
        String name, birth, id, pwd, ip;
        name = data.get(1);
        birth = data.get(2);
        id = data.get(3);
        pwd = data.get(4);
        ip = String.valueOf(user.getInetAddress());

        ServerDAO DAO = new ServerDAO();
        int result = DAO.register(name, birth, id, pwd, ip);

        if(result == 1){
            // 로그인 성공
            try {
                OutputStream os = user.getOutputStream();
                DataOutputStream dos = new DataOutputStream(os);
                dos.writeInt(1);

                dos.close(); os.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("LoginRegisterFindResetService 클래스의 login 메소드에서 문제 발생");
            }
        }else {
            // 로그인 실패
            try {
                OutputStream os = user.getOutputStream();
                DataOutputStream dos = new DataOutputStream(os);
                dos.writeInt(0);

                dos.close(); os.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("LoginRegisterFindResetService 클래스의 login 메소드에서 문제 발생");
            }
        }
    }
    public void reset(ArrayList<String> data, Socket user){
        String id, name, pwd;
        id = data.get(1);
        name = data.get(2);
        pwd = data.get(3);

        ServerDAO DAO = new ServerDAO();
        int result = DAO.reset(id, name, pwd);

        if(result == 1){
            // 로그인 성공
            try {
                OutputStream os = user.getOutputStream();
                DataOutputStream dos = new DataOutputStream(os);
                dos.writeInt(1);

                dos.close(); os.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("LoginRegisterFindResetService 클래스의 login 메소드에서 문제 발생");
            }
        }else {
            // 로그인 실패
            try {
                OutputStream os = user.getOutputStream();
                DataOutputStream dos = new DataOutputStream(os);
                dos.writeInt(0);

                dos.close(); os.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("LoginRegisterFindResetService 클래스의 login 메소드에서 문제 발생");
            }
        }
    }
}
