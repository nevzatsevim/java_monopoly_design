package Engine.Dice;

import Engine.Action.ActionInterface;
import Engine.Player.PlayerInterface;
import java.util.ArrayList;
import java.util.Random;

/**
 * Die class is defined with possible actions and values assigned to them
 * It is used to roll randomly and pick one of the actions and acted on it.
 * The methods are explained in the interface.
 *
 * @Author: Cemal Yagcioglu
 */

public class Die implements DieInterfaceDelete{

  private ArrayList<ActionInterface> possibleActions;
  private ActionInterface actionChosen;
  private ArrayList<Integer> valuesAssignedToActions;
  private int valueAssignedToChosenAction;


  public Die(ArrayList<ActionInterface> possibleActions, ArrayList<Integer> valuesAssignedToActions){
    this.possibleActions=possibleActions;
    this.valuesAssignedToActions=valuesAssignedToActions;
  }

  @Override
  public void roll() {
    int chosenSide = new Random().nextInt(possibleActions.size());
    actionChosen = possibleActions.get(chosenSide);
    valueAssignedToChosenAction = valuesAssignedToActions.get(chosenSide);
  }

  @Override
  public void actOnIt(PlayerInterface diceThrower) {
    actionChosen.actItOn(diceThrower);
  }

  @Override
  public int getValue() {
    return valueAssignedToChosenAction;
  }
}
