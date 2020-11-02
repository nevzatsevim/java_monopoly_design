package Engine.Action;

import Engine.Player.PlayerInterface;

/**
 * Player pays an amount per card owned.
 * @Author: Cemal Yagcioglu
 */
public class LandTax implements ActionInterface {
  int pricePerLand;
  public LandTax(int pricePerLand){
    this.pricePerLand=pricePerLand;
  }

  /**
   * Player pays an amount per card owned when called.
   * @param actOn
   */
  @Override
  public void actItOn(PlayerInterface actOn) {
    actOn.deductMoney(actOn.getNumberOfCards()*pricePerLand);
  }
}
