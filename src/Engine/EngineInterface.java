package Engine;

import Engine.BuyableCards.BuyableCardsInterface;
import Engine.Player.PlayerInterface;
import Engine.Tile.TileInterface;
import java.util.ArrayList;
import java.util.List;

/**
 * Engine interface allows for building of different engines
 * with methods defined for interaction of mainly engine objects
 * and the UI.
 * @Cemal Yagcioglu
 */
public interface EngineInterface {

  /**
   * Initates the Engine with the data paths.
   * @param gameTilesPath
   * @param playersPath
   * @param dicePath
   * @param luckCardsPath
   * @param numberOfPlayers
   */
  void initiate(String gameTilesPath, String playersPath,String dicePath, String luckCardsPath, int numberOfPlayers);

  /**
   * Rolls the dice.
   */
  void rollDice();

  /**
   * Buys the current active tile.
   */
  void buy();

  /**
   * Sell is used for trading owned cards.
   * @param buyer
   * @param priceNegotiated
   * @param cardToBeSold
   */
  void sell(PlayerInterface buyer, int priceNegotiated, BuyableCardsInterface cardToBeSold);

  /**
   * Buys house for the chosen card.
   */
  void buyHouse();
  /**
   * Sells house for the chosen card.
   */
  void sellHouse();
  /**
   * Buys hotel for the chosen card.
   */
  void buyHotel();
  /**
   * Sells hotel for the chosen card.
   */
  void sellHotel();
  /**
   * Mortgages the card.
   */
  void mortgagePut();

  /**
   * Lifts the mortgage on the card.
   */
  void mortgageLift();

  /**
   * Allows current active tile to be changed by the given index.
   * @param clickedTileIndex
   */
  void clickedOnTheTile(int clickedTileIndex);

  /**
   * Sets turn to the next player.
   */
  void setTurnToNextPlayer();

  /**
   * Pays the jail fine;
   */
  void payJailFine();

  /**
   * Checks if the current active tile is buyable type.
   * @return is it buyable?
   */
  boolean isCurrentTileBuyable();

  /**
   * Returns the current active player.
   * @return active player.
   */
  PlayerInterface getCurrentPlayer();

  /**
   * Returns the id of the current player.
   * @return current player id.
   */
  int getCurrentPlayerId();

  /**
   * Returns the current Tile.
   * @return current Tile.
   */
  TileInterface getCurrentTile();

  /**
   * Returns the players List
   * (usually necessary for the visualizer interactions)
   * @return
   */
  ArrayList<PlayerInterface> getPlayersList();

  /**
   * Checks whether the current player is allowed to roll the dice.
   * @return current player can roll?
   */
  boolean getCanRoll();

  /**
   * Returns the last dice values
   * @return last dice values
   */
  List<Integer> getDiceValue();

  /**
   * Returns the tile with the chosen index.
   * @param index
   * @return tile with index.
   */
  TileInterface getTileWithIndex(int index);

  /**
   * Returns whether the game is over.
   * @return is game over?
   */
  boolean isGameOver();

  /**
   * Returns the active tile index.
   * @return active tile index.
   */
  int getActiveTileIndex();
}
