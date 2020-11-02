package Data.Tests;

import Data.BackendReader.ReadXML_tiles;
import Engine.EngineInterface;
import Engine.EngineMain;
import Engine.Notifications.Notification;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReadXML_tilesTest {
    @Test
    void isTileArrayEmpty() throws Exception{
        ReadXML_tiles.ReadXML("src/Data/Files/Backend/Tiles/Classic.xml");
        assert(ReadXML_tiles.tile_info != null);
    }
    @Test
    void isActionArrayEmpty() throws Exception{
        ReadXML_tiles.ReadXML("src/Data/Files/Backend/Tiles/Classic.xml");
        assert(ReadXML_tiles.action_info != null);
    }
    @Test
    void iscardArrayEmpty() throws Exception{
        ReadXML_tiles.ReadXML("src/Data/Files/Backend/Tiles/Classic.xml");
        assert(ReadXML_tiles.getCard_type() != null);
    }

    @Test
    void isCurrentTileCorrect() throws Exception{
        ReadXML_tiles.ReadXML("src/Data/Files/Backend/Tiles/Classic.xml");
        assert(ReadXML_tiles.getCurrentTile() != null);

    }








}