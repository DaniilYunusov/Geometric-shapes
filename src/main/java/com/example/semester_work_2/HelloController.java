package com.example.semester_work_2;

import java.awt.image.RenderedImage;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javafx.embed.swing.SwingFXUtils;

import com.first_semester_work.*;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class HelloController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button add_figure;

    @FXML
    private Button clear;

    @FXML
    private Button delete_figure;

    @FXML
    private Button intersection_figures;

    @FXML
    private Button move_figure;

    @FXML
    private Canvas painting_canvas;

    @FXML
    private Button save_file;

    @FXML
    private Button save_image;

    @FXML
    private Button square_figure;

    @FXML
    private Button upload_file;

    @FXML
    private Button figure_perimeter;

    @FXML
    private HBox hbox_output_values;

    @FXML
    private TextField information_output_field;

    @FXML
    private Text output_information_about_figure;

    public TextField get_information_output_field()
    {
         return information_output_field;
    }

    public Text get_output_information_about_figure()
    {
        return output_information_about_figure;
    }

    void information_output_field_is_visible()
    {
        information_output_field.setVisible(true);
    }

    void set_text(String s)
    {
        information_output_field.setText(s);
    }

    private static String meaning = "";

    ArrayList<Pattern> patern_list = new ArrayList<>();

    private boolean clean_canvas_flag = false;

    static ArrayList<Integer> arr_of_indexes_of_red_figures = new ArrayList<>();

    public static void clean_arr_red_figures(){
        arr_of_indexes_of_red_figures.clear();
    }

    public static void add_index_in_arr_red_fig(int i){
        arr_of_indexes_of_red_figures.add(i);
    }

    public static void set_visible(String s)
    {
        meaning = s;
    }
