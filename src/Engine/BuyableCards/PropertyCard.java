package Engine.BuyableCards;
import Engine.Notifications.Notification;

/**
 * This class offers implementation of Buyable Type Property Classes.
 * They offer four additional functions/methods given in the PropertyCardInterface they implement.
 * These are: buy house, sell house, buy hotel, sell hotel.
 *
 * @Author:Cemal Yagcioglu
 */
public class PropertyCard extends BuyableCardSuperclass implements PropertyCardInterface {
  private final int MAX_HOUSE_LIMIT = 4;
  private int housePrice;
  private int hotelPrice;
  private int numberOfHouses;
  private boolean hasHotel;
  private int BUYABLE_TYPE_PROPERTY = 1;

  public PropertyCard(String propertyName, int propertyGroupIndex, int propertyGroupMaxSize,
                        int propertyPrice, int housePrice, int baseRent){
    super(propertyName, propertyGroupIndex, propertyPrice, baseRent,
        propertyGroupMaxSize);
    this.housePrice = housePrice;
    hotelPrice = this.housePrice *5;
    numberOfHouses = 0;
    hasHotel = false;
  }

  public void buyHouse(){
    if(owner!=null &&
        owner.getCurrentMoney()>housePrice &&
        hasWholeSet &&
        numberOfHouses< MAX_HOUSE_LIMIT &&
        !anyMortgagedInGroup) {
      owner.deductMoney(housePrice);
      numberOfHouses+=1;
      updateCurrentRent();
      Notification.buyHouse(owner.getID(),cardName,numberOfHouses);
    }
  }

  public void sellHouse(){
    if(numberOfHouses>0 && !hasHotel) {
      owner.addMoney(housePrice / 2);
      numberOfHouses-=1;
      updateCurrentRent();
      Notification.sellHouse(owner.getID(),cardName,numberOfHouses);
    }
  }

  public void buyHotel(){
    if(owner!=null &&
        owner.getCurrentMoney()>hotelPrice &&
        hasWholeSet &&
        numberOfHouses== MAX_HOUSE_LIMIT){
      owner.deductMoney(hotelPrice);
      hasHotel = true;
      updateCurrentRent();
      Notification.buyHotel(owner.getID(),cardName);
    }
  }

  public void sellHotel(){
    if(hasHotel){
      owner.addMoney(hotelPrice);
      hasHotel = false;
      updateCurrentRent();
      Notification.sellHotel(owner.getID(),cardName);
    }
  }

  @Override
  public void mortgagePut(){
    if(!isMortgaged && numberOfHouses==0){
     owner.addMoney(propertyPrice/2);
     owner.setMortgagedCount(propertyGroupIndex, owner.getMortgagedCount(propertyGroupIndex)+1);
     isMortgaged = true;
     updateCurrentRent();
     updateIsAnyMortgaged();
     Notification.mortgagePut(owner.getID(),cardName,getPropertyPrice()/2);
    }
  }

  @Override
  public int getInstantSellWorth(){
    if(!isMortgaged) {
      int hotelReturn = 0;
      if (hasHotel) {
        hotelReturn = hotelPrice / 2;
      }
      return (numberOfHouses * housePrice + propertyPrice) / 2 + hotelReturn;
    }
    return 0;
  }

  @Override
  public int getBuyableType() {
    return BUYABLE_TYPE_PROPERTY;
  }


  @Override
  protected void updateCurrentRent(){
    int multiplyCoeff = 2;
    int multiplyBy = 1;
    if(isMortgaged) {
      multiplyBy = 0;
    }
    else if(numberOfHouses>0){
      multiplyBy = (int) Math.pow(multiplyCoeff,numberOfHouses);
    }
    else if(hasHotel){
      multiplyBy = (int) Math.pow(multiplyCoeff, 5);
    }
    else if(hasWholeSet && !anyMortgagedInGroup){
      multiplyBy = 2;
    }
    currentRent = baseRent * multiplyBy;
  }

  public int getNumberOfHouses(){
    return numberOfHouses;
  }

  public boolean hasHotel(){
    return hasHotel;
  }


}
