package view;

import Data.FrontendReader.ReadXML_actionUI;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * This class is in charge of player actions like rolling dice, ending turn etc.
 * It is also used to toggle to dark mode and pop-up a how to play screen.
 * @author Nevzat Sevim
 */

public class actionsUI extends ToolBar {

    private display myDisplay;
    private Button rollDiceButton, tradeButton, payFineButton, nextPlayerButton, helpButton;
    private ToggleButton darkButton;
    private Label label;
    private boolean isDark;
    private String darkStyle, darkAlt;

    public actionsUI(display newDisplay) {
        ReadXML_actionUI.ReadXML();
        myDisplay = newDisplay;
        label = new Label(ReadXML_actionUI.action_button);

        setCustomColors();
        createButtons();

        this.getItems().addAll(
                label, rollDiceButton, tradeButton, payFineButton, nextPlayerButton,
                new Separator(), darkButton, helpButton);
    }

    private void createButtons() {
        String URL = ReadXML_actionUI.die_image;
        this.rollDiceButton = new Button();
        ImageView dieImage = new ImageView(new Image(URL));

        dieImage.setFitHeight(20);
        dieImage.setFitWidth(20);

        this.rollDiceButton.setGraphic(dieImage);
        rollDiceButton.setOnAction(this::rollDice);

        this.tradeButton = new Button(ReadXML_actionUI.getTrade_label());
        tradeButton.setOnAction(this::trade);

        this.payFineButton = new Button(ReadXML_actionUI.pay_Fines_Label);
        payFineButton.setOnAction(this::payJailFine);

        this.nextPlayerButton = new Button(ReadXML_actionUI.Next_Player_label);
        nextPlayerButton.setOnAction(this::nextPlayer);

        this.helpButton = new Button(ReadXML_actionUI.how_to_Play_label);
        helpButton.setOnAction(this::help);

        this.darkButton = new ToggleButton();
        darkButton.setText(ReadXML_actionUI.JOKE1);
        darkButton.setOnAction(this::goDark);
    }

    /**
     * The methods that guide button actions
     */

    private void trade(ActionEvent actionEvent) {
        new tradePopUp(myDisplay);
    }

    private void nextPlayer(ActionEvent actionEvent) {
        myDisplay.getEngine().setTurnToNextPlayer();

        int id = myDisplay.getEngine().getCurrentPlayerId();
        myDisplay.setActivePlayerID(id);

        playerStatusUI temp = myDisplay.getPlayerField();
        temp.getChildren().set(0,new Label(ReadXML_actionUI.ACTIVE + (id)));

        rollDiceButton.setDisable(false);
        nextPlayerButton.setDisable(true);
        myDisplay.updatePlayerStatusUI();
    }

    private void rollDice(ActionEvent actionEvent) {

        myDisplay.rollDice();
        rollDiceButton.setDisable(!myDisplay.getEngine().getCanRoll());
        nextPlayerButton.setDisable(myDisplay.getEngine().getCanRoll());
        myDisplay.updateFeedbackUI();
    }

    private void help(ActionEvent actionEvent) {

        WebView wv = new WebView();

        StackPane root = new StackPane();
        root.getChildren().add(wv);
        Scene scene = new Scene(root, 1000 , 1000);

        Stage stage = new Stage();
        stage.setTitle(ReadXML_actionUI.PLAY);
        stage.setScene(scene);
        stage.show();

        wv.getEngine().load(ReadXML_actionUI.HELP);
    }

    private void goDark(ActionEvent actionEvent) {

        //Check if switching to dark mode or light mode
        String dark = darkStyle;
        String alt = darkAlt;
        darkButton.setText(ReadXML_actionUI.JOKE2);

        if(isDark) { dark = null; alt = null; darkButton.setText(ReadXML_actionUI.JOKE1);}
        isDark = !isDark;

        //Set ToolBar components to appropriate colors
        darkButton.setStyle(dark);
        nextPlayerButton.setStyle(dark);
        tradeButton.setStyle(dark);
        helpButton.setStyle(dark);
        rollDiceButton.setStyle(dark);
        payFineButton.setStyle(dark);
        label.setStyle(alt);
        this.setStyle(dark);

        //set GUI components to appropriate colors
        myDisplay.getFeedbackField().toggleDark(dark, alt);
        myDisplay.getTileFiled().toggleDark(dark, alt);
        myDisplay.getSettingsBar().toggleDark(dark, alt);
        myDisplay.getPlayerField().toggleDark(dark, alt);
        myDisplay.toggleDark(dark, alt);
    }

    private void payJailFine(ActionEvent actionEvent){
        myDisplay.getEngine().payJailFine();
    }

    /**
     * This method is in charge of building a string that defines
     * Styles of GUI components. Used in Dark Mode
     */

    private void setCustomColors(){
        String TEXT_FILL = ReadXML_actionUI.TEXT_FILL;
        String BG_COLOR = ReadXML_actionUI.BACKGROUND_COLOR;
        String BORDER_COLOR = ReadXML_actionUI.BORDER_COLOR;
        String BORDER_RADIUS = ReadXML_actionUI.BORDER_RADIUS;
        String BACKGROUND_RADIUS = ReadXML_actionUI.BG_RADIUS;
        String DARK_TEXT_FILL = ReadXML_actionUI.DARK_MODE_TEXT;
        String DARK_COLOR_FILL = ReadXML_actionUI.DARK_MODE_COLOR;

        darkStyle = TEXT_FILL+ BG_COLOR + BORDER_COLOR + BORDER_RADIUS + BACKGROUND_RADIUS ;
        darkAlt = DARK_TEXT_FILL + DARK_COLOR_FILL;
    }
}
