package com.example.semester_work_2;

import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

import com.first_semester_work.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Add_Figure_Controller {

    @FXML
    private AnchorPane add_anchor_pane;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private VBox add_vbox;

    @FXML
    private Button add_choice_figure;

    @FXML
    private MenuItem add_circle;

    @FXML
    private MenuItem add_ngon;

    @FXML
    private MenuItem add_popyline;

    @FXML
    private MenuItem add_qgon;

    @FXML
    private MenuItem add_rectangle;

    @FXML
    private MenuItem add_segment;

    @FXML
    private MenuItem add_tgon;

    @FXML
    private MenuItem add_trapeze;

    @FXML
    private Button cancel_choice_figure;

    @FXML
    private SplitMenuButton choice_figure;

    @FXML
    private Spinner<Integer> choise_point_num;

    private int num_of_points;


    private static IShape figure;

//    private final int min_count_of_points = 0;


    void visibility_of_the_spinner_points(SpinnerValueFactory<Integer> valueFactory, MenuItem add_figure, boolean flag)
    {
        add_vbox.getChildren().clear();
        valueFactory.setValue(0);
        choise_point_num.setVisible(flag);
        choice_figure.setText(add_figure.getText());
    }

    void output_of_fields_for_recording_coordinates_of_points(int point_num)
    {
        for (int i = 0; i < point_num; i++) {
            Label point = new Label(Integer.toString(i+1) + " точка");
            point.setPadding(new Insets(0, 0, 0, 30));
            Label x = new Label("x: ");
            Label y = new Label("y: ");
            x.setPadding(new Insets(3, 0, 0, 30));
            TextField input_x = new TextField();
            TextField input_y = new TextField();
            HBox coord = new HBox(x, input_x, y, input_y);
            coord.setSpacing(5);
            VBox box = new VBox(point, coord);
//                    box.setSpacing(10);
            add_vbox.getChildren().add(box);
        }
    }

    Point2D[] checking_for_emptiness_and_entering_into_an_array(int point_num) throws Exception {

        Point2D[] array_of_points = new Point2D[point_num];

        for (int i = 0; i < point_num; i++) {
            Node v = add_vbox.getChildren().get(i);
            Node h = ((VBox) v).getChildren().get(1);
            Node t1 = ((HBox) h).getChildren().get(1);
            Node t2 = ((HBox) h).getChildren().get(3);
            String x = ((TextField) t1).getText();
            String y = ((TextField) t2).getText();
//            if (x.isEmpty() || y.isEmpty())
//            {
//                Shake shake_textfield_x = new Shake(t1);
//                Shake shake_textfield_y = new Shake(t2);
//                shake_textfield_x.play_animation();
//                shake_textfield_y.play_animation();
//                return false;
//            }
            Point2D point2D = new Point2D(new double[] {Double.parseDouble(x), Double.parseDouble(y)});
//            mass_of_points.add(point2D);
            array_of_points[i] = point2D;
        }
        return array_of_points;
    }

    Point2D[] array_list_to_mass(ArrayList<Point2D> arrayList)
    {
        Point2D[] p = new Point2D[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            p[i] = arrayList.get(i);
            System.out.println(p[i]);
        }
        return p;
    }

    void entering_the_received_data_into_an_array() throws Exception {
        if (Objects.equals(choice_figure.getText(), "Окружность"))
        {
            Node v = add_vbox.getChildren().get(0);
            Node h = ((VBox) v).getChildren().get(1);
            Node h1 = ((VBox) v).getChildren().get(2);
            Node t1 = ((HBox) h).getChildren().get(1);
            Node t2 = ((HBox) h).getChildren().get(3);
            Node t3 = ((HBox) h1).getChildren().get(1);
            String x_center = ((TextField) t1).getText();
            String y_center = ((TextField) t2).getText();
            String radius = ((TextField) t3).getText();
            Point2D center = new Point2D(new double[] {Double.parseDouble(x_center), Double.parseDouble(y_center)});
            double radius_circle = Double.parseDouble(radius);
            figure = new Circle(center, radius_circle);
        }
        else if (Objects.equals(choice_figure.getText(), "Отрезок"))
        {
            Point2D[] array;
            array = checking_for_emptiness_and_entering_into_an_array(2);
            figure = new Segment(array[0], array[1]);
        }
        else if (Objects.equals(choice_figure.getText(), "Ломаная линия"))
        {
            Point2D[] array;
            array = checking_for_emptiness_and_entering_into_an_array(num_of_points);
            figure = new Polyline(array);
        }
        else if (Objects.equals(choice_figure.getText(), "N-угольник"))
        {
            Point2D[] array;
            array = checking_for_emptiness_and_entering_into_an_array(num_of_points);
            figure = new NGon(array);
        }
        else if (Objects.equals(choice_figure.getText(), "Треугольник"))
        {
            Point2D[] array;
            array = checking_for_emptiness_and_entering_into_an_array(3);
            figure = new TGon(array);
        }
        else if (Objects.equals(choice_figure.getText(), "Четырехугольник"))
        {
            Point2D[] array;
            array = checking_for_emptiness_and_entering_into_an_array(4);
            figure = new QGon(array);
        }
        else if (Objects.equals(choice_figure.getText(), "Прямоугольник"))
        {
            Point2D[] array;
            array = checking_for_emptiness_and_entering_into_an_array(4);
            figure = new Rectangle(array);
        }
        else if (Objects.equals(choice_figure.getText(), "Трапеция"))
        {
            Point2D[] array;
            array = checking_for_emptiness_and_entering_into_an_array(4);
            figure = new Trapeze(array);
        }
//        System.out.println(figure);
    }

    @FXML
    void initialize() {
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,1000);
        valueFactory.setValue(0);
        choise_point_num.setValueFactory(valueFactory);
        choise_point_num.setEditable(true);
        choise_point_num.setVisible(false);
        choise_point_num.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer t1) {
                num_of_points = valueFactory.getValue();
                add_vbox.getChildren().clear();
                output_of_fields_for_recording_coordinates_of_points(num_of_points);
//                System.out.println(add_vbox.getChildren().get(0));
            }
        });
