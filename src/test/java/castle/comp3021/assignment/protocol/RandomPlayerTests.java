package castle.comp3021.assignment.protocol;

import castle.comp3021.assignment.JesonMor;
import castle.comp3021.assignment.mock.MockPiece;
import castle.comp3021.assignment.mock.MockPlayer;
import castle.comp3021.assignment.player.RandomPlayer;
import castle.comp3021.assignment.util.SampleTest;
import castle.comp3021.assignment.util.UnitTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RandomPlayerTests {
    private Configuration config;
    private MockPlayer player1;
    private RandomPlayer player2;

    @BeforeEach
    public void setUpGame() {
        this.player1 = new MockPlayer(Color.PURPLE);
        this.player2 = new RandomPlayer("RandomPlayer");
        this.config = new Configuration(3, new Player[]{player1, player2});
    }

    @Test
    @SampleTest
    public void testNextMove() {
        var piece1 = new MockPiece(player1);
        var piece2 = new MockPiece(player2);
        this.config.addInitialPiece(piece1, 0, 0);
        this.config.addInitialPiece(piece2, 2, 2);
        var game =  new JesonMor(this.config);
        var move = player2.nextMove(game, game.getAvailableMoves(player2));
        assertTrue(Arrays.asList(game.getAvailableMoves(player2)).contains(move));
    }

    /**
     * Test the randomness
     */
    @Test
    @UnitTest
    public void testNextMoveRandom() {
        var piece1 = new MockPiece(player1);
        var piece2 = new MockPiece(player2);
        this.config.addInitialPiece(piece1, 0, 0);
        this.config.addInitialPiece(piece2, 1, 2);
        var game = new JesonMor(this.config);
        var hitMap = new HashMap<Move, Boolean>();
        hitMap.put(new Move(1, 2, 0, 2), false);
        hitMap.put(new Move(1, 2, 2, 2), false);
        hitMap.put(new Move(1, 2, 1, 1), false);
        for (int i = 0; i < 100; i++) {
            var move = player2.nextMove(game, game.getAvailableMoves(player2));
            hitMap.put(move, true);
        }
        for (var entry :
                hitMap.entrySet()) {
            assertTrue(entry.getValue());
        }
    }
}
