package Engine.Action;

import Engine.Notifications.Notification;
import Engine.Player.PlayerInterface;

/**
 * Changes money amount of the player.
 * @Author: Cemal Yagcioglu
 */
public class ChangeMoney implements ActionInterface {
  private int moneyChange;
  public ChangeMoney(int moneyChange) {
    this.moneyChange = moneyChange;
  }

  /**
   * Changes money amount of the player when called.
   * @param actOn
   */
  @Override
  public void actItOn(PlayerInterface actOn) {
    actOn.addMoney(moneyChange);
    Notification.basicInfo(actOn.getID(), "gained " + moneyChange);
  }
}
