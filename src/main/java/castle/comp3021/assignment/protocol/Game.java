package castle.comp3021.assignment.protocol;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.stream.Collectors;

/**
 * The Game class
 */
public abstract class Game implements Cloneable {
    /**
     * Game configuration
     */
    protected Configuration configuration;

    /**
     * The gameboard, which is a 2-dimensional array of {@link Piece}, representing all the {@link Place} (squares) on
     * the board.
     * If there is a piece in some {@link Place} place(x,y), then {@code board[place.x()][place.y()]} will be the
     * piece object, otherwise null.
     * In other words, the board is a 2-D array with length equal to size of the board in both dimensions.
     */
    protected Piece[][] board;

    /**
     * Current player who is supposed to make a move.
     */
    protected Player currentPlayer;

    /**
     * The current number of moves made by two players
     */
    protected int numMoves = 0;

    public Game(Configuration configuration) {
        this.configuration = configuration;
        this.board = configuration.getInitialBoard();
    }

    /**
     * Start the game
     * Players will take turns according to the order in {@link Configuration#getPlayers()} to make a move until
     * a player wins.
     *
     * @return the winner
     */
    public abstract Player start();

    /**
     * Get the winner of the game. If there is no winner yet, return null;
     *
     * @param lastPlayer the last player who makes a move
     * @param lastPiece the last piece that is moved by the player
     * @param lastMove   the last move made by lastPlayer
     * @return the winner if it exists, otherwise return null
     */
    public abstract Player getWinner(Player lastPlayer, Piece lastPiece, Move lastMove);

    /**
     * Update the score of a player according to the piece and corresponding move made by him just now.
     *
     * @param player the player who just makes a move
     * @param piece  the piece that is just moved
     * @param move   the move that is just made
     */
    public abstract void updateScore(Player player, Piece piece, Move move);

    /**
     * Make a move.
     *
     * @param move the move to make
     */
    public abstract void movePiece(@NotNull Move move);

    /**
     * Get all available moves of one player.
     *
     * @param player the player whose available moves to get
     * @return an array of available moves
     */
    public abstract @NotNull Move[] getAvailableMoves(Player player);

    /**
     * Refresh the output printed in the console, which shows the following things.
     * 1. the gameboard and pieces on it
     * 2. scores of the players
     * 3. other help information
     */
    public void refreshOutput() {
        var size = this.configuration.getSize();
        var contents = new ArrayList<List<String>>();
        for (int row = size - 1; row >= 0; row--) {
            var rowContent = new ArrayList<String>();
            for (int col = 0; col < size; col++) {
                var piece = this.getPiece(col, row);
                if (piece == null) {
                    if (this.getCentralPlace().equals(new Place(col, row))) {
                        rowContent.add("x");
                    } else {
                        rowContent.add(".");
                    }
                } else {
                    var player = piece.getPlayer();
                    rowContent.add(String.format("%s%c%s",
                            player.getColor(),
                            piece.getLabel(),
                            Color.DEFAULT));
                }
            }
            contents.add(rowContent);
        }
        var xCoordinates = new ArrayList<String>();
        for (int i = 0; i < size; i++) {
            xCoordinates.add(String.valueOf((char) ('a' + i)));
        }
        Collections.reverse(contents);

        // clear screen
        System.out.print("\u001b[2J");
        System.out.flush();

        System.out.println();
        System.out.println("### COMP3021 Programming Assignment 1 ###");
        System.out.println();
        System.out.println("Guide: to move a piece, input the coordinate of source and the destination.");
        System.out.println("For example: a1->b2 means to move the piece at 'a1' to 'b2'");
        if (this.numMoves < this.configuration.getNumMovesProtection()) {
            System.out.println();
            System.out.println("Notice: first " + this.configuration.getNumMovesProtection() + " moves are not allowed to" +
                    " capture pieces or win the game.");
        }
        System.out.println();
        System.out.println("Total Moves: " + this.numMoves);
        // print scores of players
        for (var player :
                this.configuration.getPlayers()) {
            System.out.printf("%s%s%s score: %d\n", player.getColor(), player.getName(), Color.DEFAULT,
                    player.getScore());
        }
        System.out.println();
        // print the gameboard
        var leftPadding = 8;
        var paddingSpaceBuilder = new StringBuilder();
        paddingSpaceBuilder.append(" ".repeat(leftPadding));
        System.out.printf("%s%s\n",
                paddingSpaceBuilder.toString(),
                xCoordinates.parallelStream()
                        .collect(Collectors.joining(" ")));
        var borderBuilder = new StringBuilder();
        borderBuilder.append("-".repeat(Math.max(0, contents.get(0).size() * 2 - 1)));
        System.out.printf("%s%s\n",
                paddingSpaceBuilder.toString(),
                borderBuilder.toString());
        for (int row = contents.size() - 1; row >= 0; row--) {
            System.out.printf("%" + (leftPadding - 1) + "d|%s|%d\n",
                    row + 1,
                    contents.get(row).parallelStream().map(Object::toString).collect(Collectors.joining(" ")),
                    row + 1);
        }
        System.out.printf("%s%s\n",
                paddingSpaceBuilder.toString(),
                borderBuilder.toString());
        System.out.printf("%s%s\n",
                paddingSpaceBuilder.toString(),
                xCoordinates.parallelStream()
                        .collect(Collectors.joining(" ")));
        System.out.println();
    }

    /**
     * Get the piece in the place
     *
     * @param place the place of the piece
     * @return the piece
     */
    public @Nullable Piece getPiece(@NotNull Place place) {
        return this.board[place.x()][place.y()];
    }

    public @Nullable Piece getPiece(int x, int y) {
        return this.getPiece(new Place(x, y));
    }

    public Player getCurrentPlayer() {
        return currentPlayer != null ? currentPlayer : this.configuration.getPlayers()[0];
    }

    public Player[] getPlayers() {
        return configuration.getPlayers();
    }

    public int getNumMoves() {
        return numMoves;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public Place getCentralPlace() {
        return configuration.getCentralPlace();
    }

    @Override
    public Game clone() throws CloneNotSupportedException {
        var cloned = (Game) super.clone();
        cloned.configuration = this.configuration.clone();
        cloned.board = this.board.clone();
        for (int i = 0; i < this.configuration.getSize(); i++) {
            cloned.board[i] = this.board[i].clone();
            // no need to deep copy pieces
            if (this.configuration.getSize() >= 0)
                System.arraycopy(this.board[i], 0, cloned.board[i], 0, this.configuration.getSize());
        }
        cloned.currentPlayer = currentPlayer == null ? null : currentPlayer.clone();
        return cloned;
    }
}