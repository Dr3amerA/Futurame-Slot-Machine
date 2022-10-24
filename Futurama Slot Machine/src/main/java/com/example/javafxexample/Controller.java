package com.example.javafxexample;

import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

public class Controller {

    //alex test
    @FXML
    Rectangle slot1;
    @FXML
    Rectangle slot2;
    @FXML
    Rectangle slot3;

    @FXML
    Text messageBox;
    @FXML
    Text tries;
    @FXML
    Text winScore;
    @FXML
    Text lossScore;

    @FXML
    Button playButton;
    @FXML
    Button retry;


    FileInputStream inputstream = new FileInputStream("zoid.jfif");
    FileInputStream inputstream0 = new FileInputStream("ftsm3.jpg");
    FileInputStream inputstream1 = new FileInputStream("bend.png");
    FileInputStream inputstream2 = new FileInputStream("prof.png");
    FileInputStream inputstream3 = new FileInputStream("2.png");
    FileInputStream inputstream4 = new FileInputStream("11.png");




    Image zoidberg = new Image(inputstream);

    Image futuramaSlotMachine = new Image(inputstream0);

    Image bender = new Image(inputstream1);

    Image professor = new Image(inputstream2);

    Image leela = new Image(inputstream3);

    Image philip = new Image(inputstream4);



    private Image[] images = {zoidberg, bender, professor, leela, philip};
    private int counter = 10;
    private int wins = 0;
    private int losses = 0;
    private int number1;
    private int number2;
    private int number3;


    public Controller() throws FileNotFoundException {
    }

    @FXML
    public void initialize() {
        slot1.setFill(new ImagePattern(futuramaSlotMachine));
        slot2.setFill(new ImagePattern(futuramaSlotMachine));
        slot3.setFill(new ImagePattern(futuramaSlotMachine));
        tries.setText("\uD83D\uDC7D \uD83D\uDC7D \uD83D\uDC7D \uD83D\uDC7D \uD83D\uDC7D \uD83D\uDC7D \uD83D\uDC7D \uD83D\uDC7D \uD83D\uDC7D \uD83D\uDC7D    ");
        retry.setDisable(true);
    }


    public void randomImageGenerator(){
     number1 = ThreadLocalRandom.current().nextInt(0, 3);
     number2 = ThreadLocalRandom.current().nextInt(0, 3);
     number3 = ThreadLocalRandom.current().nextInt(0, 3);
}
    public void slot1Function(){
        slot1.setFill(new ImagePattern(futuramaSlotMachine));
        Duration duration = Duration.millis(1000);
        RotateTransition rotateTransitionSlot1 = new RotateTransition(duration);
        rotateTransitionSlot1.setByAngle(1080);
        rotateTransitionSlot1.setNode(slot1);
        rotateTransitionSlot1.play();
        rotateTransitionSlot1.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                slot1.setFill(new ImagePattern(images[number1]));

            }
        });
    }
    public void slot2Function(){
        slot2.setFill(new ImagePattern(futuramaSlotMachine));
        Duration duration1 = Duration.millis(1500);
        RotateTransition rotateTransitionSlot2 = new RotateTransition(duration1);
        rotateTransitionSlot2.setByAngle(1080);
        rotateTransitionSlot2.setNode(slot2);
        rotateTransitionSlot2.play();
        rotateTransitionSlot2.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                slot2.setFill(new ImagePattern(images[number2]));

            }
        });
    }
    public void slot3Function(){
        slot3.setFill(new ImagePattern(futuramaSlotMachine));
        Duration duration2 = Duration.millis(2000);
        RotateTransition rotateTransitionSlot3 = new RotateTransition(duration2);
        rotateTransitionSlot3.setByAngle(1080);
        rotateTransitionSlot3.setNode(slot3);
        rotateTransitionSlot3.play();
        rotateTransitionSlot3.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                slot3.setFill(new ImagePattern(images[number3]));
                playButton.setDisable(false);
                playButtonFunction();

            }
        });
    }


    public void winResult() {

        String musicFileWin = "winningSound.mp3";
        Media winningSound = new Media(new File(musicFileWin).toURI().toString());
        MediaPlayer mediaPlayerWin = new MediaPlayer(winningSound);
        mediaPlayerWin.play();

        //System.out.println("YOU WIN");
        messageBox.setText("YOU WIN");

        wins++;
        winScore.setText("    Wins: " + wins);
        playButton.setDisable(true);

        Parent rootWin = null;
        try {
            rootWin = FXMLLoader.load(getClass().getResource("sampleWin.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage primaryStage = new Stage();
        primaryStage.setTitle("JACKPOT!!");
        primaryStage.setScene(new Scene(rootWin, 600, 470));
        primaryStage.show();

        retry.setDisable(false);

    }
    public void failTry(){
        counter--;
        // System.out.println("Try again LOSER");
        messageBox.setText("Try again LOSER");
        String lifes = "";
        for (int i = 0; i < counter; i++) {
            lifes += " \uD83D\uDC7D";
        }
        tries.setText(lifes + "    ");
    }
    public void lossResult(){
        String musicFileLoose = "loserSoundEffects.mp3";
        Media loserSound = new Media(new File(musicFileLoose).toURI().toString());
        MediaPlayer mediaPlayerLoose = new MediaPlayer(loserSound);
        mediaPlayerLoose.play();

        counter--;
        tries.setText("");
        messageBox.setText("Better luck next time LOSER!");
        losses++;
        lossScore.setText("    Losses: " + losses);

        playButton.setDisable(true);
        retry.setDisable(false);


        Parent rootLoss = null;
        try {
            rootLoss = FXMLLoader.load(getClass().getResource("sampleLoss.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage primaryStage = new Stage();
        primaryStage.setTitle("L is for LOSER!");
        primaryStage.setScene(new Scene(rootLoss, 550, 430));
        primaryStage.show();
    }
    public void playButtonFunction(){
        if (counter > 1) {
            if (number1 == number2 && number2 == number3) {
                winResult();
            } else {
                failTry();
            }
        } else {
            lossResult();
        }
    }

    @FXML
    public void playClicked(ActionEvent event) throws IOException {
        String musicFileButton = "slotMachineSound.mp3";
        Media slotMachineSound = new Media(new File(musicFileButton).toURI().toString());
        MediaPlayer mediaPlayerButton = new MediaPlayer(slotMachineSound);
        mediaPlayerButton.play();

        playButton.setDisable(true);

        randomImageGenerator();

        slot1Function();
        slot2Function();
        slot3Function();
    }
    @FXML
    public void retryClicked(ActionEvent event) throws IOException {

        initialize();
        playButton.setDisable(false);
        counter = 10;
        tries.setText("\uD83D\uDC7D \uD83D\uDC7D \uD83D\uDC7D \uD83D\uDC7D \uD83D\uDC7D \uD83D\uDC7D \uD83D\uDC7D \uD83D\uDC7D \uD83D\uDC7D \uD83D\uDC7D    ");

    }
}

