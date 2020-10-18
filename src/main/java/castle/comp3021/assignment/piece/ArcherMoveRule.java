package castle.comp3021.assignment.piece;

import castle.comp3021.assignment.protocol.Game;
import castle.comp3021.assignment.protocol.Move;

/**
 * The rule of moving of Archer, which is similar to the moving rule of cannon in Chinese chess.
 *
 * @see <a href='https://en.wikipedia.org/wiki/Xiangqi#Cannon'>Wikipedia</a>
 */
public class ArcherMoveRule implements Rule {
    @Override
    public boolean validate(Game game, Move move) {
        if (!(game.getPiece(move.getSource()) instanceof Archer)) {
            return true;
        }
        // TODO student implementation
        var source = move.getSource();
        var destination = move.getDestination();
        var isCapturing = false;
        var sourcePiece = game.getPiece(move.getSource());
        if (sourcePiece == null) {
            return true;
        }
        var captured = game.getPiece(move.getDestination());
        if (captured != null) {
            if (captured.getPlayer().equals(sourcePiece.getPlayer())) {
                return true;
            }
            isCapturing = true;
        }
        // only one piece can be in between source and destination
        if (source.x() == destination.x()) {
            int lowerY, higherY;
            if (source.y() < destination.y()) {
                lowerY = source.y();
                higherY = destination.y();
            } else {
                higherY = source.y();
                lowerY = destination.y();
            }
            var numPieces = 0;
            for (int y = lowerY + 1; y < higherY; y++) {
                var piece = game.getPiece(source.x(), y);
                if (piece != null) {
                    numPieces++;
                }
            }
            if (isCapturing && numPieces != 1) {
                return false;
            }
            return isCapturing || numPieces == 0;
        } else if (source.y() == destination.y()) {
            int lowerX, higherX;
            if (source.x() < destination.x()) {
                lowerX = source.x();
                higherX = destination.x();
            } else {
                higherX = source.x();
                lowerX = destination.x();
            }
            var numPieces = 0;
            for (int x = lowerX + 1; x < higherX; x++) {
                var piece = game.getPiece(x, source.y());
                if (piece != null) {
                    numPieces++;
                }
            }
            if (isCapturing && numPieces != 1) {
                return false;
            }
            return isCapturing || numPieces == 0;
        } else {
            return false;
        }
    }

    @Override
    public String getDescription() {
        return "archer move rule is violated";
    }
}
