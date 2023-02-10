package com.example.semester_work_2;

import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.stage.Stage;

public class DeleteFigureController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button delete_cancel;

    @FXML
    private Button delete_figure;

    @FXML
    private SplitMenuButton delete_split_menu_button_figure;

    ArrayList<MenuItem> items_array = new ArrayList<>();

    int figure_index;

    @FXML
    void initialize() {
        delete_split_menu_button_figure.setMaxSize(150, 0);
        for (int i = 0; i < HelloApplication.getFiguresList().size(); i++) {
            MenuItem item = new MenuItem(HelloApplication.getFigure(i).toString());
            delete_split_menu_button_figure.getItems().add(item);
            items_array.add(item);
        }
        for (int i = 0; i < items_array.size(); i++) {
            int finalI = i;
            items_array.get(i).setOnAction(actionEvent ->
            {
                delete_split_menu_button_figure.setText(items_array.get(finalI).getText());
                figure_index = finalI;
            });
        }
        delete_cancel.setOnAction(actionEvent -> {
            Stage stage = (Stage) delete_cancel.getScene().getWindow();
            stage.close();
        });
        delete_figure.setOnAction(actionEvent -> {
            if (!Objects.equals(delete_split_menu_button_figure.getText(), "Фигура"))
            {
                HelloApplication.getFiguresList().remove(figure_index);
                items_array.remove(figure_index);
                delete_split_menu_button_figure.getItems().remove(figure_index);
                delete_split_menu_button_figure.setText("Фигура");

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Успех");
                alert.setHeaderText("Удаление завершено.");
                alert.setContentText("Удаление успешно завершено.");
                alert.showAndWait();
                Stage stage = (Stage) delete_split_menu_button_figure.getScene().getWindow();
                stage.close();
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText("Возникла следующая ошибка:");
                alert.setContentText("Фигура не выбрана.");
                alert.showAndWait();
                System.out.println(HelloApplication.getFiguresList());
            }
        });
    }
}
