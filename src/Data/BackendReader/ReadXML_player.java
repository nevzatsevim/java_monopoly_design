package Data.BackendReader;

import Engine.Player.Player;
import Engine.Player.PlayerInterface;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * this class is responsible for reading in the player class for the engine.
 * this class returns player arraylist which provides information for the engine player class
 *
 */

public class ReadXML_player {
    private static DocumentBuilderFactory factory;
    private static DocumentBuilder builder;
    private static Document doc;
    public static ArrayList<PlayerInterface> player_info = new ArrayList();
    public static int money;
    public static int position;

    public static void builder() throws ParserConfigurationException {
        factory = DocumentBuilderFactory.newInstance();//static method returns the document
        builder = factory.newDocumentBuilder();
    }

    public static void extract_Root(String path) throws ParserConfigurationException, IOException, SAXException {
        builder();
        doc = builder.parse(path);
    }

    public static ArrayList<PlayerInterface> ReadXML(int numPlayer, String path)  {
        try {
            extract_Root(path);

            NodeList playerList = doc.getElementsByTagName("player");
            for(int i = 0; i < numPlayer; i++) {
                Node p = playerList.item(i);
                if (p.getNodeType() == Node.ELEMENT_NODE) {
                    Element player = (Element) p;
                    String id = player.getAttribute("number");
                    getChildNodes(player, id);
                }
            }
        } catch (Exception e) {

        }
        return player_info;
    }


    public static void getChildNodes(Element student, String id) {
        money = Integer.parseInt(student.getElementsByTagName("money").item(0).getTextContent());
        position = Integer.parseInt(student.getElementsByTagName("position").item(0).getTextContent());
       player_info.add(new Player(money,position,Integer.parseInt(id)));
    }

    public static int getPosition() {
        return position;
    }

    public static int getMoney() {
        return money;
    }



    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        ReadXML(0,"");
    }
}







