package monopoly_team04.Model;

<<<<<<< HEAD
<<<<<<< HEAD
import monopoly_team04.Model.Controller.Game;

=======
>>>>>>> 82633877f6cc8985eab3c12b8c74116b0fd35b65
=======
>>>>>>> 01cbc3aa8adc2d94a0e2c353efe82add9cc6e4ee
/**
 * Concrete subclasses will extend this eventual abstract class
 * Subclasses based on their doAction methods
 */
public interface ActionCard {

    /**
     * doAction method will vary for different cards
     * Some actions will include: go to jail, get out of jail, win money, lose money, move to space
     * Must take Game as parameter to handle all possible actions
     * Need game to access board if action is moveTo/goToJail
     * Need game to access bank if action is win money from bank and players if player to your right pays you...
     * @param game
     */
    public void doAction(Game game);
}