package Engine.Action;

import Engine.Notifications.Notification;
import Engine.Player.PlayerInterface;

/**
 * Player goes to the position given.
 * @Author: Cemal Yagcioglu
 */
public class SetPosition implements ActionInterface {
  private int newPosition;

  public SetPosition(int newPosition) {
    this.newPosition = newPosition;
  }

  /**
   * layer goes to the position given when called.
   * @param actOn
   */
  @Override
  public void actItOn(PlayerInterface actOn) {
    actOn.setPosition(newPosition);
    Notification.basicInfo(actOn.getID(), " new Position: "+newPosition);
  }
}
