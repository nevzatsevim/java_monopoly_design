package view;

import Engine.Player.PlayerInterface;
import java.util.ArrayList;
import java.util.List;
import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * This class is used to display the information of a selected player.
 * The selected player updates itself when a player action takes place
 * @author Nevzat Sevim, Cemal Yagcioglu
 */

public class playerStatusUI extends VBox {

    private ComboBox playerBox;
    private TextArea playerField;
    private display myDisplay;
    private Label activeLabel;

    private int height, width;

    public playerStatusUI(display newDisplay){
        myDisplay = newDisplay;
        height = (3 * myDisplay.getHeight()) / 10;
        width = (3* myDisplay.getWidth()) / 10;

        this.setAlignment(Pos.TOP_CENTER);
        this.setMinSize(width,height);
        this.setMaxSize(width,height);

        activeLabel = new Label("Active Player: " + 1);
        ArrayList<PlayerInterface> playersList = myDisplay.getEngine().getPlayersList();

        setPlayerBox(playersList);

        playerField = new TextArea();
        playerField.setPrefHeight(height);

        this.getChildren().addAll(activeLabel, playerBox, new Separator());

        playerStatusUpdated();
        playerBox.getSelectionModel().selectFirst();
    }

    private void setPlayerBox(ArrayList<PlayerInterface> playersList) {
        playerBox = new ComboBox();
        playerBox.setPrefWidth(width);
        playerBox.setPromptText("Choose Player");

        for(PlayerInterface player : playersList) {
            playerBox.getItems().add(player.getID());
        }

        playerBox.setOnAction(this::playerStatusUpdatedHandler);
        playerBox.getSelectionModel().selectFirst();
    }

    private void playerStatusUpdatedHandler(Event event) { playerStatusUpdated(); }

    private void playerStatusUpdated() {
        this.getChildren().remove(playerField);
        int chosenPlayerID = (int) playerBox.getSelectionModel().getSelectedItem();
        ArrayList<PlayerInterface> playersList = myDisplay.getEngine().getPlayersList();
        PlayerInterface chosenPlayer = playersList.get(chosenPlayerID-1);
        playerField = new TextArea();
        playerField.setPrefHeight(height);
        playerField.appendText("Liquid Cash: $"+chosenPlayer.getCurrentMoney()+"\n");
        playerField.appendText("Assets: $"+chosenPlayer.getAssetsValue()+"\n");
        playerField.appendText("Net Worth : $"+chosenPlayer.getNetworth()+"\n");
        playerField.appendText("Properties: "+ listNicePrint(chosenPlayer.getOwnedCardsInfo()));

        this.getChildren().add(playerField);
    }

    public void updatePlayerStatus(int playerID){
        playerBox.getSelectionModel().select(playerID-1);
        playerStatusUpdated();
    }

    private String listNicePrint(List<String> stringList){
        String output = "\n";
        for(String item: stringList){
            output += "   " +item + "\n";
        }
        return output;
    }

    /**
     * Toggles the Class to dark mode
     * @param dark & alt: two strings that define Style preferences
     */

    public void toggleDark(String dark, String alt){

        activeLabel.setStyle(alt);
        playerBox.setStyle(dark);
        playerField.setStyle(dark);
        this.setStyle(dark);

        Region content = (Region) playerField.lookup(".content");
        content.setStyle(dark);
    }

}
