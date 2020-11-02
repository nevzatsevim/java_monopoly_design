package Data.Tests;

import Data.BackendReader.ReadXML_player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class ReadXML_playerTest  {
    @org.junit.jupiter.api.Test
    void testifRightvalForPos() throws Exception{
        ReadXML_player.ReadXML(3,"src/Data/Files/Backend/Player/PlayerOriginal.xml");
        assert(ReadXML_player.getPosition() == 0);
    }
    @org.junit.jupiter.api.Test
    void testifRightvalForMoney() throws  Exception{

        ReadXML_player.ReadXML(3,"src/Data/Files/Backend/Player/PlayerOriginal.xml");
        assert(ReadXML_player.getMoney() == 1500);

    }
    @Test
    void testifArrayisNull() throws Exception{
        ReadXML_player.ReadXML(3,"src/Data/Files/Backend/Player/PlayerOriginal.xml");
        assert(ReadXML_player.player_info != null);
    }

}