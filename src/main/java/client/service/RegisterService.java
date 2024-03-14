package client.service;

import client.AlertClass;

import java.time.LocalDate;

public class RegisterService {

  // 회원가입 서비스를 수행하는 메서드
  public boolean registerUser(String userName, String userId, String password, String passwordRe, LocalDate birthDay) {
    // 아이디 중복 확인
    boolean isIdAvailable = isIdAvailable(userId);
    if (!isIdAvailable) {
      AlertClass.showAlert("아이디 중복 확인", "이미 사용 중인 아이디입니다.");
      return false;
    }

    // 비밀번호 일치 여부 확인
    if (!password.equals(passwordRe)) {
      AlertClass.showAlert("비밀번호 확인", "비밀번호가 일치하지 않습니다.");
      return false;
    }
    // 회원정보 로직 작성(서버로 회원 정보 전송)
    // 회원가입 성공 여부에 따라 true/false
    return true;
  }

  // 아이디 중복 확인 메서드
  private boolean isIdAvailable(String userId) {
    // 여기에 아이디 중복 확인 로직을 구현합니다.
    // 이 예제에서는 임시로 항상 true를 반환하여 테스트합니다.
    return true;
  }

}