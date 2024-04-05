module com.example.liveparkgood {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.liveparkgood to javafx.fxml;
    exports com.example.liveparkgood;
}