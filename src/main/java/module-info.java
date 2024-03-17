module BullsAndCows {
  requires javafx.controls;
  requires javafx.fxml;
  requires java.sql;


  opens main to javafx.fxml;
  exports main;

  opens client to javafx.fxml;
  exports client;
  exports client.message;
  opens client.message to javafx.fxml;

}