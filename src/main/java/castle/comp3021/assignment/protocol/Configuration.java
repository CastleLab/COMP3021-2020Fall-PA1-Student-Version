package castle.comp3021.assignment.protocol;

import castle.comp3021.assignment.protocol.exception.InvalidConfigurationError;

/**
 * Game configuration, including:
 * 1. size of gameboard
 * 2. place (square) of central square in classical Jeson Mor
 * 3. the initial game board with pieces on it, which is configurable through {@link Configuration#addInitialPiece(Piece, Place)}
 * 4. the two players.
 */
public class Configuration implements Cloneable {
    /**
     * Size of gameboard.
     * The gameboard has equal size in width and height.
     * The size should be an odd number.
     */
    private final int size;

    /**
     * All players in the game.
     */
    private Player[] players;

    /**
     * The initial map of the gameboard, containing initial pieces and their places.
     * This map has keys for all places in the gameboard, with or without pieces.
     * If there is no piece in one place, the place is mapped to null.
     */
    private Piece[][] initialBoard;

    /**
     * The central square of the gameboard.
     */
    private Place centralPlace;

    private final int numMovesProtection;

    /**
     * Constructor of configuration
     *
     * @param size size of the gameboard.
     */
    public Configuration(int size, Player[] players, int numMovesProtection) {
        // validate size
        if (size < 3) {
            throw new InvalidConfigurationError("size of gameboard must be at least 3");
        }
        if (size % 2 != 1) {
            throw new InvalidConfigurationError("size of gameboard must be an odd number");
        }
        if (size > 26) {
            throw new InvalidConfigurationError("size of gameboard is at most 26");
        }
        this.size = size;
        // We only have 2 players
        this.players = players;
        if (players.length != 2) {
            throw new InvalidConfigurationError("there must be exactly two players");
        }
        // initialize map of the game board by putting every place null (meaning no piece)
        this.initialBoard = new Piece[size][];
        for (int x = 0; x < size; x++) {
            this.initialBoard[x] = new Piece[size];
            for (int y = 0; y < size; y++) {
                this.initialBoard[x][y] = null;
            }
        }
        // calculate the central place
        this.centralPlace = new Place(size / 2, size / 2);

        if (numMovesProtection < 0) {
            throw new InvalidConfigurationError("number of moves with capture protection cannot be negative");
        }
        this.numMovesProtection = numMovesProtection;
    }

    public Configuration(int size, Player[] players) {
        this(size, players, 0);
    }

    /**
     * Add piece to the initial gameboard.
     * The player that this piece belongs to will be automatically added into the configuration.
     *
     * @param piece piece to be added
     * @param place place to put the piece
     */
    public void addInitialPiece(Piece piece, Place place) {
        if (!piece.getPlayer().equals(this.players[0]) && !piece.getPlayer().equals(this.players[1])) {
            throw new InvalidConfigurationError("the player of the piece is unknown");
        }
        if (place.x() >= this.size || place.y() >= this.size) {
            // The place must be inside the gameboard
            throw new InvalidConfigurationError("the place" + place.toString() + " must be inside the gameboard");
        }
        if (place.equals(this.centralPlace)) {
            throw new InvalidConfigurationError("piece cannot be put at central place initially");
        }

        // put the piece on the initial board
        this.initialBoard[place.x()][place.y()] = piece;
    }

    public void addInitialPiece(Piece piece, int x, int y) {
        this.addInitialPiece(piece, new Place(x, y));
    }

    public int getSize() {
        return size;
    }

    public Player[] getPlayers() {
        return players;
    }

    public Piece[][] getInitialBoard() {
        return initialBoard;
    }

    public Place getCentralPlace() {
        return centralPlace;
    }

    public int getNumMovesProtection() {
        return numMovesProtection;
    }

    @Override
    public Configuration clone() throws CloneNotSupportedException {
        var cloned = (Configuration) super.clone();
        cloned.players = this.players.clone();
        for (int i = 0; i < this.players.length; i++) {
            cloned.players[i] = this.players[i].clone();
        }
        cloned.initialBoard = this.initialBoard.clone();
        for (int i = 0; i < this.size; i++) {
            cloned.initialBoard[i] = this.initialBoard[i].clone();
            // no need to deep copy piece
            System.arraycopy(this.initialBoard[i], 0, cloned.initialBoard[i], 0, this.size);
        }
        cloned.centralPlace = this.centralPlace.clone();
        return cloned;
    }
}
