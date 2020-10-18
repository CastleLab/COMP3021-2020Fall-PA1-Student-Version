package castle.comp3021.assignment.protocol;


/**
 * The abstract class that a game piece should extend.
 */
public abstract class Piece {
    /**
     * The player that owns this piece.
     */
    private final Player player;

    public Piece(Player player) {
        this.player = player;
    }

    public final Player getPlayer() {
        return this.player;
    }

    /**
     * Returns a char which is used to facilitate output in the console
     *
     * @return the label to represent the piece on the board
     */
    public abstract char getLabel();

    /**
     * Returns a set of moves that are valid to make given the current place of the piece.
     *
     * @param game   the game object
     * @param source the current place of the piece
     * @return a set of available moves
     */
    public abstract Move[] getAvailableMoves(Game game, Place source);
}
