package castle.comp3021.assignment.protocol;

import castle.comp3021.assignment.JesonMor;
import castle.comp3021.assignment.mock.MockPiece;
import castle.comp3021.assignment.mock.MockPlayer;
import castle.comp3021.assignment.piece.Archer;
import castle.comp3021.assignment.util.Compares;
import castle.comp3021.assignment.util.OptionalArcherImplementation;
import castle.comp3021.assignment.util.SampleTest;
import castle.comp3021.assignment.util.UnitTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

@OptionalArcherImplementation
public class ArcherTests {
    private Configuration config;
    private MockPlayer player1;
    private MockPlayer player2;

    @BeforeEach
    public void setUpGame() {
        this.player1 = new MockPlayer(Color.PURPLE);
        this.player2 = new MockPlayer(Color.YELLOW);
        this.config = new Configuration(3, new Player[]{player1, player2});
    }

    /**
     * Test if the returned value of {@link Archer#getAvailableMoves(Game, Place)} contains and only contains all
     * valid moves.
     */
    @Test
    public void testGetAvailableMovesSimple() {
        var archer1 = new Archer(player1);
        var archer2 = new Archer(player2);
        this.config.addInitialPiece(archer1, 0, 0);
        this.config.addInitialPiece(archer2, 0, 2);
        var game = new JesonMor(this.config);
        var moves = archer1.getAvailableMoves(game, new Place(0, 0));
        var expectedMoves = new Move[]{
                new Move(0, 0, 0, 1),
                new Move(0, 0, 1, 0),
                new Move(0, 0, 2, 0),
        };
        assertTrue(Compares.areContentsEqual(moves, expectedMoves));

        moves = archer2.getAvailableMoves(game, new Place(0, 2));
        expectedMoves = new Move[]{
                new Move(0, 2, 0, 1),
                new Move(0, 2, 1, 2),
                new Move(0, 2, 2, 2),
        };
        assertTrue(Compares.areContentsEqual(moves, expectedMoves));
    }

    /**
     * Test if the returned value of {@link Archer#getAvailableMoves(Game, Place)} contains and only contains all
     * valid moves.
     */
    @Test
    public void testGetAvailableMovesComplex() {
        var archer1 = new Archer(player1);
        var archer2 = new Archer(player2);
        var piece = new MockPiece(player2);
        this.config.addInitialPiece(archer1, 0, 0);
        this.config.addInitialPiece(archer2, 0, 2);
        this.config.addInitialPiece(piece, 0, 1);
        var game = new JesonMor(this.config);
        var moves = archer1.getAvailableMoves(game, new Place(0, 0));
        var expectedMoves = new Move[]{
                new Move(0, 0, 0, 2),
                new Move(0, 0, 1, 0),
                new Move(0, 0, 2, 0),
        };
        assertTrue(Compares.areContentsEqual(moves, expectedMoves));

        moves = archer2.getAvailableMoves(game, new Place(0, 2));
        expectedMoves = new Move[]{
                new Move(0, 2, 0, 0),
                new Move(0, 2, 2, 2),
                new Move(0, 2, 1, 2),
        };
        assertTrue(Compares.areContentsEqual(moves, expectedMoves));
    }

}
