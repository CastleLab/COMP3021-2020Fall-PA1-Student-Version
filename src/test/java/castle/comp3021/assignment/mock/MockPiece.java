package castle.comp3021.assignment.mock;

import castle.comp3021.assignment.protocol.*;

import java.util.ArrayList;
import java.util.Arrays;

public class MockPiece extends Piece {
    public MockPiece(Player player) {
        super(player);
    }

    public MockPiece() {
        super(new MockPlayer());
    }

    @Override
    public char getLabel() {
        return 'M';
    }

    @Override
    public Move[] getAvailableMoves(Game game, Place source) {
        var moves = new ArrayList<>(Arrays.asList(
                new Move(source, source.x() + 1, source.y()),
                new Move(source, source.x() - 1, source.y()),
                new Move(source, source.x(), source.y() + 1),
                new Move(source, source.x(), source.y() - 1)));
        return moves.stream()
                .filter(move -> validateMove(game, move))
                .toArray(Move[]::new);
    }

    private boolean validateMove(Game game, Move move) {
        return move.getDestination().x() < game.getConfiguration().getSize()
                && move.getDestination().y() < game.getConfiguration().getSize()
                && move.getSource().x() < game.getConfiguration().getSize()
                && move.getSource().y() < game.getConfiguration().getSize()
                && move.getDestination().x() >= 0
                && move.getDestination().y() >= 0
                && move.getSource().x() >= 0
                && move.getSource().y() >= 0;
    }
}
