package Engine.Tile;

import Engine.Action.ActionInterface;
import Engine.BuyableCards.BuyableCardsInterface;
import Engine.Player.PlayerInterface;

/**
 * Allows implementation of Tile squares on the Monopoly Board.
 * The interface
 */
public interface TileInterface {

  /**
   * Acts the premise of the Action assigned to the Tile.
   * Returns null if no action is assigned.
   * @param playerLanded
   */
  void actThePremise(PlayerInterface playerLanded);

  /**
   * Returns the tile's name.
   * @return tile name.
   */
  String getTileName();

  /**
   * Returns the buyable card assigned to the tile.
   * Returns null if no buyable card is assigned.
   * @return buyable card assigned.
   */
  BuyableCardsInterface getTheBuyableCard();

}
