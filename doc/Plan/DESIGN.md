# DESIGN
#### Team Members: Farzeen Najam, Nevzat Sevim, Cemal Yagcioglu

#### Roles:
Farzeen: Data reader/parser for both front-end and back-end.
Nevzat : Visualizer, front-end, UI design.
Cemal : Engine, data object interaction, game rules and actions.

#### Design Goals:
This project intends to implement a template for all monopoly games that 
can be set to play any specific game modes with the selected data files. 
On Engine, a lot of classes have easy to implement interfaces. First of all, other 
programmers can add any desired action by using the actionInterface, and assigning 
that action to the desired Tile, or Die face(side), or LuckCard. By this the player 
is able to make any Tile player lands on the game, any die player throws and any luckcard 
they pick to act any action they define. Further more, PlayerInterface, DieInterface, 
BuyableCardsInterface, LuckCardInterface and TileInterface offers easy to follow methods that the
programmer can use to create a specific data object he/she wants.

##### High Level Description:
Controller initializes engine with the correct data paths. Engine initializes the data objects 
like Players, Dice portfolio etc using the parsers static methods. When initialized every Tile has 
either BuyableCard or Action (acted when landed on) or neither (visiting jail). The dice portfolio has 
all dice defined, with each die possible number of actions are defined (one per die's side). Alongside these, 
other data objects necessary such as Players and LuckCards are initialized. All the functions of 
data objects (Tiles, Dice portfolio, Players etc) are put together on the engine to respond correctly to the 
button clicks on the front-end. For example when dice is rolled, Front-end class roll() method in the engine.
Roll() in engine calls roll() on dicePortfolio, which in respond rolls and acts the actions on the dice. Those actions 
update Player objects (position, amount of money etc). These updates reflect to the front-end. (Player position is 
now updated and rollDice button is disabled and the NextPlayer button is enabled). 

#### New possible features
One feature is we were so close, but couldn't implement was starting the new game without restarting the program. 
To implement in, one just simply needs to reopen the initialization pop-up we implemented when a certain button on the front end is clicked. 

