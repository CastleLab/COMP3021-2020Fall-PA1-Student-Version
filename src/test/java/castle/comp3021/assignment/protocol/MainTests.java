package castle.comp3021.assignment.protocol;

import castle.comp3021.assignment.Main;
import castle.comp3021.assignment.util.SampleTest;
import org.junit.jupiter.api.Test;

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
}
