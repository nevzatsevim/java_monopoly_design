package Data.Tests;

import Data.BackendReader.ReadXML_luckCard;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ReadXML_luckCardTest {
    @Test
    void ifTextexists() throws ParserConfigurationException, SAXException, IOException {
        ReadXML_luckCard.ReadXML("src/Data/Files/Backend/LuckCardDesk/LuckCard.xml");
        assert(ReadXML_luckCard.getText()!= null);
    }
    @Test
    void ifclassNameexists() throws ParserConfigurationException, SAXException, IOException {
        ReadXML_luckCard.ReadXML("src/Data/Files/Backend/LuckCardDesk/LuckCard.xml");
        assert(ReadXML_luckCard.getClassName().equals("ChangePosition"));
    }
    @Test
    void ifClassNameExists() throws ParserConfigurationException, SAXException, IOException {
        ReadXML_luckCard.ReadXML("src/Data/Files/Backend/LuckCardDesk/LuckCard.xml");
        assert(ReadXML_luckCard.getClassName().equals("ChangePosition"));
    }
    @Test
    void ifParamExists() throws ParserConfigurationException, SAXException, IOException {
        ReadXML_luckCard.ReadXML("src/Data/Files/Backend/LuckCardDesk/LuckCard.xml");
        assert(ReadXML_luckCard.getParamInput() != null);
    }


}