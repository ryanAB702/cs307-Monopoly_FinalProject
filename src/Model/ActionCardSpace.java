package Model;

import java.util.ArrayList;
import java.util.List;

public class ActionCardSpace extends AbstractSpace {

    List<Player> myOccupants = new ArrayList<>();
    Deck myDeck;

    public ActionCardSpace(int locationIndex, String spaceName, Deck deckType){
        super(locationIndex, spaceName);
        myDeck = deckType;
    }


    /***
     * This method performs the specific action that a type of space requires.
     * It takes game in as a parameter so that it can do things such as
     * figure out the current player, get the bank and perform bank actions,
     * get a specific deck and draw a card, and more.
     * @param game the active Game driver class for this game
     */
    public void doAction(Game game){
        ActionCard cardDrawn = myDeck.drawCard();
        cardDrawn.doAction(game);
    }


}
