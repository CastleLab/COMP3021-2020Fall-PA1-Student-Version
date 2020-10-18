package castle.comp3021.assignment.piece;


import castle.comp3021.assignment.protocol.Game;
import castle.comp3021.assignment.protocol.Move;

/**
 * Interface that a rule should implement.
 * Every mode in the gameboard should not violate the rule.
 */
public interface Rule {
    /**
     * Validate the move. if the rule is violated return false.
     * <p>
     * [Hint] the piece that is to be moved can be obtained using {@code game.getPiece(move.getSource()}.
     *
     * @param game the current game object
     * @param move the move to be validated
     * @return true if the rule check pass. If the rule is violated, return false.
     */
    public boolean validate(Game game, Move move);

    /**
     * Returns a string description of the rule, which will be printed as error message when the rule is violated.
     *
     * @return description of the rule
     */
    public String getDescription();
}

// Below are several predefined global rules for all moves.

