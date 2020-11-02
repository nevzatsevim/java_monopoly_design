package Engine.Action;
import Engine.LuckCards.LuckCardQueue;
import Engine.Notifications.Notification;
import Engine.Player.PlayerInterface;

/**
 * Draws luck card.
 * @Author: Cemal Yagcioglu
 */
public class DrawLuckCard implements ActionInterface{

  public DrawLuckCard(){

  }

  /**
   * Draws luck card when called.
   * @param actOn
   */
  @Override
  public void actItOn(PlayerInterface actOn) {
    String cardInfo = LuckCardQueue.draw();
    Notification.luckCard(actOn.getID(),cardInfo);
    LuckCardQueue.act(actOn);
  }

}
