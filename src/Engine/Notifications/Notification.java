package Engine.Notifications;

import java.util.ArrayList;
import java.util.List;

/**
 * This classes is for updating and maintaining the feedback notifications
 * on the right side of the game screen. It offers static methods that
 * can be interacted with easily.
 * @Author: Cemal Yagcioglu
 */

public class Notification {
  private static List<String> notifications = new ArrayList<String>();
  private static final String PLAYER = "Player ";
  private static final String ROLLED = " rolled ";
  private static final String PAID = " paid ";
  private static final String TO = " to ";
  private static final String BOUGHT = " bought ";
  private static final String SOLD = " sold ";
  private static final String FOR = " for ";
  private static final String CURRENCY = " dollars ";
  private static final String FROM = " from ";
  private static final String HOUSE = " house ";
  private static final String HOTEL = " hotel ";
  private static final String NUMBER = " number ";
  private static final String MORTGAGED = " mortgaged ";
  private static final String LIFT_MORTGAGE = " lift mortgage ";
  private static final String LOST_THE_GAME = " lost the game. Bye bye!";
  private static final String ON = " on ";
  private static final String IS = " is ";
  private static final String SENT = " sent ";
  private static final String JAIL = " jail ";
  private static final String COMMUNITY_CARD = "Community Card :";
  private static final String NEWLINE = "\n";


  /**
   * Rolled dice notification.
   * @param diceValues
   * @param diceThrowerID
   */
  public static void rolled(List<Integer> diceValues, int diceThrowerID){
    notifications.add(PLAYER+diceThrowerID+ROLLED+ diceValues + NEWLINE);
  }

  /**
   * Rent notification.
   * @param rent
   * @param propertyOwnerID
   * @param rentPayerID
   */
  public static void rent(int rent, int propertyOwnerID, int rentPayerID){
    notifications.add(PLAYER+rentPayerID+PAID+rent+CURRENCY+TO+PLAYER+propertyOwnerID+ NEWLINE);
  }

  /**
   * Bought card notification.
   * @param ownerID
   * @param cardName
   * @param price
   */
  public static void buy(int ownerID, String cardName, int price){
    notifications.add(PLAYER+ownerID+BOUGHT+cardName+FOR+price+ CURRENCY + NEWLINE);
  }

  /**
   * Sold/traded card notification.
   * @param buyerID
   * @param sellerID
   * @param cardName
   * @param price
   */
  public static void sell(int buyerID, int sellerID, String cardName, int price){
    notifications.add(PLAYER+buyerID+BOUGHT+cardName+FROM+PLAYER+sellerID+FOR+price+CURRENCY+NEWLINE);
  }

  /**
   * Bought house notification.
   * @param ownerID
   * @param cardName
   * @param numberOfHouses
   */
  public static void buyHouse(int ownerID, String cardName, int numberOfHouses){
    notifications.add(PLAYER+ownerID+BOUGHT+HOUSE+NUMBER+numberOfHouses+FOR+cardName+NEWLINE);
  }

  /**
   * Sold house notification.
   * @param ownerID
   * @param cardName
   * @param numberOfHouses
   */
  public static void sellHouse(int ownerID, String cardName, int numberOfHouses){
    notifications.add(PLAYER+ownerID+SOLD+HOUSE+NUMBER+numberOfHouses+FOR+cardName+NEWLINE);
  }

  /**
   * Bought hotel notification.
   * @param ownerID
   * @param cardName
   */
  public static void buyHotel(int ownerID, String cardName){
    notifications.add(PLAYER+ownerID+BOUGHT+HOTEL+FOR+cardName+NEWLINE);
  }

  /**
   * Sold hotel notification
   * @param ownerID
   * @param cardName
   */
  public static void sellHotel(int ownerID, String cardName){
    notifications.add(PLAYER+ownerID+SOLD+HOTEL+FOR+cardName+NEWLINE);
  }

  /**
   * Mortgage notification.
   * @param ownerID
   * @param cardName
   * @param mortgagePrice
   */
  public static void mortgagePut(int ownerID, String cardName, int mortgagePrice){
    notifications.add(PLAYER+ownerID+MORTGAGED+cardName+FOR+mortgagePrice+CURRENCY+NEWLINE);
  }

  /**
   * Mortgage lifted notification.
   * @param ownerID
   * @param cardName
   * @param mortgagePrice
   */
  public static void mortgageLift(int ownerID, String cardName,int mortgagePrice){
    notifications.add(PLAYER+ownerID+LIFT_MORTGAGE+ON+cardName+mortgagePrice+CURRENCY+NEWLINE);
  }

  /**
   * Jail notification.
   * @param criminalID
   */
  public static void jail(int criminalID){
    notifications.add(PLAYER+criminalID+IS+SENT+TO+JAIL+NEWLINE);
  }

  /**
   * Luck card picked notification.
   * @param drawerID
   * @param cardInfo
   */
  public static void luckCard(int drawerID, String cardInfo){
    notifications.add(PLAYER+drawerID+" " + COMMUNITY_CARD+cardInfo+NEWLINE);
  }

  /**
   * Basic info print with player ID and the info string.
   * @param playerID
   * @param info
   */
  public static void basicInfo(int playerID, String info){
    notifications.add(PLAYER+playerID+" " + info+NEWLINE);
  }

  /**
   * Player lost notification.
   * @param playerID
   */
  public static void playerLost(int playerID){
    notifications.add(PLAYER+playerID+LOST_THE_GAME+NEWLINE);
  }

  /**
   * Returns the notifications list.
   * @return notifications list.
   */
  public static List<String> getNotifications(){
    return notifications;
  }




}
