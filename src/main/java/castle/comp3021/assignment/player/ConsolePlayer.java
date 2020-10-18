package castle.comp3021.assignment.player;

import castle.comp3021.assignment.protocol.Game;
import castle.comp3021.assignment.piece.*;
import castle.comp3021.assignment.protocol.Color;
import castle.comp3021.assignment.protocol.Move;
import castle.comp3021.assignment.protocol.Place;
import castle.comp3021.assignment.protocol.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Scanner;

/**
 * The player that makes move according to user input from console.
 */
public class ConsolePlayer extends Player {
    public ConsolePlayer(String name, Color color) {
        super(name, color);
    }

    public ConsolePlayer(String name) {
        this(name, Color.GREEN);
    }

    @Override
    public @NotNull Move nextMove(Game game, Move[] availableMoves) {
        var in = new Scanner(System.in);
        Move move;
        while (true) {
            System.out.printf("[%s] Make a Move: ", game.getCurrentPlayer().getName());
            var moveStr = in.nextLine().toLowerCase();
            move = parseMove(moveStr);
            if (move != null) {
                var error = validateMove(game, move);
                if (error != null) {
                    System.out.println("[Invalid Move]: " + error);
                    continue;
                }
                break;
            }
            System.out.println("[Invalid Move]: Incorrect format");
        }
        return move;
    }

    private String validateMove(Game game, Move move) {
        var rules = new Rule[]{
                new OutOfBoundaryRule(),
                new OccupiedRule(),
                new VacantRule(),
                new NilMoveRule(),
                new FirstNMovesProtectionRule(game.getConfiguration().getNumMovesProtection()),
                new ArcherMoveRule(),
                new KnightMoveRule(),
                new KnightBlockRule(),
        };
        for (var rule :
                rules) {
            if (!rule.validate(game, move)) {
                return rule.getDescription();
            }

            var piece = game.getPiece(move.getSource());
            if (piece == null) {
                return "No piece at " + move.getSource().toString();
            }
            if (!this.equals(piece.getPlayer())) {
                return "Cannot move a piece not belonging to you";
            }
        }
        return null;
    }

    private static Place parsePlace(String str) {
        if (str.length() < 2) {
            return null;
        }
        try {
            var x = str.charAt(0) - 'a';
            var y = Integer.parseInt(str.substring(1)) - 1;
            return new Place(x, y);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private static Move parseMove(String str) {
        var segments = str.split("->");
        if (segments.length < 2) {
            return null;
        }
        var source = parsePlace(segments[0].strip());
        if (source == null) {
            return null;
        }
        var destination = parsePlace(segments[1].strip());
        if (destination == null) {
            return null;
        }
        return new Move(source, destination);
    }
}
