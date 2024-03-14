package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

//Application 클래스를 확장하여 JavaFX 애플리케이션을 시작합니다.
public class MainClass extends Application {
  @Override
  public void start(Stage stage) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(MainClass.class.getResource("start.fxml"));
    Scene scene = new Scene(fxmlLoader.load(), 320, 240);
    stage.setTitle("Hello!");
    stage.setScene(scene);
    stage.show();
  }
/*
*  start 메소드는 JavaFX 애플리케이션의 시작 시에 호출됩니다.
* 이 메소드 내에서는 FXMLLoader를 사용하여 FXML 파일("chartFXML.fxml")을 로드하고,
*  해당 파일에서 정의된 GUI 구조를 가져와 Scene 객체를 생성합니다.
* 마지막으로, Stage 객체에 이 Scene을 설정하고 화면에 표시합니다.
*/
  public static void main(String[] args) {
    launch();
    System.out.printf("dsfsdf");
  }

}
