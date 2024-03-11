module org.example.bullsandcows {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.bullsandcows to javafx.fxml;
    exports org.example.bullsandcows;
}