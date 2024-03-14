package client.service;

import client.AlertClass;
import javafx.scene.control.TextInputDialog;

import java.time.LocalDate;
import java.util.Optional;

public class ResetPwdService {

  // 비밀번호 초기화 서비스를 수행하는 메서드
  public void resetPassword(String userId, String userName, LocalDate birthDay) {
//    // 입력값 유효성 검사
//    if (userId.isEmpty() || userName.isEmpty() || birthDay == null) {
//      AlertClass.showAlert("입력 오류", "모든 필드를 입력해주세요.");
//      return;
//    }

    // DB에서 사용자를 찾아 일치하는지 확인
    boolean isUserFound = isUserFoundInDB(userId, userName, birthDay);

    // 사용자가 DB에 있는 경우 비밀번호 초기화 프로세스 시작
    if (isUserFound) {
      // 여기에 비밀번호 초기화 프로세스 실행 코드를 구현합니다.
      // 성공 시 알림 창을 표시합니다.
      resetPasswordProcess(userId);
    } else {
      // DB에서 일치하는 사용자가 없는 경우
      AlertClass.showAlert("비밀번호 초기화", "일치하는 사용자가 없습니다.");
    }
  }

  // DB에서 사용자를 찾아 일치하는지 확인하는 메서드
  private boolean isUserFoundInDB(String userId, String userName, LocalDate birthDay) {
    // 여기에 DB에서 사용자를 찾는 로직을 구현합니다.
    // 실제로는 DB와 연동하여 사용자를 조회하고 일치 여부를 판단해야 합니다.
    // 여기에서는 임시로 true를 반환하여 테스트합니다.
    return true;
  }
  // 비밀번호 초기화 프로세스를 실행하는 메서드
  private void resetPasswordProcess(String userId) {
    // 새로운 비밀번호를 입력받습니다. 이 부분은 사용자 입력을 받는 화면을 구현하셔야 합니다.
    TextInputDialog dialog = new TextInputDialog();
    dialog.setTitle("비밀번호 초기화");
    dialog.setHeaderText(null);
    dialog.setContentText("새로운 비밀번호를 입력해주세요:");

    Optional<String> result = dialog.showAndWait();

    // 사용자가 입력한 새로운 비밀번호를 서버에 전송하는 로직을 구현합니다.
    // 여기에서는 임시로 콘솔에 출력합니다.
    result.ifPresent(newPassword -> {
      System.out.println("새로운 비밀번호: " + newPassword);
      // 여기에 서버에 새로운 비밀번호를 전송하는 로직을 구현합니다.
      // 실제로는 서버와의 통신을 통해 비밀번호를 업데이트해야 합니다.
      // 이 부분은 서버와의 통신 코드로 대체되어야 합니다.
      // 서버에 새로운 비밀번호를 전송한 후, 성공 여부에 따라 알림 창을 표시할 수 있습니다.
      AlertClass.showAlertInfo("비밀번호 초기화", "비밀번호가 초기화되었습니다.");
    });
  }
}