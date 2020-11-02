package Engine.Tile;

import java.util.ArrayList;

/**
 * Tile Portfolio is used to keep tile interface objects as collections
 * and easily interact with them.
 * @Author: Cemal Yagcioglu
 */
public class TilePortfolio {
  private ArrayList<TileInterface> tileList = new ArrayList<>();

  public TilePortfolio(){
    //Do nothing
  }

  /**
   * Adds tile to the portfolio.
   * @param tileInput
   */
  public void addTile(TileInterface tileInput){
    tileList.add(tileInput);
  }

  /**
   * Gets the tile with the position index.
   * @param tilePositionIndex
   * @return tile.
   */
  public TileInterface getTile(int tilePositionIndex) {
    return tileList.get(tilePositionIndex);
  }

  /**
   * Returns the number of tiles in the portfolio.
   * @return number of tiles.
   */
  public int getSize(){
    return tileList.size();
  }


}
