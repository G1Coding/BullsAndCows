package client.service;

import client.AlertClass;

import java.time.LocalDate;

public class SearchIDService {

  // 아이디 찾기 서비스를 수행하는 메서드
  public void searchId(String userName, LocalDate birthDay) {
//    // 입력값 유효성 검사
//    if (userName.isEmpty() || birthDay == null) {
//      AlertClass.showAlert("입력 오류", "모든 필드를 입력해주세요.");
//      return;
//    }

    // 서버 응답에 따라 아이디를 찾았는지 확인
    boolean isUserIdFound = isUserIdFoundInServer(userName, birthDay);

    // 서버 응답에 따라 알림 창 표시
    if (isUserIdFound) {
      // 아이디를 찾은 경우
      AlertClass.showAlertInfo("아이디 찾기", "사용자의 아이디는 XXXX입니다.");
    } else {
      // 일치하는 회원 정보가 없는 경우
      AlertClass.showAlert("아이디 찾기", "일치하는 회원 정보가 없습니다.");
    }
  }

  // 서버 응답에 따라 아이디를 찾았는지 여부를 확인하는 메서드
  private boolean isUserIdFoundInServer(String userName, LocalDate birthDay) {
    // 여기에 서버와 통신하여 아이디를 찾는 로직을 구현합니다.
    // 실제로는 서버와의 통신을 통해 사용자를 조회하고 아이디를 찾아야 합니다.
    // 여기에서는 임시로 true를 반환하여 테스트합니다.
    return true;
  }
}