//        System.out.println(add_vbox.getChildren().get(0));
        cancel_choice_figure.setOnAction(actionEvent -> {
            Stage stage = (Stage) add_choice_figure.getScene().getWindow();
            stage.close();
        });
        add_segment.setOnAction(actionEvent -> {
            visibility_of_the_spinner_points(valueFactory, add_segment, false);
            output_of_fields_for_recording_coordinates_of_points(2);
        });
        add_popyline.setOnAction(actionEvent -> {
            visibility_of_the_spinner_points(valueFactory, add_popyline, true);
            output_of_fields_for_recording_coordinates_of_points(num_of_points);
        });
        add_circle.setOnAction(actionEvent -> {
            visibility_of_the_spinner_points(valueFactory, add_circle, false);
            Label center = new Label("Центр");
            center.setPadding(new Insets(0, 0, 0, 30));
            Label x = new Label("x: ");
            Label y = new Label("y: ");
            x.setPadding(new Insets(0, 0, 0, 30));
            Label radius = new Label("Радиус: ");
            radius.setPadding(new Insets(0, 0, 0, 30));
            TextField input_x = new TextField();
            TextField input_y = new TextField();
            TextField input_radius = new TextField();
            HBox coord = new HBox(x, input_x, y, input_y);
            HBox cir_radius = new HBox(radius, input_radius);
            coord.setSpacing(5);
            cir_radius.setSpacing(5);
            VBox box = new VBox(center, coord, cir_radius);
            box.setSpacing(10);
            add_vbox.getChildren().add(box);
        });
        add_ngon.setOnAction(actionEvent -> {
            visibility_of_the_spinner_points(valueFactory, add_ngon, true);
            output_of_fields_for_recording_coordinates_of_points(num_of_points);
        });
        add_tgon.setOnAction(actionEvent -> {
            visibility_of_the_spinner_points(valueFactory, add_tgon, false);
            output_of_fields_for_recording_coordinates_of_points(3);
        });
        add_qgon.setOnAction(actionEvent -> {
            visibility_of_the_spinner_points(valueFactory, add_qgon, false);
            output_of_fields_for_recording_coordinates_of_points(4);
        });
        add_rectangle.setOnAction(actionEvent -> {
            visibility_of_the_spinner_points(valueFactory, add_rectangle, false);
            output_of_fields_for_recording_coordinates_of_points(4);
        });
        add_trapeze.setOnAction(actionEvent -> {
            visibility_of_the_spinner_points(valueFactory, add_trapeze, false);
            output_of_fields_for_recording_coordinates_of_points(4);
        });
        add_choice_figure.setOnAction(actionEvent -> {
            try {
                entering_the_received_data_into_an_array();
                if (!Objects.equals(choice_figure.getText(), "Фигура"))
                {
                    HelloApplication.add_figures(figure);
                    System.out.println(HelloApplication.getFiguresList());
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Успех");
                    alert.setHeaderText("Добавление завершено");
                    alert.setContentText("Добавление успешно завершено");
                    alert.showAndWait();
                    Stage stage = (Stage) add_choice_figure.getScene().getWindow();
                    stage.close();
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Ошибка");
                    alert.setHeaderText("Возникла следующая ошибка: ");
                    alert.setContentText("Не выбран тип фигуры");
                    alert.showAndWait();
                }
            } catch (Exception e) {
//                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText("Возникла следующая ошибка: ");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
//                System.out.println("FUCK");
            }
        });
    }
}
