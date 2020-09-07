package castle.comp3021.assignment.player;

import castle.comp3021.assignment.protocol.Game;
import castle.comp3021.assignment.protocol.Color;
import castle.comp3021.assignment.protocol.Move;
import castle.comp3021.assignment.protocol.Player;
import org.jetbrains.annotations.NotNull;

/**
 * A computer player that makes a move randomly.
 */
public class RandomPlayer extends Player {
    public RandomPlayer(String name, Color color) {
        super(name, color);
    }

    public RandomPlayer(String name) {
        this(name, Color.BLUE);
    }

    /**
     * Choose a move from available moves.
     * This method will be called by {@link Game} object to get the move that the player wants to make when it is the
     * player's turn.
     * <p>
     * {@link RandomPlayer} chooses a move from available ones randomly.
     * <p>
     * <strong>Attention: Student should make sure the {@link Move} returned is valid.</strong>
     *
     * @param game           the current game object
     * @param availableMoves available moves for this player to choose from.
     * @return the chosen move
     */
    @Override
    public @NotNull Move nextMove(Game game, Move[] availableMoves) {
        // TODO student implementation
        return availableMoves[0];
    }
}
