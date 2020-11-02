package Engine.Dice;
import Engine.Action.ActionInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import Engine.Player.PlayerInterface;

/**
 * Dice portfolio is used for the collection of the dice.
 * After dice are read from the data file, the dice portfolio is inititated
 * and it offers methods for simple interaction with dice. It also handles
 * 'inter-die' interactions such as throwing double.
 * @Author: Cemal Yagcioglu
 */

public class DicePortfolio {
  private final int FREQUENCY_CONDITION_THRESHOLD = 2;
  private ArrayList<DieInterfaceDelete> dice;
  private int conditionMetInRow =0;
  private int frequencyCondition;
  private int conditionMetInRowThreshold;
  private ActionInterface frequencyMetAction;
  private ActionInterface conditionMetPassedLimitAction;
  private List<Integer> lastDiceValues = new ArrayList<>();
  private boolean throwAgain;


  public DicePortfolio(ArrayList<DieInterfaceDelete> dice,int frequencyCondition, ActionInterface frequencyMetAction, int conditionMetInRowThreshold, ActionInterface conditionMetPassedLimitAction){
    this.dice=dice;
    this.frequencyCondition=frequencyCondition;
    this.frequencyMetAction = frequencyMetAction;
    this.conditionMetInRowThreshold=conditionMetInRowThreshold;
    this.conditionMetPassedLimitAction=conditionMetPassedLimitAction;
  }


  /**
   * Rolls the dice in the portfolio.
   * Checks and updates jail terms.
   * Checks for frequency condition defined and acts if condition is met
   * @param diceThrower
   */
  public void roll(PlayerInterface diceThrower){
    lastDiceValues.clear();
    throwAgain = false;
    for(DieInterfaceDelete die : dice){
      die.roll();
      lastDiceValues.add(die.getValue());
      if(diceThrower.getJailTerm()==0) {
        die.actOnIt(diceThrower);
      }
    }
    jailTermUpdate(diceThrower);
    frequencyActionProcess(diceThrower);
    setLastDiceSum(diceThrower);
  }

  private void jailTermUpdate(PlayerInterface diceThrower) {
    if(diceThrower.getJailTerm()!=0) {
      int remainingJailTerm = diceThrower.getJailTerm() - 1;
      diceThrower.setJailTerm(remainingJailTerm);
    }
  }

  private void frequencyActionProcess(PlayerInterface diceThrower) {
    if(checkForFrequencyCondition(lastDiceValues)){
      conditionMetInRow +=1;
      if(conditionMetInRow>=conditionMetInRowThreshold){
        conditionMetPassedLimitAction.actItOn(diceThrower);
        conditionMetInRow=0;
      }
      else {
        throwAgain=true;
        frequencyMetAction.actItOn(diceThrower);
        if(diceThrower.getJailTerm()!=0) {
          diceThrower.setJailTerm(0);
        }
      }
    }
    else{
      conditionMetInRow=0;
    }
  }

  private void setLastDiceSum(PlayerInterface diceThrower) {
    int diceSum =0;
    for(int dieValue:lastDiceValues){
      diceSum += dieValue;
    }
    diceThrower.setLastDiceSum(diceSum);
  }

  private boolean checkForFrequencyCondition(List<Integer> diceValues){
    for(int dieValue : diceValues) {
      if (Collections.frequency(diceValues, dieValue) >= FREQUENCY_CONDITION_THRESHOLD) {
        return true;
      }
    }
    return false;
  }

  /**
   * Checks if the player should throw the dice again.
   * @return throw again.
   */
  public boolean throwAgain(){
    return throwAgain;
  }

  /**
   * Returns the last dice values thrown. Useful for some price calculations.
   * @return last dices thrown.
   */
  public List<Integer> getLastDiceValues(){
    return lastDiceValues;
    }

}
