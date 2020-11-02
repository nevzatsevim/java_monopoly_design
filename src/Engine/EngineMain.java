package Engine;

import Data.BackendReader.ReadXML_Die;
import Data.BackendReader.ReadXML_luckCard;
import Data.BackendReader.ReadXML_player;
import Data.BackendReader.ReadXML_tiles;
import Engine.BuyableCards.BuyableCardsInterface;
import Engine.BuyableCards.PropertyCard;
import Engine.Dice.DicePortfolio;
import Engine.LuckCards.LuckCardQueue;
import Engine.Notifications.Notification;
import Engine.Player.PlayerInterface;
import Engine.Tile.TileInterface;
import Engine.Tile.TilePortfolio;
import java.util.ArrayList;
import java.util.List;

/**
 * Implements the methods on the Engine Interface.
 * Mainly responsible for the rules and engine interactions of the game.
 * @Author: Cemal Yagcioglu
 */

public class EngineMain implements EngineInterface{

  private int currentPlayerIndex = 0;
  private ArrayList<PlayerInterface> playersList = new ArrayList<PlayerInterface>();
  private PlayerInterface currentPlayer;
  private TilePortfolio allTiles;
  private DicePortfolio dicePortfolio;
  private LuckCardQueue luckCardQueue;
  private TileInterface currentActiveTile;
  private boolean canRoll = true;
  private boolean gameIsOver = false;
  private final int JAIL_FINE = 50;


  public void initiate(String gameTilesPath, String playersPath,String dicePath, String luckCardsPath, int numberOfPlayers){
    playersList = ReadXML_player.ReadXML(numberOfPlayers,playersPath);
    dicePortfolio = ReadXML_Die.ReadXML(dicePath);
    luckCardQueue = ReadXML_luckCard.ReadXML(luckCardsPath);
    allTiles = ReadXML_tiles.ReadXML(gameTilesPath);
    currentPlayer = playersList.get(0);
    currentActiveTile = allTiles.getTile(0);
  }

  @Override
  public void rollDice() {
    if(canRoll) {
      dicePortfolio.roll(currentPlayer);
      Notification.rolled(getDiceValue(),currentPlayer.getID());
      landOnTheNewPosition();
      currentActiveTile = allTiles.getTile(currentPlayer.getCurrentPositionIndex());
      if (!dicePortfolio.throwAgain()) {
        canRoll = false;
      }
    }
  }

  private void landOnTheNewPosition() {
    TileInterface landedOnTile = allTiles.getTile(currentPlayer.getCurrentPositionIndex());
    BuyableCardsInterface landedOnBuyableCard = landedOnTile.getTheBuyableCard();
    if(landedOnBuyableCard!=null){
      landedOnBuyableCard.landOn(currentPlayer);
    }
    landedOnTile.actThePremise(currentPlayer);
    checkIfPlayerLost();
  }



  @Override
  public void buy() {
    if(!canRoll || dicePortfolio.throwAgain()) {
      BuyableCardsInterface buyableCard = allTiles.getTile(currentPlayer.getCurrentPositionIndex()).getTheBuyableCard();
      if(buyableCard!=null) {
        buyableCard.buy(currentPlayer);
      }
    }
  }

  @Override
  public void sell(PlayerInterface buyer, int priceNegotiated, BuyableCardsInterface cardToBeSold) {
    cardToBeSold.sellToAnotherPlayer(buyer,priceNegotiated);
  }

  @Override
  public void buyHouse() {
    if(canRoll && ownerIsTheActiveCurrentPlayer() &&
        isCurrentTilePropertyCard()) {
        PropertyCard currentPropertyCard = (PropertyCard) currentActiveTile.getTheBuyableCard();
        currentPropertyCard.buyHouse();
      }
    }


  @Override
  public void sellHouse() {
    if(ownerIsTheActiveCurrentPlayer() && isCurrentTilePropertyCard()) {
      PropertyCard currentPropertyCard = (PropertyCard) currentActiveTile.getTheBuyableCard();
      currentPropertyCard.sellHouse();
    }
  }

