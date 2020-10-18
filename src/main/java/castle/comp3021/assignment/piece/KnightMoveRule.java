package castle.comp3021.assignment.piece;

import castle.comp3021.assignment.protocol.Game;
import castle.comp3021.assignment.protocol.Move;

/**
 * Moving rule of Knight in chess (no block)
 */
public class KnightMoveRule implements Rule {

    @Override
    public boolean validate(Game game, Move move) {
        if (!(game.getPiece(move.getSource()) instanceof Knight)) {
            return true;
        }
        // TODO student implementation
        var source = move.getSource();
        var destination = move.getDestination();
        return Math.abs(destination.x() - source.x()) == 2 && Math.abs(destination.y() - source.y()) == 1 ||
                Math.abs(destination.x() - source.x()) == 1 && Math.abs(destination.y() - source.y()) == 2;
    }

    @Override
    public String getDescription() {
        return "knight move rule is violated";
    }
}
