package castle.comp3021.assignment.protocol;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.StringJoiner;

public class MoveRecord implements Cloneable {
    private Player player;
    private Move move;

    public MoveRecord(@NotNull Player player, @NotNull Move move) {
        this.player = player;
        this.move = move;
    }

    public Player getPlayer() {
        return player;
    }

    public Move getMove() {
        return move;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MoveRecord that = (MoveRecord) o;
        return player.equals(that.player) &&
                move.equals(that.move);
    }

    @Override
    public int hashCode() {
        return Objects.hash(player, move);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", MoveRecord.class.getSimpleName() + "[", "]")
                .add("player=" + player)
                .add("move=" + move)
                .toString();
    }

    @Override
    public MoveRecord clone() throws CloneNotSupportedException {
        var cloned = (MoveRecord) super.clone();
        cloned.player = this.player.clone();
        cloned.move = this.move.clone();
        return cloned;
    }
}
