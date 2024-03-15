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
        ip = (user.getInetAddress().toString()).replaceAll("/","");

        ServerDAO DAO = new ServerDAO();
        int result = DAO.register(name, birth, id, pwd, ip);

        if(result == 1){
            // 로그인 성공
            try {
                OutputStream os = user.getOutputStream();
                DataOutputStream dos = new DataOutputStream(os);
                dos.writeInt(1);

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

            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("LoginRegisterFindResetService 클래스의 login 메소드에서 문제 발생");
            }
        }
    }
    public void reset(ArrayList<String> data, Socket user){
        String id, name, birth;
        id = data.get(1);
        name = data.get(2);
        birth = data.get(3);

        ServerDAO DAO = new ServerDAO();
        int result = DAO.reset(id, name, birth);

        if(result == 1){
            // 리셋 성공
            try {
                OutputStream os = user.getOutputStream();
                DataOutputStream dos = new DataOutputStream(os);
                dos.writeInt(1);

            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("LoginRegisterFindResetService 클래스의 login 메소드에서 문제 발생");
            }
        }else {
            // 리셋 실패
            try {
                OutputStream os = user.getOutputStream();
                DataOutputStream dos = new DataOutputStream(os);
                dos.writeInt(0);

            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("LoginRegisterFindResetService 클래스의 login 메소드에서 문제 발생");
            }
        }
    }

    public void chkName(ArrayList<String> data, Socket user) {
        String name = data.get(1);

        ServerDAO DAO = new ServerDAO();

        // 중복되면 0, 중복되지 않으면 1
        int result = DAO.chkName(name);

        if(result == 1){
            // 중복 값이 있으면
            try {
                OutputStream os = user.getOutputStream();
                DataOutputStream dos = new DataOutputStream(os);
                dos.writeInt(1);

            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("LoginRegisterFindResetService 클래스의 chkName 메소드에서 문제 발생");
            }
        }else {
            // 중복 값이 없으면
            try {
                OutputStream os = user.getOutputStream();
                DataOutputStream dos = new DataOutputStream(os);
                dos.writeInt(0);

            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("LoginRegisterFindResetService 클래스의 chkName 메소드에서 문제 발생");
            }
        }
    }

    public void chkId(ArrayList<String> data, Socket user){
        String id = data.get(1);

        ServerDAO DAO = new ServerDAO();
        // 중복되면 0, 중복되지 않으면 1
        int result = DAO.chkId(id);

        if(result == 1){
            // 아이디 중복 값이 있으면
            try {
                OutputStream os = user.getOutputStream();
                DataOutputStream dos = new DataOutputStream(os);
                dos.writeInt(1);

            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("LoginRegisterFindResetService 클래스의 chkId 메소드에서 문제 발생");
            }
        }else {
            // 아이디 중복 값이 없으면
            try {
                OutputStream os = user.getOutputStream();
                DataOutputStream dos = new DataOutputStream(os);
                dos.writeInt(0);

            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("LoginRegisterFindResetService 클래스의 chkId 메소드에서 문제 발생");
            }
        }
    }

    public void findId(ArrayList<String> data, Socket user) {
        String name = data.get(1);
        String birth = data.get(2);

        ServerDAO DAO = new ServerDAO();
        // 닉네임 반환
        String result = DAO.findId(name, birth);

        try {
            OutputStream os = user.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);
            dos.writeUTF(result);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("LoginRegisterFindResetService 클래스의 findId 메소드에서 문제 발생");
        }
    }

    public void resetPwd(ArrayList<String> data, Socket user){
        String id = data.get(1);
        String pwd = data.get(2);

        ServerDAO DAO = new ServerDAO();
        int result = DAO.resetPwd(id, pwd);

        try {
            OutputStream os = user.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);
            dos.writeInt(result);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("LoginRegisterFindResetService 클래스의 resetPwd 메소드에서 문제 발생");
        }
    }
}
