package Engine.Action;

import Engine.Notifications.Notification;
import Engine.Player.PlayerInterface;

/**
 * Changes the position of the player when acted.
 * @Author: Cemal Yagcioglu
 */
public class ChangePosition implements ActionInterface {
  private int positionChange;
  private final int TABLE_SIZE = 40;
  private final int PASS_START_TILE_MONEY = 200;

  public ChangePosition(int positionChange) {
    this.positionChange = positionChange;
  }

  /**
   * Changes the position of the player when called.
   * @param actOn
   */
  @Override
  public void actItOn(PlayerInterface actOn) {
    int currentPosition = actOn.getCurrentPositionIndex();
    int newPosition = (currentPosition + positionChange)%TABLE_SIZE;
    actOn.setPosition(newPosition);
    if(currentPosition+positionChange>TABLE_SIZE){
      actOn.addMoney(PASS_START_TILE_MONEY);
      Notification.basicInfo(actOn.getID(), "Passed the GO and earned: "+PASS_START_TILE_MONEY);
    }
  }
}
