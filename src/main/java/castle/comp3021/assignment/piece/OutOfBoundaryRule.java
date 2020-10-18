package castle.comp3021.assignment.piece;

import castle.comp3021.assignment.protocol.Game;
import castle.comp3021.assignment.protocol.Move;

/**
 * Global rule that requires the source and destination should be inside the board boundary.
 */
public class OutOfBoundaryRule implements Rule {
    @Override
    public boolean validate(Game game, Move move) {
        if (move.getSource().x() < 0 ||
                move.getSource().y() < 0) {
            return false;
        }
        if (move.getDestination().x() < 0 ||
                move.getDestination().y() < 0) {
            return false;
        }
        if (move.getSource().x() >= game.getConfiguration().getSize() ||
                move.getSource().y() >= game.getConfiguration().getSize()) {
            return false;
        }
        return move.getDestination().x() < game.getConfiguration().getSize() &&
                move.getDestination().y() < game.getConfiguration().getSize();
    }

    @Override
    public String getDescription() {
        return "place is out of boundary of gameboard";
    }
}
