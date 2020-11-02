package Engine.Action;

import Engine.Player.PlayerInterface;

/**
 * Player gets 'get-out-of-the-jail' card.
 * @Author: Cemal Yagcioglu
 */
public class OutOfJailCard implements ActionInterface {

  public OutOfJailCard(){

  }

  /**
   * Player gets 'get-out-of-the-jail' card when called.
   * @param actOn
   */
  @Override
  public void actItOn(PlayerInterface actOn) {
    actOn.setHasOutOfJailCard();
  }
}
