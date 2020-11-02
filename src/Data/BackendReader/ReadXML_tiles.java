package Data.BackendReader;

import Engine.Action.ActionInterface;
import Engine.BuyableCards.BuyableCardSuperclass;
import Engine.BuyableCards.PropertyCard;
import Engine.BuyableCards.PublicTransportCard;
import Engine.BuyableCards.PublicUtilityCard;
import Engine.Notifications.Notification;
import Engine.Tile.Tile;
import Engine.Tile.TileInterface;
import Engine.Tile.TilePortfolio;
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

public class ReadXML_tiles {
    private static DocumentBuilderFactory factory;
    private static DocumentBuilder builder;
    private static Document doc;
    private static String name;
    private static int propertyGroupIndex;
    private static int propertyPrice;
    private static int baseRent;
    private static int maxSize;
    private static int housePrice;
    private static int hotelPrice;
    private static String card_type;
    private static String className;
    private static String parameterInput;
    private static String tile_name;
    private static Tile currentTile;
    private static BuyableCardSuperclass currentCard;
    public static ArrayList<ActionInterface> action_info = new ArrayList<>();
    public static ArrayList<PropertyCard> property_card_info = new ArrayList<>();
    public static ArrayList<PublicUtilityCard> utility_info = new ArrayList<>();
    public static ArrayList<PublicTransportCard> transport_info = new ArrayList<>();
    public static ArrayList<TileInterface> tile_info = new ArrayList<>();
    private static ActionInterface lastAction;
    private static final String ACTION_PACKAGE_PATH = "Engine.Action.";
    public static TilePortfolio tilePortfolio;

    public static String TILENAME= "Tilename";
    public static String ACTION = "action";
    public static String CARD = "card";

    public static void builder() throws ParserConfigurationException {
        factory = DocumentBuilderFactory.newInstance();//static method returns the document
        builder = factory.newDocumentBuilder();
    }

    public static void extract_Root(String path) throws ParserConfigurationException, IOException, SAXException {
        builder();
        doc = builder.parse(path);
    }
    public static TilePortfolio ReadXML(String path) {
         tilePortfolio = new TilePortfolio();
        try {
            extract_Root(path);
            NodeList tileList = doc.getElementsByTagName("Tile");
            for (int i = 0; i < tileList.getLength(); i++) {
                Node p = tileList.item(i);
                    Element tile = (Element) p;
                    tile_name = tile.getElementsByTagName(TILENAME).item(0).getTextContent();
                    NodeList action_list = tile.getElementsByTagName(ACTION);
                    NodeList card_list = tile.getElementsByTagName(CARD);
                    select_list(action_list,card_list);
                    chooseArray(action_list,card_list,tilePortfolio);
                }

            return tilePortfolio;
        } catch (Exception e) {

        }
        return null;
    }

    public static void select_list(NodeList action_list, NodeList card_list){
        if(action_list.getLength() != 0) {//action_list is not null
            Node act = action_list.item(0);
            Element action = (Element) act;
            getActionNodes(action);
        }

        if(card_list.getLength() != 0 ) { //card_list is not null
            Node c = card_list.item(0);
            Element card = (Element) c;
            addReadCards(card);
        }

    }

    public static void chooseArray(NodeList action_list,NodeList card_list, TilePortfolio tilePortfolio ){
        if(action_list.getLength()==0 && card_list.getLength()==0){
            currentTile = new Tile(tile_name);
            tile_info.add(currentTile);
            tilePortfolio.addTile(currentTile);

        }
        else if(action_list.getLength()!=0 && card_list.getLength()==0){
            currentTile = new Tile(tile_name,lastAction);
            tile_info.add(currentTile);
            tilePortfolio.addTile(currentTile);
        }
        else if(action_list.getLength()==0 && card_list.getLength()!=0) {
            currentTile = new Tile(tile_name, currentCard);
            tile_info.add(currentTile);
            tilePortfolio.addTile(currentTile);

        }
        else{
            currentTile = new Tile(tile_name,currentCard,lastAction);
            tile_info.add(currentTile);
            tilePortfolio.addTile(currentTile);

        }

    }

    public static String getCard_type() {
        return card_type;
    }


    private static void addReadCards(Element card){
        card_type = card.getAttribute("type");
        name = card.getElementsByTagName("name").item(0).getTextContent();
        propertyGroupIndex = Integer.parseInt(card.getElementsByTagName("propertyGI").item(0).getTextContent());
        propertyPrice = Integer.parseInt(card.getElementsByTagName("propertyPrice").item(0).getTextContent());
        baseRent = Integer.parseInt(card.getElementsByTagName("baseRent").item(0).getTextContent());
        maxSize = Integer.parseInt(card.getElementsByTagName("maxSize").item(0).getTextContent());
        getCardTypes(card_type,card);

    }



    private static void getActionNodes(Element action){
        try {
            className = action.getElementsByTagName("ActionClassName").item(0).getTextContent();
            NodeList parameterNL = action.getElementsByTagName("Parameter");
            if (parameterNL.getLength()>0) {
                parameterInput = action.getElementsByTagName("Parameter").item(0).getTextContent();
                int parameterInputInteger = Integer.parseInt(parameterInput);
                Constructor<?> constructor = Class.forName(ACTION_PACKAGE_PATH + className).getConstructor(int.class);
                lastAction = (ActionInterface) constructor.newInstance(parameterInputInteger);
            } else {
                Constructor<?> constructor = Class.forName(ACTION_PACKAGE_PATH + className)
                        .getConstructor();
                lastAction = (ActionInterface) constructor.newInstance();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void getCardTypes(String card_type, Element card){
        if(card_type.equals("Property Card")){
            housePrice = Integer.parseInt(card.getElementsByTagName("housePrice").item(0).getTextContent());
            hotelPrice = Integer.parseInt(card.getElementsByTagName("hotelPrice").item(0).getTextContent());
            property_card_info.add(new PropertyCard(name,propertyGroupIndex,maxSize,propertyPrice,housePrice,baseRent));
            currentCard = new PropertyCard(name,propertyGroupIndex,maxSize,propertyPrice,housePrice,baseRent);
        }
        else if (card_type.equals("Public Utility Card")){
            utility_info.add(new PublicUtilityCard(name,propertyGroupIndex,maxSize,propertyPrice,baseRent));
            currentCard = new PublicUtilityCard(name,propertyGroupIndex,maxSize,propertyPrice,baseRent);
        }
        else{
            transport_info.add(new PublicTransportCard(name,propertyGroupIndex,maxSize,propertyPrice,baseRent));
            currentCard = new PublicTransportCard(name,propertyGroupIndex,maxSize, propertyPrice,baseRent);
        }
    }


    public static Tile getCurrentTile() {
        return currentTile;
    }

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        ReadXML("");
    }

}
