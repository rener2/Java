package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;


/**
 * Controller class.
 */
public class Controller {

    /**
     * Clicker label.
     */
    @FXML private Label clickerLabel;
    /**
     * Click button.
     */
    @FXML private Button clickButton;
    /**
     * Click count label.
     */
    @FXML private Label clickDisplay;
    /**
     * Button for the cursor.
     */
    @FXML private Button buyCursor;
    /**
     * Cursor label.
     */
    @FXML private Label cursors;
    /**
     * Button to close the pop up window.
     */
    @FXML private Button okButton;

    /**
     * Stage for pop up window.
     */
    private Stage popUpStage = new Stage();
    /**
     * Clicker amount.
     */
    private int clickerAmount = 0;
    /**
     * Cursor amount.
     */
    private int cursorAmount = 1;
    /**
     * Click count.
     */
    private int clickCount = 0;
    /**
     * Current value.
     */
    private int currentValue = 1;
    /**
     * Initial cursor price.
     */
    private final int cursorStartingPrice = 20;
    /**
     * Initial seconds for cursor.
     */
    private final double startingSeconds = 5.0;
    /**
     * Initial clicker price.
     */
    private final int startingClickerCost = 100;
    /**
     * Clicker price.
     */
    private int clickerCost = 0;
    /**
     * Cursor price.
     */
    private int cursorPrice = 0;
    /**
     * Seconds for cursor.
     */
    private double seconds = 0;
    /**
     * Timeline time minus.
     */
    private final double secondMinus = 0.1;
    /**
     * Timeline to increase click amount.
     */
    private Timeline timeline = new Timeline();
    /**
     * Clicker cost price increase.
     */
    private final int clickerCostIncrease = 200;
    /**
     * Cursor cost price increase.
     */
    private final int cursorPriceIncrease = 20;
    /**
     * Pop up window x size.
     */
    private final int popUpXSize = 416;
    /**
     * Pop up window y size.
     */
    private final int popUpYSize = 220;

    /**
     * Initialize button deactivation
     * in the beginning.
     */
    public void initialize() {
        try {
            seconds = startingSeconds;
            clickerCost = startingClickerCost;
            cursorPrice = cursorStartingPrice;
            buyCursor.setDisable(true);
            clickButton.setDisable(true);
        } catch (NullPointerException e) {
        }
    }

    /**
     * Create a timeline
     * to increase click amount.
     */
    public void timer() {
        timeline.stop();
        timeline = new Timeline(
                new KeyFrame(
                        Duration.ZERO,
                        new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                clickCount++;
                                buttonTimer();
                                clickDisplay.setText("Cookies: " + String.valueOf(clickCount));
                            }
                        }
                ), new KeyFrame(Duration.seconds(seconds)));
    timeline.setCycleCount(Timeline.INDEFINITE);
    timeline.play();
}

    /**
     * Set buttons active or inactive
     * depending on their value and click amount.
     */
    public void buttonTimer() {
        if (clickCount >= cursorPrice) {
            buyCursor.setDisable(false);
        } else {
            buyCursor.setDisable(true);
        }
        if (clickCount >= clickerCost) {
            clickButton.setDisable(false);
        } else {
            clickButton.setDisable(true);
        }
    }

    /**
     * Set seconds for timeline 0.1 secods lower
     * and increase the value of the clicker.
     * @param event ActionEvent for the function.
     */
    public final void clicker(ActionEvent event) {
        if (clickCount >= clickerCost && seconds > 0) {
            clickCount -= clickerCost;
            seconds -= secondMinus;
            clickerCost += clickerCostIncrease;
            clickerAmount++;
            timer();
            buttonTimer();
            clickButton.setText("Clicker: " + String.valueOf(clickerCost));
            clickerLabel.setText("Clickers: " + String.valueOf(clickerAmount));
            clickDisplay.setText("Cookies: " + String.valueOf(clickCount));
        }
        System.out.println(seconds);
    }

    /**
     * Increase click count by cursor value.
     * @param e ActionEvent for the function.
     */
    public final void cookieClick(ActionEvent e) {
        clickCount += currentValue;
        buttonTimer();
        clickDisplay.setText("Cookies: " + String.valueOf(clickCount));
        System.out.println(clickCount);
    }

    /**
     * Increase cursor value by one.
     * @param e ActionEvent for the function.
     */
    public final void cursorClick(ActionEvent e) {
        if (clickCount - cursorPrice >= 0) {
            currentValue++;
            clickCount -= cursorPrice;
            cursorPrice += cursorPriceIncrease;
            cursorAmount++;
            buttonTimer();
            clickDisplay.setText("Cookies: " + String.valueOf(clickCount));
            cursors.setText("Cursors: " + String.valueOf(cursorAmount));
            buyCursor.setText("Cursor " + String.valueOf(cursorPrice));
    }
    }

    /**
     * Stage for the pop up window.
     * @param e ActionEvent for the function.
     * @throws IOException for exception handling.
     */
    public final void popUp(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("info.fxml"));
        popUpStage.setTitle("Info");
        Scene scene = new Scene(root, popUpXSize, popUpYSize);
        scene.getStylesheets().addAll(this.getClass().getResource("application.css").toExternalForm());
        popUpStage.setScene(scene);
        popUpStage.setResizable(false);
        popUpStage.show();
    }

    /**
     * Close the pop up window.
     * @param e ActionEvent for the function.
     */
    public final void closePopUp(ActionEvent e) {
        Stage popUpStage = (Stage) okButton.getScene().getWindow();
        popUpStage.close();
    }
}
