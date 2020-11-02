package view;

import Engine.BuyableCards.BuyableCardsInterface;
import Engine.Player.PlayerInterface;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * This class is in extends Stage to create a pop-up that allows
 * the players to trade their properties
 * @author Nevzat Sevim, Cemal Yagcioglu
 */

public class tradePopUp extends Stage {

    private ComboBox propertiesBox, player1Box, player2Box;
    private Button tradeButton;
    private TextField costField;
    private display myDisplay;
    private ArrayList<PlayerInterface> playersList;
    private PlayerInterface chosenSeller;
    private PlayerInterface chosenBuyer;
    private static final int width = 200;

    public tradePopUp(display newDisplay){
        myDisplay = newDisplay;

        setBoxes();

        tradeButton = new Button();
        tradeButton.setText("Trade");
        tradeButton.setPrefWidth(width);
        tradeButton.setOnAction(this::trade);

        costField = new TextField();

        VBox root = new VBox();
        root.getChildren().addAll(new Label("Trade from:"), player1Box, new Separator(),
                new Label("Trade to:"), player2Box, new Separator(),
                new Label("Trading:"), propertiesBox, new Separator(),
                new Label("Price:"), costField, new Separator(),
                tradeButton);

        Scene scene = new Scene(root, width,250);
        this.setScene(scene);
        this.show();

    }

    private void setBoxes() {
        player1Box = new ComboBox();
        player1Box.setPromptText("Choose Seller");
        player1Box.setPrefWidth(width);

        player2Box =  new ComboBox();
        player2Box.setPromptText("Choose Buyer");
        player2Box.setPrefWidth(width);

        propertiesBox = new ComboBox();
        propertiesBox.setPromptText("Choose Property");
        propertiesBox.setPrefWidth(width);

        playersList = myDisplay.getEngine().getPlayersList();
        for(PlayerInterface player : playersList) {
            player1Box.getItems().add(player.getID());
        }
        player1Box.setOnAction(this::sellerIsSet);
    }

    private void trade(ActionEvent actionEvent) {
        if(costField.getText()!=null &&
                player2Box.getSelectionModel().getSelectedItem()!=null &&
                propertiesBox.getSelectionModel().getSelectedItem()!=null){

            int costNegotiated = Integer.parseInt(costField.getText());
            int chosenBuyerID = (int) player2Box.getSelectionModel().getSelectedItem();
            int selectedCardIndex =  propertiesBox.getSelectionModel().getSelectedIndex();

            chosenBuyer = playersList.get(chosenBuyerID-1);

            BuyableCardsInterface cardToBeSold = chosenSeller.getCard(selectedCardIndex);

            myDisplay.getEngine().sell(chosenBuyer,costNegotiated,cardToBeSold);
            myDisplay.updatePlayerStatusUI();

            myDisplay.getTileFiled().checkTile(myDisplay.getEngine().getCurrentTile());
            myDisplay.updateFeedbackUI();

            propertiesBox.setValue(null);
        }
    }

    private void sellerIsSet(Event Event){
        playersList = myDisplay.getEngine().getPlayersList();

        for(PlayerInterface player : playersList) {
            if(player.getID()!=(int) player1Box.getSelectionModel().getSelectedItem())
                player2Box.getItems().add(player.getID());
        }

        int chosenSellerID = (int) player1Box.getSelectionModel().getSelectedItem();
        chosenSeller = playersList.get(chosenSellerID-1);

        propertiesBox.getItems().setAll(chosenSeller.getOwnedCardsInfo());
    }


}
