
As a fun side project to help pass time during the quarantine and to help with this project, 
we’ve made an home-made Monopoly with the design we want to follow during this project. To still have the nostalgia of childhood, it is in Turkish but easy
to understand design-wise looking at the following photos. 
Please look at the photos in the folder named Monopoly.


**Introduction**

In this project, we are trying to recreate the classic game of MONOPOLY and make the game playable in different versions. In order to achieve this, we will need to create objects that interact with each other 
(such as properties and players) and GUI that allows the user to select which edition of the game to play and 
take turns to decide what to do in their turn after they roll the dice. 
The GUI will offer options like buying properties, adding houses and hotels when possible. 
The player actions that do not require human interaction (such as going to jail,
 moving the player icon, or picking up a chance card) will be handled automatically.
 
**Overview & Design Details**

Functionality wise, the game requires the cooperation of three main parts:
Engine file creation and importation/parsing along with data holding objects like cards.
Engine responsible for running each turn and manipulating the data objects accordingly
Visualization is responsible for visualizing all the data changes and interactions of the user with the game.

Engine will have Property Cards objects module, Public Transportation Cards module, (possibly) Tiles module, Chance Cards module, Community Chest Cards module, and Money module.
The engine will have three modules with the main module responsible for update/run methods for each player’s turn. The second dice module with dice objects class, the third Player module. 

Visualizer will mainly have one module responsible for visualizing the changes of data and additional modules for property files and images. 

The Engine section will load and create all the data objects needed to play the game using the specifications given by the user (what type of Monopoly the user wants) 
so that it loads the correct data. The engine will use its Dice module to roll the dice and move the player to the new position, and in that new position do actions such as draw cards, 
buy the property, pay rent using the data objects created by the data section. Visualizer will have use Engine sections data classes’ information and the action taken on UI to
manipulate its Player object as well as Engine sections Property Card Objects (for example if the player bought a house, 
the engine should update the property card object to reflect the new pricing.
[The property object can have a simple method such as addHouse and sell houses to make it easy for the engine to just call]).
Please look at the modules.png picture for more details.

**Example games**

We will try to implement at least three of these extensions to the our base game so that the game will be playable in different mods (This list is by CS308 website specifications part):
Dice: type of dice; extra turn after rolling doubles; go to jail after too many doubles in a row

Funds: money bills; electronic money; starting amount per player; income-tax rate, etc.

Properties: order on board; which property group, including utilities/railroads; cost; house/hotel prices; rents; mortgaging/un-mortgaging

Go: collecting $200 or another amount; landing directly on it; when passing enables the player to collect money

Landing on properties: buying; auctioning; paying rent based on mortgage/monopoly/house/hotel status

Chance/Community Chest: different actions on cards; keeping cards (e.g., get out of jail)

Jail: various ways to get into and out of jail; various options for money paid to get out of jail

Houses/hotels: cost; buying; building shortages

Turn-based action: roll and move; moving again based on game actions; other results of the move; any player can manage the property.

Joining a game: choose token; choose first to go; choose starting position; choose the number of tokens per player; choose "local" variation separate from game rules


**DESIGN CONSIDERATION:**
We were considering refactoring the code in mvc model to better layout our project design.
Engine could be our view.controller with Engine being model ad player GUI in Visualization.
We still have not finalised this design because in this project there is a lot of dependency
between the three APIs which means we need to find a way to minimize dependencies and 
support encapsulation. If we do not do mvc, the design could become confusing whenever we
try to draw lines between the different classes. For example a player class in engine would
update the players position, how much money,land he has but would depend on the data file
and ultimately visualization would upload the graphics.
So there still needs to be work done to make sure the design is easy to follow and easy to
refactor and debug in the start rather than towards the end which is never a good idea.