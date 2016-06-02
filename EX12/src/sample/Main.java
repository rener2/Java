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
     * Scene size.
     */
    private final int xSize = 300;

    /**
     * Scene size.
     */
    private final int ySize = 275;

    /**
     * Start method.
     * @param primaryStage Stage
     * @throws Exception for exceptions
     */
    @Override
    public final void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, xSize, ySize));
        primaryStage.show();

    }

    /**
     * Main method.
     * @param args for console commands
     */
    public static void main(String[] args) {
        launch(args);
    }
}
