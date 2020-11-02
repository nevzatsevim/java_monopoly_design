package Engine.Action;

import Engine.Notifications.Notification;
import Engine.Player.PlayerInterface;

/**
 * Player pays some percent of his/her money.
 * @Author: Cemal Yagcioglu
 */
public class PayPercent implements ActionInterface{
  int percent;

  public PayPercent(int percent){
    this.percent=percent;
  }

  /**
   * Player pays some percent of his/her money when called.
   * @param actOn
   */
  @Override
  public void actItOn(PlayerInterface actOn) {
    int moneyLost = actOn.getCurrentMoney()*(percent)/100;
    actOn.deductMoney(moneyLost);
    Notification.basicInfo(actOn.getID()," lost percent:" + percent+ " = "+moneyLost);
  }

}
