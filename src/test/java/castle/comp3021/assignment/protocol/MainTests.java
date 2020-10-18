package castle.comp3021.assignment.protocol;

import castle.comp3021.assignment.Main;
import castle.comp3021.assignment.piece.Archer;
import castle.comp3021.assignment.piece.Knight;
import castle.comp3021.assignment.util.OptionalArcherImplementation;
import castle.comp3021.assignment.util.SampleTest;
import castle.comp3021.assignment.util.UnitTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class MainTests {
    @Test
    @SampleTest
    public void testInitialBoard1() {
        var game = Main.createGame(9, 0);
        for (int i = 0; i < game.getConfiguration().getSize(); i++) {
            for (int j = 0; j < game.getConfiguration().getSize(); j++) {
                var piece = game.board[i][j];
                // piece must be initially on the first and last row of gameboard
                if (j == 0 || j == game.getConfiguration().getSize() - 1) {
                    assertNotNull(piece);
                } else {
                    assertNull(piece);
                }
            }
        }
    }

    @Test
    @UnitTest
    public void testPieceBelonging() {
        var game = Main.createGame(9, 0);
        var firstRow = new ArrayList<Piece>();
        var lastRow = new ArrayList<Piece>();
        for (int i = 0; i < game.getConfiguration().getSize(); i++) {
            firstRow.add(game.board[i][game.getConfiguration().getSize() - 1]);
            lastRow.add(game.board[i][0]);
        }

        var player = firstRow.get(0).getPlayer();
        for (var piece :
                firstRow) {
            assertEquals(player, piece.getPlayer());
        }

        player = lastRow.get(0).getPlayer();
        for (var piece :
                lastRow) {
            assertEquals(player, piece.getPlayer());
        }
    }

    @Test
    @OptionalArcherImplementation
    @UnitTest
    public void testHalfArcher() {
        var game = Main.createGame(9, 0);
        var firstRow = new ArrayList<Piece>();
        var lastRow = new ArrayList<Piece>();
        for (int i = 0; i < game.getConfiguration().getSize(); i++) {
            firstRow.add(game.board[i][game.getConfiguration().getSize() - 1]);
            lastRow.add(game.board[i][0]);
        }

        var archers = firstRow.stream()
                .filter(piece -> piece instanceof Archer)
                .count();
        var knights = firstRow.stream()
                .filter(piece -> piece instanceof Knight)
                .count();
        assertEquals(1, Math.abs(archers - knights));
        archers = lastRow.stream()
                .filter(piece -> piece instanceof Archer)
                .count();
        knights = lastRow.stream()
                .filter(piece -> piece instanceof Knight)
                .count();
        assertEquals(1, Math.abs(archers - knights));
    }
}
