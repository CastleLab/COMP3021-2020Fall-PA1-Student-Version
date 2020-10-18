package castle.comp3021.assignment;

import castle.comp3021.assignment.piece.Knight;
import castle.comp3021.assignment.protocol.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;

public class JesonMor extends Game {
    public JesonMor(Configuration configuration) {
        super(configuration);
    }

    /**
     * Start the game
     * Players will take turns according to the order in {@link Configuration#getPlayers()} to make a move until
     * a player wins.
     *
     * @return the winner
     */
    @Override
    public Player start() {
        // reset all things
        Player winner;
        this.numMoves = 0;
        this.board = configuration.getInitialBoard();
        this.currentPlayer = null;
        this.refreshOutput();
        while (true) {
            var player = this.configuration.getPlayers()[this.numMoves % this.configuration.getPlayers().length];
            this.currentPlayer = player;
            // let player make next move
            var availableMoves = this.getAvailableMoves(player);
            // there shouldn't be no available moves, if no available moves, the player with lower score wins
            if (availableMoves.length <= 0) {
                System.out.println("No available moves for the player " + player.getName());
                if (this.configuration.getPlayers()[0].getScore() < this.configuration.getPlayers()[1].getScore()) {
                    winner = this.configuration.getPlayers()[0];
                } else if (this.configuration.getPlayers()[0].getScore() > this.configuration.getPlayers()[1].getScore()) {
                    winner = this.configuration.getPlayers()[1];
                } else {
                    winner = player;
                }
            } else {
                var move = player.nextMove(this, availableMoves);
                var movedPiece = this.getPiece(move.getSource());
                // make move
                this.movePiece(move);
                this.numMoves++;
                System.out.println(player.getName() + " moved piece at " + move.getSource() + "to " + move.getDestination());
                this.updateScore(player, movedPiece, move);

                this.refreshOutput();

                // check if there is a winner and if there is, return the winner.
                // if there is no winner yet, continue the loop with label "round"
                winner = this.getWinner(player, movedPiece, move);
            }
            if (winner != null) {
                System.out.println();
                System.out.println("Congratulations! ");
                System.out.printf("Winner: %s%s%s\n", winner.getColor(), winner.getName(), Color.DEFAULT);
                return winner;
            }
        }
    }

    /**
     * Get the winner of the game. If there is no winner yet, return null;
     *
     * @param lastPlayer the last player who makes a move
     * @param lastMove   the last move made by lastPlayer
     * @param lastPiece  the last piece that is moved by the player
     * @return the winner if it exists, otherwise return null
     */
    @Override
    public Player getWinner(Player lastPlayer, Piece lastPiece, Move lastMove) {
        // TODO student implementation
        Player winner = null;

        // no winner within numMovesProtection moves
        if (this.numMoves <= this.configuration.getNumMovesProtection()) {
            return null;
        }

        // first way to win: a piece leaves the central square, the piece should not be an Archer
        if ((lastPiece instanceof Knight) && lastMove.getSource().equals(this.configuration.getCentralPlace())
                && !lastMove.getDestination().equals(this.configuration.getCentralPlace())) {
            winner = lastPlayer;
        } else {
            // second way to win: one player captures all the pieces of other players
            Player remainingPlayer = null;
            for (int i = 0; i < this.configuration.getSize(); i++) {
                for (int j = 0; j < this.configuration.getSize(); j++) {
                    var piece = this.getPiece(i, j);
                    if (piece == null) {
                        continue;
                    }
                    if (remainingPlayer == null) {
                        remainingPlayer = piece.getPlayer();
                    } else if (remainingPlayer != piece.getPlayer()) {
                        // there are still two players having pieces on board
                        return null;
                    }
                }
            }
            // if the previous for loop terminates, then there must be 1 player on board (it cannot be null).
            // then winner appears
            winner = remainingPlayer;
        }

        return winner;
    }

    /**
     * Update the score of a player according to the piece and corresponding move made by him just now.
     *
     * @param player the player who just makes a move
     * @param piece  the piece that is just moved
     * @param move   the move that is just made
     */
    public void updateScore(Player player, Piece piece, Move move) {
        // TODO student implementation
        var newScore = player.getScore();
        newScore += Math.abs(move.getSource().x() - move.getDestination().x());
        newScore += Math.abs(move.getSource().y() - move.getDestination().y());
        player.setScore(newScore);
    }


    /**
     * Make a move.
     *
     * @param move the move to make
     */
    public void movePiece(@NotNull Move move) {
        // TODO student implementation

        var sourcePiece = this.getPiece(move.getSource());
        assert sourcePiece != null;
        var destPiece = this.getPiece(move.getDestination());

        assert destPiece == null || !destPiece.getPlayer().equals(sourcePiece.getPlayer())
                : "cannot capture a piece belonging to the same player";

        // move the piece
        this.board[move.getDestination().x()][move.getDestination().y()] = sourcePiece;
        this.board[move.getSource().x()][move.getSource().y()] = null;
    }

    /**
     * Get all available moves of one player.
     *
     * @param player the player whose available moves to get
     * @return an array of available moves
     */
    public @NotNull Move[] getAvailableMoves(Player player) {
        // TODO student implementation
        var moves = new ArrayList<Move>();
        // find all pieces belonging to the player
        for (int i = 0; i < this.configuration.getSize(); i++) {
            for (int j = 0; j < this.configuration.getSize(); j++) {
                var piece = this.getPiece(i, j);
                if (piece == null) {
                    continue;
                }
                if (!piece.getPlayer().equals(player)) {
                    continue;
                }
                var candidateMoves = piece.getAvailableMoves(this, new Place(i, j));
                moves.addAll(Arrays.asList(candidateMoves));
            }
        }
        return moves.toArray(new Move[0]);
    }
}
