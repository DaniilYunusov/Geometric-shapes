package com.example.semester_work_2;

import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

import com.first_semester_work.*;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class IntersectionFigureController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane intersection_anchorpane;

    @FXML
    private Button intersection_calculate;

    @FXML
    private MenuItem intersection_circle;

    @FXML
    private SplitMenuButton intersection_figure_1;

    @FXML
    private SplitMenuButton intersection_figure_2;

    @FXML
    private SplitMenuButton intersection_figure_type;

    @FXML
    private MenuItem intersection_ngon;

    @FXML
    private MenuItem intersection_polyline;

    @FXML
    private MenuItem intersection_qgon;

    @FXML
    private MenuItem intersection_rectangle;

    @FXML
    private MenuItem intersection_segment;

    @FXML
    private MenuItem intersection_tgon;

    @FXML
    private MenuItem intersection_trapeze;

    @FXML
    private Button intersection_cancel;

    ArrayList<MenuItem> items_array = new ArrayList<>();

    ArrayList<MenuItem> items_array_2 = new ArrayList<>();

    ArrayList<IShape> shapes_selected_type = new ArrayList<>();

    int figure_index = -1;

    boolean array_figure_1_is_filled_in = false;

    int index_figure_1;

    int index_figure_2;

    ArrayList<Integer> need_fin_ind = new ArrayList<>();

    void zeroing_comboboxes()
    {
        intersection_figure_1.setText("Фигура №1");
        intersection_figure_2.setText("Фигура №2");
        need_fin_ind.clear();
        items_array.clear();
        items_array_2.clear();
        intersection_figure_1.getItems().clear();
        intersection_figure_2.getItems().clear();
    }

    void menuitems_add(int i)
    {
        shapes_selected_type.add(HelloApplication.getFigure(i));
        MenuItem item = new MenuItem(HelloApplication.getFigure(i).toString());
        intersection_figure_1.getItems().add(item);
        items_array.add(item);
    }

    @FXML
    void initialize() {
        intersection_figure_1.setMaxSize(150, 0);
        intersection_figure_2.setMaxSize(150, 0);
        intersection_cancel.setOnAction(actionEvent -> {
            Stage stage = (Stage) intersection_cancel.getScene().getWindow();
            stage.close();
        });
        intersection_ngon.setOnAction(actionEvent -> {
            intersection_figure_type.setText("N-угольник");
            zeroing_comboboxes();
            shapes_selected_type.clear();

            for (int i = 0; i < HelloApplication.getFiguresList().size(); i++) {
                if (HelloApplication.getFigure(i).getClass() == NGon.class){
                    menuitems_add(i);
                    need_fin_ind.add(i);
                }
            }
            this.initialize();
        });
        intersection_segment.setOnAction(actionEvent -> {
            intersection_figure_type.setText("Отрезок");
            zeroing_comboboxes();
            shapes_selected_type.clear();

            for (int i = 0; i < HelloApplication.getFiguresList().size(); i++) {
                if (HelloApplication.getFigure(i) instanceof Segment){
                    menuitems_add(i);
                    need_fin_ind.add(i);
                }
            }
            this.initialize();
        });
        intersection_polyline.setOnAction(actionEvent -> {
            intersection_figure_type.setText("Ломаная");
            zeroing_comboboxes();
            shapes_selected_type.clear();

            for (int i = 0; i < HelloApplication.getFiguresList().size(); i++) {
                if (HelloApplication.getFigure(i) instanceof Polyline){
                    menuitems_add(i);
                    need_fin_ind.add(i);
                }
            }
            this.initialize();
        });
        intersection_circle.setOnAction(actionEvent -> {
            intersection_figure_type.setText("Окружность");
            zeroing_comboboxes();
            shapes_selected_type.clear();

            for (int i = 0; i < HelloApplication.getFiguresList().size(); i++) {
                if (HelloApplication.getFigure(i) instanceof Circle){
                    menuitems_add(i);
                    need_fin_ind.add(i);
                }
            }
            this.initialize();
        });
        intersection_tgon.setOnAction(actionEvent -> {
            intersection_figure_type.setText("Треугольник");
            zeroing_comboboxes();
            shapes_selected_type.clear();

            for (int i = 0; i < HelloApplication.getFiguresList().size(); i++) {
                if (HelloApplication.getFigure(i) instanceof TGon){
                    menuitems_add(i);
                    need_fin_ind.add(i);
                }
            }
            this.initialize();
        });
        intersection_qgon.setOnAction(actionEvent -> {
            intersection_figure_type.setText("Четырехугольник");
            zeroing_comboboxes();
            shapes_selected_type.clear();

            for (int i = 0; i < HelloApplication.getFiguresList().size(); i++) {
                if (HelloApplication.getFigure(i).getClass() == QGon.class){
                    menuitems_add(i);
                    need_fin_ind.add(i);
                }
            }
            this.initialize();
        });
        intersection_rectangle.setOnAction(actionEvent -> {
            intersection_figure_type.setText("Прямоугольник");
            zeroing_comboboxes();
            shapes_selected_type.clear();

            for (int i = 0; i < HelloApplication.getFiguresList().size(); i++) {
                if (HelloApplication.getFigure(i) instanceof Rectangle){
                    menuitems_add(i);
                    need_fin_ind.add(i);
                }
            }
            this.initialize();
        });
        intersection_trapeze.setOnAction(actionEvent -> {
            intersection_figure_type.setText("Трапеция");
            zeroing_comboboxes();
            shapes_selected_type.clear();

            for (int i = 0; i < HelloApplication.getFiguresList().size(); i++) {
                if (HelloApplication.getFigure(i) instanceof Trapeze){
                    menuitems_add(i);
                    need_fin_ind.add(i);
                }
            }
            this.initialize();
        });
        for (int i = 0; i < items_array.size(); i++) {
            int finalI = i;
            items_array.get(i).setOnAction(actionEvent ->
            {
                index_figure_1 = finalI;
                array_figure_1_is_filled_in = true;
                intersection_figure_1.setText(items_array.get(finalI).getText());
                figure_index = finalI;
                items_array_2.clear();
                intersection_figure_2.getItems().clear();
                intersection_figure_2.setText("Фигура №2");
                this.initialize();
            });
        }
        if (array_figure_1_is_filled_in)
        {
            for (int i = 0; i < items_array.size(); i++) {
                if (i != figure_index)
                {
                    MenuItem item_1 = new MenuItem(items_array.get(i).getText());
                    intersection_figure_2.getItems().add(item_1);
                    items_array_2.add(item_1);
                }
            }
            for (int i = 0; i < items_array_2.size(); i++) {
                int finalI = i;
                items_array_2.get(i).setOnAction(actionEvent ->
                {
                    for (int j = 0; j < shapes_selected_type.size(); j++) {
                        if (Objects.equals(shapes_selected_type.get(j).toString(), items_array_2.get(finalI).getText()))
                        {
//                            System.out.println("This is correct if!");
                            index_figure_2 = j;
                        }
                    }
                    intersection_figure_2.setText(items_array_2.get(finalI).getText());
                });
            }
            array_figure_1_is_filled_in = false;
        }
        intersection_calculate.setOnAction(actionEvent -> {
            if (!Objects.equals(intersection_figure_1.getText(), "Фигура №1")
                    && !Objects.equals(intersection_figure_2.getText(), "Фигура №2")
                    && !Objects.equals(intersection_figure_type.getText(), "Тип фигуры"))
            {
                if (HelloApplication.getFigure(need_fin_ind.get(index_figure_1)).cross(HelloApplication.getFigure(need_fin_ind.get(index_figure_2))))
                    HelloController.set_visible("Пересекаются.");
                else
                    HelloController.set_visible("Не пересекаются.");
                for (int i = 0; i < need_fin_ind.size(); i++) {
                    HelloController.add_index_in_arr_red_fig(need_fin_ind.get(i));
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Успех");
                alert.setHeaderText("Проверка на пересечение совершена.");
                alert.setContentText("Проверка на пересечение успешно совершена.");
                alert.showAndWait();
                Stage stage = (Stage) intersection_cancel.getScene().getWindow();
                stage.close();
            }
            else if (Objects.equals(intersection_figure_type.getText(), "Тип фигуры"))
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText("Возникла следующая ошибка: ");
                alert.setContentText("Не выбран тип фигур.");
                alert.showAndWait();
            }
            else if (Objects.equals(intersection_figure_1.getText(), "Фигура №1") ||
                    Objects.equals(intersection_figure_2.getText(), "Фигура №2"))
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText("Возникла следующая ошибка: ");
                alert.setContentText("Не выбран(а/ы) фигур(а/ы)");
                alert.showAndWait();
            }
        });
    }
}
