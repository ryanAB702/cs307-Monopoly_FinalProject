package View;

import Model.*;
import View.SpaceDisplay.*;
import View.SpaceDisplay.CornerDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;

import java.awt.geom.Point2D;
import java.util.Map;

public class Board {
    //Todo: needs to be refactored but wanted to make it work, all data is read in from file

    private static final String BOARD_PATH = "classic.jpg";

    private Pane myBoardPane;
    private GridPane myGridPane;
    private ImageView boardLogo;
    private Map<Point2D.Double, AbstractSpace> indexToName;
    private Map<String,String> nameToColor;
    private Map<String,Integer> nameToPrice;

    public Board(Pane board) {
        this.myBoardPane = board;
        myGridPane = new GridPane();
        myGridPane.setGridLinesVisible(true);
        setUpGridConstraints();
        setUpBoardConfig();
        createSpaces();
    }


    private void createSpaces(){
        for (Map.Entry<Point2D.Double, AbstractSpace> entry : indexToName.entrySet()) {
            String name = entry.getValue().getMyName().replace("_", " ");
            if (entry.getValue() instanceof PropSpace) {
                String price = nameToPrice.get(name).toString();
                String color = nameToColor.get(name);
                if (entry.getKey().getY() == 10) {
                    BottomPropertyDisplay propSpaces = new BottomPropertyDisplay(name, price, color, myBoardPane, "#c7edc9");
                    myGridPane.add(propSpaces.getMyPropStackPane(), (int) entry.getKey().getX(), 10);
                }
                if (entry.getKey().getX() == 0) {
                    LeftPropertyDisplay propSpaces = new LeftPropertyDisplay(name, price, color, myBoardPane, "#c7edc9");
                    myGridPane.add(propSpaces.getMyPropStackPane(), 0, (int) entry.getKey().getY());
                }
                if (entry.getKey().getY() == 0) {
                    TopPropertyDisplay propSpaces = new TopPropertyDisplay(name, price, color, myBoardPane, "#c7edc9");
                    myGridPane.add(propSpaces.getMyPropStackPane(), (int) entry.getKey().getX(), 0);
                }
                if (entry.getKey().getX() == 10) {
                    RightPropertyDisplay propSpaces = new RightPropertyDisplay(name, price, color, myBoardPane, "#c7edc9");
                    myGridPane.add(propSpaces.getMyPropStackPane(), 10, (int) entry.getKey().getY());
                }
            }
            if (entry.getValue() instanceof TaxSpace) {
                if (entry.getKey().getY() == 10) {
                    BottomPropertyDisplay propSpaces = new BottomPropertyDisplay(myBoardPane, "#c7edc9", "tax.png");
                    myGridPane.add(propSpaces.getMyPropStackPane(), (int) entry.getKey().getX(), 10);
                }
                if (entry.getKey().getX() == 10) {
                    RightPropertyDisplay propSpaces = new RightPropertyDisplay(myBoardPane, "#c7edc9", "tax.png");
                    myGridPane.add(propSpaces.getMyPropStackPane(), 10, (int) entry.getKey().getY());
                }
            }
            if (entry.getValue() instanceof ActionCardSpace) {
                String image;
                if (name.equals("COMMUNITY CHEST")) {
                    image = "chest.png";
                } else {
                    image = "chance.png";
                }
                if (entry.getKey().getY() == 10) {
                    BottomPropertyDisplay propSpaces = new BottomPropertyDisplay(myBoardPane, "#c7edc9", image);
                    myGridPane.add(propSpaces.getMyPropStackPane(), (int) entry.getKey().getX(), 10);
                }
                if (entry.getKey().getX() == 10) {
                    RightPropertyDisplay propSpaces = new RightPropertyDisplay(myBoardPane, "#c7edc9", image);
                    myGridPane.add(propSpaces.getMyPropStackPane(), 10, (int) entry.getKey().getY());
                }
                if (entry.getKey().getX() == 0) {
                    LeftPropertyDisplay propSpaces = new LeftPropertyDisplay(myBoardPane, "#c7edc9", image);
                    myGridPane.add(propSpaces.getMyPropStackPane(), 0, (int) entry.getKey().getY());
                }
                if (entry.getKey().getY() == 0) {
                    TopPropertyDisplay propSpaces = new TopPropertyDisplay(myBoardPane, "#c7edc9", image);
                    myGridPane.add(propSpaces.getMyPropStackPane(), (int) entry.getKey().getX(), 0);
                }
            } else {
                CornerDisplay goSpace = new CornerDisplay("#c7edc9", myBoardPane, "go.png");
                myGridPane.add(goSpace.getMyPropertyStackPane(), 10, 10);
                CornerDisplay parkingSpace = new CornerDisplay("#c7edc9", myBoardPane, "freeParking.png");
                myGridPane.add(parkingSpace.getMyPropertyStackPane(), 0, 0);
                CornerDisplay jailSpace = new CornerDisplay("#c7edc9", myBoardPane, "jail.png");
                myGridPane.add(jailSpace.getMyPropertyStackPane(), 0, 10);
                CornerDisplay goToJail = new CornerDisplay("#c7edc9", myBoardPane, "goToJail.png");
                myGridPane.add(goToJail.getMyPropertyStackPane(), 10, 0);

                //Todo: THIS IS JUST AN EXAMPLE FOR SPRINT 1 PURPOSES WILL REFACTOR OUT AFTER
                StackPane token = new StackPane();
                Circle popUpExample = new Circle(10);
                Label player1 = new Label("1");
                player1.setId("popUp");
                token.getChildren().addAll(popUpExample,player1);

                Popup myPopup = new BuyPropertyPopup("Property", "Would you like to purchase this property?");
                popUpExample.setOnMouseClicked(e -> myPopup.display());
                myGridPane.add(token, 1 ,10);

            }
        }
    }

    private void setUpGridConstraints(){
        final int numCols = 11;
        final int numRows = 11;
        for (int i = 0; i < numCols; i++) {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPercentWidth(myBoardPane.getPrefHeight() / numCols);
            myGridPane.getColumnConstraints().add(colConst);
        }
        for (int i = 0; i < numRows; i++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPercentHeight(myBoardPane.getPrefHeight() / numRows);
            myGridPane.getRowConstraints().add(rowConst);
        }
    }

    private void setUpBoardConfig(){
        BoardConfigReader configs = new BoardConfigReader();
        indexToName = configs.getIndexToName();
        nameToColor = configs.getNameToColor();
        nameToPrice = configs.getNameToPrice();
    }

    public Pane getGridPane() {
        return myGridPane;
    }

    public ImageView getLogo() {
        var logo = new Image(this.getClass().getClassLoader().getResourceAsStream(BOARD_PATH));
        boardLogo = new ImageView(logo);
        boardLogo.setFitWidth((myBoardPane.getPrefWidth() / 13) * 9);
        boardLogo.setFitHeight((myBoardPane.getPrefWidth() / 13) * 9);
        boardLogo.setId("boardLogo");
        return boardLogo;
    }
}