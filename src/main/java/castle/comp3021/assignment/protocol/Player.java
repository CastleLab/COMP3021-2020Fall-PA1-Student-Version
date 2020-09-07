package castle.comp3021.assignment.protocol;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Player interface for the game.
 * Implementations could be computer player or human player.
 */
public abstract class Player implements Cloneable {
    /**
     * The name of a player.
     * By default, name is a unique identifier to distinguish different players. But this can be overrided by
     * overriding {@link Player#equals(Object)} method.
     */
    protected final String name;

    /**
     * The game score of this player.
     * Score will be updated whenever the player makes a move through {@link Game#updateScore(Player, Piece, Move)}
     * and {@link Player#setScore(int)}
     */
    protected int score;

    /**
     * The color representing this player
     */
    protected final Color color;

    public Player(String name, Color color) {
        this.name = name;
        this.color = color;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return name.equals(player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public final String getName() {
        return this.name;
    }

    public final int getScore() {
        return score;
    }

    public final void setScore(int score) {
        this.score = score;
    }

    public String toString() {
        return this.name;
    }

    /**
     * Get color used to represent this player.
     *
     * @return color of this player
     */
    public final Color getColor() {
        return this.color;
    }

    @Override
    public Player clone() throws CloneNotSupportedException {
        return (Player) super.clone();
    }

    /**
     * Choose a move from available moves.
     * This method will be called by {@link Game} object to get the move that the player wants to make when it is the
     * player's turn.
     *
     * @param game           the current game object
     * @param availableMoves available moves for this player to choose from.
     * @return the chosen move
     */
    public abstract @NotNull Move nextMove(Game game, Move[] availableMoves);

}
