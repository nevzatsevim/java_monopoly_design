package Engine.LuckCards;

import Engine.Player.PlayerInterface;
import java.util.LinkedList;

/**
 * LuckCardQueue queues the LuckCardInterface type cards in it.
 * It manages the Luck Card Desk and allow interactions with it.
 * @Author: Cemal Yagcioglu
 */

public class LuckCardQueue {
  private static LinkedList<LuckCardInterface> luckCardQueue;
  private static LuckCardInterface topCard;

  public LuckCardQueue(){
    luckCardQueue = new LinkedList<>();

  }

  /**
   * Adds luck card to queue.
   * @param luckCard
   */
  public void addLuckCard(LuckCardInterface luckCard){
    luckCardQueue.add(luckCard);
  }

  /**
   * Draws the luck card on the top of the queue.
   * @return the text of the top card.
   */
  public static String draw(){
    topCard = luckCardQueue.getFirst();
    return topCard.getText();
  }

  /**
   * Acts the drawen cards defined action.
   * @param drawingPlayer
   */
  public static void act(PlayerInterface drawingPlayer){
    topCard.actThePremiseOn(drawingPlayer);
    luckCardQueue.removeFirst();
    luckCardQueue.addLast(topCard);
    topCard = luckCardQueue.getFirst();
  }


}
