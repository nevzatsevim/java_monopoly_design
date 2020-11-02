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
 * this provides necessary information for the board for GUI.
 */
public class ReadXML_board {
    private static int board_type;
    private static int val_0;
    private static int val_7;
    private static int val_10;
    private static int val_700;
    private static int val_95;
    private static float val_56;
    private static int val_30;
    private static int val_605;
    private static int val_20;
    private static int stageHeight;
    private static int stageWidth;
    private static String monopoly_image;
    private static DocumentBuilderFactory factory;
    private static DocumentBuilder builder;
    private static Document doc;

    public static void builder() throws ParserConfigurationException {
        factory = DocumentBuilderFactory.newInstance();//static method returns the document
        builder = factory.newDocumentBuilder();
    }


    public static void extract_Root() throws ParserConfigurationException, IOException, SAXException {
        builder();
        doc = builder.parse("src/Data/Files/Frontend/board.xml");
        doc.getDocumentElement().normalize();
    }


    public static void ReadXML() throws IOException, SAXException, ParserConfigurationException {
        extract_Root();
        NodeList die = doc.getElementsByTagName("board");
        for (int i = 0; i < die.getLength(); i++) {
            Node p = die.item(i);
            if (p.getNodeType() == Node.ELEMENT_NODE) {
                Element action_info = (Element) p;
                board_type = Integer.parseInt(action_info.getAttribute("type"));
                if (board_type == 1) {
                    stageHeight = Integer.parseInt(action_info.getElementsByTagName("stageHeight").item(0).getTextContent());
                    stageWidth = Integer.parseInt(action_info.getElementsByTagName("stageWidth").item(0).getTextContent());
                    val_0 = Integer.parseInt(action_info.getElementsByTagName("a").item(0).getTextContent());
                    val_7 = Integer.parseInt(action_info.getElementsByTagName("b").item(0).getTextContent());
                    val_10 = Integer.parseInt(action_info.getElementsByTagName("c").item(0).getTextContent());
                    val_700 = Integer.parseInt(action_info.getElementsByTagName("d").item(0).getTextContent());
                    val_95 = Integer.parseInt(action_info.getElementsByTagName("e").item(0).getTextContent());
                    val_56 = Float.parseFloat((action_info.getElementsByTagName("f").item(0).getTextContent()));
                    val_30 = Integer.parseInt(action_info.getElementsByTagName("g").item(0).getTextContent());
                    val_605 = Integer.parseInt(action_info.getElementsByTagName("h").item(0).getTextContent());
                    val_20 = Integer.parseInt(action_info.getElementsByTagName("i").item(0).getTextContent());
                    monopoly_image = action_info.getElementsByTagName("url").item(0).getTextContent();

                }
            }
        }
    }
    public static int getVal_0() {
        return val_0;
    }
    public static int getVal_7() {
        return val_7;
    }
    public static int getVal_10() {
        return val_10;
    }
    public static int getVal_700() {
        return val_700;
    }
    public static int getVal_95() {
        return val_95;
    }
    public static float getVal_56() {
        return val_56;
    }
    public static int getVal_30() {
        return val_30;
    }
    public static int getVal_605() {
        return val_605;
    }
    public static int getVal_20() {
        return val_20;
    }
    public static int getStageWidth() {
        return stageWidth;
    }
    public static int getStageHeight() {
        return stageHeight;
    }
    public static int getBoard_type() { return board_type; }
    public static String getMonopoly_image() { return monopoly_image; }




    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        ReadXML();
    }
}
