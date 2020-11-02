package Engine.LuckCards;

import Engine.Action.ActionInterface;
import Engine.Player.PlayerInterface;

/**
 * This class implements the luck Card Interface.
 * Explanations for the public methods are provided on the interface.
 * @Author: Cemal Yagcioglu
 */
public class LuckCard implements LuckCardInterface {
  private ActionInterface assignedAction;
  String cardText;

  public LuckCard(String cardText, ActionInterface assignedAction){
    this.cardText =cardText;
    this.assignedAction =assignedAction;
  }

  @Override
  public void actThePremiseOn(PlayerInterface pickerPlayer) {
    assignedAction.actItOn(pickerPlayer);
  }

  @Override
  public String getText(){
    return cardText;
  }
}