package Engine.BuyableCards;

/**
 * PropertyCardInterface allows for implementation of Tiles with BuyableCards
 * that offer managing of house and hotel buildings.
 * @Author: Cemal Yagcioglu
 */

public interface PropertyCardInterface extends BuyableCardsInterface{

  /**
   * Builds a house on the card's tile.
   */
  void buyHouse();

  /**
   * Sells the house built on the card's tile.
   */
  void sellHouse();

  /**
   * Builds a hotel on the card's tile.
   */
  void buyHotel();

  /**
   * Sells the hotel built on the card's tile.
   */
  void sellHotel();



}
