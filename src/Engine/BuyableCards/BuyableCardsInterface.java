package Engine.BuyableCards;

import Engine.Player.PlayerInterface;

/**
 * This is the interface for buyable cards on the game.
 * @Author: Cemal Yagcioglu
 */
public interface BuyableCardsInterface {

  /**
   * Buys the card.
   * @param buyer
   */
  void buy(PlayerInterface buyer);

  /**
   * Trades the selected card with another player.
   * @param buyingPlayer
   * @param priceNegotiated
   */
  void sellToAnotherPlayer(PlayerInterface buyingPlayer, int priceNegotiated);

  /**
   * When a player lands on the tile with the card, this method does what is necessary
   * like paying the rent.
   * @param landingPlayer
   */
  void landOn(PlayerInterface landingPlayer);

  /**
   * Mortgages the card.
   */
  void mortgagePut();

  /**
   * Lifts the mortgage on the card.
   */
  void mortgageLift();

  /**
   * Called when owner of the card has lost the game.
   */
  void ownerHasLost();

  /**
   * Gives instant sell value of the card.
   * @return instant sell value.
   */
  int getInstantSellWorth();

  /**
   * Returns the buyable type int defined for the card
   * (property,public transport etc)
   * @return buyable type
   */
  int getBuyableType();

  /**
   * Returns the group index the card belongs to.
   * @return group index
   */
  int getGroupIndex();

  /**
   * Returns the base rent for the property.
   * @return base rent
   */
  int getBaseRent();

  /**
   * Returns the current rent.
   * @return current rent.
   */
  int getCurrentRent();

  /**
   * Returns the property price.
   * @return property price
   */
  int getPropertyPrice();

  /**
   * Returns whether the card is mortgaged.
   * @return is mortgaged?
   */
  boolean checkMortgaged();

  /**
   * Returns the card name.
   * @return card name.
   */
  String getName();

  /**
   * Returns the owner of the card.
   * @return card owner.
   */
  PlayerInterface getOwner();







}
