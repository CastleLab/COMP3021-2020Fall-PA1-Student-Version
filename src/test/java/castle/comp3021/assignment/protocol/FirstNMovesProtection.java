package castle.comp3021.assignment.protocol;

import castle.comp3021.assignment.JesonMor;
import castle.comp3021.assignment.mock.MockPlayer;
import castle.comp3021.assignment.piece.Knight;
import castle.comp3021.assignment.util.UnitTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FirstNMovesProtection {
    private MockPlayer player1;
    private MockPlayer player2;
    private Knight knight1;
    private Knight knight2;

    @BeforeEach
    public void setUpGame() {
        this.player1 = new MockPlayer(Color.PURPLE);
        this.player2 = new MockPlayer(Color.YELLOW);
        this.knight1 = new Knight(player1);
        this.knight2 = new Knight(player2);
    }


    @Test
    @UnitTest
    public void testNoProtection() {
        var config = new Configuration(3, new Player[]{player1, player2}, 0);
        config.addInitialPiece(knight1, 0, 0);
        config.addInitialPiece(knight2, 2, 1);
        var game = new JesonMor(config);
        assertTrue(Arrays.asList(game.getAvailableMoves(player1)).contains(new Move(0, 0, 2, 1)));
    }

    @Test
    @UnitTest
    public void testWithProtection() {
        var config = new Configuration(3, new Player[]{player1, player2}, 5);
        config.addInitialPiece(knight1, 0, 0);
        config.addInitialPiece(knight2, 2, 1);
        var game = new JesonMor(config);
        assertFalse(Arrays.asList(game.getAvailableMoves(player1)).contains(new Move(0, 0, 2, 1)));
    }

    @Test
    @UnitTest
    public void testWithThenWithoutProtection() {
        var config = new Configuration(3, new Player[]{player1, player2}, 5);
        config.addInitialPiece(knight1, 0, 0);
        config.addInitialPiece(knight2, 2, 1);
        var game = new JesonMor(config);
        game.numMoves = 5;
        assertTrue(Arrays.asList(game.getAvailableMoves(player1)).contains(new Move(0, 0, 2, 1)));
    }
}
