package server.DAO;

import server.DTO.ReceiveResultDTO;
import server.DTO.SendResultDTO;

import java.sql.*;

public class ServerDAO {
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    public ServerDAO() {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            String url = "jdbc:oracle:thin:@bullsandcows.cjykwegay094.us-east-1.rds.amazonaws.com:1521:orcl";
            String id = "admin";
            String pwd = "12345678";
            con = DriverManager.getConnection(url, id, pwd);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public String getName(String userIp){
        String sql = "select name from member where ip=?";
        String userName = null;
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1,userIp);
            rs = ps.executeQuery();
            if(rs.next()){
                userName = rs.getString("name");
            }
            return userName;
        }catch (Exception e){
            System.out.println("ServerDAO 클래스 내 getName 메소드 오류");
        }
        return null;
    }

    public void saveReceiveResult(ReceiveResultDTO receiveResultDTO){
        String sql = "insert into try_list values(?,?,?,?,?,?,?,?,?)";
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, receiveResultDTO.getList_num());
            ps.setInt(2, receiveResultDTO.getFirst_num());
            ps.setInt(3, receiveResultDTO.getSecond_num());
            ps.setInt(4, receiveResultDTO.getThird_num());
            ps.setInt(5, receiveResultDTO.getFourth_num());
            ps.setString(6, receiveResultDTO.getUser_name());
            ps.setInt(7, receiveResultDTO.getStrike());
            ps.setInt(8, receiveResultDTO.getBall());
            ps.setInt(9, receiveResultDTO.getOut());
        }catch(Exception e){
            System.out.println("ServerDAO의 saveReceiveResult 메소드에서 오류 발생");
        }
    }

    public int login(String id, String pwd){
        String sql = "SELECT COUNT(*) FROM MEMBER WHERE id=? AND pwd=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1,id);
            ps.setString(2,pwd);
            rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ServerDAO 클래스의 login 메소드에서 문제 발생");
            return 0;
        }
    }

    public int register(String name, String birth, String id, String pwd, String ip){
        String sql = "INSERT INTO MEMBER VALUES (?,?,?,?,?)";

        // 아이디, 닉네임의 중복 값이 있다면
        if(chkId(id)==1 || chkName(name)==1){
            return 0;
        }
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1,id);
            ps.setString(2,name);
            ps.setString(3,pwd);
            ps.setString(4,birth);
            ps.setString(5,ip.replaceAll("/", ""));
            int result = ps.executeUpdate();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ServerDAO 클래스의 register 메소드에서 문제 발생");
            return 0;
        }
    }

    public int reset(String id, String name, String birth){
        String sql = "SELECT COUNT(*) FROM MEMBER WHERE id=? AND name=? AND birth=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1,id);
            ps.setString(2,name);
            ps.setString(3,birth);
            rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ServerDAO 클래스의 reset 메소드에서 문제 발생");
            return 0;
        }
    }

    public String getInetAddress(String ip){
        String sql = "SELECT name FROM member WHERE ip=?";
        String userName=null;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, ip.replaceAll("/", ""));
            rs = ps.executeQuery();
            if(rs.next()){
                userName = rs.getString("name");
                return userName;
            }
            return userName;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ServerDAO 클래스의 reset 메소드에서 문제 발생");
            return userName;
        }
    }

    public int chkName(String name){
        String sql = "SELECT COUNT(*) FROM MEMBER WHERE name=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1,name);
            rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ServerDAO 클래스의 chkName 메소드에서 문제 발생");
            return 0;
        }
    }

    public int chkId(String id){
        String sql = "SELECT COUNT(*) FROM MEMBER WHERE id=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1,id);
            rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ServerDAO 클래스의 chkId 메소드에서 문제 발생");
            return 0;
        }
    }

    public String findId(String name, String birth){
        String sql = "SELECT id FROM member WHERE name=? AND birth=?";
        String userId="x";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, birth);
            rs = ps.executeQuery();
            if(rs.next()){
                userId = rs.getString("id");
                return userId;
            }
            return userId;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ServerDAO 클래스의 findId 메소드에서 문제 발생");
            return userId;
        }
    }

    public int resetPwd(String id, String pwd){
        String sql = "UPDATE MEMBER SET pwd=? WHERE id=?";
        int result = 0;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1,pwd);
            ps.setString(2,id);
            result = ps.executeUpdate();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ServerDAO 클래스의 chkId 메소드에서 문제 발생");
            return 0;
        }
    }
}
