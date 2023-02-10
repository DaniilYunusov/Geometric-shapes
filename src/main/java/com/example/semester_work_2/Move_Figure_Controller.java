package com.example.semester_work_2;

import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

import com.first_semester_work.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Move_Figure_Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button move_cancel;

    @FXML
    private Button move_movement;

    @FXML
    private MenuItem move_rotation;

    @FXML
    private MenuItem move_shift;

    @FXML
    private SplitMenuButton move_split_menu_button_figure;

    @FXML
    private SplitMenuButton move_split_menu_button_type_movement;

    @FXML
    private MenuItem move_symmetry;

    @FXML
    private VBox move_vbox;

    ArrayList<MenuItem> items_array = new ArrayList<>();

    int figure_index;

    double angle_of_rotation;

    Point2D shift_vector;

    int symmetry_axis;

    void getting_the_entering_data() throws Exception {
        if (Objects.equals(move_split_menu_button_type_movement.getText(), "Сдвиг"))
        {
            Node v = move_vbox.getChildren().get(1);
            Node h1 = ((HBox) v).getChildren().get(1);
            Node h2 = ((HBox) v).getChildren().get(3);
            String x_vector = ((TextField) h1).getText();
            String y_vector = ((TextField) h2).getText();
            double[] k = new double[]{Double.parseDouble(x_vector), Double.parseDouble(y_vector)};
            shift_vector = new Point2D(new double[]{Double.parseDouble(x_vector), Double.parseDouble(y_vector)});
        }
        else if (Objects.equals(move_split_menu_button_type_movement.getText(), "Поворот"))
        {
            Node v = move_vbox.getChildren().get(0);
            Node h1 = ((HBox) v).getChildren().get(1);
            String angle = ((TextField) h1).getText();
            angle_of_rotation = Double.parseDouble(angle);
        }
        else if (Objects.equals(move_split_menu_button_type_movement.getText(), "Симметрия"))
        {
            Node v = move_vbox.getChildren().get(0);
            Node h1 = ((HBox) v).getChildren().get(1);
            String axis = ((Spinner<String>) h1).getValue();
            if (Objects.equals(axis, "x"))
                symmetry_axis = 0;
            else if (Objects.equals(axis, "y"))
                symmetry_axis = 1;
        }
    }

    void changing_figure_in_main_array(String s) throws Exception {
        if (Objects.equals(s, "shift"))
        {
            if (HelloApplication.getFigure(figure_index).getClass() == Segment.class)
                HelloApplication.getFiguresList().set(figure_index, (Segment) HelloApplication.getFigure(figure_index).shift(shift_vector));
            else if (HelloApplication.getFigure(figure_index).getClass() == Polyline.class)
                HelloApplication.getFiguresList().set(figure_index, (Polyline) HelloApplication.getFigure(figure_index).shift(shift_vector));
            else if (HelloApplication.getFigure(figure_index).getClass() == Circle.class)
                HelloApplication.getFiguresList().set(figure_index, (Circle) HelloApplication.getFigure(figure_index).shift(shift_vector));
            else if (HelloApplication.getFigure(figure_index).getClass() == NGon.class)
                HelloApplication.getFiguresList().set(figure_index, (NGon) HelloApplication.getFigure(figure_index).shift(shift_vector));
            else if (HelloApplication.getFigure(figure_index).getClass() == TGon.class)
                HelloApplication.getFiguresList().set(figure_index, (TGon) HelloApplication.getFigure(figure_index).shift(shift_vector));
            else if (HelloApplication.getFigure(figure_index).getClass() == QGon.class)
                HelloApplication.getFiguresList().set(figure_index, (QGon) HelloApplication.getFigure(figure_index).shift(shift_vector));
            else if (HelloApplication.getFigure(figure_index).getClass() == Rectangle.class)
                HelloApplication.getFiguresList().set(figure_index, (Rectangle) HelloApplication.getFigure(figure_index).shift(shift_vector));
            else if (HelloApplication.getFigure(figure_index).getClass() == Trapeze.class)
                HelloApplication.getFiguresList().set(figure_index, (Trapeze) HelloApplication.getFigure(figure_index).shift(shift_vector));
        }
        else if (Objects.equals(s, "rot")){
            if (HelloApplication.getFigure(figure_index).getClass() == Segment.class)
                HelloApplication.getFiguresList().set(figure_index, (Segment) HelloApplication.getFigure(figure_index).rot(angle_of_rotation));
            else if (HelloApplication.getFigure(figure_index).getClass() == Polyline.class)
                HelloApplication.getFiguresList().set(figure_index, (Polyline) HelloApplication.getFigure(figure_index).rot(angle_of_rotation));
            else if (HelloApplication.getFigure(figure_index).getClass() == Circle.class)
                HelloApplication.getFiguresList().set(figure_index, (Circle) HelloApplication.getFigure(figure_index).rot(angle_of_rotation));
            else if (HelloApplication.getFigure(figure_index).getClass() == NGon.class)
                HelloApplication.getFiguresList().set(figure_index, (NGon) HelloApplication.getFigure(figure_index).rot(angle_of_rotation));
            else if (HelloApplication.getFigure(figure_index).getClass() == TGon.class)
                HelloApplication.getFiguresList().set(figure_index, (TGon) HelloApplication.getFigure(figure_index).rot(angle_of_rotation));
            else if (HelloApplication.getFigure(figure_index).getClass() == QGon.class)
                HelloApplication.getFiguresList().set(figure_index, (QGon) HelloApplication.getFigure(figure_index).rot(angle_of_rotation));
            else if (HelloApplication.getFigure(figure_index).getClass() == Rectangle.class)
                HelloApplication.getFiguresList().set(figure_index, (Rectangle) HelloApplication.getFigure(figure_index).rot(angle_of_rotation));
            else if (HelloApplication.getFigure(figure_index).getClass() == Trapeze.class)
                HelloApplication.getFiguresList().set(figure_index, (Trapeze) HelloApplication.getFigure(figure_index).rot(angle_of_rotation));
        }
        else if (Objects.equals(s, "symAxis"))
        {
            if (HelloApplication.getFigure(figure_index).getClass() == Segment.class)
                HelloApplication.getFiguresList().set(figure_index, (Segment) HelloApplication.getFigure(figure_index).symAxis(symmetry_axis));
            else if (HelloApplication.getFigure(figure_index).getClass() == Polyline.class)
                HelloApplication.getFiguresList().set(figure_index, (Polyline) HelloApplication.getFigure(figure_index).symAxis(symmetry_axis));
            else if (HelloApplication.getFigure(figure_index).getClass() == Circle.class)
                HelloApplication.getFiguresList().set(figure_index, (Circle) HelloApplication.getFigure(figure_index).symAxis(symmetry_axis));
            else if (HelloApplication.getFigure(figure_index).getClass() == NGon.class)
                HelloApplication.getFiguresList().set(figure_index, (NGon) HelloApplication.getFigure(figure_index).symAxis(symmetry_axis));
            else if (HelloApplication.getFigure(figure_index).getClass() == TGon.class)
                HelloApplication.getFiguresList().set(figure_index, (TGon) HelloApplication.getFigure(figure_index).symAxis(symmetry_axis));
            else if (HelloApplication.getFigure(figure_index).getClass() == QGon.class)
                HelloApplication.getFiguresList().set(figure_index, (QGon) HelloApplication.getFigure(figure_index).symAxis(symmetry_axis));
            else if (HelloApplication.getFigure(figure_index).getClass() == Rectangle.class)
                HelloApplication.getFiguresList().set(figure_index, (Rectangle) HelloApplication.getFigure(figure_index).symAxis(symmetry_axis));
            else if (HelloApplication.getFigure(figure_index).getClass() == Trapeze.class)
                HelloApplication.getFiguresList().set(figure_index, (Trapeze) HelloApplication.getFigure(figure_index).symAxis(symmetry_axis));
        }
    }

    void movement_figure() throws Exception {
        if (Objects.equals(move_split_menu_button_type_movement.getText(), "Сдвиг") &&
                !Objects.equals(move_split_menu_button_figure.getText(), "Фигура"))
        {
            changing_figure_in_main_array("shift");
//            HelloApplication.getFiguresList().set(figure_index, (IShape) HelloApplication.getFigure(figure_index).shift(shift_vector));
        }
        else if (Objects.equals(move_split_menu_button_type_movement.getText(), "Поворот") &&
                !Objects.equals(move_split_menu_button_figure.getText(), "Фигура"))
        {
            changing_figure_in_main_array("rot");
//            HelloApplication.getFiguresList().set(figure_index, (IShape) HelloApplication.getFigure(figure_index).rot(angle_of_rotation));
        }
        else if (Objects.equals(move_split_menu_button_type_movement.getText(), "Симметрия") &&
                !Objects.equals(move_split_menu_button_figure.getText(), "Фигура"))
        {
            changing_figure_in_main_array("symAxis");
//            HelloApplication.getFiguresList().set(figure_index, (IShape) HelloApplication.getFigure(figure_index).symAxis(symmetry_axis));
        }
    }

    void alert_error(String error_message)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка");
        alert.setHeaderText("Возникла следующая ошибка: ");
        alert.setContentText(error_message);
        alert.showAndWait();
    }

    @FXML
    void initialize() {
        move_split_menu_button_figure.setMaxSize(150, 0);
        for (int i = 0; i < HelloApplication.getFiguresList().size(); i++) {
            MenuItem item = new MenuItem(HelloApplication.getFigure(i).toString());
            move_split_menu_button_figure.getItems().add(item);
            items_array.add(item);
        }
        //        move_split_menu_button_figure();
        move_cancel.setOnAction(actionEvent -> {
            Stage stage = (Stage) move_cancel.getScene().getWindow();
            stage.close();
        });
        for (int i = 0; i < items_array.size(); i++) {
            int finalI = i;
            items_array.get(i).setOnAction(actionEvent ->
            {
                move_split_menu_button_figure.setText(items_array.get(finalI).getText());
                figure_index = finalI;
            });
        }
        move_shift.setOnAction(actionEvent -> {
            move_vbox.getChildren().clear();
            move_split_menu_button_type_movement.setText(move_shift.getText());
            Label vector = new Label("Вектор сдвига");
            Label x = new Label("x: ");
            Label y = new Label("y: ");
            x.setPadding(new Insets(3, 0, 0, 0));
            y.setPadding(new Insets(3, 0, 0, 0));
            TextField x_input = new TextField();
            TextField y_input = new TextField();
            x_input.setPrefWidth(30);
            y_input.setPrefWidth(30);
            HBox coord = new HBox(x, x_input, y, y_input);
            coord.setSpacing(5);
            move_vbox.getChildren().add(vector);
            move_vbox.getChildren().add(coord);
        });
        move_rotation.setOnAction(actionEvent -> {
            move_vbox.getChildren().clear();
            move_split_menu_button_type_movement.setText(move_rotation.getText());
            Label rotation = new Label("Угол поворота: ");
            rotation.setPadding(new Insets(3, 0, 0, 0));
            TextField rotation_input = new TextField();
            rotation_input.setPrefWidth(30);
            HBox rot_hbox = new HBox(rotation, rotation_input);
            rot_hbox.setSpacing(5);
            move_vbox.getChildren().add(rot_hbox);
        });
        move_symmetry.setOnAction(actionEvent -> {
            move_vbox.getChildren().clear();
            move_split_menu_button_type_movement.setText(move_symmetry.getText());
            ObservableList<String> axis = FXCollections.observableArrayList("x", "y");
            Label axis_figure = new Label("Ось симметрии: ");
            axis_figure.setPadding(new Insets(3, 0, 0, 0));
            Spinner<String> spinner = new Spinner<>();
            spinner.setPrefWidth(60);
            SpinnerValueFactory<String> valueFactory = new SpinnerValueFactory.ListSpinnerValueFactory<String>(axis);
            valueFactory.setValue("x");
            spinner.setValueFactory(valueFactory);
            HBox axis_fig = new HBox(axis_figure, spinner);
            move_vbox.getChildren().add(axis_fig);
        });
        move_movement.setOnAction(actionEvent -> {
            try {
                getting_the_entering_data();
                movement_figure();
                if (Objects.equals(move_split_menu_button_figure.getText(), "Фигура"))
                {
                    alert_error("Не выбрана фигура.");
                }
                else if (Objects.equals(move_split_menu_button_type_movement.getText(), "Тип перемещения"))
                {
                    alert_error("Не выбран тип перемещения.");
                }
                else if (!Objects.equals(move_split_menu_button_figure.getText(), "Фигура") &&
                        !Objects.equals(move_split_menu_button_type_movement.getText(), "Тип перемещения"))
                {
                    System.out.println(HelloApplication.getFiguresList());

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Успех");
                    alert.setHeaderText("Перемещение завершено.");
                    alert.setContentText("Перемещение успешно завершено.");
                    alert.showAndWait();
                    Stage stage = (Stage) move_split_menu_button_type_movement.getScene().getWindow();
                    stage.close();
                }
            } catch (Exception e) {
                alert_error(e.getMessage());
            }
        });
    }
}
