package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Main class.
 */
public class Main extends Application {

    /**
     * Amount of boxes in layout.
     */
    public static final int BOX_AMOUNT = 10;
    /**
     * Layout size in pixels.
     */
    public static final int LAYOUT_SIZE = 400;

    /**
     * Start method.
     * @param primaryStage GUI window.
     * @throws Exception handling.
     */
    @Override
    public final void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("EX11");

        Button button = new Button();
        button.setText("Click me!");
        TextField input = new TextField();
        Label userLabel = new Label();
        button.setOnAction(e -> userLabel.setText(input.getText()));

        VBox layout = new VBox(BOX_AMOUNT);
        layout.getChildren().addAll(input, button, userLabel);
        Scene scene = new Scene(layout, LAYOUT_SIZE, LAYOUT_SIZE);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Main method.
     * @param args arguments for launch.
     */
    public static void main(String[] args) {
        launch(args);


    }

}
