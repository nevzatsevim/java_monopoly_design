package Data.Tests;

import Data.BackendReader.ReadXML_Die;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReadXML_DieTest {

    @Test
    void isDiceValNull() throws  Exception{
        ReadXML_Die.ReadXML("src/Data/Files/Backend/Dice/DieCopy.xml");
        assert(ReadXML_Die.getDice() != null);
    }
    @Test
    void isActionforDieNull() throws  Exception {
        ReadXML_Die.ReadXML("src/Data/Files/Backend/Dice/DieCopy.xml");
        assert(ReadXML_Die.actionsForDie != null);
    }

    @Test
    void isClassNameExpected() throws  Exception {
        ReadXML_Die.ReadXML("src/Data/Files/Backend/Dice/DieCopy.xml");
        assertEquals("ChangePosition", ReadXML_Die.getClassName());
    }

    @Test
    void getLastParamValue() throws Exception {
        ReadXML_Die.ReadXML("src/Data/Files/Backend/Dice/DieCopy.xml");
        assertEquals("6", ReadXML_Die.getParameterInput());

    }
    @Test
    void getArrayListValue() throws Exception{
        ReadXML_Die.ReadXML("src/Data/Files/Backend/Dice/DieCopy.xml");
        assert(ReadXML_Die.value_Assigned != null);
    }

}