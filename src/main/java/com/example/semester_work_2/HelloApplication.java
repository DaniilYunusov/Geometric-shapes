package com.example.semester_work_2;

import com.first_semester_work.IShape;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class HelloApplication extends Application {

    private static ArrayList<IShape> figures = new ArrayList<>();

    public static void add_figures(IShape iShape)
    {
        figures.add(iShape);
    }

    public static List<IShape> getFiguresList()
    {
        return figures;
    }

    public static IShape getFigure(int i)
    {
        return figures.get(i);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Геометрические фигуры");
        stage.setScene(scene);

        InputStream iconStream = getClass().getResourceAsStream("/images/icon.png");
        stage.getIcons().add(new Image(iconStream));

        stage.show();
    }

    public static void main(String[] args) {
//        Application.launch(args);
        launch();
    }
}