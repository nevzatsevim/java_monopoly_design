package Data.BackendReader;

import Engine.BuyableCards.PropertyCard;
import Engine.BuyableCards.PublicTransportCard;
import Engine.BuyableCards.PublicUtilityCard;
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

public class ReadXML_cards {
    private static String name;
    private static int propertyGroupIndex;
    private static int propertyPrice;
    private static int baseRent;
    private static int maxSize;
    private static int housePrice;
    private static int hotelPrice;

    private static DocumentBuilderFactory factory;
    private static DocumentBuilder builder;
    private static Document doc;
    public static ArrayList<PropertyCard> property_card_info = new ArrayList<>();
    public static ArrayList<PublicUtilityCard> utility_info = new ArrayList<>();
    public static ArrayList<PublicTransportCard> transport_info = new ArrayList<>();

    public static void builder() throws ParserConfigurationException {
        factory = DocumentBuilderFactory.newInstance();//static method returns the document
        builder = factory.newDocumentBuilder();
    }

    public static void extract_Root() throws ParserConfigurationException, IOException, SAXException {
        builder();
        doc = builder.parse("src/Data/Files/Backend/Cards.xml");
    }
    public static void ReadXML() throws IOException, SAXException, ParserConfigurationException {
        extract_Root();
        NodeList cardList = doc.getElementsByTagName("card"); //root name
        for (int i = 0; i < cardList.getLength(); i++) {
            Node p = cardList.item(i);
            if (p.getNodeType() == Node.ELEMENT_NODE) {
                Element card = (Element) p;
                String card_type = card.getAttribute("type");
                name = (card.getElementsByTagName("name").item(0).getTextContent());
                propertyGroupIndex = Integer.parseInt(card.getElementsByTagName("propertyGI").item(0).getTextContent());
                propertyPrice = Integer.parseInt(card.getElementsByTagName("propertyPrice").item(0).getTextContent());
                baseRent = Integer.parseInt(card.getElementsByTagName("baseRent").item(0).getTextContent());
                maxSize = Integer.parseInt(card.getElementsByTagName("maxSize").item(0).getTextContent());
                if(card_type.equals("Property Card")){
                    getPropertyNodes(card,card_type);
                    //property_card_info.add(new PropertyCard(name,propertyGroupIndex,propertyPrice,baseRent,maxSize,housePrice,myno));
                }
                else if (card_type.equals("Public Utility Card")){
                    //utility_info.add(new PublicUtilityCard(name,propertyGroupIndex,propertyPrice,baseRent,maxSize));
                }
                else{
                    //transport_info.add(new PublicTransportCard(name,propertyGroupIndex,propertyPrice,baseRent,maxSize));
                }
            }
        }

    }

    public static void getPropertyNodes(Element card, String id) {
         housePrice = Integer.parseInt(card.getElementsByTagName("housePrice").item(0).getTextContent());
         hotelPrice = Integer.parseInt(card.getElementsByTagName("hotelPrice").item(0).getTextContent());
    }


    public static ArrayList<PublicUtilityCard> getUtility_info() {
        return utility_info;
    }
    public static ArrayList<PublicTransportCard> getTransport_info() {
        return transport_info;
    }
    public static ArrayList<PropertyCard> getProperty_card_info() {
        return property_card_info;
    }






 /*
                    System.out.println("A");
                    if(currentTile.getTheBuyableCard()!=null) {
                        System.out.println("B");
                        tilePortfolio
                            .addTile(currentTile.getTheBuyableCard(), tile_name, tile_info.size() - 1,
                                lastAction);
                        System.out.println("B2");
                        System.out.println("tile_info_size: "+ Integer.toString(tile_info.size()-1));
                    }
                    else {
                        System.out.println("C");
                        tilePortfolio.addTile(currentTile,tile_info.size() - 1);
                        System.out.println("tile_info_size: "+ Integer.toString(tile_info.size()-1));
                    }
                    System.out.println("D");
                }
                //System.out.println(currentTile.getTileName() + " " + currentTile.getTheBuyableCard().getName());
                */



    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        ReadXML();
    }



}
