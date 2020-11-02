package view;

import Engine.BuyableCards.BuyableCardsInterface;
import Engine.BuyableCards.PropertyCard;
import Engine.Tile.TileInterface;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * This class is used to display the information of a selected tile.
 * The selected tile updates itself when a player action takes place
 * @author Nevzat Sevim, Cemal Yagcioglu
 */

public class tileStatusUI extends VBox {

    private display myDisplay;

    private TextArea tileField;
    private HBox buttonsBox;
    private Button buyButton, mortgageButton;
    private Button buildHouseButton, buildHotelButton, destroyHouseButton, destroyHotelButton;
    private Label statusLabel;

    private double height, width;

    public tileStatusUI(display newDisplay){

        this.myDisplay = newDisplay;

        height = (5 * myDisplay.getHeight()) / 10;
        width = (3* myDisplay.getWidth()) / 10;

        this.setAlignment(Pos.TOP_CENTER);
        this.setPrefSize(width,height);
        this.setMinSize(width,height);
        this.setMaxSize(width,height);

        this.statusLabel = new Label("Selected Tile");
        createButtons();

        tileField = new TextArea();

        checkTile(myDisplay.getEngine().getTileWithIndex(0));

        setDisplayArea();

        this.getChildren().addAll(statusLabel, tileField, buttonsBox);
    }

    private void setDisplayArea() {
        buttonsBox = new HBox();
        buttonsBox.setPrefWidth(width);

        VBox box1 = new VBox();
        box1.setPrefWidth(width/3);
        VBox box2 = new VBox();
        box2.setPrefWidth(width/3);
        VBox box3 = new VBox();
        box3.setPrefWidth(width/3);

        box1.getChildren().addAll(buildHouseButton, destroyHouseButton);
        box2.getChildren().addAll(buildHotelButton,destroyHotelButton);
        box3.getChildren().addAll(buyButton, mortgageButton);

        buttonsBox.getChildren().addAll(box1,box2, box3);
    }

    private void createButtons() {
        this.buyButton = new Button("Buy");
        buyButton.setPrefWidth(width/3);
        buyButton.setPrefHeight(40);
        buyButton.setOnAction(this::buy);

        this.mortgageButton = new Button("Mortgage");
        mortgageButton.setPrefWidth(width/3);
        mortgageButton.setPrefHeight(40);
        mortgageButton.setOnAction(this::mortgage);

        this.buildHouseButton = new Button();
        buttonAdjuster(buildHouseButton, new ImageView(new Image("/view/images/house.png")));
        buildHouseButton.setOnAction(this::buildHouse);

        this.buildHotelButton = new Button();
        buttonAdjuster(buildHotelButton, new ImageView(new Image("/view/images/hotel.png")));
        buildHotelButton.setOnAction(this::buildHotel);

        this.destroyHotelButton = new Button();
        buttonAdjuster(destroyHotelButton, new ImageView(new Image("/view/images/boom.png")));
        destroyHotelButton.setOnAction(this::destroyHotel);

        this.destroyHouseButton = new Button();
        buttonAdjuster(destroyHouseButton, new ImageView(new Image("/view/images/demolition.png")));
        destroyHouseButton.setOnAction(this::destroyHouse);

    }

    /**
     * The Longest method we have that hardcode chooses appropriate
     * text to display on the tileField TextArea
     * @param currentTile: The property associated with the selected tile
     */

