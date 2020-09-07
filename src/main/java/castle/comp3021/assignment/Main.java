package castle.comp3021.assignment;

import castle.comp3021.assignment.piece.Knight;
import castle.comp3021.assignment.player.ConsolePlayer;
import castle.comp3021.assignment.player.RandomPlayer;
import castle.comp3021.assignment.protocol.Configuration;
import castle.comp3021.assignment.protocol.Game;
import castle.comp3021.assignment.protocol.Player;

public class Main {
    /**
     * Create and initialize a game.
     * Student should first construct a {@link Configuration} object with two players.
     * Then add initial pieces to gameboard in the {@link Configuration} object.
     * After that, use this {@link Configuration} object to construct a {@link JesonMor} game object
     * This method should return the created {@link JesonMor} object.
     * <p>
     * The initialized gameboard should comply to the requirements of the assignment.
     * The user player should be put
     * first in the player array in the configuration and user player should moves first.
     *
     * <strong>Attention: The code in this method is only an example of using {@link Configuration} to initialize
     * gameboard, Students should remove them and implement on their own.</strong>
     *
     * @param size               size of gameboard
     * @param numMovesProtection number of moves with capture protection
     * @return the game object
     */
    public static Game createGame(int size, int numMovesProtection) {
        // TODO student implementation
        // The following lines are example of constructing a game object, you may modify them as you wish.
        var userPlayer = new ConsolePlayer("UserPlayer");
        var computerPlayer = new RandomPlayer("ComputerPlayer");
        // we give
        Configuration configuration = new Configuration(size, new Player[]{userPlayer, computerPlayer}, numMovesProtection);
        var knight1 = new Knight(userPlayer);
        var knight2 = new Knight(computerPlayer);
        // put knight1 at place(0,0) on the gameboard
        configuration.addInitialPiece(knight1, 0, 0);
        // put knight2 at place(1,0) on the gameboard
        configuration.addInitialPiece(knight2, 1, 0);
        return new JesonMor(configuration);
    }

    public static void main(String[] args) {
        // start the game
        var helper = "two integer arguments are required specifying size of gameboard and number of moves with capturing protection ";
        if (args.length < 2) {
            throw new IllegalArgumentException(helper);
        }
        int size;
        int numMovesProtection;
        try {
            size = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("the first argument is not a number");
        }
        try {
            numMovesProtection = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("the second argument is not a number");
        }
        createGame(size, numMovesProtection).start();
    }
}
