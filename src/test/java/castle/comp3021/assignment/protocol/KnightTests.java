package castle.comp3021.assignment.protocol;

import castle.comp3021.assignment.JesonMor;
import castle.comp3021.assignment.mock.MockPiece;
import castle.comp3021.assignment.mock.MockPlayer;
import castle.comp3021.assignment.piece.Knight;
import castle.comp3021.assignment.util.Compares;
import castle.comp3021.assignment.util.SampleTest;
import castle.comp3021.assignment.util.UnitTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class KnightTests {
    private Configuration config;
    private MockPlayer player1;
    private MockPlayer player2;

    @BeforeEach
    public void setUpGame() {
        this.player1 = new MockPlayer(Color.PURPLE);
        this.player2 = new MockPlayer(Color.YELLOW);
        this.config = new Configuration(5, new Player[]{player1, player2});
    }

    /**
     * Test if the returned value of {@link Knight#getAvailableMoves(Game, Place)} contains and only contains all
     * valid moves.
     */
    @Test
    @SampleTest
    public void testGetAvailableMovesSimple() {
        var knight1 = new Knight(player1);
        var knight2 = new Knight(player2);
        this.config.addInitialPiece(knight1, 0, 0);
        this.config.addInitialPiece(knight2, 4, 4);
        var game = new JesonMor(this.config);
        var moves = knight1.getAvailableMoves(game, new Place(0, 0));
        var expectedMoves = new Move[]{
                new Move(0, 0, 1, 2),
                new Move(0, 0, 2, 1),
        };
        Assertions.assertTrue(Compares.areContentsEqual(moves, expectedMoves));

        moves = knight2.getAvailableMoves(game, new Place(4, 4));
        expectedMoves = new Move[]{
                new Move(4, 4, 3, 2),
                new Move(4, 4, 2, 3),
        };
        assertTrue(Compares.areContentsEqual(moves, expectedMoves));
    }

    /**
     * Test if the returned value of {@link Knight#getAvailableMoves(Game, Place)} contains and only contains all
     * valid moves.
     */
    @Test
    @UnitTest
    public void testGetAvailableMovesOccupied() {
        var knight1 = new Knight(player1);
        var knight2 = new Knight(player2);
        this.config.addInitialPiece(knight1, 1, 1);
        this.config.addInitialPiece(knight2, 4, 4);
        this.config.addInitialPiece(new MockPiece(player1), 3, 2);
        var game = new JesonMor(this.config);
        var moves = knight1.getAvailableMoves(game, new Place(1, 1));
        var expectedMoves = new Move[]{
                new Move(1, 1, 3, 0),
                new Move(1, 1, 0, 3),
                new Move(1, 1, 2, 3),
        };
        assertTrue(Compares.areContentsEqual(moves, expectedMoves));
    }

    /**
     * Test if the returned value of {@link Knight#getAvailableMoves(Game, Place)} contains and only contains all
     * valid moves.
     */
    @Test
    @UnitTest
    public void testGetAvailableMovesCapture() {
        var knight1 = new Knight(player1);
        var knight2 = new Knight(player2);
        this.config.addInitialPiece(knight1, 1, 1);
        this.config.addInitialPiece(knight2, 2, 3);
        var game = new JesonMor(this.config);
        var moves = knight1.getAvailableMoves(game, new Place(1, 1));
        var expectedMoves = new Move[]{
                new Move(1, 1, 3, 0),
                new Move(1, 1, 0, 3),
                new Move(1, 1, 2, 3),
                new Move(1, 1, 3, 2),
        };
        assertTrue(Compares.areContentsEqual(moves, expectedMoves));
    }

    /**
     * Test if the returned value of {@link Knight#getAvailableMoves(Game, Place)} contains and only contains all
     * valid moves.
     */
    @Test
    @UnitTest
    public void testGetAvailableMovesBlocked() {
        this.config = new Configuration(9, new Player[]{player1, player2});
        var knight1 = new Knight(player1);
        var knight2 = new Knight(player2);
        this.config.addInitialPiece(knight1, 3, 3);
        this.config.addInitialPiece(knight2, 2, 3);
        var game = new JesonMor(this.config);
        var moves = knight1.getAvailableMoves(game, new Place(3, 3));
        var expectedMoves = new Move[]{
                new Move(3, 3, 2, 1),
                new Move(3, 3, 2, 5),
                new Move(3, 3, 4, 1),
                new Move(3, 3, 4, 5),
                new Move(3, 3, 5, 2),
                new Move(3, 3, 5, 4),
        };
        assertTrue(Compares.areContentsEqual(moves, expectedMoves));
    }
}
