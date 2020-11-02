package view;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

/**
 * This class is in charge of setting the game board and displaying the
 * components of the game such as pieces and houses.
 * @author Nevzat Sevim
 */

public class boardUI extends Pane {

    private display myDisplay;
    private double boardSize;
    private int tileIndex;

    private ImageView[] playerList;
    private ImageView board;
    private String boardPath;

    private int[] houseArray;
    private boolean[] hotelArray;
    private ImageView[] buildingImages;
    private double coordinateX,coordinateY;

    public boardUI(display newDisplay, String newBoardPath){
        this.myDisplay = newDisplay;
        boardPath = newBoardPath;

        houseArray = new int[40];
        hotelArray = new boolean[40];
        buildingImages = new ImageView[40];

        setBoard();
        setImages();

        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                tileCalculator(event.getX(), event.getY());
                myDisplay.getEngine().clickedOnTheTile(tileIndex);
                myDisplay.getTileFiled().checkTile(myDisplay.getEngine().getCurrentTile());
            }
        });
    }

    private void setBoard() {
        boardSize = (7 * myDisplay.getHeight())/10;
        this.setPrefSize(boardSize,boardSize);

        board = new ImageView( new Image("/view/images/boards/" + boardPath + ".png"));
        board.toBack();

        board.setFitWidth(boardSize);
        board.setFitHeight(boardSize);

        this.getChildren().addAll(board);
    }


    private void setImages(){
        playerList = new ImageView[5];
        int length = myDisplay.getPlayerList().size();

        //Setting images for players
        for(int i=0; i<length; i++){
            ImageView temp = new ImageView(new Image("/view/images/playerIcons/" + i + ".png"));
            temp.relocate(boardSize - 30 - (20*i),boardSize - 30 - (20*i));
            temp.setFitHeight(30);
            temp.setFitWidth(30);
            playerList[i] = temp;
            this.getChildren().addAll(playerList[i]);
        }

        //Setting empty images for starting buildings
        for(int j= 0; j<40; j++){
            ImageView im = new ImageView(new Image("/view/images/boardModifiers/void.png"));
            im.relocate(coordinateX,coordinateY);
            im.setFitHeight(20);
            im.setFitWidth(20);
            buildingImages[j] = im;
            this.getChildren().addAll(buildingImages[j]);
        }
    }

    /**
     * Calculates which pixel the player icon will be placed and executes
     * @param i & p: index of the tile & which player to move
     */

    public void movePiece(int i, int p){
        double x = 0;
        double y = 0;

        //land on bottom row
        if(i<= 10){
            x = (boardSize / 700) * 605 - (56.66 * i) + p*5;
            y = (boardSize / 700) * 605 + (56.66/2) + p*5; }

        //land on left column
        if(i>10 & i<20){
            y = (boardSize / 700) * 605 - (56.66 * (i-10)) + p*5;
            x = (boardSize / 700) * (56.66/2) + p*5; }

        //land on top row
        if(i>20 & i<30){
            x = (boardSize / 700) * 95 + (56.66 * (i-21)) + p*5;
            y = (boardSize / 700) * (56.66/2) + p*5; }

        //land on right column
        if(i>30 & i<40){
            x = (boardSize / 700) * 605 + (56.66/2) + p*5;
            y = (boardSize / 700) * 95 + (56.66 * (i-31)) + p*5; }

        this.getChildren().get(p).relocate(x,y);
    }


    /**
     * Calculates which tile was clicked on the board by the user
     * @param x & y: locations of mouse clicked event
     */

    public void tileCalculator(double x, double y){

        double prevX = 0;
        double prevY = 0;

        //Checking tiles for top row
        for(int z = 0; z< 10; z++){
            if(x <= ((boardSize / 700) * 95 + (56.66 * z)) & x > prevX & y <= ((boardSize / 700) * 95))  {
                tileIndex = z+20;}
            prevX = (boardSize / 700) * 95 + (56.66 * z); }

        //Checking tiles for right column
        for(int z = 0; z< 10; z++){
            if(x > ((boardSize / 700) * 605) & y <= ((boardSize / 700) * 95 + (56.66 * z)) & y > prevY)  {
                tileIndex = z + 30;}
            prevY = (boardSize / 700) * 95 + (56.66 * z); }

        prevX = boardSize;
        prevY = boardSize;

        //Checking tiles for bottom row
        for(int z = 0; z< 10; z++){
            if(x >= ((boardSize / 700) * 605 - (56.66 * z)) & x < prevX & y > ((boardSize / 700) * 605))  {
                tileIndex = z;}
            prevX = ((boardSize / 700) * 605 - (56.66 * z)); }

        //Checking tiles for left column
        for(int z = 0; z< 10; z++){
            if(y >= ((boardSize / 700) * 605 - (56.66 * z)) & y < prevY & x < ((boardSize / 700) * 95))  {
                tileIndex = z + 10;}
            prevY = ((boardSize / 700) * 605 - (56.66 * z)); }
    }

    /**
     * Calculates which tile we will put a building
     * @param index & house & hotel: the index of building and the type of building
     */

    public void updateBuildings(int index, int house, boolean hasHotel){
        houseArray[index] = house;
        hotelArray[index] = hasHotel;
        buildingPositioner(index);

        Image im;

        if(hasHotel) {
            im = new Image("/view/images/boardModifiers/boardHotel.png"); }

        else if (house != 0){
            im = new Image("/view/images/boardModifiers/boardHouse" + house + ".png"); }

        else{
            im = new Image("/view/images/boardModifiers/void.png"); }

        buildingImages[index].setImage(im);
        buildingImages[index].relocate(coordinateX,coordinateY);
        buildingImages[index].setFitHeight(20);
        buildingImages[index].setFitWidth(20);
    }

    /**
     * Helper Method that Calculates which pixel to put png image
     * @param i: the index of building
     */

    private void buildingPositioner(int i) {

        //Buildings on bottom row
        if (i <= 10) {
            coordinateX = (boardSize / 700) * 625 - (56.66 * i);
            coordinateY = (boardSize / 700) * 605; }

        //buildings on left column
        if (i > 10 & i < 20) {
            coordinateY = (boardSize / 700) * 625 - (56.66 * (i - 10));
            coordinateX = (boardSize / 700) * (75); }

        //Buildings on top row
        if (i > 20 & i < 30) {
            coordinateX = (boardSize / 700) * 115 + (56.66 * (i - 21));
            coordinateY = (boardSize / 700) * (73); }

        //Buildings on right column
        if (i > 30 & i < 40) {
            coordinateX = (boardSize / 700) * 605;
            coordinateY = (boardSize / 700) * 115 + (56.66 * (i - 31)); }
    }

}
