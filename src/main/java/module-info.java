module com.example.demokurs {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;


    opens com.example.demokurs to javafx.fxml;
    exports com.example.demokurs;
    exports com.example.demokurs.Controllers;
    opens com.example.demokurs.Controllers to javafx.fxml;
    exports com.example.demokurs.Repository;
    opens com.example.demokurs.Repository to javafx.fxml;
    exports com.example.demokurs.Entity;
    opens com.example.demokurs.Entity to javafx.fxml;
    exports com.example.demokurs.Services;
    opens com.example.demokurs.Services to javafx.fxml;
    exports com.example.demokurs.Exception;
    opens com.example.demokurs.Exception to javafx.fxml;
}