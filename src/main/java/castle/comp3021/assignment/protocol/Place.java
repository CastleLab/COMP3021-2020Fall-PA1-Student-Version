package castle.comp3021.assignment.protocol;

/**
 * A square (position, place) in the gameboard.
 * Represented by coordinates a 2-D coordinate system.
 * <p>
 * x and y coordinates of a place on gameboard are two fields of this class.
 */
public record Place(int x, int y) implements Cloneable {
    @Override
    public Place clone() throws CloneNotSupportedException {
        return (Place) super.clone();
    }
}