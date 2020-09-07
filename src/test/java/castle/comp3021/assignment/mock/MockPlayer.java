package castle.comp3021.assignment.mock;

import castle.comp3021.assignment.protocol.Color;
import castle.comp3021.assignment.protocol.Game;
import castle.comp3021.assignment.protocol.Move;
import castle.comp3021.assignment.protocol.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class MockPlayer extends Player {
    private Move[] nextMoves;
    private int nextMoveCounter = 0;

    public MockPlayer() {
        this(Color.CYAN);
    }

    public MockPlayer(Color color) {
        super("MockPlayer-" + new Random().nextInt(), color);
    }

    public void resetCounter() {
        this.nextMoveCounter = 0;
    }

    public void setNextMoves(Move[] nextMoves) {
        this.nextMoves = nextMoves;
        this.resetCounter();
    }

    @Override
    public @NotNull Move nextMove(Game game, Move[] availableMoves) {
        if (nextMoves == null) {
            return availableMoves[new Random().nextInt(availableMoves.length)];
        } else {
            return this.nextMoves[this.nextMoveCounter++];
        }
    }
}
