package Engine.Player;

import Engine.BuyableCards.BuyableCardsInterface;
import Engine.Notifications.Notification;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Implements PlayerInterface (methods are explained on the interface)
 * This class defines a Player's data and its interactions with the rest of the program
 * @Author: Cemal Yagcioglu
 */
public class Player implements PlayerInterface{
  private int money;
  private int position;
  private int ID;
  private int jailTerm;
  private int lastDiceSum;
  private boolean hasOutOfJail = false;
  private HashMap<Integer, Integer> mortgageCountMap = new HashMap<>();
  private List<BuyableCardsInterface> buyableCardsList = new ArrayList<>();

  public Player(int startingMoney, int startingPosition, int id){
    this.money = startingMoney;
    this.position = startingPosition;
    this.ID = id;
  }

  public int getID() { return ID; }

  @Override
  public void setPosition(int positionIndex) {
    position = positionIndex;
  }

  @Override
  public void addMoney(int moneyAmount) {
    money += moneyAmount;
  }

  @Override
  public void deductMoney(int moneyAmount) {
    money -= moneyAmount;
  }

  @Override
  public int getCurrentMoney() {
    return money;
  }

  @Override
  public int getCurrentPositionIndex() {
    return position;
  }

  @Override
  public void setMortgagedCount(int propertyGroupIndex, int mortgageCount) {
    if(!mortgageCountMap.containsKey(propertyGroupIndex)){
      mortgageCountMap.put(propertyGroupIndex,0);
    }
    int currentCount = mortgageCountMap.get(propertyGroupIndex);
    mortgageCountMap.put(propertyGroupIndex,currentCount+1);
  }

  @Override
  public int getMortgagedCount(int propertyGroupIndex) {
    if(mortgageCountMap.containsKey(propertyGroupIndex)){
      return mortgageCountMap.get(propertyGroupIndex);
    }
    return 0;
  }

  @Override
  public int getOwnedPropertyGroupSize(int propertyGroupIndex) {
    int count = 0;
    for(BuyableCardsInterface buyableCard : buyableCardsList){
      if(buyableCard.getGroupIndex()==propertyGroupIndex){
        count+=1;
      }
    }
    return count;
  }

  @Override
  public void addCard(BuyableCardsInterface buyableCard) {
    buyableCardsList.add(buyableCard);
  }

  @Override
  public void removeCard(BuyableCardsInterface removeCard) {
    buyableCardsList.remove(removeCard);
  }

  @Override
  public int getLastDiceSum() {
    return lastDiceSum;
  }

  public List<String> getOwnedCardsInfo(){
    List<String> ownedCardNames = new ArrayList<>();
    for(BuyableCardsInterface buyableCard : buyableCardsList){
      String mortgageInfo = "";
      if(buyableCard.checkMortgaged()){
        mortgageInfo = " (MORTGAGED)";
      }
      ownedCardNames.add(buyableCard.getName()+mortgageInfo);
    }
    return ownedCardNames;
  }

  public int getAssetsValue(){
    int assetsValue =0;
    for(BuyableCardsInterface buyableCard : buyableCardsList){
      assetsValue += buyableCard.getInstantSellWorth();
    }
    return assetsValue;
  }

  public int getNetworth(){
    return money+getAssetsValue();
  }

  public BuyableCardsInterface getCard(int cardIndex){
    return buyableCardsList.get(cardIndex);
  }

  public void playerHasLost(){
    for(BuyableCardsInterface buyableCard : buyableCardsList){
      buyableCard.ownerHasLost();
    }
  }

  public void setJailTerm(int jailTerm){
    this.jailTerm = jailTerm;
    if(jailTerm==0 || hasOutOfJail) {
      Notification
          .basicInfo(this.getID(), " Successfully got out of the jail. It can move on the next turn.");
      hasOutOfJail=false;
    }
    else{
      Notification.basicInfo(this.getID()," Remaining Jail Term: "+jailTerm);
    }
  }

  public int getJailTerm(){
    return jailTerm;
  }

  public void setLastDiceSum(int diceSum){
    lastDiceSum = diceSum;
  }

  public int getNumberOfCards(){
    return buyableCardsList.size();
  }

  public void setHasOutOfJailCard(){
    hasOutOfJail=true;
  }


}
