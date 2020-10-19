package castle.comp3021.assignment.protocol;

import castle.comp3021.assignment.JesonMor;
import castle.comp3021.assignment.mock.MockPlayer;
import castle.comp3021.assignment.piece.Archer;
import castle.comp3021.assignment.piece.Knight;
import castle.comp3021.assignment.util.OptionalArcherImplementation;
import castle.comp3021.assignment.util.SampleTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameTieBreakerTests {
    @Test
    @OptionalArcherImplementation
    public void testMoveAndDeadlock() {
        var player1 = new MockPlayer();
        var player2 = new MockPlayer();
        var config = new Configuration(3, new Player[]{player1, player2});
        config.addInitialPiece(new Knight(player1), 0, 0);
        config.addInitialPiece(new Archer(player1), 1, 0);
        config.addInitialPiece(new Knight(player1), 2, 0);
        config.addInitialPiece(new Knight(player2), 0, 2);
        config.addInitialPiece(new Archer(player2), 1, 2);
        config.addInitialPiece(new Knight(player2), 2, 2);
        var game = new JesonMor(config);
        player1.setNextMoves(new Move[]{
                new Move(1, 0, 1, 1),
                new Move(2, 0, 1, 2),
        });
        player2.setNextMoves(new Move[]{
                new Move(0, 2, 1, 0),
        });
        var winner = game.start();
        assertEquals(player2, winner);
        assertEquals(4, player1.getScore());
        assertEquals(3, player2.getScore());
        assertEquals(3, game.getNumMoves());
    }
}
