package view;

import javafx.scene.control.*;

/**
 * This is a class that we intended to use but were unable to implement.
 * The original idea was to change the game settings anytime during the
 * game, but this has resulted in complications. Therefore, we have
 * decided to switch to predefining the game parameters before the game
 * starts.
 */

public class settingsUI extends ToolBar {

    private display myDisplay;
    private ComboBox boardsBox, playersBox, diceBox;
    private Button startButton;
    private Label label;

    public settingsUI(display newDisplay) {

        label = new Label("Game Settings:");

        createButtons();

        this.getItems().addAll(label, boardsBox, playersBox, diceBox,
                new Separator(), startButton);
    }

    private void createButtons() {
        this.boardsBox = new ComboBox();
        boardsBox.setPromptText("Choose Board");
        boardsBox.setDisable(true);
        boardsBox.getItems().addAll("Classic");

        this.playersBox = new ComboBox();
        playersBox.setPromptText("# of Players");
        playersBox.setDisable(true);
        playersBox.getItems().addAll(2,3,4,5);

        this.diceBox = new ComboBox();
        diceBox.setPromptText("Choose Dice");
        diceBox.setDisable(true);
        diceBox.getItems().addAll("6 sided");

        this.startButton = new Button("Start");
        startButton.setDisable(true);
    }

    /**
     * Toggles the Class to dark mode
     * @param dark & alt: two strings that define Style preferences
     */

    public void toggleDark(String dark, String alt){

        playersBox.setStyle(dark);
        diceBox.setStyle(dark);
        label.setStyle(alt);
        startButton.setStyle(dark);
        boardsBox.setStyle(dark);
        this.setStyle(dark);
    }
}
