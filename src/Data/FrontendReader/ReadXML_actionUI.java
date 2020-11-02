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

public class ReadXML_actionUI {
    private static DocumentBuilderFactory factory;
    private static DocumentBuilder builder;
    private static Document doc;
    public static String action_button;
    public static String  trade_label;
    public static String pay_Fines_Label;
    public static String Next_Player_label;
    public static String how_to_Play_label;
    public static String die_image;
    public static String TEXT_FILL;
    public static String BACKGROUND_COLOR;
    public static String BORDER_COLOR;
    public static String BORDER_RADIUS;
    public static String BG_RADIUS;
    public static String DARK_MODE_TEXT;
    public static String DARK_MODE_COLOR;
    public static String JOKE1;
    public static String JOKE2;
    public static String HELP;
    public static String PLAY;
    public static String ACTIVE;

    /**
     * This class is supposed to read an xml file that provides actionUI with all
     * the strings it needs so the frontend user does not have to hardcode those
     * string values.
     * Please look at the getter methods in the end and use it to remove hardcode values.
     * @throws ParserConfigurationException
     */


    public static void builder() throws ParserConfigurationException {
        factory = DocumentBuilderFactory.newInstance();//static method returns the document
        builder = factory.newDocumentBuilder();
    }

    public static void extract_Root() throws ParserConfigurationException, IOException, SAXException {
        builder();
        doc = builder.parse("src/Data/Files/Frontend/ActionUI.xml");
        doc.getDocumentElement().normalize();
    }

    public static void ReadXML()  {
        try {
            extract_Root();
            NodeList die = doc.getElementsByTagName("playerAct");
            for (int i = 0; i < die.getLength(); i++) {
                Node p = die.item(i);
                if (p.getNodeType() == Node.ELEMENT_NODE) {
                    Element action_info = (Element) p;
                    action_button = action_info.getAttribute("act");
                    die_image = action_info.getElementsByTagName("url").item(0).getTextContent();
                    trade_label = action_info.getElementsByTagName("button1").item(0).getTextContent();
                    pay_Fines_Label = action_info.getElementsByTagName("button2").item(0).getTextContent();
                    Next_Player_label = action_info.getElementsByTagName("button3").item(0).getTextContent();
                    how_to_Play_label = action_info.getElementsByTagName("button4").item(0).getTextContent();
                    PLAY =  action_info.getElementsByTagName("button5").item(0).getTextContent();
                    ACTIVE =  action_info.getElementsByTagName("button6").item(0).getTextContent();
                    TEXT_FILL = action_info.getElementsByTagName("Text").item(0).getTextContent();
                    BACKGROUND_COLOR = action_info.getElementsByTagName("BG").item(0).getTextContent();
                    BORDER_COLOR = action_info.getElementsByTagName("BOG").item(0).getTextContent();
                    BORDER_RADIUS = action_info.getElementsByTagName("BOR").item(0).getTextContent();
                    BG_RADIUS = action_info.getElementsByTagName("BGR").item(0).getTextContent();
                    DARK_MODE_TEXT = action_info.getElementsByTagName("DarkT").item(0).getTextContent();
                    DARK_MODE_COLOR = action_info.getElementsByTagName("DarkB").item(0).getTextContent();
                    JOKE1 = action_info.getElementsByTagName("Joke1").item(0).getTextContent();
                    JOKE2 = action_info.getElementsByTagName("Joke2").item(0).getTextContent();
                    HELP = action_info.getElementsByTagName("load").item(0).getTextContent();


                }
            }
        }
        catch (Exception e){

        }
    }

    public static String getAction_button() {
        return action_button;
    }
    public static String getTrade_label() {
        return trade_label;
    }
    public static String getPay_Fines_Label() {
        return pay_Fines_Label;
    }
    public static String getNext_Player_label() {
        return Next_Player_label;
    }
    public static String getHow_to_Play_label() {
        return how_to_Play_label;
    }
    public static String getDie_image() { return die_image; }



    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        ReadXML();
    }
}
