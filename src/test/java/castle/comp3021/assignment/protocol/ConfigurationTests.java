package castle.comp3021.assignment.protocol;

import castle.comp3021.assignment.mock.MockPiece;
import castle.comp3021.assignment.mock.MockPlayer;
import castle.comp3021.assignment.protocol.exception.InvalidConfigurationError;
import castle.comp3021.assignment.util.SampleTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@SampleTest
public class ConfigurationTests {
    @Test
    public void testSizeOdd() {
        assertThrows(InvalidConfigurationError.class, () -> new Configuration(-1, new Player[]{new MockPlayer(), new MockPlayer()}));
        assertThrows(InvalidConfigurationError.class, () -> new Configuration(0, new Player[]{new MockPlayer(), new MockPlayer()}));
        assertThrows(InvalidConfigurationError.class, () -> new Configuration(1, new Player[]{new MockPlayer(), new MockPlayer()}));
        assertThrows(InvalidConfigurationError.class, () -> new Configuration(2, new Player[]{new MockPlayer(), new MockPlayer()}));
        assertDoesNotThrow(() -> new Configuration(3, new Player[]{new MockPlayer(), new MockPlayer()}));
    }

    @Test
    public void testPlayersNumber() {
        assertThrows(InvalidConfigurationError.class, () -> new Configuration(3, new Player[]{new MockPlayer()}));
        assertThrows(InvalidConfigurationError.class, () -> new Configuration(3, new Player[]{new MockPlayer(), new MockPlayer(),
                new MockPlayer()}));
        assertDoesNotThrow(() -> new Configuration(3, new Player[]{new MockPlayer(), new MockPlayer(),}));
    }

    @Test
    public void testCentralPlace() {
        assertEquals(new Configuration(3, new Player[]{new MockPlayer(), new MockPlayer()}).getCentralPlace(), new Place(1, 1));
        assertEquals(new Configuration(5, new Player[]{new MockPlayer(), new MockPlayer()}).getCentralPlace(), new Place(2, 2));
        assertEquals(new Configuration(9, new Player[]{new MockPlayer(), new MockPlayer()}).getCentralPlace(), new Place(4, 4));
    }

    @Test
    public void testAddInitialPiece() {
        var player1 = new MockPlayer();
        var player2 = new MockPlayer();
        var piece = new MockPiece(player1);
        assertThrows(InvalidConfigurationError.class, () -> new Configuration(3, new Player[]{new MockPlayer(), new MockPlayer()})
                .addInitialPiece(piece, 0, 0));
        assertThrows(InvalidConfigurationError.class, () -> new Configuration(3, new Player[]{player1, player2})
                .addInitialPiece(piece, 100, 0));
        var config = new Configuration(3, new Player[]{player1, player2});
        config.addInitialPiece(piece, 0, 0);
        assertEquals(2, config.getPlayers().length);
        assertEquals(piece, config.getInitialBoard()[0][0]);
    }
}
