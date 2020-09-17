package castle.comp3021.assignment.protocol;

import castle.comp3021.assignment.JesonMor;
import castle.comp3021.assignment.mock.MockPiece;
import castle.comp3021.assignment.mock.MockPlayer;
import castle.comp3021.assignment.piece.Knight;
import castle.comp3021.assignment.util.Compares;
import castle.comp3021.assignment.util.SampleTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class GameUnitTests {
    @Test
    @SampleTest
    public void testGetPiece() {
        var player = new MockPlayer();
        var config = new Configuration(3, new Player[]{player, new MockPlayer()});

        var piece1 = new MockPiece(player);
        config.addInitialPiece(piece1, 0, 0);
        Assertions.assertEquals(piece1, new JesonMor(config).getPiece(0, 0));

        var piece2 = new MockPiece(player);
        config.addInitialPiece(piece2, 0, 0);
        assertNull(new JesonMor(config).getPiece(1, 1));
    }

    @Test
    @SampleTest
    public void testDefaultValues() {
        var player = new MockPlayer();
        var config = new Configuration(3, new Player[]{player, new MockPlayer()});
        var game = new JesonMor(config);
        assertEquals(0, game.getNumMoves());
        assertEquals(player, game.getCurrentPlayer());
        assertEquals(config, game.getConfiguration());
    }

    /**
     * Test a normal move
     **/
    @Test
    @SampleTest
    public void testMovePieceNormal() throws CloneNotSupportedException {
        var player = new MockPlayer();
        var piece = new MockPiece(player);
        var config = new Configuration(3, new Player[]{player, new MockPlayer()});
        config.addInitialPiece(piece, 0, 0);
        var game = new JesonMor(config);
        var beforeBoard = game.clone().board;
        game.movePiece(new Move(0, 0, 1, 0));
        assertNull(game.getPiece(0, 0));
        assertEquals(piece, game.getPiece(1, 0));
        Assertions.assertFalse(Compares.isBoardEqual(beforeBoard, game.board));
    }

    /**
     * The returned available moves should be valid.
     */
    @Test
    @SampleTest
    public void testGetAvailableMovesNormal() {
        var player = new MockPlayer();
        var config = new Configuration(5, new Player[]{player, new MockPlayer()});
        config.addInitialPiece(new MockPiece(player), 1, 1);
        var game = new JesonMor(config);
        var expectMoves = new Move[]{
                new Move(1, 1, 0, 1),
                new Move(1, 1, 1, 0),
                new Move(1, 1, 1, 2),
                new Move(1, 1, 2, 1),
        };
        assertTrue(Compares.areContentsEqual(expectMoves, game.getAvailableMoves(player)));
    }

    /**
     * The score is calculated according to Manhattan distance between source and destination
     */
    @Test
    @SampleTest
    public void testUpdateScoreBasic1() {
        var player = new MockPlayer();
        var config = new Configuration(3, new Player[]{player, new MockPlayer()});
        var piece = new MockPiece(player);
        config.addInitialPiece(piece, 0, 0);
        var game = new JesonMor(config);
        var move = new Move(0, 0, 1, 0);
        game.movePiece(move);
        game.updateScore(player, piece, move);
        assertEquals(1, player.getScore());
    }

    @Test
    @SampleTest
    public void testWinByLeaveCentralPlace() {
        var player1 = new MockPlayer();
        var player2 = new MockPlayer();
        var piece1 = new Knight(player1);
        var config = new Configuration(5, new Player[]{player1, player2});
        config.addInitialPiece(piece1, 1, 0);
        config.addInitialPiece(new Knight(player2), 2, 1);
        var game = new JesonMor(config);
        game.board[1][4] = game.board[1][0];
        game.board[1][0] = null;
        game.board[3][3] = game.board[2][1];
        game.board[2][1] = null;
        game.numMoves = 3;
        game.currentPlayer = player1;
        var winner = game.getWinner(player1, piece1, new Move(2, 2, 1, 4));
        assertEquals(player1, winner);
    }
}
