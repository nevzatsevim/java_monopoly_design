package Data.BackendReader;

import Engine.Action.ActionInterface;
import Engine.LuckCards.LuckCard;
import Engine.LuckCards.LuckCardQueue;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.ArrayList;

public class ReadXML_luckCard {
    private static DocumentBuilderFactory factory;
    private static DocumentBuilder builder;
    private static Document doc;
    private static String className;
    private static String paramInput;

    public static String getText() {
        return text;
    }

    private static String text;
    private static ActionInterface lastAction;
    private static ArrayList<LuckCard> luck_array;
    private static LuckCard luckCard;
    private static final String ACTION_PACKAGE_PATH = "Engine.Action.";

    public static void builder() throws ParserConfigurationException {
        factory = DocumentBuilderFactory.newInstance();//static method returns the document
        builder = factory.newDocumentBuilder();
    }

    public static void extract_Root(String path) throws ParserConfigurationException, IOException, SAXException {
        builder();
        doc = builder.parse(path);
        doc.getDocumentElement().normalize();
    }

    public static LuckCardQueue ReadXML(String path) {
        LuckCardQueue luckCardQueue = new LuckCardQueue();
        try{
        extract_Root(path);
        NodeList cardlist = doc.getElementsByTagName("luck");
        for(int i = 0; i < cardlist.getLength(); i++) {
            Node card = cardlist.item(i);
            if (card.getNodeType() == Node.ELEMENT_NODE) {
                Element luck = (Element) card;
                int luck_card_number = Integer.parseInt(luck.getAttribute("num"));
                getChildNodes(luck, luck_card_number);
                luckCardQueue.addLuckCard(luckCard);

            }
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return luckCardQueue;
    }

    public static String getClassName() {
        return className;
    }

    public static String getParamInput() {
        return paramInput;
    }

    public static void getChildNodes(Element luck, Integer id) {
           text = luck.getElementsByTagName("textCard").item(0).getTextContent();
        try {
            className = luck.getElementsByTagName("ActionClassName").item(0).getTextContent();
            paramInput = luck.getElementsByTagName("ParameterName").item(0).getTextContent();
            if (paramInput != null) {
                int parameterInputInteger = Integer.parseInt(paramInput);
                Constructor<?> constructor = Class.forName(ACTION_PACKAGE_PATH + className).getConstructor(int.class);
                lastAction = (ActionInterface) constructor.newInstance(parameterInputInteger);
            } else {
                Constructor<?> constructor = Class.forName(ACTION_PACKAGE_PATH + className)
                        .getConstructor();
                lastAction = (ActionInterface) constructor.newInstance();
            }
              luckCard = new LuckCard(text,lastAction);


        } catch (Exception e) {
            e.printStackTrace();
        }



    }
    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        ReadXML("");
    }


}
