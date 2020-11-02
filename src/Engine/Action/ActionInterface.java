package Engine.Action;
import Engine.Player.PlayerInterface;


/**
 * This action interface allows implementations of new actions.
 * For current implementation, these actions are assigned to
 *  1.LuckCards
 *  2.Tiles (acts when a player lands on it)
 *  3.Die face
 * @Author: Cemal Yagcioglu
 */
public interface ActionInterface {

  /**
   * Acts the action defined.
   * @param actOn
   */
  void actItOn(PlayerInterface actOn);
}