    public void checkTile(TileInterface currentTile){

        //Clear the TextArea and display the tile name
        tileField.clear();
        String tileName = currentTile.getTileName();
        tileField.appendText("Property Name: " + tileName + "\n");

        //If the tile is a buyable tile, display the appropriate text
        if(myDisplay.getEngine().isCurrentTileBuyable()){

            //Display the Mortgage and Ownership Info
            if(currentTile.getTheBuyableCard().getOwner()!=null){
                String ownerInfo = Integer.toString(currentTile.getTheBuyableCard().getOwner().getID());
                if(currentTile.getTheBuyableCard().checkMortgaged()){
                    ownerInfo+= " (MORTGAGED)";
                }
                tileField.appendText("Owner: " + ownerInfo+ "\n");
            }

            int Rent = currentTile.getTheBuyableCard().getCurrentRent();

            //is Public Transport
            tileField.appendText("Current Rent:  "+Rent+"\n");

            //is Water or Electricity
            if(currentTile.getTheBuyableCard().getBuyableType()==3)
                tileField.appendText("Rent Times The Last Dice Value");

            //is Property
            if(currentTile.getTheBuyableCard().getBuyableType()==1) {
                tileField.appendText("  1 House: " + (Rent * 2) + "\n");
                tileField.appendText("  2 Houses: " + (Rent * 4) + "\n");
                tileField.appendText("  3 Houses: " + (Rent * 8) + "\n");
                tileField.appendText("  4 Houses: " + (Rent * 16) + "\n");
                tileField.appendText("  Hotel: " + (Rent * 32) + "\n");
            }
        }
    }

    /**
     * Bellow can be found the action events for all the Buttons
     */

    private void mortgage(ActionEvent actionEvent) {
        if(!checkIfMortgaged())
            myDisplay.getEngine().mortgagePut();
        else
            myDisplay.getEngine().mortgageLift();

        updateUIs();
    }

    private void buy(ActionEvent actionEvent) {
        myDisplay.getEngine().buy();
        updateUIs();
    }

    private void buildHouse(ActionEvent actionEvent){
        myDisplay.getEngine().buyHouse();
        updateTileVisual();
        updateUIs();
    }

    private void buildHotel(ActionEvent actionEvent){
        myDisplay.getEngine().buyHotel();
        updateTileVisual();
        updateUIs();
    }

    private void destroyHouse(ActionEvent actionEvent){
        myDisplay.getEngine().sellHouse();
        updateTileVisual();
        updateUIs();
    }

    private void destroyHotel(ActionEvent actionEvent){
        myDisplay.getEngine().sellHotel();
        updateTileVisual();
        updateUIs();
    }

    /**
     * Bellow can be found four helper methods to decrease duplication
     */

    private boolean checkIfMortgaged(){
        return myDisplay.getEngine().getCurrentTile().getTheBuyableCard()!=null &&
                myDisplay.getEngine().getCurrentTile().getTheBuyableCard().checkMortgaged();
    }

    private void updateUIs() {
        myDisplay.updatePlayerStatusUI();
        myDisplay.updateFeedbackUI();
        checkTile(myDisplay.getEngine().getCurrentTile());
    }

    private void updateTileVisual() {
        boardUI board = myDisplay.getBoard();
        if(myDisplay.getEngine().getCurrentTile().getTheBuyableCard().getBuyableType()==1) {
            PropertyCard pCard = (PropertyCard) myDisplay.getEngine().getCurrentTile()
                .getTheBuyableCard();
            board.updateBuildings(myDisplay.getEngine().getActiveTileIndex(),
                pCard.getNumberOfHouses(), pCard.hasHotel());
        }
    }

    private void buttonAdjuster(Button button, ImageView im){
        button.setPrefWidth(width/3);
        button.setPrefHeight(40);

        im.setFitWidth(30);
        im.setFitHeight(30);
        button.setGraphic(im);
    }

    /**
     * Toggles the Class to dark mode
     * @param dark & alt: two strings that define Style preferences
     */

    public void toggleDark(String dark, String alt){

        statusLabel.setStyle(alt);
        buyButton.setStyle(dark);
        mortgageButton.setStyle(dark);
        buildHotelButton.setStyle(dark);
        buildHouseButton.setStyle(dark);
        destroyHouseButton.setStyle(dark);
        destroyHotelButton.setStyle(dark);

        tileField.setStyle(dark);
        this.setStyle(dark);

        Region content = (Region) tileField.lookup(".content");
        content.setStyle(dark);
    }
}
