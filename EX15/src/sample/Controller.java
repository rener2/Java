package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.Random;

/**
 * Controller class.
 */
public class Controller {


    /**
     * Goats imageview.
     */
    @FXML
    ImageView goat;

    /**
     * Label for the score.
     */
    @FXML
    Label score;

    /**
     * Layout for the game.
     */
    @FXML
    AnchorPane layout;

    /**
     * X axis bounds for the mine.
     */
    private final int xBounds = 700;

    /**
     * Y axis bounds for the mine.
     */
    private final int yBounds = 450;

    /**
     * Height of the mines.
     */
    private final int mineHeight = 29;

    /**
     * Width of the mines.
     */
    private final int mineWidth = 31;

    /**
     * Time delay for new mines.
     */
    private final int mineTime = 5;

    /**
     * Movement range of the goat with
     * one move.
     */
    private final int movementSize = 5;

    /**
     * Y axis distance between the
     * goat and the mine.
     */
    private final int yDistance = 30;

    /**
     * X axis distance between the
     * goat and the mine.
     */
    private final int xDistance = 70;

    /**
     * Score count.
     */
    private int scoreCount = 0;

    /**
     * Timeline for the game.
     */
    private Timeline timeline = new Timeline();

    /**
     * List of mines.
     */
    private ArrayList<ImageView> mines = new ArrayList<>();



    /**
     * Initialize timer in the beginning.
     */
    public void initialize() {
        timer();
    }

    /**
     * Create a timeline for the game.
     */
    public void timer() {
        timeline.stop();
        timeline = new Timeline(
                new KeyFrame(
                        Duration.ZERO,
                        new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                newMine();
                            }
                        }
                ), new KeyFrame(Duration.seconds(mineTime)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    /**
     * Create a new mine.
     */
    public void newMine() {
        Random random = new Random();
        double randomX = random.nextInt(xBounds);
        double randomY = random.nextInt(yBounds);
        ImageView newMine = new ImageView("mine.png");
        newMine.setLayoutX(randomX);
        newMine.setLayoutY(randomY);
        newMine.setFitHeight(mineHeight);
        newMine.setFitWidth(mineWidth);
        layout.getChildren().removeAll(mines);
        mines.add(newMine);
        layout.getChildren().addAll(mines);
    }

    /**
     * Add one score point and update the label.
     */
    public void addPoint() {
        scoreCount++;
        score.setText("Mines collected: " + scoreCount);
    }

    /**
     * Move the goat as commanded
     * from the keyboard.
     * @param event keyboard event.
     */
    @FXML
    public void moveGoat(KeyEvent event) {
        if (event.getCode() == KeyCode.UP || event.getCode() == KeyCode.DOWN
                || event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.LEFT) {
            try {
                if (event.getCode() == KeyCode.UP) {
                    goat.relocate(goat.getLayoutX(), goat.getLayoutY() - movementSize);
                } else if (event.getCode() == KeyCode.DOWN) {
                    goat.relocate(goat.getLayoutX(), goat.getLayoutY() + movementSize);
                } else if (event.getCode() == KeyCode.RIGHT) {
                    Image goatRight = new Image("/goat.png");
                    goat.setImage(goatRight);
                    goat.relocate(goat.getLayoutX() + movementSize, goat.getLayoutY());
                } else if (event.getCode() == KeyCode.LEFT) {
                    Image goatLeft = new Image("/goatLeft.png");
                    goat.setImage(goatLeft);
                    goat.relocate(goat.getLayoutX() - movementSize, goat.getLayoutY());
                }
                checkCollision();
            } catch (Exception e) {
                e.printStackTrace();
            }
            event.consume();
        }
    }

    /**
     * Check goats collision with a mine.
     */
    public void checkCollision() {
        System.out.println("Checking collision");
        System.out.println("Goat: " + goat.getLayoutX() + ", " + goat.getLayoutY());
        for (Node mine: layout.getChildren().subList(2, layout.getChildren().size())) {
            System.out.println("Mine: " + mine.getLayoutX() + ", " + mine.getLayoutY());
            if (Math.abs(goat.getLayoutY() - mine.getLayoutY()) <= yDistance) {
                if (Math.abs(goat.getLayoutX() - mine.getLayoutX()) <= xDistance) {
                    System.out.println("collision");
                    layout.getChildren().remove(mine);
                    mines.remove(mine);
                    addPoint();
                }
            }
        }
    }
}
