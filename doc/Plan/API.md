Engine API:
These are not all the API methods but a general overview of how the game APIs will be divided.
```java
    /**
     * Engine class 
     */
public class Engine {

  public void main() {
  /**
this method is responsible for updating and running methods during each players' turn by calling
on the Player class.
**/
 
  }
  public void dice(){
  //this method would probably call on the Dice class and update the dice accordingly.
  }
  
  public void Player(){
  //this method would probably call on the player Class and update the players accordingly.
  }
  
}
  

```

DATA API:
```java
    /**
     * DATA CLASS
     */
public class Engine{
   public void addHouse_RemoveHouse(){
   //buy or sell houses on owned property; its values loaded from specific files.
   }
   public void readGame(){
  //reads the xml parser and load the game.
   }
  
   public void propertyCard_classes(){
  //load files that contains what to do when a  specific card is picked.
   }
   

}



  

```
VISUALIZATION API
```java
    /**
     * Visualization
     */
public class VISUALIZATION{
   public void ToolBar(){
   //includes rolling dice, setting up the board and resetting the game.
   }
   public void Specific_Property_Player(){
  // event handler to interact with the propertyCard_classes by clicking a specific property that a player holds.
   }
  
   public void create_Board(){
 // Create a Board based on the data chosen from the Toolbar
   }
   
  public void sell_buy_property(){
   //Selling properties when the hotels and houses are clicked on the screen.
}


}





