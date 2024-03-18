package client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;

public class MainClass extends Application {
    public static Socket sock;
    public static String gameStartSign = "sdjfsd";

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(client.Controller.class.getResource("start.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 1000, 650);
        stage.setTitle("BullsAndCows Game");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws Exception {
        sock = new Socket("192.168.42.118", 7979);
        System.out.printf("소켓 연결 완료");

        launch(args);

        try {
            Thread.sleep(3000000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}