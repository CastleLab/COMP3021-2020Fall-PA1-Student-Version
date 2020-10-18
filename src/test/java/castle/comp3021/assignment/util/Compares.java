package castle.comp3021.assignment.util;

import castle.comp3021.assignment.protocol.Piece;
import java.util.Arrays;
import java.util.Objects;

public class Compares {
    public static boolean isBoardEqual(Piece[][] expected, Piece[][] actual) {
        if (expected.length != actual.length) {
            return false;
        }
        for (int i = 0; i < expected.length; i++) {
            if (expected[i].length != actual[i].length) {
                return false;
            }
            for (int j = 0; j < expected[i].length; j++) {
                if (!Objects.equals(expected[i][j], actual[i][j])) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean areContentsEqual(Object[] expected, Object[] actual) {
        if (expected.length != actual.length) {
            return false;
        }
        for (var item :
                actual) {
            if (!Arrays.asList(expected).contains(item)) {
                return false;
            }
        }
        return true;
    }

    public static boolean contentsContain(Object[] array, Object[] contained) {
        return Arrays.asList(array).containsAll(Arrays.asList(contained));
    }
}
