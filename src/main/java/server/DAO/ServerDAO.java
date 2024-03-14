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
            System.out.println("드라이버 연동 전");
            Class.forName("oracle.jdbc.OracleDriver");
            System.out.println("드라이버 호출 성공");
            String url = "jdbc:oracle:thin:@bullsandcows.cjykwegay094.us-east-1.rds.amazonaws.com:1521:orcl";
            String id = "admin";
            String pwd = "12345678";
            con = DriverManager.getConnection(url, id, pwd);
            System.out.println("DB 접속 성공");
        }catch(Exception e){
            System.out.println("DB 연동 오류");
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
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1,name);
            ps.setString(2,birth);
            ps.setString(3,id);
            ps.setString(4,pwd);
            ps.setString(5,ip);
            int result = ps.executeUpdate();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ServerDAO 클래스의 register 메소드에서 문제 발생");
            return 0;
        }
    }

    public int reset(String id, String name, String pwd){
        String sql = "SELECT COUNT(*) FROM MEMBER WHERE id=? AND birth=? AND pwd=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1,id);
            ps.setString(2,name);
            ps.setString(3,pwd);
            rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ServerDAO 클래스의 reset 메소드에서 문제 발생");
            return 0;
        }
    }
}
