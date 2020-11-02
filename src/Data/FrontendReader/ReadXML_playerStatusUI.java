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

public class ReadXML_playerStatusUI {
    private static int val_5;
    private static int val_3;
    private static int val_10;
    private static String label;
    private static String choosePlayer;
    private static String liquidCash;
    private static String assets;
    private static String netWorth;
    private static String properties;
    private static int id;

    private static DocumentBuilderFactory factory;
    private static DocumentBuilder builder;
    private static Document doc;

    public static void builder() throws ParserConfigurationException {
        factory = DocumentBuilderFactory.newInstance();//static method returns the document
        builder = factory.newDocumentBuilder();
    }

    public static void extract_Root() throws ParserConfigurationException, IOException, SAXException {
        builder();
        doc = builder.parse("src/Data/Files/Frontend/playerStatusUI.xml");
        doc.getDocumentElement().normalize();
    }
    public static void ReadXML() throws IOException, SAXException, ParserConfigurationException {
        extract_Root();
        NodeList name = doc.getElementsByTagName("playerStatuses");
        for (int i = 0; i < name.getLength(); i++) {
            Node p = name.item(i);
            if (p.getNodeType() == Node.ELEMENT_NODE) {
                Element status_info = (Element) p;
                id = Integer.parseInt(status_info.getAttribute("id"));
                label =  status_info.getElementsByTagName("label").item(0).getTextContent();
                choosePlayer = status_info.getElementsByTagName("prompt").item(0).getTextContent();
                liquidCash = status_info.getElementsByTagName("prompt1").item(0).getTextContent();
                assets = status_info.getElementsByTagName("prompt2").item(0).getTextContent();
                netWorth = status_info.getElementsByTagName("prompt3").item(0).getTextContent();
                properties = status_info.getElementsByTagName("prompt4").item(0).getTextContent();
                val_5 = Integer.parseInt(status_info.getElementsByTagName("a").item(0).getTextContent());
                val_3 = Integer.parseInt(status_info.getElementsByTagName("b").item(0).getTextContent());
                val_10 = Integer.parseInt(status_info.getElementsByTagName("c").item(0).getTextContent());
                //System.out.println(label+ " " +  choosePlayer + " " + liquidCash);
            }
        }

    }
    public static int getVal_5() {
        return val_5;
    }

    public static int getVal_3() {
        return val_3;
    }

    public static int getVal_10() {
        return val_10;
    }

    public static String getLabel() {
        return label;
    }

    public static String getChoosePlayer() {
        return choosePlayer;
    }

    public static String getLiquidCash() {
        return liquidCash;
    }

    public static String getAssets() {
        return assets;
    }

    public static String getNetWorth() {
        return netWorth;
    }

    public static String getProperties() {
        return properties;
    }

    public static int getId() {
        return id;
    }




    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        ReadXML();
    }


}
