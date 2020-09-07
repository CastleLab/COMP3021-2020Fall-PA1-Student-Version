package castle.comp3021.assignment.mock;

import castle.comp3021.assignment.protocol.Color;
import castle.comp3021.assignment.protocol.Game;
import castle.comp3021.assignment.protocol.Move;
import org.jetbrains.annotations.NotNull;

public class EventuallyWinPlayer extends MockPlayer {
    public EventuallyWinPlayer(Color color) {
        super(color);
    }

    @Override
    public @NotNull Move nextMove(Game game, Move[] availableMoves) {
        var minDistance = Integer.MAX_VALUE;
        var best = availableMoves[0];
        for (var move :
                availableMoves) {
            var dist = Math.abs(move.getDestination().x() - move.getSource().x()) +
                    Math.abs(move.getDestination().y() - move.getSource().y());
            if (dist <= minDistance) {
                minDistance = dist;
                best = move;
            }
        }
        return best;
    }
}
