package Engine.Action;
import Engine.Notifications.Notification;
import Engine.Player.PlayerInterface;


/**
 * Player goes to Jail when Acted.
 * @Author: Cemal Yagcioglu
 */
public class GoToJail implements ActionInterface {
  private static final int JAIL_POSITION=10;
  private static final int JAIL_TERM=3;

  public GoToJail() {
  }

  /**
   * Player goes to Jail when called.
   * @param actOn
   */
  @Override
  public void actItOn(PlayerInterface actOn) {
    actOn.setPosition(JAIL_POSITION);
    actOn.setJailTerm(JAIL_TERM);
    Notification.jail(actOn.getID());
  }

}
