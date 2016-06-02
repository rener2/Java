package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;
import java.io.IOException;
import java.util.Random;

/**
 * Controller class.
 */
public class Controller {


    /**
     * Round button.
     */
    @FXML Button roundButton;
    /**
     * Games timeline.
     */
    private Timeline timeline = new Timeline();
    /**
     * Timers timeline.
     */
    private Timeline clockTimeline = new Timeline();
    /**
     * Buttons starting x position.
     */
    private double xPosition = 0;
    /**
     * Buttons starting y position.
     */
    private double yPosition = 0;
    /**
     * Click count.
     */
    private int clickCount = 0;
    /**
     * Remaining time.
     */
    private int timeLeft;
    /**
     * Starting time.
     */
    private final int startingTime = 10;
    /**
     * Max x location.
     */
    private final int maxX = 520;
    /**
     * Max y location.
     */
    private final int maxY = 320;
    /**
     * Timeline duration in milliseconds.
     */
    private final int duration = 2000;
    /**
     * Timer label.
     */
    @FXML Label clock;
    /**
     * Click count label.
     */
    @FXML Label points;



    /**
     * Display collected points and start the timer.
     * @param e action
     * @throws IOException for exception handling
     */
    public void startAction(ActionEvent e) throws IOException {
        clickCount = 0;
        timeLeft = startingTime;
        points.setText("Kogutud " + clickCount + " punkti");
        clock.setText("Aega jäänud: " + timeLeft + " sekundit");
        timer();
    }

    /**
     * Choose random coordinates.
     * Call out moveButton function.
     */
    public void positionChange() {
        Random random = new Random();
        System.out.println(roundButton.getLayoutX() + "==" + xPosition);
        //roundButton.relocate(xPosition, yPosition);
        xPosition = random.nextInt(maxX);
        yPosition = random.nextInt(maxY);
        System.out.println(timeLeft);
        moveButton();
    }

    /**
     * Create a new timeline to move
     * the button to given coordinates.
     */
    public void moveButton() {
        timeline.stop();
        timeline = new Timeline();
        timeline.getKeyFrames().addAll(
        new KeyFrame(new Duration(duration), // set end position at 40s
                new KeyValue(roundButton.translateXProperty(), xPosition),
                new KeyValue(roundButton.translateYProperty(), yPosition)));
        timeline.play();
    }

    /**
     * Create a timeline for the timer.
     * Also call out positionChange function every 2
     * seconds until the time runs out.
     */
    public void timer() {
        clockTimeline.stop();
        clockTimeline = new Timeline(
                new KeyFrame(
                        Duration.ZERO,
                        new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                if (timeLeft % 2 == 0) {
                                    positionChange();
                                }
                                timeLeft -= 1;
                                clock.setText(String.valueOf("Aega jäänud: " + timeLeft + " sekundit"));
                            }
                        }
                ), new KeyFrame(Duration.seconds(1)));
        clockTimeline.setCycleCount(startingTime);
        clockTimeline.play();
    }

    /**
     * Increase click amount.
     * @param e action
     */
    public void counter(ActionEvent e) {
        if (timeline.getStatus().equals(Animation.Status.RUNNING)) {
            clickCount++;
            points.setText("Kogutud " + clickCount + " punkti");
            System.out.println(clickCount);
        }
    }
}
