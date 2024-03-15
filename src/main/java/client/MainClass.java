package client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;

public class MainClass extends Application {




  @Override
  public void start(Stage stage) throws Exception {
    Socket sock = new Socket("192.168.0.23", 7979);

    FXMLLoader fxmlLoader = new FXMLLoader(client.Controller.class.getResource("start.fxml"));
    Controller controller = new Controller(sock);
    fxmlLoader.setController(controller);


    Parent root = fxmlLoader.load();
    Scene scene = new Scene(root, 1000, 650);
    stage.setTitle("BullsAndCows Game");
    stage.setScene(scene);
    stage.show();

  }
  public static void main(String[] args) throws IOException {

    launch(args);
  }
}