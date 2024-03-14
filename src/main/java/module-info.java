module BullsAndCows {
  requires javafx.controls;
  requires javafx.fxml;
  requires org.jetbrains.annotations;
  requires java.sql;


  opens main to javafx.fxml;
  exports main;


}