//    private static List<IShape> figures = new ArrayList<>();
//
//    public static void add_figures(IShape iShape)
//    {
//        figures.add(iShape);
//    }

    void create_new_table(String title_name, String fxml_file_name)
    {
        FXMLLoader loader = new FXMLLoader(HelloController.class.getResource(fxml_file_name));
//            loader.setLocation(getClass().getResource());
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();

//            Add_Figure_Controller controller = loader.getController();
//            controller.initialize("hello");

        InputStream iconStream = getClass().getResourceAsStream("/images/icon.png");
        stage.getIcons().add(new Image(iconStream));

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title_name);
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    void write_into_file(String text, File file)
    {
        try(FileWriter writer = new FileWriter(file, false))
        {
            writer.write(text);
            writer.append('\n');

            writer.flush();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    void add_into_pattern_list() {
        Pattern pattern_circ = Pattern.compile("Окружность: \\(Center:\\(-?\\w+\\.-?\\w+,-?\\w+\\.-?\\w+\\);Radius:\\w+\\.\\w+\\)");
        Pattern pattern_segment = Pattern.compile("Отрезок: \\[(\\(-?\\w+\\.-?\\w+,-?\\w+\\.-?\\w+\\);?)+]");
        Pattern pattern_polyline = Pattern.compile("Ломаная: \\[(\\(-?\\w+\\.-?\\w+,-?\\w+\\.-?\\w+\\);?)+]");
        Pattern pattern_ngon = Pattern.compile("Многоугольник: \\[(\\(-?\\w+\\.-?\\w+,-?\\w+\\.-?\\w+\\);?)+]");
        Pattern pattern_tgon = Pattern.compile("Треугольник: \\[(\\(-?\\w+\\.-?\\w+,-?\\w+\\.-?\\w+\\);?)+]");
        Pattern pattern_qgon = Pattern.compile("Четырехугольник: \\[(\\(-?\\w+\\.-?\\w+,-?\\w+\\.-?\\w+\\);?)+]");
        Pattern pattern_rectangle = Pattern.compile("Прямоугольник: \\[(\\(-?\\w+\\.-?\\w+,-?\\w+\\.-?\\w+\\);?)+]");
        Pattern pattern_trapeze = Pattern.compile("Трапеция: \\[(\\(-?\\w+\\.-?\\w+,-?\\w+\\.-?\\w+\\);?)+]");
        patern_list.add(pattern_circ);
        patern_list.add(pattern_segment);
        patern_list.add(pattern_polyline);
        patern_list.add(pattern_ngon);
        patern_list.add(pattern_tgon);
        patern_list.add(pattern_qgon);
        patern_list.add(pattern_rectangle);
        patern_list.add(pattern_trapeze);
    }

    boolean check_correct_of_the_file_content(String s, int line_num) throws Exception {
        ArrayList<Double> doubles_list = new ArrayList<>();
        Pattern general = Pattern.compile("-?\\w+\\.-?\\w+");
        for (Pattern pattern : patern_list) {
            Matcher matcher = pattern.matcher(s);
            if (matcher.matches()) {
                if (s.startsWith("Окружность:")) {
                    Matcher matcher1 = general.matcher(s);
                    while (matcher1.find()) {
                        doubles_list.add(Double.parseDouble(s.substring(matcher1.start(), matcher1.end())));
                    }
                    double radius = doubles_list.get(2);
                    Point2D center = new Point2D(new double[]{doubles_list.get(0), doubles_list.get(1)});
                    Circle circle = new Circle(center, radius);
                    HelloApplication.add_figures(circle);
                    doubles_list.clear();
                }
                else if (s.startsWith("Отрезок:")){
                    Point2D[] mass_of_point = mass_of_point_completion(s, general, doubles_list);
                    Segment segment = new Segment(mass_of_point[0], mass_of_point[1]);
                    HelloApplication.add_figures(segment);
                    doubles_list.clear();
                }
                else if (s.startsWith("Ломаная:")){
                    Point2D[] mass_of_point = mass_of_point_completion(s, general, doubles_list);
                    Polyline polyline = new Polyline(mass_of_point);
                    HelloApplication.add_figures(polyline);
                    doubles_list.clear();
                }
                else if (s.startsWith("Многоугольник:")){
                    Point2D[] mass_of_point = mass_of_point_completion(s, general, doubles_list);
                    NGon nGon = new NGon(mass_of_point);
                    HelloApplication.add_figures(nGon);
                    doubles_list.clear();
                }
                else if (s.startsWith("Треугольник:")){
                    Point2D[] mass_of_point = mass_of_point_completion(s, general, doubles_list);
                    TGon tGon = new TGon(mass_of_point);
                    HelloApplication.add_figures(tGon);
                    doubles_list.clear();
                }
                else if (s.startsWith("Четырехугольник:")){
                    Point2D[] mass_of_point = mass_of_point_completion(s, general, doubles_list);
                    QGon qGon = new QGon(mass_of_point);
                    HelloApplication.add_figures(qGon);
                    doubles_list.clear();
                }
                else if (s.startsWith("Прямоугольник:")){
                    Point2D[] mass_of_point = mass_of_point_completion(s, general, doubles_list);
                    Rectangle rectangle = new Rectangle(mass_of_point);
                    HelloApplication.add_figures(rectangle);
                    doubles_list.clear();
                }
                else if (s.startsWith("Трапеция:")){
                    Point2D[] mass_of_point = mass_of_point_completion(s, general, doubles_list);
                    Trapeze trapeze = new Trapeze(mass_of_point);
                    HelloApplication.add_figures(trapeze);
                    doubles_list.clear();
                }
                return true;
            }
        }
        Pattern first_string = Pattern.compile("\\w+");
        Matcher matcher_first_string = first_string.matcher(s);
        if (matcher_first_string.matches() && line_num == 0)
            return true;
        return s.isEmpty();
    }

    Point2D[] mass_of_point_completion(String s, Pattern general, ArrayList<Double> doubles_list) throws Exception {
        Matcher matcher1 = general.matcher(s);
        while (matcher1.find()) {
            doubles_list.add(Double.parseDouble(s.substring(matcher1.start(), matcher1.end())));
        }
        Point2D[] mass_of_point = new Point2D[doubles_list.size()/2];
        for (int i = 0, j = 0; i < doubles_list.size(); i += 2, j++) {
            Point2D point2D = new Point2D(new double[]{doubles_list.get(i), doubles_list.get(i + 1)});
            mass_of_point[j] = point2D;
        }
        return mass_of_point;
    }

    @FXML
    void initialize() {
//        add_figure.setOnAction(new EventHandler<ActionEvent>() {
//
//            @Override
//            public void handle(ActionEvent event) {
////                Stage stage1 = new Stage();
//                add_figure.setText("You've clicked!");
//            }
//        });
        if (!clean_canvas_flag){
            information_output_field.setEditable(false);
            information_output_field.setVisible(false);
            output_information_about_figure.setVisible(false);
        }
        add_figure.setOnAction(actionEvent -> {
//            add_figure.getScene().getWindow().hide();
            information_output_field.setVisible(false);
            output_information_about_figure.setVisible(false);
            create_new_table("Создание новой фигуры", "add-figure.fxml");
            this.initialize();
        });

        move_figure.setOnAction(actionEvent -> {
            information_output_field.setVisible(false);
            output_information_about_figure.setVisible(false);
            create_new_table("Движение фигуры", "move-figure.fxml");
            clean_canvas_flag = false;
            this.initialize();
        });

        delete_figure.setOnAction(actionEvent -> {
            information_output_field.setVisible(false);
            output_information_about_figure.setVisible(false);
            create_new_table("Удаление фигуры", "delete-figure.fxml");
            clean_canvas_flag = false;
            this.initialize();
        });

        clear.setOnAction(actionEvent -> {
            information_output_field.setVisible(false);
            output_information_about_figure.setVisible(false);
            if (!HelloApplication.getFiguresList().isEmpty())
            {
                HelloApplication.getFiguresList().clear();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Успех");
                alert.setHeaderText("Очистка поля завершена.");
                alert.setContentText("Очистка поля успешно завершена.");
                alert.showAndWait();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText("Возникла следующая ошибка: ");
                alert.setContentText("Очищать нечего. Поле и так пустое.");
                alert.showAndWait();
            }

            System.out.println(HelloApplication.getFiguresList());
            clean_canvas_flag = false;
            this.initialize();
        });

        save_file.setOnAction(actionEvent -> {
            information_output_field.setVisible(false);
            output_information_about_figure.setVisible(false);
            if (!HelloApplication.getFiguresList().isEmpty())
            {
                Stage stage = new Stage();
                FileChooser fileChooser = new FileChooser();

                fileChooser.setTitle("Сохранение файла");
                FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
                fileChooser.getExtensionFilters().add(extFilter);

                File file = fileChooser.showSaveDialog(stage);

                if (file != null) {
                    String content = HelloApplication.getFiguresList().size() + "\n";
                    for (int i = 0; i < HelloApplication.getFiguresList().size(); i++) {
                        content = content.concat(HelloApplication.getFigure(i).toString()) + "\n";
                    }
                    write_into_file(content, file);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Успех");
                    alert.setHeaderText("Сохранение файла завершено.");
                    alert.setContentText("Сохранение файла успешно завершено.");
                    alert.showAndWait();
                }
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText("Возникла следующая ошибка: ");
                alert.setContentText("Сохранять нечего. Фигур нет.");
                alert.showAndWait();
            }
        });
//        information_output_field.setOnKeyTyped(Event::consume);
        square_figure.setOnAction(actionEvent -> {
            create_new_table("Площадь фигуры", "square-figure.fxml");
            if (!Objects.equals(meaning, ""))
            {
                information_output_field.setVisible(true);
                output_information_about_figure.setVisible(true);
                output_information_about_figure.setText("Площадь:");
                information_output_field.setText(meaning);
            }
            this.initialize();
        });
        figure_perimeter.setOnAction(actionEvent -> {
            create_new_table("Периметр фигуры", "perimeter-figure.fxml");
            if (!Objects.equals(meaning, ""))
            {
                information_output_field.setVisible(true);
                output_information_about_figure.setVisible(true);
                output_information_about_figure.setText("Периметр:");
                information_output_field.setText(meaning);
            }
            this.initialize();
        });
        intersection_figures.setOnAction(actionEvent -> {
            create_new_table("Пересечение фигур", "intersection-figure.fxml");
            if (!Objects.equals(meaning, ""))
            {
                information_output_field.setVisible(true);
                output_information_about_figure.setVisible(true);
                output_information_about_figure.setText("Пересечение:");
                information_output_field.setText(meaning);
            }
            this.initialize();
        });
        upload_file.setOnAction(actionEvent -> {
            information_output_field.setVisible(false);
            output_information_about_figure.setVisible(false);
            HelloApplication.getFiguresList().clear();
            clean_canvas_flag = false;

            Stage stage = new Stage();

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Сохранить файл.");
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");

            File file = fileChooser.showOpenDialog(stage);
            if (file != null)
            {
                fileChooser.setInitialDirectory(file.getParentFile());

                try {
                    FileReader fr = new FileReader(file);
                    BufferedReader reader = new BufferedReader(fr);
                    String line = reader.readLine();
                    int line_num = 0;

                    add_into_pattern_list();
                    while (line != null)
                    {
                        if (!check_correct_of_the_file_content(line, line_num))
                        {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Ошибка");
                            alert.setHeaderText("Возникла следующая ошибка: ");
                            alert.setContentText("Содержимое файла не является набором фигур с координатами.");
                            alert.showAndWait();
                            break;
                        }
                        line_num++;
                        line = reader.readLine();
                    }
                    System.out.println(HelloApplication.getFiguresList());
                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Ошибка");
                    alert.setHeaderText("Возникла следующая ошибка: ");
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();
                }
            }
            this.initialize();
        });
        save_image.setOnAction(actionEvent -> {
            Stage stage = new Stage();
            FileChooser fileChooser = new FileChooser();

            fileChooser.setTitle("Сохранение файла");
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png");
            fileChooser.getExtensionFilters().add(extFilter);

            File file = fileChooser.showSaveDialog(stage);
            if (file != null){
                try {
                    WritableImage writableImage = new WritableImage((int) painting_canvas.getWidth(), (int) painting_canvas.getHeight());
                    painting_canvas.snapshot(null, writableImage);
                    ImageIO.write(SwingFXUtils.fromFXImage(writableImage, null), "png", file);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Успех");
                    alert.setHeaderText("Сохранение изображения завершено.");
                    alert.setContentText("Сохранение изображения успешно завершено.");
                    alert.showAndWait();

                } catch (IOException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Ошибка");
                    alert.setHeaderText("Возникла следующая ошибка: ");
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();
                }
            }
        });
        GraphicsContext gc = painting_canvas.getGraphicsContext2D();
        if (!clean_canvas_flag)
            gc.clearRect(0, 0, painting_canvas.getWidth(), painting_canvas.getHeight());
        clean_canvas_flag = true;
        gc.setStroke(Color.GRAY);
        gc.strokeLine(painting_canvas.getWidth() / 2, 0, painting_canvas.getWidth() / 2, painting_canvas.getHeight());
        gc.strokeLine(0, painting_canvas.getHeight() / 2, painting_canvas.getWidth(), painting_canvas.getHeight() / 2);
        gc.setStroke(Color.BLACK);
        for (int i = 0; i < HelloApplication.getFiguresList().size(); i++) {
//            System.out.println(arr_of_indexes_of_red_figures); //
            if (arr_of_indexes_of_red_figures.contains(i)){
                gc.setStroke(Color.RED);
            }
            if (HelloApplication.getFigure(i) instanceof Segment){
                Segment s = (Segment) HelloApplication.getFigure(i);
                gc.strokeLine(painting_canvas.getWidth() / 2 + s.getStart().getX(0),
                        painting_canvas.getHeight() / 2 - s.getStart().getX(1),
                        painting_canvas.getWidth() / 2 + s.getFinish().getX(0),
                        painting_canvas.getHeight() / 2 - s.getFinish().getX(1));
            }
            if (HelloApplication.getFigure(i) instanceof Polyline){
                Polyline p = (Polyline) HelloApplication.getFigure(i);
                for (int j = 0; j < p.getN() - 1; j++) {
                    gc.strokeLine(painting_canvas.getWidth() / 2 + p.getP(j).getX(0),
                            painting_canvas.getHeight() / 2 - p.getP(j).getX(1),
                            painting_canvas.getWidth() / 2 + p.getP(j+1).getX(0),
                            painting_canvas.getHeight() / 2 - p.getP(j+1).getX(1));
                }
            }
            if (HelloApplication.getFigure(i) instanceof Circle){
                Circle c = (Circle) HelloApplication.getFigure(i);
                gc.strokeOval(painting_canvas.getWidth() / 2 + c.getP().getX(0) - c.getR(),
                        painting_canvas.getHeight() / 2 - c.getP().getX(1) - c.getR(),
                        c.getR() * 2, c.getR() * 2);
            }
            if (HelloApplication.getFigure(i) instanceof NGon){
                NGon n = (NGon) HelloApplication.getFigure(i);
                for (int j = 0; j < n.getN() - 1; j++) {
                    gc.strokeLine(painting_canvas.getWidth() / 2 + n.getP(j).getX(0),
                            painting_canvas.getHeight() / 2 - n.getP(j).getX(1),
                            painting_canvas.getWidth() / 2 + n.getP(j+1).getX(0),
                            painting_canvas.getHeight() / 2 - n.getP(j+1).getX(1));
                }
                gc.strokeLine(painting_canvas.getWidth() / 2 + n.getP(n.getN() - 1).getX(0),
                        painting_canvas.getHeight() / 2 - n.getP(n.getN() - 1).getX(1),
                        painting_canvas.getWidth() / 2 + n.getP(0).getX(0),
                        painting_canvas.getHeight() / 2 - n.getP(0).getX(1));
            }
            gc.setStroke(Color.BLACK);
        }
        arr_of_indexes_of_red_figures.clear();
    }
}