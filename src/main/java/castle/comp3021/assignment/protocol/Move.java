package castle.comp3021.assignment.protocol;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * Move class represent a move made by a {@link Player}, moving a piece from one {@link Place} to another {@link Place}.
 */
public class Move implements Cloneable {
    /**
     * Source place of the move.
     */
    private Place source;

    /**
     * Move destination
     */
    private Place destination;

    public Move(Place source, Place destination) {
        this.source = source;
        this.destination = destination;
    }

    public Move(int sourceX, int sourceY, int destinationX, int destinationY) {
        this(new Place(sourceX, sourceY), new Place(destinationX, destinationY));
    }

    public Move(Place source, int destinationX, int destinationY) {
        this(source, new Place(destinationX, destinationY));
    }

    /* Getters start */
    public Place getSource() {
        return source;
    }

    public Place getDestination() {
        return destination;
    }
    /* Getters end */

    /* Object methods start */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Move move = (Move) o;
        return source.equals(move.source) &&
                destination.equals(move.destination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, destination);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Move.class.getSimpleName() + "[", "]")
                .add("source=" + source)
                .add("destination=" + destination)
                .toString();
    }

    @Override
    public Move clone() throws CloneNotSupportedException {
        var cloned = (Move) super.clone();
        cloned.source = this.source.clone();
        cloned.destination = this.destination.clone();
        return cloned;
    }

    /* Object methods end */

}
