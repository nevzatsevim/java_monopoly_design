package Data.FrontendReader;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * this class provides all the necessary data to settingsUI class initially
 * for the classic board.
 */

public class ReadXML_settingUI {
    private static float barHeight;
    private static int barWidth_10;
    private static int barWidth7;
    private static String start;
    private static String GameSetting;
    private static String ChooseBoard;
    private static String noOfPlayers;
    private static String chooseDice;
    private static String noOfDice;
    private static String board_type;
    private static int player2;
    private static int player3;
    private static int player4;
    private static int player5;


    private static DocumentBuilderFactory factory;
    private static DocumentBuilder builder;
    private static Document doc;

    public static void builder() throws ParserConfigurationException {
        factory = DocumentBuilderFactory.newInstance();//static method returns the document
        builder = factory.newDocumentBuilder();
    }

    public static void extract_Root() throws ParserConfigurationException, IOException, SAXException {
        builder();
        doc = builder.parse("src/Data/Files/Frontend/SettingsUI.xml");
        doc.getDocumentElement().normalize();
    }



    public static void ReadXML() throws IOException, SAXException, ParserConfigurationException {
        extract_Root();
        NodeList setup = doc.getElementsByTagName("setup");
        for (int i = 0; i < setup.getLength(); i++) {
            Node p = setup.item(i);
            if (p.getNodeType() == Node.ELEMENT_NODE) {
                Element setting_info = (Element) p;
                board_type = (setting_info.getAttribute("board_type"));
                if(board_type.equals("Classic") ){
                    barHeight = Float.parseFloat(setting_info.getElementsByTagName("barHeight").item(0).getTextContent());
                    barWidth_10 = Integer.parseInt(setting_info.getElementsByTagName("barWidth").item(0).getTextContent());
                    barWidth7 = Integer.parseInt(setting_info.getElementsByTagName("barWidth7").item(0).getTextContent());
                    start = setting_info.getElementsByTagName("start").item(0).getTextContent();
                    GameSetting = setting_info.getElementsByTagName("settings").item(0).getTextContent();
                    ChooseBoard = setting_info.getElementsByTagName("chooseBoard").item(0).getTextContent();
                    noOfPlayers = setting_info.getElementsByTagName("player").item(0).getTextContent();
                    chooseDice =  setting_info.getElementsByTagName("dice").item(0).getTextContent();
                    noOfDice = setting_info.getElementsByTagName("diceSide").item(0).getTextContent();
                    player2 = Integer.parseInt(setting_info.getElementsByTagName("player2").item(0).getTextContent());
                    player3 = Integer.parseInt(setting_info.getElementsByTagName("player3").item(0).getTextContent());
                    player4 = Integer.parseInt(setting_info.getElementsByTagName("player4").item(0).getTextContent());
                    player5 = Integer.parseInt(setting_info.getElementsByTagName("player5").item(0).getTextContent());
                }





            }
        }
    }

    public static float getBarHeight() {
        return barHeight;
    }

    public static int getBarWidth_10() {
        return barWidth_10;
    }

    public static int getBarWidth7() {
        return barWidth7;
    }

    public static String getStart() {
        return start;
    }

    public static String getGameSetting() {
        return GameSetting;
    }

    public static String getChooseBoard() {
        return ChooseBoard;
    }

    public static String getNoOfPlayers() {
        return noOfPlayers;
    }

    public static String getChooseDice() {
        return chooseDice;
    }

    public static String getNoOfDice() {
        return noOfDice;
    }

    public static int getPlayer2() {
        return player2;
    }

    public static int getPlayer3() {
        return player3;
    }

    public static int getPlayer4() {
        return player4;
    }

    public static int getPlayer5() {
        return player5;
    }

    public static String getBoard_type() {
        return board_type;
    }

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        ReadXML();
    }
}
