package castle.comp3021.assignment.piece;

import castle.comp3021.assignment.protocol.Game;
import castle.comp3021.assignment.protocol.Move;
import castle.comp3021.assignment.protocol.Place;

/**
 * The blocking rule applying on Knights. The rule is similar to the blocking rule for horse in Chinese chess.
 *
 * @see <a href='https://en.wikipedia.org/wiki/Xiangqi#Horse'>Wikipedia</a>
 */
public class KnightBlockRule implements Rule {
    @Override
    public boolean validate(Game game, Move move) {
        if (!(game.getPiece(move.getSource()) instanceof Knight)) {
            return true;
        }
        // TODO student implementation

        var source = move.getSource();
        var destination = move.getDestination();
        if (Math.abs(source.x() - destination.x()) == 2) {
            var blockPlace = new Place((source.x() + destination.x()) / 2, source.y());
            // some other piece is at the block place of knight
            return game.getPiece(blockPlace) == null;
        } else if (Math.abs(source.y() - destination.y()) == 2) {
            var blockPlace = new Place(source.x(), (source.y() + destination.y()) / 2);
            // some other piece is at the block place of knight
            return game.getPiece(blockPlace) == null;
        } else {
            return false;
        }
    }

    @Override
    public String getDescription() {
        return "knight is blocked by another piece";
    }
}
