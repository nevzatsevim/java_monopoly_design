package view;

import Engine.EngineInterface;
import Engine.EngineMain;
import java.io.File;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Main Class that starts the Game. A pop-Up screen takes inputs; this
 * input (data) is used to initialize the game with parameters
 * @author Nevzat Sevim, Cemal Yagcioglu
 */

    public class controller extends Application {

        private final int stageHeight = 1000;
        private final int stageWidth = 1000;
        private final String DATA_PATH = "src/Data/Files/Backend/";
        private final String XML = ".xml";

        private static final String TITLE = "Monopoly";
        private Stage currentStage;
        private Stage startStage;

        private Button startButton;
        private ComboBox boardsBox, numPlayersBox, diceBox, luckBox, playersBox;

        private String boardPath;
        private String playersPath;
        private String luckPath;
        private String dicePath;
        private int noPlayers;

        public static void main(String[] args) {
            launch(args);
        }

        @Override
        public void start(Stage currentStage) {
            this.currentStage=currentStage;
            startStage();
        }

        private void startStage() {
            startStage = new Stage();
            VBox startVBox = new VBox();

            setStartingButtons();

            startVBox.getChildren().addAll(boardsBox,playersBox,luckBox, diceBox,numPlayersBox,startButton);

            Scene startScene = new Scene(startVBox);
            startStage.setScene(startScene);
            startStage.setTitle(TITLE);
            startStage.setWidth(200);
            startStage.setHeight(300);
            startStage.show();
        }

        private void setStartingButtons() {
            this.boardsBox = new ComboBox();
            boardsBox.setPromptText("Choose Board");
            boardsBox.setPrefSize(200,50);
            boardsBox.getItems().addAll(getFileNames("Tiles"));
            boardsBox.setOnAction(this::setBoardFile);

            this.playersBox = new ComboBox();
            playersBox.setPromptText("Choose Player Type");
            playersBox.setPrefSize(200,50);
            playersBox.getItems().addAll(getFileNames("Player"));
            playersBox.setOnAction(this::setPlayersFile);

            this.luckBox = new ComboBox();
            luckBox.setPromptText("Choose Luck Cards");
            luckBox.setPrefSize(200,50);
            luckBox.getItems().addAll(getFileNames("LuckCardDesk"));
            luckBox.setOnAction(this::setLuckFile);

            this.diceBox = new ComboBox();
            diceBox.setPromptText("Choose Dice");
            diceBox.setPrefSize(200,50);
            diceBox.getItems().addAll(getFileNames("Dice"));
            diceBox.setOnAction(this::setDiceFile);

            this.numPlayersBox = new ComboBox();
            numPlayersBox.setPromptText("# of Players");
            numPlayersBox.setPrefSize(200,50);
            numPlayersBox.getItems().addAll(2,3,4,5);
            numPlayersBox.setOnAction(this::setNumberOfPlayers);

            this.startButton = new Button("Start");
            startButton.setPrefSize(200,50);
            startButton.setOnAction(this::gameStage);
        }

        private void gameStage(ActionEvent actionEvent){
            startStage.close();
            EngineInterface engine = new EngineMain();
            engine.initiate(boardPath,playersPath,dicePath,luckPath,noPlayers);

            display myDisplay = new display(this, engine);
            Scene myScene = myDisplay.getScene();

            currentStage.setScene(myScene);
            currentStage.setTitle(TITLE);
            currentStage.setWidth(stageWidth + 10);
            currentStage.setHeight(stageHeight + 10);
            currentStage.setResizable(true);
            currentStage.show();
        }

        public int getStageHeight(){
            return stageHeight;
        }

        public int getStageWidth(){
            return stageWidth;
        }

        private ArrayList<String> getFileNames(String folderName) {
            ArrayList<String> fileNames = new ArrayList<>();
            File f = new File(DATA_PATH+folderName);
            for(String fileName:f.list()){
                if(fileName.endsWith(".xml")){
                    fileNames.add(fileName.replace(XML,""));
                }
            }
            return fileNames;
        }

        private void setBoardFile(Event event){
            boardPath = DATA_PATH+"Tiles/"+boardsBox.getSelectionModel().getSelectedItem()+XML;
        }

        private void setLuckFile(Event event){
            luckPath = DATA_PATH+"LuckCardDesk/"+luckBox.getSelectionModel().getSelectedItem()+XML;
        }
        private void setPlayersFile(Event event){
            playersPath = DATA_PATH+"Player/"+playersBox.getSelectionModel().getSelectedItem()+XML;
        }
        private void setDiceFile(Event event){
            dicePath = DATA_PATH+"Dice/"+diceBox.getSelectionModel().getSelectedItem()+XML;
        }
        private void setNumberOfPlayers(Event event){
            noPlayers = (int) numPlayersBox.getSelectionModel().getSelectedItem();
        }

        public String getBoardPath(){ return (String) boardsBox.getSelectionModel().getSelectedItem(); }

    }


