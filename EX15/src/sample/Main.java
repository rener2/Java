package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class.
 */
public class Main extends Application {

    /**
     * X axis size.
     */
    private final int xSize = 720;

    /**
     * Y axis size.
     */
    private final int ySize = 480;

    /**
     * X axis distance between the
     * goat and the mine.
     * @param primaryStage primary stage of the window.
     * @throws Exception for exception handling.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        Scene scene = new Scene(root, xSize, ySize);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    /**
     * Main method.
     * @param args for console commands.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
