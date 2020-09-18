package castle.comp3021.assignment.protocol;

import castle.comp3021.assignment.JesonMor;
import castle.comp3021.assignment.mock.MockPlayer;
import castle.comp3021.assignment.piece.Knight;
import castle.comp3021.assignment.util.SampleTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameMockPlayerIntegratedTests {
    private Configuration config;
    private MockPlayer player1;
    private MockPlayer player2;

    @BeforeEach
    public void setUpGame() {
        this.player1 = new MockPlayer(Color.PURPLE);
        this.player2 = new MockPlayer(Color.YELLOW);
        this.config = new Configuration(3, new Player[]{player1, player2});
    }

    @AfterEach
    public void clear() {
        this.player1 = null;
        this.player2 = null;
        this.config = null;
    }

    @Test
    @SampleTest
    public void testCentralPlaceWin() {
        var config = new Configuration(5, new Player[]{player1, player2});
        config.addInitialPiece(new Knight(player1), 0, 0);
        config.addInitialPiece(new Knight(player2), 4, 4);
        player1.setNextMoves(new Move[]{
                new Move(0, 0, 2, 1),
                new Move(2, 1, 4, 2),
                new Move(4, 2, 3, 0),
                new Move(3, 0, 2, 2),
                new Move(2, 2, 1, 4),
        });
        player2.setNextMoves(new Move[]{
                new Move(4, 4, 2, 3),
                new Move(2, 3, 4, 4),
                new Move(4, 4, 2, 3),
                new Move(2, 3, 4, 4),
        });
        var game = new JesonMor(config);
        var winner = game.start();
        assertEquals(player1, winner);
        assertEquals(9, game.getNumMoves());
        assertEquals(15, player1.getScore());
        assertEquals(12, player2.getScore());
    }
}
