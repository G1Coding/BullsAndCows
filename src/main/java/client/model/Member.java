package client.model;

public class Member {
  private String userid;
  private String userpw;
  private String username;
  private String userbirth;

  public Member(String id, String pw, String name, String birth) {
    userid = id;
    userpw = pw;
    username = name;
    userbirth = birth;
  }

  public String getUserid() {return userid;}
  public void setUserid(String userid) {this.userid = userid;}
  public String getUserpw() {return userpw;}
  public void setUserpw(String userpw) {this.userpw = userpw;}
  public String getUsername() {return username;}
  public void setUsername(String username) {this.username = username;}
  public String getUserbirth() {return userbirth;}
  public void setUserbirth(String userbirth) {this.userbirth = userbirth;}




}
