package client.message;

import client.message.AlertClass;

import java.time.LocalDate;

//필드에 값이 모두 들어있는지 확인하는 클래스
public class FieldValidation {
  public static boolean loginInput(String userId, String password) {
    // 입력값 검사
    if (userId.isEmpty() || password.isEmpty()) {
      AlertClass.showAlert("입력 오류", "모든 필드를 입력해주세요.");
      return false;
    }
    return true;
  }

  public static boolean registerInput(String userName, LocalDate birthDay, String userId, String password, String passwordRe) {
    // 입력값 검사
    if (userName.isEmpty() || birthDay == null || userId.isEmpty() || password.isEmpty() || passwordRe.isEmpty() ) {
      AlertClass.showAlert("입력 오류", "모든 필드를 입력해주세요.");
      return false;
    }
    return true;
  }

  public static boolean researchIdInput(String userName, LocalDate birthDay) {
    // 입력값 검사
    if (userName.isEmpty() || birthDay == null) {
      AlertClass.showAlert("입력 오류", "모든 필드를 입력해주세요.");
      return false;
    }
    return true;
  }

  public static boolean resetPwdInput(String userId, String userName, LocalDate birthDay) {
    // 입력값 검사
    if (userId.isEmpty() || userName.isEmpty() || birthDay == null) {
      AlertClass.showAlert("입력 오류", "모든 필드를 입력해주세요.");
      return false;
    }
    return true;
  }
}