  @Override
  public void buyHotel() {
    if(canRoll && ownerIsTheActiveCurrentPlayer() && isCurrentTilePropertyCard()) {
      PropertyCard currentPropertyCard = (PropertyCard) currentActiveTile.getTheBuyableCard();
      currentPropertyCard.buyHotel();
    }
  }

  @Override
  public void sellHotel() {
    if(ownerIsTheActiveCurrentPlayer() && isCurrentTilePropertyCard()) {
      PropertyCard currentPropertyCard = (PropertyCard) currentActiveTile.getTheBuyableCard();
      currentPropertyCard.sellHotel();
    }
  }

  @Override
  public void mortgagePut() {
    if(ownerIsTheActiveCurrentPlayer()) {
      BuyableCardsInterface currentBuyableCard = currentActiveTile.getTheBuyableCard();
      currentBuyableCard.mortgagePut();
    }
  }

  @Override
  public void mortgageLift() {
    if(canRoll && ownerIsTheActiveCurrentPlayer()) {
      BuyableCardsInterface currentBuyableCard = currentActiveTile.getTheBuyableCard();
      currentBuyableCard.mortgageLift();
    }
  }

  public boolean isCurrentTileBuyable(){
    return currentActiveTile.getTheBuyableCard()!=null;
  }

  public void setTurnToNextPlayer(){
    if(!canRoll && currentPlayer.getCurrentMoney()>=0) {
      int totalNumberOfPlayers = playersList.size();
      currentPlayerIndex = (currentPlayerIndex + 1) % totalNumberOfPlayers;
      currentPlayer = playersList.get(currentPlayerIndex);
      canRoll = true;
    }
  }

  public void clickedOnTheTile(int tileClickedIndex){
    int currentClickedTileIndex = tileClickedIndex;
    currentActiveTile = allTiles.getTile(currentClickedTileIndex);
  }

  public PlayerInterface getCurrentPlayer(){
    return currentPlayer;
  }

  public int getCurrentPlayerId(){
    return currentPlayerIndex+1;
  }

  public ArrayList<PlayerInterface> getPlayersList() {
    return playersList;
  }

  public TileInterface getCurrentTile(){
    return currentActiveTile;
  }

  public boolean getCanRoll() { return canRoll;}

  public List<Integer> getDiceValue(){ return dicePortfolio.getLastDiceValues();}

  public TileInterface getTileWithIndex(int index){ return allTiles.getTile(index); }

  private boolean ownerIsTheActiveCurrentPlayer() {
    if (currentActiveTile.getTheBuyableCard() != null) {
      return currentPlayer == currentActiveTile.getTheBuyableCard().getOwner();
    }
    return false;
  }

  private void checkIfPlayerLost(){
    if(currentPlayer.getNetworth()<0){
      Notification.playerLost(currentPlayer.getID());
      currentPlayer.playerHasLost();
      playersList.remove(currentPlayer);
      if(playersList.size()<=1){
        gameIsOver=true;
      }
      currentPlayerIndex = (currentPlayerIndex+1)%playersList.size();
      currentPlayer = playersList.get(currentPlayerIndex);
    }
  }

  public boolean isGameOver(){
    return gameIsOver;
  }

  public int getActiveTileIndex(){
    for(int i=0; i<allTiles.getSize();i++){
      if(allTiles.getTile(i) == currentActiveTile){
        return i;
      }
    }
    return -1;
  }

  public void payJailFine(){
    if(currentPlayer.getJailTerm()!=0) {
      currentPlayer.setJailTerm(0);
      currentPlayer.deductMoney(JAIL_FINE);
    }
  }

  private boolean isCurrentTilePropertyCard() {
    if(currentActiveTile.getTheBuyableCard()!=null) {
      return currentActiveTile.getTheBuyableCard().getBuyableType() == 1;
    }
    return false;
  }
}
