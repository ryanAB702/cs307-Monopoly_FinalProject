package usecases

import monopoly_team04.Model.*;

/***
 * 2. A player lands on Chance, draws "Advance to Go, collect $200", your token is moved and your funds are updated appropriately,
 * and the card is returned to the bottom of the deck.
 */
<<<<<<< HEAD
<<<<<<< HEAD
import monopoly_team04.Model.Controller.Board;
import monopoly_team04.Model.Controller.Dice;
import monopoly_team04.Model.Controller.Game;
import monopoly_team04.Model.*;
import org.mockito.Mockito;

import monopoly_team04.Model.*;
=======
=======
>>>>>>> 01cbc3aa8adc2d94a0e2c353efe82add9cc6e4ee
import Controller.Board;
import Controller.Dice;
import Model.*;

import Controller.*;
import Model.*;
<<<<<<< HEAD
>>>>>>> 82633877f6cc8985eab3c12b8c74116b0fd35b65
=======
>>>>>>> 01cbc3aa8adc2d94a0e2c353efe82add9cc6e4ee

/***
 * 2. A player lands on Chance, draws "Advance to Go, collect $200", your token is moved and your funds are updated appropriately,
 * and the card is returned to the bottom of the deck.
 */
public class apiUseCase4{
    Player player = Mockito.mock(Player.class);
    Board board = Mockito.mock(Board.class);
    Game game = Mockito.mock(Game.class);
    Bank bank = Mockito.mock(Bank.class);
    Dice dice = Mockito.mock(Dice.class);
    Deck chanceDeck = Mockito.mock(Deck.class);

    game.startTurn(player);
    //In start turn method of game:
    int roll = game.dice.roll(6);
    int newLocation = player.token.move(roll);
    Space sp = board.getSpaceAt(newLocation);
    sp.doAction(game);

    //doAction method for chance space will ->
    ActionCard cd = game.chanceDeck.draw();
    cd.doAction(game);

    //cd.doAction method will ->
    game.bank.makePayment(200, player);
    //the Go space, which is a concrete AbstractSpace class, will have a method that gets its index on the board
    int Goindex = 29;
    player.getMyToken.moveTo(Goindex);
}