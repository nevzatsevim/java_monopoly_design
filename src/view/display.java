package view;

import Engine.EngineInterface;
import Engine.Player.PlayerInterface;
import java.util.ArrayList;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * This class is in the backbone of GUI, every part that is integrated
 * on the GUI interacts with the engine and with each other though this class
 * @author Nevzat Sevim
 */
public class display {

    private controller myController;
    private int height, width;

    private boardUI board;
    private actionsUI actionsBar;
    private settingsUI settingsBar;
    private playerStatusUI playerField;
    private tileStatusUI tileFiled;
    private feedbackUI feedbackField;

    private Scene myScene;
    private int activePlayerPosition;
    private int activePlayerID = 1;
    private EngineInterface engine;
    private ArrayList<PlayerInterface> playerList;
    private VBox vBox1, vBox2;
    private HBox hBox;

    public display(controller newController, EngineInterface engine) {

        //start engine and get players
        this.engine = engine;
        playerList = engine.getPlayersList();

        //Set display properties
        myController = newController;
        height = myController.getStageHeight();
        width = myController.getStageWidth();

        String boardPath = myController.getBoardPath();

        //Initialize all the Components
        board = new boardUI(this, boardPath);
        actionsBar = new actionsUI(this);
        settingsBar = new settingsUI(this);
        feedbackField = new feedbackUI(this);
        playerField = new playerStatusUI(this);
        tileFiled = new tileStatusUI(this);

        vBox1 = new VBox();
        vBox1.getChildren().addAll(settingsBar, actionsBar, board);

        vBox2 = new VBox();
        vBox2.getChildren().addAll(playerField, feedbackField, tileFiled);

        Separator separator =new Separator();
        separator.setOrientation(Orientation.VERTICAL);

        hBox = new HBox();
        hBox.getChildren().addAll(vBox1, separator, vBox2);
        myScene = new Scene(hBox);
    }

    /**
     * rollDice method sends information to the Board to update player position
     * if the game has been won by a player a win pop-Up screen appears
     */

    public void rollDice() {
        engine.rollDice();
        activePlayerPosition = engine.getCurrentPlayer().getCurrentPositionIndex();
        board.movePiece(activePlayerPosition, engine.getCurrentPlayerId());
        tileFiled.checkTile(engine.getTileWithIndex(activePlayerPosition));

        if(engine.isGameOver()){

            ImageView im = new ImageView(new Image("/view/images/win.gif"));
            im.setFitWidth(700); im.setFitHeight(700);

            Label winLabel = new Label("Player " + engine.getCurrentPlayerId() + " Wins");
            winLabel.setTextFill(Color.PINK);
            winLabel.setFont(Font.font(100));

            StackPane root = new StackPane();
            root.getChildren().addAll(im, winLabel);
            Scene scene = new Scene(root, 700 , 700);

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        }
        updatePlayerStatusUI();
        updateFeedbackUI();
    }

    /**
     * Toggles the Class to dark mode
     * @param dark & alt: two strings that define Style preferences
     */

    public void toggleDark(String dark, String alt){

        hBox.setStyle(dark);
        vBox1.setStyle(dark);
        vBox2.setStyle(dark);
    }

    /**
     * Bellow can be found the get methods accessed by the frontend components
     */

    public EngineInterface getEngine(){ return engine; }

    public playerStatusUI getPlayerField(){ return playerField; }

    public tileStatusUI getTileFiled(){return tileFiled; }

    public feedbackUI getFeedbackField() { return feedbackField; }

    public settingsUI getSettingsBar() { return settingsBar;}

    public ArrayList<PlayerInterface> getPlayerList(){ return playerList; }

    public boardUI getBoard() {return board;}

    public Scene getScene(){
        return myScene;
    }

    public int getHeight(){
        return height;
    }

    public int getWidth(){
        return width;
    }

    public controller getController() {return myController;}

    /**
     * Bellow can be found the set methods accessed by the frontend components
     */

    public void setActivePlayerID(int id) { this.activePlayerID = id; }

    public void updatePlayerStatusUI(){ playerField.updatePlayerStatus(activePlayerID); }

    public void updateFeedbackUI(){ feedbackField.updateFeedBackUI(); }

}
