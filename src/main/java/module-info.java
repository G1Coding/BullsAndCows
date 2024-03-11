module BullsAndCows {
  requires javafx.controls;
  requires javafx.fxml;
  requires org.jetbrains.annotations;


  opens main to javafx.fxml;
  exports main;


}