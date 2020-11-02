package Engine.Player;

import Engine.BuyableCards.BuyableCardsInterface;
import java.util.List;

/**
 * This interface allows implementation of a Player type object.
 * It defines all the necessary methods to actively interact with the Player data.
 * @Author: Cemal Yagcioglu
 */
public interface PlayerInterface {

  /**
   * Sets position of the player.
   * @param positionIndex
   */
  void setPosition(int positionIndex);

  /**
   * Adds money to the players money amount.
   * @param moneyAmount
   */
  void addMoney(int moneyAmount);

  /**
   * Deducst money from the players money amount.
   * @param moneyAmount
   */
  void deductMoney(int moneyAmount);

  /**
   * Sets the number of mortgaged properties within a property group.
   * @param propertyGroupIndex
   * @param mortgageCount
   */
  void setMortgagedCount(int propertyGroupIndex, int mortgageCount);

  /**
   * Adds card to Player's Buyable cards.
   * @param buyableCard
   */
  void addCard(BuyableCardsInterface buyableCard);

  /**
   * Removes a card from the Player's BuyableC ards.
   * @param removeCard
   */
  void removeCard(BuyableCardsInterface removeCard);

  /**
   * Called when player loses the game.
   */
  void playerHasLost();

  /**
   * Sets the jail term.
   * @param jailTerm
   */
  void setJailTerm(int jailTerm);

  /**
   * Gets the last dice sum of the player.
   * @param diceSum
   */
  void setLastDiceSum(int diceSum);

  /**
   * Gives player a 'get-out-of-jail' card.
   */
  void setHasOutOfJailCard();

  /**
   * Return current money.
   * @return current money.
   */
  int getCurrentMoney();

  /**
   * Returns current position index on the board.
   * @return position index.
   */
  int getCurrentPositionIndex();

  /**
   * Returns number of mortgaged properties within the property group.
   * @param propertyGroupIndex
   * @return number of mortgaged properties within the group.
   */
  int getMortgagedCount(int propertyGroupIndex);

  /**
   * Returns number of properties within the property group.
   * @param propertyGroupIndex
   * @return number of properties within the group.
   */
  int getOwnedPropertyGroupSize(int propertyGroupIndex);

  /**
   * Returns the sum of the last dice thrown.
   * @return dice sum
   */
  int getLastDiceSum();

  /**
   * Returns the player id.
   * @return player id.
   */
  int getID();

  /**
   * Returns the value of the assets.
   * @return assets value.
   */
  int getAssetsValue();

  /**
   * Returns the networth of the player.
   * @return networth.
   */
  int getNetworth();

  /**
   * Returns the left jail term (turn) of the player.
   * @return jail term.
   */
  int getJailTerm();

  /**
   * Returns number of cards owned.
   * @return number of cards owned.
   */
  int getNumberOfCards();

  /**
   * Returns information string about the cards owned (usually used by front end)
   * @return owned cards info.
   */
  List<String> getOwnedCardsInfo();

  /**
   * Returns the card with the index
   * @param cardIndex
   * @return card.
   */
  BuyableCardsInterface getCard(int cardIndex);





}
