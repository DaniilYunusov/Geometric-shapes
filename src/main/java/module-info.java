module com.example.semester_work_2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.swing;


    opens com.example.semester_work_2 to javafx.fxml;
    exports com.example.semester_work_2;
}