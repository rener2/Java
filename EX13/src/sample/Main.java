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
     * Layout window x size.
     */
    private final int layoutXSize = 600;
    /**
     * Layout window x size.
     */
    private final int layoutYSize = 400;

    /**
     * Start method.
     * Starts the main window.
     * @param primaryStage  primary stage.
     * @throws Exception for exception handling.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Clicker");
        Scene scene = new Scene(root, layoutXSize, layoutYSize);
        scene.getStylesheets().addAll(this.getClass().getResource("application.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
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
