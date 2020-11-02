**Nevzat Sevim:**  

Create a frontend ToolBar that includes rolling dice, setting up the board and resetting the game.

Create an event handler to interact with the tile_classes by clicking a specific tile on the board.

Create an event handler to interact with the propertyCard_classes by clicking a specific property that a player holds.

Create a movable player icon that changes location when dice is rolled.

Display the amount of money every player has on the screen.

Create a Board based on the data chosen from the Toolbar.

Make property cards appear under the player spaces whenever a new property is bought.

Create a button that allows the user to end his/her turn so that the next player can play.

Selling properties when the hotels and houses are clicked on the screen.


**Cemal Yagcioglu:**

Buy and sell houses on the owned property → Property type class’ rent payment will increase/decrease. (addhouse/removeHouse method)

Buy a property (now the propertyBuyable = false)

Mortgage on owned property (now the propertyBuyable = false, requiresRent = false)

Initialize the game →  readXML  → parser → initializer. 

New type of game initialized → The load data methods load the newData by reading the initializingProperties file.

Save the current state of the game → save the current data object’ data to xml files. 

Chance card is picked → displayText displays the text of the card and applyChances applies the changes required by the card. 

Community Chest card is picked → displayText displays the text of the card and applyChances applies the changes required by the card.   (same with chance card)

**Farzeen Najam:**

Update the current score of all the players by updating the player class through player class in the Engine package.

Update the current position of all the players using relevant methods in the Player class in the Engine package .

Enable players to buy different properties from money they have.

Roll the dice and calculate the number of doubles in row.

Keep track of which players win and lose based on the game rules.

Calculate the current money a player has after certain purchases made or lands sold.

Evaluate which player’s turn it is in a series of games.

Accommodate different types of dices if applicable.


**API EXAMPLES**
If the user's position is changed in the game, it would be done in the Engine Module
in the Player class in the changePosition method which will calculate the new position
based on whatever is read in the data file and parsed through in specific_property method 
All of this will be visualized in the Visualization API.

If the user buys or sells property, the money will be calculated in the Engine Module
of the project in the Property class in the property_specific method and this
information would come from the data file uploaded about specific property files 
and displayed in visualization API.

If the player wins or loses, the score will be calculated using the engine and displayed 
in visual API.

if the Money changes, then the engine API would be used to keep track of
players current money based on the information read in data file and visualize that 
in the frontend using visualization API.

If the player is on a specific card then Engine would do the task based on whatever
is provided by the data API and then uploaded in the visualization.




