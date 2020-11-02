package Engine;

import Engine.EngineInterface;
import Engine.EngineMain;


class EngineMainTest {

  @org.junit.jupiter.api.Test
  void testCurrentTileIsNullBeforeInit() throws Exception {
    EngineInterface myEngine = new EngineMain();
    String SOURCE = "src/Data/Files/Backend/";
    myEngine.initiate(SOURCE+"Tiles/DeleteChanceCardHellTiles.xml",
                  SOURCE+"Player/PlayerOriginal.xml",
                  SOURCE+"Dice/DieCopy.xml",
                SOURCE+"LuckCardDesk/LuckCard.xml",
                4);
    assert(myEngine.getCurrentPlayer()==null);
  }

  @org.junit.jupiter.api.Test
  void testInitiatesPlayersArrayCorrectly() throws Exception {
    EngineInterface myEngine = new EngineMain();
    String SOURCE = "src/Data/Files/Backend/";
    myEngine.initiate(SOURCE+"Tiles/DeleteChanceCardHellTiles.xml",
        SOURCE+"Player/PlayerOriginal.xml",
        SOURCE+"Dice/DieCopy.xml",
        SOURCE+"LuckCardDesk/LuckCard.xml",
        4);
    assert(myEngine.getPlayersList().size()!=0);
  }

  @org.junit.jupiter.api.Test
  void testcurrentTileAssignedAfterInit() throws Exception {
    EngineInterface myEngine = new EngineMain();
    String SOURCE = "src/Data/Files/Backend/";
    myEngine.initiate(SOURCE+"Tiles/DeleteChanceCardHellTiles.xml",
        SOURCE+"Player/PlayerOriginal.xml",
        SOURCE+"Dice/DieCopy.xml",
        SOURCE+"LuckCardDesk/LuckCard.xml",
        4);
    assert(myEngine.getCurrentTile()!=null);
  }

  @org.junit.jupiter.api.Test
  void testcurrentPlayerAssignedAfterInit() throws Exception {
    EngineInterface myEngine = new EngineMain();
    String SOURCE = "src/Data/Files/Backend/";
    myEngine.initiate(SOURCE+"Tiles/DeleteChanceCardHellTiles.xml",
        SOURCE+"Player/PlayerOriginal.xml",
        SOURCE+"Dice/DieCopy.xml",
        SOURCE+"LuckCardDesk/LuckCard.xml",
        4);
    assert(myEngine.getCurrentPlayer()!=null);
    assert(myEngine.getCurrentPlayer().getCurrentPositionIndex()==0);
  }

}