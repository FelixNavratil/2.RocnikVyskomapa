module com.example.realnetahlevyskomapa {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.realnetahlevyskomapa to javafx.fxml;
    exports com.example.realnetahlevyskomapa;
}