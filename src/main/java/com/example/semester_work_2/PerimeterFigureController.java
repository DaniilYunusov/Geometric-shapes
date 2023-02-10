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

public class PerimeterFigureController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button perimeter_cancel;

    @FXML
    private Button perimeter_figure;

    @FXML
    private SplitMenuButton perimeter_split_menu_button_figure;

    ArrayList<MenuItem> items_array = new ArrayList<>();

    int figure_index;

    @FXML
    void initialize() {
        perimeter_split_menu_button_figure.setMaxSize(150, 0);
        for (int i = 0; i < HelloApplication.getFiguresList().size(); i++) {
            MenuItem item = new MenuItem(HelloApplication.getFigure(i).toString());
            perimeter_split_menu_button_figure.getItems().add(item);
            items_array.add(item);
        }
        for (int i = 0; i < items_array.size(); i++) {
            int finalI = i;
            items_array.get(i).setOnAction(actionEvent ->
            {
                perimeter_split_menu_button_figure.setText(items_array.get(finalI).getText());
                figure_index = finalI;
            });
        }
        perimeter_cancel.setOnAction(actionEvent -> {
            Stage stage = (Stage) perimeter_cancel.getScene().getWindow();
            stage.close();
        });
        perimeter_figure.setOnAction(actionEvent -> {
            if (!Objects.equals(perimeter_split_menu_button_figure.getText(), "Фигура"))
            {
                try {
                    HelloController.set_visible(Double.toString(HelloApplication.getFigure(figure_index).length()));
                    HelloController.add_index_in_arr_red_fig(figure_index);
                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Ошибка");
                    alert.setHeaderText("Возникла следующая ошибка: ");
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Успех");
                alert.setHeaderText("Периметр подсчитан.");
                alert.setContentText("Периметр успешно подсчитан.");
                alert.showAndWait();
                Stage stage = (Stage) perimeter_cancel.getScene().getWindow();
                stage.close();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText("Возникла следующая ошибка: ");
                alert.setContentText("Фигура не выбрана.");
                alert.showAndWait();
            }
        });
    }
}
