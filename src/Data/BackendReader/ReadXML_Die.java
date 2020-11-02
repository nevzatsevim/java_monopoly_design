package Data.BackendReader;

import Engine.Action.ActionInterface;
import Engine.Dice.DicePortfolio;
import Engine.Dice.Die;
import Engine.Dice.DieInterfaceDelete;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * This class is responsible for providing backend engine with essential information for Die class.
 * The first arrayList it returns is Action arraylist which contains information for every face of a die.
 * The second arraylist it returns is an arraylist of value assigned.
 * both of these arraylists together will complete the die classs.
 * Engine will manipulate this data according to how die class will require it
 *
 */

public class ReadXML_Die {
    private static String className;
    private static String parameterInput;
    private static int monChange;
    private static int valueAssign;
    private static DocumentBuilderFactory factory;
    private static DocumentBuilder builder;
    private static Document doc;
    private static NodeList die_list;
    private static ArrayList<ActionInterface> preActions = new ArrayList<>();
    private static final String ACTION_PACKAGE_PATH = "Engine.Action.";
    public static Integer freqCondition;
    public static Integer conditionMet;


    public static ArrayList<ActionInterface> actionsForDie; //arraylist of actions

    public static ArrayList<Integer> getValue_Assigned() {
        return value_Assigned;
    }

    public static ArrayList<DieInterfaceDelete> getDice() {
        return Dice;
    }

    public static ArrayList<Integer>  value_Assigned;

    public static ArrayList<ActionInterface> getActionsForDie() {
        return actionsForDie;
    }

    public static ArrayList<DieInterfaceDelete> Dice = new ArrayList<>();


    public static void builder() throws ParserConfigurationException {
        factory = DocumentBuilderFactory.newInstance();//static method returns the document
        builder = factory.newDocumentBuilder();
    }

    public static void extract_Root(String path) throws ParserConfigurationException, IOException, SAXException {
        builder();
        doc = builder.parse(path);
        doc.getDocumentElement().normalize();
    }

    public static DicePortfolio ReadXML(String path)  {
        try {
            extract_Root(path);
            freqCondition = Integer.valueOf(doc.getElementsByTagName("frequencyCondition").item(0).getTextContent());
            conditionMet = Integer.valueOf(doc.getElementsByTagName("conditionMetInRowThreshold").item(0).getTextContent());
            getInitialActions((Element) doc.getElementsByTagName("action1").item(0));
            getInitialActions((Element) doc.getElementsByTagName("action2").item(0));
            NodeList die = doc.getElementsByTagName("DieNumber");
            for (int i = 0; i < die.getLength(); i++) { //2
                Node p = die.item(i); //2
                if (p.getNodeType() == Node.ELEMENT_NODE) {
                    Element die_info = (Element) p;
                    Integer id = Integer.valueOf(die_info.getAttribute("number"));
                    die_list = die_info.getElementsByTagName("Die"); //each is 6 faces.
                    value_Assigned = new ArrayList<>();
                    actionsForDie = new ArrayList<>();
                    for (int j = 0; j < die_list.getLength(); j++) { //6
                        Node d = die_list.item(j);
                        Element die_face = (Element) d;
                        String face_number = die_face.getAttribute("face");
                            if (d.getNodeType() == die_face.ELEMENT_NODE) {
                                childNodes(die_face);
                            }
                        }
                    }
                Dice.add(new Die(actionsForDie, value_Assigned));
            }

        } catch (Exception e){

        }
        return new DicePortfolio(Dice, 2,
            preActions.get(0), 3,
            preActions.get(1));
    }


    public static String getClassName() {
        return className;
    }

    public static String getParameterInput() {
        return parameterInput;
    }

    private static void childNodes(Element die_face) {

        try {
            className = die_face.getElementsByTagName("ActionClassName").item(0).getTextContent();
            parameterInput = die_face.getElementsByTagName("Parameter").item(0).getTextContent();
            ActionInterface action;
            if (parameterInput != null) {
                int parameterInputInteger = Integer.parseInt(parameterInput);
                Constructor<?> constructor = Class.forName(ACTION_PACKAGE_PATH + className).getConstructor(int.class);
                action = (ActionInterface) constructor.newInstance(parameterInputInteger);
            } else {
                Constructor<?> constructor = Class.forName(ACTION_PACKAGE_PATH + className)
                    .getConstructor();
                action = (ActionInterface) constructor.newInstance();
            }
            valueAssign = Integer
                .parseInt(die_face.getElementsByTagName("valueAssigned").item(0).getTextContent());
            actionsForDie.add(action);
            value_Assigned.add(valueAssign);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void getInitialActions(Element actionItem) {
        try {
            className = actionItem.getElementsByTagName("ActionClassName").item(0).getTextContent();
            parameterInput = actionItem.getElementsByTagName("Parameter").item(0).getTextContent();
            ActionInterface action;
            if (parameterInput != null) {
                int parameterInputInteger = Integer.parseInt(parameterInput);
                Constructor<?> constructor = Class.forName(ACTION_PACKAGE_PATH + className).getConstructor(int.class);
                action = (ActionInterface) constructor.newInstance(parameterInputInteger);
            } else {
                Constructor<?> constructor = Class.forName(ACTION_PACKAGE_PATH + className)
                    .getConstructor();
                action = (ActionInterface) constructor.newInstance();
            }
            if(action!=null) {
                preActions.add(action);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        ReadXML("");

    }

}
