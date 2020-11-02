package Engine.Dice;
import Engine.Player.PlayerInterface;

/**
 * This die interface is for objects used in dice portfolio.
 * They allow rolling, acting the action defined and getting the value assigned.
 *
 * @Author: Cemal Yagcioglu
 */
public interface DieInterfaceDelete {

  /**
   * Rolls the die.
   */
  void roll();

  /**
   * Acts on the dice thrower with defined actions.
   * @param diceThrower
   */
  void actOnIt(PlayerInterface diceThrower);

  /**
   * Returns the number assigned to the die's last rolled face.
   * @return
   */
  int getValue();

}
