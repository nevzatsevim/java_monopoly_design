package Engine.Tile;

import Engine.Action.ActionInterface;
import Engine.BuyableCards.BuyableCardsInterface;
import Engine.Player.PlayerInterface;

/**
 * Implemets the methods described on the Tile Interface.
 *
 * @Author: Cemal Yagcioglu
 */

public class Tile implements TileInterface {
  public BuyableCardsInterface buyableCard;
  private String tileName;
  private ActionInterface actionAssigned;
  private static final String BUYABLE = "BUYABLE";
  private static final String NONBUYABLE = "NONBUYABLE";

  public Tile(String tileName, BuyableCardsInterface buyableCard, ActionInterface actionAssigned){
    this.buyableCard = buyableCard;
    this.tileName = tileName;
    this.actionAssigned=actionAssigned;
  }

  public Tile(String tileName,BuyableCardsInterface buyableCard ){
    this.tileName=tileName;
    this.buyableCard = buyableCard;
  }

  public Tile(String tileName, ActionInterface actionAssigned){
    this.tileName=tileName;
    this.actionAssigned=actionAssigned;
  }

  public Tile(String tileName){
    this.tileName = tileName;
  }

  @Override
  public void actThePremise(PlayerInterface playerLanded) {
    if(actionAssigned!=null) {
      actionAssigned.actItOn(playerLanded);
    }
  }

  @Override
  public BuyableCardsInterface getTheBuyableCard() {
    return buyableCard;

  }
  @Override
  public String getTileName(){
    return tileName;
  }


}
