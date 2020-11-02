# API Changes
#### Team members: ns217, cy111, fn26

## Goals
### View API:
The goal of the view API is to create a GUI that allows user to set up a custom game of their preferences at and let them
interact with this game. The objective in creating the GUI is to make the game as appealing and easy to interact with as possible.
View displays the board, tile information, player information etc. It is made up of multiple components that work together
by updating the game engine. 

### Engine API:
As engine acts as the bridge between front-end and back-end, it requires to provide a lot of 
publicly acessible methods of interaction. The project made it necessary to include some additional methods 
to already described in the prior API. 

1. boolean getCanRoll();  [Added for button disabling function when player no longer can roll the dice.]
2. List<Integer> getDiceValue(); [Required for the public utility cards that require payment in order of last dice values sum.]
3. boolean isGameOver(); [To check whether game is over from the front end]
3. void payJailFine(); [Paying fine to get-out-of jail.]
### Data API:
Our APIs obviously became more specific as we progressed in the course.
Added: BackendReader that has ReadXMl_die,, luckcard, player, tiles that read
that specific data and provide frontend with information.

I could not notice any big changes in my API.
### 

## API
### VIEW

[CHANGES FOR VIEW API: Divided the API into 8 sections for good graphics and clean code]

#### actionsUI

This class is in charge of player actions like rolling dice, ending turn etc.
It is also used to toggle to dark mode and pop-up how to play & trade screens.

- private void goDark(ActionEvent actionEvent) [ADDED]

    Changes the color of GUI to allow users to enjoy a dark mode
    
- private void trade(ActionEvent actionEvent) [ADDED]

    Opens a pop up simply by initializing a tradePopUp class
    
#### tradePopUP

This class allows players to trade with each other over a set price and property card

- private void trade(ActionEvent actionEvent) [CHANGED]

    Gets inputs from parameters of the pop up and executes the trade

- private void sellerIsSet(Event Event) [ADDED]

    When the seller ComboBox is set, updates the instance variables of the class
    
#### settingsUI [UNUSED/REPLACED]

This toolbar class was planned to get inputs from the user to initialize the game, it was replaced 
and the content of this class was moved to the controller

#### boardUI 

This class is in charge of setting the game board and displaying the components of the game such as pieces and houses.

- public void updateBuildings(int index, int house, boolean hasHotel) [ADDED]

- private void buildingPositioner(int i) [ADDED]

    These two methods were added later so that when a building is placed on the board, the program knows what to place
    and where to place it
    
#### controller  

This class experienced the biggest change when the contents of the settingsUI was moved to this class
    
- private void startStage() [ADDED]
    
    Creates a VBox with buttons so that player can choose game modes

- private void gameStage(ActionEvent actionEvent) [ADDED]

    Starts the actual game depending on the preferences on the starting Stage

- private void setBoardFile(Event event) [ADDED]

    Chooses the board type and tiles from xml
            
- private void setLuckFile(Event event) [ADDED]

    Chooses the luck cards from xml
            
- private void setPlayersFile(Event event) [ADDED]

    Chooses the player types from xml
            
- private void setDiceFile(Event event) [ADDED]

    Chooses the dice from xml
            
- private void setNumberOfPlayers(Event event) [ADDED]

    Chooses the # of players
    
- public String getBoardPath() [ADDED]

    Updates the instance variable boardPath so that the boardUI class can load the correct image
    
     
## Reflection
Game of Monopoly requires a lot of data to be actively 
updated and shown on the front-end. Thus, addition of any chances to 
the plan reflected on to the APIs. 
