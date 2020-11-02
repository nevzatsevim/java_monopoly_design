package Engine.LuckCards;

import Engine.Player.PlayerInterface;

/**
 * This interface is for implementation of luck cards in the game.
 * @Author: Cemal Yagcioglu
 */
public interface LuckCardInterface {

  /**
   * Acts the premise on the luck card.
   * @param pickerPlayer
   */
  void actThePremiseOn(PlayerInterface pickerPlayer);

  /**
   * Returns the text written on the card.
   * @return text on the card.
   */
  String getText();

}
