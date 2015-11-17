/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proiectmps;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Reflection;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;


class Letter extends Text{

    private boolean isSelected;
    private Text word;
    private ArrayList<Letter> letters = new ArrayList<>();

    public Letter (String letter,Text word,ArrayList<Letter> letters){
        this.word = word;
        this.setText(letter);
        this.setFont(Font.font("Verdana",100));
        this.letters = letters;
        addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                isClicked();
            }
        });
    }

    private String makeTextFromLetters(){
        String w = "";
        for(Letter l : this.letters){
            w += l.getText();
        }
        return w;
    }

    public void isClicked(){
        this.isSelected = !isSelected;
        if(isSelected) {
            this.setFill(Color.rgb(255, 138, 0));
            letters.add(this);
            word.setText(makeTextFromLetters());
        }
        else {
            this.setFill(Color.BLACK);
            letters.remove(this);
            word.setText(makeTextFromLetters());
        }
    }

}

public class Interfata extends Application {

    private ArrayList<Letter> lettersDisplayed;
    private ArrayList<Letter> availableLetters;
    private GridPane grid = new GridPane();;
    private BorderPane border = new BorderPane();
    private StackPane titleContainer = new StackPane();
    private StackPane wordContainer = new StackPane();
    private VBox vbButtons = new VBox();
    private VBox vbRightPane = new VBox();
    private Text displayWord = new Text("");
    private ImageView img = new ImageView("images/topImage.jpg");
    private Button newGame = new Button("Joc Nou");
    private Button verifyWord = new Button("Verifica");
    private Button exit = new Button("Iesi");
    private Label score = new Label("0");
    private Label mesaj = new Label("");
    private Label scoreLabel = new Label("Scor");
    private Label mesajLabel = new Label("Mesaj");
    private Controller controller = new Controller();

    private void populateLetters(Text displayWord){
        //creez letters in functie de ce fata au zarurile
        Zar [] zaruri = controller.getZaruri();
        lettersDisplayed = new ArrayList<>();
        availableLetters = new ArrayList<>();
        System.out.println("display letters : " + lettersDisplayed.size());
        for (int i = 0; i < 9; i ++){
            availableLetters.add(new Letter(String.valueOf(zaruri[i].faces[zaruri[i].active_face]),displayWord,lettersDisplayed));
        }
        border.getChildren().remove(grid);
        grid.getChildren().clear();
        for(int i = 0; i < 3; i ++)
            for(int j = 0; j < 3; j ++)
                grid.add(availableLetters.get(3*i+j),j,i);
        score.setText("0");
        mesaj.setText("");
        displayWord.setText("");
        border.setCenter(grid);

    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        System.out.println("ALEX : " + getClass().getProtectionDomain().getCodeSource().getLocation().getPath());
        primaryStage.setTitle("Jocul Literelor");
        titleContainer.setAlignment(Pos.CENTER);
        Scene scene = new Scene(border,1200,800);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        scene.getStylesheets().add(Interfata.class.getResource("resources/style.css").toExternalForm());

        vbButtons.setSpacing(10);
        vbButtons.setAlignment(Pos.CENTER_RIGHT);
        newGame.setMaxWidth(Double.MAX_VALUE);
        verifyWord.setMaxWidth(Double.MAX_VALUE);
        exit.setMaxWidth(Double.MAX_VALUE);
        vbButtons.getChildren().addAll(newGame,verifyWord,exit);
        vbButtons.setPadding(new Insets(25,25,25,25));

        vbRightPane.setPadding(new Insets(25,25,25,0));
        vbRightPane.setSpacing(10);
        vbRightPane.setPrefWidth(350);
        vbRightPane.setAlignment(Pos.CENTER_LEFT);

        mesaj.setMaxWidth(Double.MAX_VALUE);
        mesajLabel.setMaxWidth(Double.MAX_VALUE);
        score.setMaxWidth(Double.MAX_VALUE);
        scoreLabel.setMaxWidth(Double.MAX_VALUE);

        mesaj.setWrapText(true);
        mesajLabel.setFont(Font.font("Verdana",20));
        mesaj.setFont(Font.font("Verdana",20));
        mesaj.setTextFill(Paint.valueOf("Red"));
        score.setFont(Font.font("Verdana",20));
        score.setTextFill(Paint.valueOf("Red"));
        scoreLabel.setFont(Font.font("Verdana",20));
        vbRightPane.getChildren().addAll(scoreLabel,score,mesajLabel,mesaj );

        displayWord.setFont(Font.font("Century",50));

        Reflection r = new Reflection();
        r.setFraction(0.7f);
        displayWord.setEffect(r);
        titleContainer.setId("topArea");



        populateLetters(displayWord);

        grid.setAlignment(Pos.TOP_CENTER);
        grid.setHgap(40);
        grid.setVgap(20);
        grid.setPadding(new Insets(25,-25,25,125));


        exit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Platform.exit();
            }
        });

        verifyWord.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println(displayWord.getText().length());
                int scor = 0;
                if (displayWord.getText().length()< 4)
                    mesaj.setText("Cuvantul este prea scurt");
                else {
                    scor = scor + controller.sendWord(displayWord.getText().toLowerCase());
                    if (scor >= 0) {
                        scor = Integer.parseInt(score.getText()) + scor;
                        System.out.println("Scor : " + scor);
                        mesaj.setText("Bravo, ai acumulat " + String.valueOf(scor) + " puncte");
                        score.setText(String.valueOf(scor));
                    }
                    else{
                        mesaj.setText("Cuvant invalid");
                    }
                }

            }
        });

        newGame.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                populateLetters(displayWord);
            }
        });

        wordContainer.setAlignment(Pos.CENTER);
        wordContainer.setPadding(new Insets(25,25,40,25));
        wordContainer.getChildren().add(displayWord);

        img.fitWidthProperty().bind(primaryStage.widthProperty());
        img.setFitHeight(200);
        border.setTop(img);
       // border.setCenter(grid);
        border.setBottom(wordContainer);
        border.setLeft(vbButtons);
        border.setRight(vbRightPane);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}