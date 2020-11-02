package Engine.BuyableCards;

/**
 *Defines the BuyableCardsInterface implementaion for the Public Transport cards in the game.
 * @Author:Cemal Yagcioglu
 */
public class PublicUtilityCard extends BuyableCardSuperclass implements PublicCardInterface {
  private int BUYABLE_TYPE_UTILITY= 3;
  public PublicUtilityCard(String propertyName, int propertyGroupIndex, int propertyGroupMaxSize,
      int propertyPrice, int baseRent){
    super(propertyName, propertyGroupIndex, propertyPrice, baseRent,
        propertyGroupMaxSize);
  }

  @Override
  public void mortgagePut(){
    if(!isMortgaged){
      owner.addMoney(propertyPrice/2);
      owner.setMortgagedCount(propertyGroupIndex, owner.getMortgagedCount(propertyGroupIndex)+1);
      isMortgaged = true;
      updateCurrentRent();
      updateIsAnyMortgaged();
    }
  }

  @Override
  public int getInstantSellWorth(){
    if(!isMortgaged) {
      return propertyPrice / 2;
    }
    return 0;
  }


  @Override
  protected void updateCurrentRent(){
    int diceSum = owner.getLastDiceSum();
    int multiplyBy = (int) Math.pow(2,currentOwnedGroupSize-1) * diceSum;
    currentRent = baseRent*multiplyBy;
  }

  @Override
  public int getBuyableType() {
    return BUYABLE_TYPE_UTILITY;
  }
}
