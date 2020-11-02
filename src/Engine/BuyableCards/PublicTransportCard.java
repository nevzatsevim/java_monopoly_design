package Engine.BuyableCards;

/**
 * Defines the BuyableCardsInterface implementaion for the Public Transport cards in the game.
 *
 * @Author: Cemal Yagcioglu
 */

public class PublicTransportCard extends BuyableCardSuperclass implements PublicCardInterface{
  private int BUYABLE_TYPE_TRANSPORT = 2;

  public PublicTransportCard(String propertyName, int propertyGroupIndex, int propertyGroupMaxSize,
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
    int multiplyBy = (int) Math.pow(2,currentOwnedGroupSize-1);
    currentRent = baseRent*multiplyBy;
  }

  @Override
  public int getBuyableType() {
    return BUYABLE_TYPE_TRANSPORT;
  }
}
