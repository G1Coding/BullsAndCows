package client;

import client.service.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
  private final LoginService loginService = new LoginService();
  private final RegisterService registerService = new RegisterService();
  private final ResetPwdService resetPwdService = new ResetPwdService();
  private final SearchIDService searchIDService = new SearchIDService();


  @FXML
  private ToggleButton loginBtn;
  @FXML
  private ToggleButton registerBtn;
  @FXML
  private ToggleButton resetBtn;
  @FXML
  private ToggleButton backBtn;
  @FXML
  private ToggleButton startBtn;
  @FXML
  private TextField txtId;
  @FXML
  private PasswordField txtPassword;
  @FXML
  private PasswordField txtRePassword;
  @FXML
  private TextField txtName;
  @FXML
  private DatePicker userBirth;
  @FXML
  private ToggleButton registerEndBtn;
  @FXML
  private ToggleButton checkIdBtn;

  @FXML
  private ToggleButton searchIdBtn;
  @FXML
  private ToggleButton resetPasswordBtn;
  @FXML
  private TextField txtNameForSearchId;
  @FXML
  private DatePicker userBirthForSearchId;
  @FXML
  private ToggleButton sendNumBtn;

  @FXML
  private TextField num1;
  @FXML
  private TextField num2;
  @FXML
  private TextField num3;
  @FXML
  private TextField num4;


  @Override
  public void initialize(URL location, ResourceBundle resources) {
    // TODO Auto-generated method stub
  }

  //★★★★★★로그인 시작★★★★★★★
  @FXML
  public void loginBtnClicked(ActionEvent event) {
    System.out.println("★★★★★★로그인 시작★★★★★★★");
    String userId = txtId.getText();
    String password = txtPassword.getText();

    // 입력값 유효성 검사
    if (userId.isEmpty() || password.isEmpty()) {
      AlertClass.showAlert("입력 오류", "모든 필드를 입력해주세요.");
      return;
    }

    System.out.println("사용자 아이디: " + userId);
    System.out.println("비밀번호: " + password);

    // 로그인 정보를 담는 ArrayList 생성 (전송)
    ArrayList<String> loginData = new ArrayList<>();
    loginData.add("로그인"); // Status
    loginData.add(userId);
    loginData.add(password);

    System.out.print(loginData);

    // 로그인 서비스 호출하여 인증 수행
    boolean isAuthenticated = loginService.authenticateUser(loginData);

    //응답
    if (isAuthenticated) {
      moveStartPage(); // 로그인 성공 시 다음 페이지로 이동
    } else {
      AlertClass.showAlert("로그인 실패", "아이디 또는 비밀번호가 일치하지 않습니다.");
    }
  }


  //★★★★★★회원가입★★★★★★★
  @FXML
  public void registerBtnCliked(ActionEvent event) {
    System.out.println("★★★★★★회원가입12★★★★★★★");

    boolean registerEndBtnSelected = registerEndBtn.isSelected();

    if (registerEndBtnSelected) {

      String userName = txtName.getText();
      String userId = txtId.getText();
      String password = txtPassword.getText();
      String passwordRe = txtRePassword.getText();
      LocalDate birthDay = userBirth.getValue();

      //입력값 유효성 검사
      if (userName.isEmpty() || userId.isEmpty() || password.isEmpty() || passwordRe.isEmpty() || birthDay == null) {
        AlertClass.showAlert("입력 오류", "모든 필드를 입력해주세요.");
        return;
      }
      System.out.println("사용자 이름: " + userName);
      System.out.println("사용자 아이디: " + userId);
      System.out.println("비밀번호: " + password);
      System.out.println("비밀번호 확인: " + passwordRe);
      System.out.println("사용자 생일: " + birthDay);

      // 회원가입 서비스 호출하여 회원가입 수행
      boolean isRegistered = registerService.registerUser(userName, userId, password, passwordRe, birthDay);

      //비밀번호 일치 여부 확인
      if (!password.equals(passwordRe)) {
        AlertClass.showAlert("비밀번호 확인", "비밀번호가 일치하지 않습니다.");
        return;
      }

      if (isRegistered) {
        AlertClass.showAlert("회원가입 완료", "회원가입이 완료되었습니다.");
        try {
          // 현재 스테이지 가져오기 (회원가입 창)
          Stage stage = (Stage) registerEndBtn.getScene().getWindow();

          // 메인 화면으로 전환할 FXML 파일 로드
          Parent mainSceneRoot = FXMLLoader.load(getClass().getResource("start.fxml"));
          Scene mainScene = new Scene(mainSceneRoot);

          // 스테이지에 새로운 씬 설정하여 메인 화면 표시
          stage.setScene(mainScene);
          stage.show();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

  // 아이디 중복 확인 이벤트 핸들러
  @FXML
  public void isIdUsing(ActionEvent event) {
    String userId = txtId.getText();
    boolean isAvailable = isIdUsing(userId);
    if (isAvailable) {
      AlertClass.showAlert("아이디 중복 확인", "사용 가능한 아이디입니다.");
    } else {
      AlertClass.showAlert("아이디 중복 확인", "이미 사용 중인 아이디입니다.");
    }
  }

  // 아이디 중복 확인 로직을 수행하는 메서드
  public boolean isIdUsing(String userId) {

    return true; //임시
  }

  //★★★★★★ID 찾기★★★★★★★
  @FXML
  public void searchIdBtnClicked(ActionEvent event) {
    System.out.printf("★★★★★★ID 찾기★★★★★★★");

    // searchIdButton은 해당 버튼의 아이디입니다. 버튼을 누르면 해당 아이디로 버튼이 활성화되도록 설정해야 합니다.
    boolean searchIdButtonSelected = searchIdBtn.isSelected();

    if (searchIdButtonSelected) {
      String userName = txtNameForSearchId.getText();
      LocalDate birthDay = userBirthForSearchId.getValue();

      // 입력값 유효성 검사
      if (userName.isEmpty() || birthDay == null) {
        AlertClass.showAlert("입력 오류", "모든 필드를 입력해주세요.");
        return;
      }

      System.out.println("사용자 이름: " + userName);
      System.out.println("사용자 생일: " + birthDay);

      // 아이디 찾기 서비스 호출하여 아이디 찾기 수행
      searchIDService.searchId(userName, birthDay);
    }
  }


  //★★★★★★비밀번호 초기화★★★★★★★
  @FXML
  public void resetPwdBtnClicked(ActionEvent event) {
    System.out.println("★★★★★★비밀번호 초기화★★★★★★★");

    boolean resetPasswordBtnSelected = resetPasswordBtn.isSelected();

    if (resetPasswordBtnSelected) {
      String userId = txtId.getText();
      String userName = txtName.getText();
      LocalDate birthDay = userBirth.getValue();

      // 입력값 유효성 검사
      if (userId.isEmpty() || userName.isEmpty() || birthDay == null) {
        AlertClass.showAlert("입력 오류", "모든 필드를 입력해주세요.");
        return;
      }

      System.out.println("사용자 아이디: " + userId);
      System.out.println("사용자 이름: " + userName);
      System.out.println("사용자 생일: " + birthDay);

      // 비밀번호 초기화 서비스 호출하여 비밀번호 초기화 수행
      resetPwdService.resetPassword(userId, userName, birthDay);
    }
  }


  @FXML
  public void sendNumBtnClicked(ActionEvent event) {
    // Implement sending the numbers to the server here

    // 입력값 유효성 검사
    if (num1.getText().isEmpty() || num2.getText().isEmpty() || num3.getText().isEmpty() || num4.getText().isEmpty()) {
      AlertClass.showAlert("입력 오류", "모든 필드를 입력해주세요.");
      return;
    }

    if (!isValidInput(num1.getText()) || !isValidInput(num2.getText()) || !isValidInput(num3.getText()) || !isValidInput(num4.getText())) {
      AlertClass.showAlert("잘못된 값", "정수 값(0~  9)만 입력하세요");
      return;
    }

    ArrayList<Integer> sendNum = new ArrayList<>();
    sendNum.add(Integer.parseInt(num1.getText()));
    sendNum.add(Integer.parseInt(num2.getText()));
    sendNum.add(Integer.parseInt(num3.getText()));
    sendNum.add(Integer.parseInt(num4.getText()));

    boolean result = checkNum(sendNum);
    if (!result) {
      AlertClass.showAlert("중복값", "각 자리 수는 중복될 수 없습니다");
      return;
    }

    System.out.println(sendNum);
  }


  //숫자 게임 중복 값 체크
  public boolean checkNum(ArrayList<Integer> checkNum) {

    for (int i = 0; i <= checkNum.size(); i++) {
      for (int j = i + 1; i < checkNum.size(); j++) {
        if (checkNum.get(i).equals(checkNum.get(j)))
          return false;
      }
    }
    return true;
  }

  private boolean isValidInput(String text) {
    return text.matches("\\d") && Integer.parseInt(text) >= 0 && Integer.parseInt(text) <= 9;
  }


  //아래부터는 페이지 이동 메서드
  public void moveStartPage() {
    // 새 스테이지 추가
    //Stage newStage = new Stage();
    Stage stage = (Stage) loginBtn.getScene().getWindow();

    try {
      // 기존 스테이지 + 새 레이아웃
      /* 새로만든 레이아웃을 기존 스테이지에 띄움 */
      Parent second = FXMLLoader.load(getClass().getResource("main.fxml"));

      // 씬에 레이아웃 추가
      Scene sc = new Scene(second);
      stage.setScene(sc);
      stage.show();

    } catch (IOException e) {
      e.printStackTrace();
    }

  }


  public void moveResetPage() {
    Stage stage = (Stage) resetBtn.getScene().getWindow();

    try {
      // 기존 스테이지 + 새 레이아웃
      /* 새로만든 레이아웃을 기존 스테이지에 띄움 */
      Parent second = FXMLLoader.load(getClass().getResource("resetpwd.fxml"));

      // 씬에 레이아웃 추가
      Scene sc = new Scene(second);
      stage.setScene(sc);
      stage.show();

    } catch (IOException e) {
      e.printStackTrace();
    }

  }


  public void moveRegister() {
    Stage stage = (Stage) registerBtn.getScene().getWindow();

    try {
      // 기존 스테이지 + 새 레이아웃
      /* 새로만든 레이아웃을 기존 스테이지에 띄움 */
      Parent second = FXMLLoader.load(getClass().getResource("register.fxml"));

      // 씬에 레이아웃 추가
      Scene sc = new Scene(second);
      stage.setScene(sc);
      stage.show();

    } catch (IOException e) {
      e.printStackTrace();
    }

  }


  public void moveBackPage() {
    Stage stage = (Stage) backBtn.getScene().getWindow();

    try {
      // 기존 스테이지 + 새 레이아웃
      /* 새로만든 레이아웃을 기존 스테이지에 띄움 */
      Parent second = FXMLLoader.load(getClass().getResource("start.fxml"));

      // 씬에 레이아웃 추가
      Scene sc = new Scene(second);
      stage.setScene(sc);
      stage.show();

    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  public void moveIngamePage() {
    // 새 스테이지 추가
    Stage newStage = new Stage();
    Stage stage = (Stage) startBtn.getScene().getWindow();

    try {
      // 기존 스테이지 + 새 레이아웃
      /* 새로만든 레이아웃을 기존 스테이지에 띄움 */
      Parent second = FXMLLoader.load(getClass().getResource("ingame.fxml"));

      // 씬에 레이아웃 추가
      Scene sc = new Scene(second);
      stage.setScene(sc);
      stage.show();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
