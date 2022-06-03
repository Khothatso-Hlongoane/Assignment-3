package com.example.assignment3;

import javafx.animation.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;

import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class HelloApplication extends Application {
    TranslateTransition tr = new TranslateTransition();
    AnimationTimer collision;
    private int score = 1;
    Label scoreLb = new Label();


    public ImageView coin1(Scene scene){
        Image firstCoin = new Image("coin.png");
        ImageView coinView = new ImageView(firstCoin);

        coinView.setFitHeight(40);
        coinView.setFitWidth(40);
        coinView.setX(1100);
        coinView.setY(40);

        tr = new TranslateTransition(Duration.millis(5500),coinView);
        tr.setByX(-1200);
        tr.setCycleCount(Integer.MAX_VALUE);
        tr.play();

        return coinView;
    }
    public ImageView coin2(Scene scene){
        Image secondCoin = new Image("coin.png");
        ImageView coinView = new ImageView(secondCoin);

        coinView.setFitHeight(40);
        coinView.setFitWidth(40);
        coinView.setX(1100);
        coinView.setY(260);

        tr = new TranslateTransition(Duration.millis(5500),coinView);
        tr.setByX(-1200);
        tr.setCycleCount(Integer.MAX_VALUE);
        tr.play();

        return coinView;
    }
    private ImageView Control1(Scene scene){
        Image firstCloud = new Image("cloud.png");
        ImageView cloudView = new ImageView(firstCloud);

        cloudView.setFitHeight(60);
        cloudView.setFitWidth(100);
        cloudView.setX(700);
        cloudView.setY(40);

        TranslateTransition tr = new TranslateTransition(Duration.millis(6000),cloudView);
        tr.setByX(-1200);
        tr.setCycleCount(Integer.MAX_VALUE);
        tr.play();

        return cloudView;
    }
    private ImageView Control2(Scene scene){
        Image secondCloud = new Image("cloud.png");
        ImageView cloudView = new ImageView(secondCloud);

        cloudView.setFitHeight(60);
        cloudView.setFitWidth(100);
        cloudView.setX(800);
        cloudView.setY(200);

        TranslateTransition tr = new TranslateTransition(Duration.millis(7000),cloudView);
        tr.setByX(-1200);
        tr.setCycleCount(Integer.MAX_VALUE);
        tr.play();

        return cloudView;
    }
    private ImageView Control3(Scene scene){
        Image thirdCloud = new Image("cloud.png");
        ImageView cloudView = new ImageView(thirdCloud);

        cloudView.setFitHeight(60);
        cloudView.setFitWidth(100);
        cloudView.setX(1100);
        cloudView.setY(120);

        TranslateTransition tr = new TranslateTransition(Duration.millis(6500),cloudView);
        tr.setByX(-1200);
        tr.setCycleCount(Integer.MAX_VALUE);
        tr.play();

        return cloudView;
    }
    private ImageView Control4(Scene scene){
        Image forthCloud = new Image("cloud.png");
        ImageView cloudView = new ImageView(forthCloud);

        cloudView.setFitHeight(60);
        cloudView.setFitWidth(100);
        cloudView.setX(1100);
        cloudView.setY(350);

        TranslateTransition tr = new TranslateTransition(Duration.millis(4800),cloudView);
        tr.setByX(-1200);
        tr.setCycleCount(Integer.MAX_VALUE);
        tr.play();

        return cloudView;
    }
    public void checkCollision(ImageView planeView, ImageView show1, Stage stage, TranslateTransition tr){
        if(planeView.getBoundsInParent().intersects(show1.getBoundsInParent())){
            System.out.println("Game over!!!");
            Image img = new Image("tenor.gif");
            planeView.setImage(img);
            PauseTransition pause = new PauseTransition(Duration.millis(6200));
            pause.setOnFinished(event -> stage.close());
            pause.play();
            //collision.stop();
        }

    }

    public int checkCoin(ImageView planeView, ImageView show5) {
        if (planeView.getBoundsInParent().intersects(show5.getBoundsInParent())) {
            score = score+ 1;
            scoreLb.setText("Score = "+score);
            show5.setImage(null);

        }
        else{
            show5.setImage(new Image("coin.png"));
        }

        return score;
    }

    @Override
    public void start(Stage stage) throws IOException {

        Pane root = new Pane();

        BackgroundImage img = new BackgroundImage(new Image("download.jpeg",900,700,false,true),
                BackgroundRepeat.REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        root.setBackground(new Background(img));

        Scene scene = new Scene(root, 700, 500);
        stage.setTitle("2D Game");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

        Image plane = new Image("plane.png");
        ImageView planeView = new ImageView(plane);
        planeView.setFitWidth(120);
        planeView.setFitHeight(140);
        planeView.setPreserveRatio(true);
        planeView.setLayoutX(40);
        planeView.setLayoutY(40);

        ImageView show1 = Control1(scene);
        ImageView show2 = Control2(scene);
        ImageView show3 = Control3(scene);
        ImageView show4 = Control4(scene);
        ImageView show5 = coin1(scene);
        ImageView show6 = coin2(scene);

        scoreLb.setLayoutX(570);
        scoreLb.setLayoutY(2);
        scoreLb.setStyle(
                "-fx-font-size: 24; -fx-background-color:white;"
        );

        root.getChildren().addAll(planeView,show1, show2, show3, show4, show5, show6, scoreLb);

        AnimationTimer collision = new AnimationTimer() {
            @Override
            public void handle(long l) {
                checkCollision(planeView,show1, stage, tr);
                checkCollision(planeView,show2, stage, tr);
                checkCollision(planeView,show3, stage, tr);
                checkCollision(planeView,show4, stage, tr);
                checkCoin(planeView,show5);
                checkCoin(planeView,show6);
            }
        };
        collision.start();
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if(keyEvent.getCode() == KeyCode.UP){
                    planeView.setLayoutY(planeView.getLayoutY()-15);
                } else if (keyEvent.getCode() == KeyCode.DOWN) {
                    planeView.setLayoutY(planeView.getLayoutY()+15);
                }
                else if (keyEvent.getCode() == KeyCode.LEFT) {
                    planeView.setLayoutX(planeView.getLayoutX()-10);
                }
                else if (keyEvent.getCode() == KeyCode.RIGHT) {
                    planeView.setLayoutX(planeView.getLayoutX()+10);
                }
            }
        });

    }

    public static void main(String[] args) {
        launch();
    }
}