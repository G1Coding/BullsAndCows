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
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import static client.MainClass.sock;

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

  @FXML Label serverResponse;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
  }

  //★★★★★★로그인 시작★★★★★★★
  @FXML
  public void loginBtnClicked(ActionEvent event) throws IOException {
    System.out.println("★★★★★★로그인 시작★★★★★★★");

    String userId = txtId.getText();
    String password = txtPassword.getText();

    // 입력값 유효성 검사
    if (!FieldValidation.loginInput(userId, password)) {
      return; // 유효하지 않은 입력값이 있으면 여기서 종료
    }

    System.out.println("사용자 아이디: " + userId);
    System.out.println("비밀번호: " + password);

    // 로그인 정보를 담는 ArrayList 생성 (전송)
    ArrayList<String> loginData = new ArrayList<>();
    loginData.add("로그인"); // Status
    loginData.add(userId);
    loginData.add(password);

    System.out.print(loginData);

    Socket sock = MainClass.sock;

    // 로그인 서비스 호출하여 인증 수행
    boolean isAuthenticated = loginService.authenticateUser(loginData, sock);

    //응답
    if (isAuthenticated) {
      System.out.printf("로그인 성공!!!");
      moveStartPage(); // 로그인 성공 시 다음 페이지로 이동
    } else {
      AlertClass.showAlert("로그인 실패", "아이디 또는 비밀번호가 일치하지 않습니다.");
    }
  }


  //★★★★★★회원가입★★★★★★★
  @FXML
  public void registerBtnCliked(ActionEvent event) {
    System.out.println("★★★★★★회원가입12★★★★★★★");
    String userName = txtName.getText();
    String userId = txtId.getText();
    String password = txtPassword.getText();
    String passwordRe = txtRePassword.getText();
    LocalDate birthDay = userBirth.getValue();

    // 입력값 유효성 검사
    if (!FieldValidation.registerInput(userName, birthDay, userId, password, passwordRe)) {
      return; // 유효하지 않은 입력값이 있으면 여기서 종료
    }


    System.out.println("사용자 이름: " + userName);
    System.out.println("사용자 아이디: " + userId);
    System.out.println("비밀번호: " + password);
    System.out.println("비밀번호 확인: " + passwordRe);
    System.out.println("사용자 생일: " + birthDay);
    System.out.println("사용자 생일: " + String.valueOf(birthDay));


    // 로그인 정보를 담는 ArrayList 생성 (전송)
    ArrayList<String> registerData = new ArrayList<>();
    registerData.add("회원가입"); // Status
    registerData.add(userName);
    registerData.add(String.valueOf(birthDay));
    registerData.add(userId);
    registerData.add(password);

    System.out.print(registerData);

    Socket sock = MainClass.sock;
    // 회원가입 서비스 호출하여 인증 수행
    boolean isRegistered = registerService.registerUser(registerData, sock);

    ArrayList<String> registerName = new ArrayList<>();
    registerName.add("닉네임");
    registerName.add(userName);


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
    } else {
      AlertClass.showAlert("회원가입 실패", "닉네임과 아이디 중복 체크 해주세요");

    }
  }


  // 아이디 중복 확인 이벤트 핸들러
  @FXML
  public void isIdUsing(ActionEvent event) {

    String userId = txtId.getText();

    ArrayList<String> registerID = new ArrayList<>();
    registerID.add("아이디");
    registerID.add(userId);

    System.out.print(registerID);

    Socket sock = MainClass.sock;

    boolean isIdDuplication = registerService.registerCheckID(registerID, sock);
    if (isIdDuplication) {
      AlertClass.showAlert("아이디 중복 확인", "이미 사용 중인 아이디입니다..");

    } else {
      AlertClass.showAlert("아이디 중복 확인", "사용 가능한 아이디입니다.");

    }
  }


  //닉네임 중복 확인
  @FXML
  public void isNameUsing(ActionEvent event) {
    String userName = txtName.getText();
    ArrayList<String> registerName = new ArrayList<>();
    registerName.add("닉네임");
    registerName.add(userName);

    System.out.print(registerName);

    Socket sock = MainClass.sock;

    boolean isIdDuplication = registerService.registerCheckName(registerName, sock);
    if (isIdDuplication) {
      AlertClass.showAlert("닉네임 중복 확인", "이미 사용 중인 닉네임이다...");

    } else {
      AlertClass.showAlert("닉네임 중복 확인", "사용 가능한 닉네임입니다.");

    }
  }


  //★★★★★★ID 찾기★★★★★★★
  @FXML
  public void searchIdBtnClicked(ActionEvent event) {
    // searchIdButton은 해당 버튼의 아이디입니다. 버튼을 누르면 해당 아이디로 버튼이 활성화되도록 설정해야 합니다.
    boolean searchIdButtonSelected = searchIdBtn.isSelected();
    if (searchIdButtonSelected) {
      System.out.printf("★★★★★★ID 찾기★★★★★★★");
      String userName = txtNameForSearchId.getText();
      LocalDate birthDay = userBirthForSearchId.getValue();

      // 입력값 유효성 검사
      if (!FieldValidation.researchIdInput(userName, birthDay)) {
        return; // 유효하지 않은 입력값이 있으면 여기서 종료
      }

      // 로그인 정보를 담는 ArrayList 생성 (전송)
      ArrayList<String> searchingId = new ArrayList<>();
      searchingId.add("찾기");
      searchingId.add(userName); // Status
      searchingId.add(String.valueOf(birthDay));

      System.out.print(searchingId);

      Socket sock = MainClass.sock;
      // 아이디 찾기 서비스 호출하여 아이디 찾기 수행
      String result = searchIDService.searchId(searchingId, sock);

      if (result.equals("없음")) {
        AlertClass.showAlert("아이디 찾기 실패", "일치하는 회원 정보가 없습니다.");
      } else {
        AlertClass.showAlert("아이디 찾기 성공", "회원님의 아이디는 " + result + " 입니다.");
      }
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
      if (!FieldValidation.resetPwdInput(userId, userName, birthDay)) {
        return; // 유효하지 않은 입력값이 있으면 여기서 종료
      }

      ArrayList<String> resetPwd = new ArrayList<>();
      resetPwd.add("초기화");
      resetPwd.add(userId);
      resetPwd.add(userName);
      resetPwd.add(String.valueOf(birthDay));

      System.out.print(resetPwd);

      Socket sock = MainClass.sock;
      boolean isUser = resetPwdService.resetPassword(resetPwd, sock);

      //응답
      if (isUser) {
        System.out.print("사용자 확인 성공!!!");

        String newPwd = resetPasswordProcess(userId);
        ArrayList<String> savePwd = new ArrayList<>();
        savePwd.add("비밀번호초기화");
        savePwd.add(userId);
        savePwd.add(newPwd);

        System.out.println(savePwd);

        boolean isresettingPwd = resetPwdService.resetPassword(savePwd, sock);

        //응답
        if (isresettingPwd) {
          System.out.printf("비밀번호 초기화 성공!");
          // moveStartPage(); // 로그인 성공 시 다음 페이지로 이동
        } else {
          AlertClass.showAlert("초기화 실패", "초기화에 실패했습니다.");
        }


      } else {
        AlertClass.showAlert("초기화 실패", "일치하는 사용자 정보가 없습니다.");
      }
    }
  }


  public static String resetPasswordProcess(String userId) {
    // 새로운 비밀번호를 입력받습니다. 이 부분은 사용자 입력을 받는 화면을 구현하셔야 합니다.
    TextInputDialog dialog = new TextInputDialog();
    dialog.setTitle("비밀번호 초기화");
    dialog.setHeaderText(null);
    dialog.setContentText("새로운 비밀번호를 입력해주세요:");

    Optional<String> result = dialog.showAndWait();

    if (result.isPresent()) {
      String newPassword = result.get();
      System.out.println("새로운 비밀번호: " + newPassword);
      AlertClass.showAlertInfo("비밀번호 초기화", "비밀번호가 초기화되었습니다.");
      return newPassword;
    } else {
      return null; // 사용자가 취소한 경우 null 반환
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


  @FXML
  public void moveIngamePage(ActionEvent event) throws IOException {
    System.out.println("★★★★★★게임 시작★★★★★★★");
//    boolean startButtonSelected = startBtn.isSelected();
//    if (startButtonSelected) {
    ArrayList<String> startSign = new ArrayList<>();
    startSign.add("게임시작");

    //InGame.startGame(startSign, sock);
    // 로그인 서비스 호출하여 인증 수행
    String twoUserStart = InGame.startGame(startSign, sock);
    //응답
    if (twoUserStart.equals("게임시작")) {
      System.out.printf("게임 시작!!!");
      // 로그인 성공 시 다음 페이지로 이동
    }
    if (twoUserStart.equals("상대방을 찾는 중...")){
      AlertClass.showAlert("상대방을 기다리는 중", "상대방을 기다리는 중입니다.");
    }
    else {
      System.out.printf("오류");

    }
  }


//     while(true){
//       InputStream inputStream = sock.getInputStream();
//       DataInputStream dataInputStream = new DataInputStream(inputStream);
//
//
//       String signResult2 = dataInputStream.readUTF();
//
//
//       serverResponse.setText(signResult2);
//     }

    }
//      AlertClass.showAlert("게임시작", "상대방을 찾는 중");

