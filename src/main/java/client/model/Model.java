package client.model;

import java.util.ArrayList;

public class Model {

  /*  HashMap으로 이루어진 데이터
  private HashpMap<String, String> table;
  String user;
  public MyDB() {
    table = new HashMap<>();
    table.put("123", "111");
    table.put("222", "111");
    table.put("333", "111");
    }

   public String getPassword(String DB_id) {
    return table.get(DB_id);
    }


   */


  private ArrayList<Member> list = new ArrayList<Member>();

  public Model() {
    list.add(new Member("me", "555", "별곤", "941219"));
    list.add(new Member("test", "1234", "관리자", "830218"));
  }

  public Member login(String id, String pw) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getUserid().equals(id)) {
        if (list.get(i).getUserpw().equals(pw)) {
          return list.get(i);
        } else return null;
      }
    }
    return null;
  }

}
