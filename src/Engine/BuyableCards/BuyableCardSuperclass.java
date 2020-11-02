package Engine.BuyableCards;

import Engine.Notifications.Notification;
import Engine.Player.PlayerInterface;

/**
 * This Buyable Super Class implements BuyableCardsInterface.
 * It is used to implement Property Cards, Public Transportation cards
 * and Public Utility Cards.
 *
 * The methods explanation provided in the interface is apt for implemented methods.
 * The remaining ones are explained.
 *
 * @Author: Cemal Yagcioglu
 */
public abstract class BuyableCardSuperclass implements BuyableCardsInterface {
  protected final static double MORTGAGE_LIFT_PRICE_MULTIPLIER = 1.1;
  protected String cardName;
  protected int propertyGroupIndex;
  protected int propertyPrice;
  protected int baseRent;
  protected PlayerInterface owner;
  protected boolean hasWholeSet;
  protected boolean anyMortgagedInGroup;
  protected int propertyGroupMaxSize;
  protected int currentRent = 0;
  protected int currentOwnedGroupSize;
  protected boolean isMortgaged;


  public BuyableCardSuperclass(
      String cardName, int propertyGroupIndex, int propertyPrice,
      int baseRent, int propertyGroupMaxSize) {
    this.cardName = cardName;
    this.propertyGroupIndex = propertyGroupIndex;
    this.propertyPrice = propertyPrice;
    this.baseRent = baseRent;
    this.propertyGroupMaxSize =propertyGroupMaxSize;
    this.anyMortgagedInGroup = false;
    this.isMortgaged = false;
    this.currentRent=baseRent;
  }

  public void buy(PlayerInterface player){
    if(player.getCurrentMoney()>propertyPrice & owner==null) {
      player.deductMoney(propertyPrice);
      player.addCard(this);
      owner = player;
      ownerChanged();
      Notification.buy(owner.getID(),cardName,propertyPrice);
    }
  }

  public void sellToAnotherPlayer(PlayerInterface buyerPlayer, int priceNegotiated){
    if(buyerPlayer.getCurrentMoney()>priceNegotiated){
      owner.addMoney(priceNegotiated);
      owner.removeCard(this);
      buyerPlayer.deductMoney(priceNegotiated);
      buyerPlayer.addCard(this);
      Notification.sell(buyerPlayer.getID(),owner.getID(),cardName,priceNegotiated);
      owner = buyerPlayer;
      ownerChanged();

    }

  }

  public void landOn(PlayerInterface landingPlayer){
    if(owner!=null && owner!=landingPlayer) {
      if (landingPlayer.getNetworth() >= currentRent){
        owner.addMoney(currentRent);
        landingPlayer.deductMoney(currentRent);
        Notification.rent(currentRent, owner.getID(), landingPlayer.getID());}
      else{
        Notification.rent(landingPlayer.getNetworth(),owner.getID(), landingPlayer.getID());
        owner.addMoney(landingPlayer.getNetworth());
        landingPlayer.deductMoney(currentRent);
      }
    }
  }

  public abstract void mortgagePut();

  public void mortgageLift(){
    int price = (int) ((propertyPrice/2)* MORTGAGE_LIFT_PRICE_MULTIPLIER);
    if(isMortgaged && owner.getCurrentMoney()>price){
      isMortgaged = false;
      owner.setMortgagedCount(propertyGroupIndex, owner.getMortgagedCount(propertyGroupIndex)-1);
      isMortgaged = false;
      updateCurrentRent();
      updateIsAnyMortgaged();
      Notification.mortgageLift(owner.getID(),cardName,price);
    }
  }

  public abstract int getInstantSellWorth();

  protected abstract void updateCurrentRent();

  private void updateOwnedGroupSize(){
    currentOwnedGroupSize = owner.getOwnedPropertyGroupSize(propertyGroupIndex);
    if(currentOwnedGroupSize==propertyGroupMaxSize && !anyMortgagedInGroup){
      hasWholeSet = true;
    }
  }

  protected void updateIsAnyMortgaged(){
    int mortgagedCount = owner.getMortgagedCount(propertyGroupIndex);
    if(mortgagedCount>0){
      anyMortgagedInGroup =true;
    }
  }

  private void ownerChanged(){
    updateIsAnyMortgaged();
    updateOwnedGroupSize();
    updateCurrentRent();
  }

  public int getGroupIndex(){
    return propertyGroupIndex;
  }

  @Override
  public String getName() {
    return cardName;
  }

  public PlayerInterface getOwner(){
    return owner;
  }

  public int getBaseRent(){
    return baseRent;
  }

  public boolean checkMortgaged(){
    return isMortgaged;
  }

  public int getCurrentRent() {
    return currentRent;
  }

  public int getPropertyPrice(){
    return propertyPrice;
  }

  public void ownerHasLost(){
    owner = null;
    this.anyMortgagedInGroup = false;
    this.isMortgaged = false;
  }
}
