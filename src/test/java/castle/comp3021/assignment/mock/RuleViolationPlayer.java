package castle.comp3021.assignment.mock;

import castle.comp3021.assignment.protocol.Color;
import castle.comp3021.assignment.protocol.Game;
import castle.comp3021.assignment.protocol.Move;
import org.jetbrains.annotations.NotNull;


public class RuleViolationPlayer extends EventuallyWinPlayer {
    public RuleViolationPlayer(Color color) {
        super(color);
    }

    public interface NextMoveListener {
        public Move nextMove(Game game, Move[] availableMoves);
    }

    private NextMoveListener nextMoveListener;

    public void setNextMoveListener(NextMoveListener nextMoveListener) {
        this.nextMoveListener = nextMoveListener;
    }

    @Override
    public @NotNull Move nextMove(Game game, Move[] availableMoves) {
        if (this.nextMoveListener == null) {
            return super.nextMove(game, availableMoves);
        } else {
            var move = this.nextMoveListener.nextMove(game, availableMoves);
            if (move == null) {
                return super.nextMove(game, availableMoves);
            }
            return move;
        }
    }
}
