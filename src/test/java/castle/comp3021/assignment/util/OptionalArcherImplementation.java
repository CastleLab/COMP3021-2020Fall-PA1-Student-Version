package castle.comp3021.assignment.util;

import castle.comp3021.assignment.mock.MockPlayer;
import castle.comp3021.assignment.piece.Archer;
import org.junit.jupiter.api.extension.ConditionEvaluationResult;
import org.junit.jupiter.api.extension.ExecutionCondition;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@ExtendWith(ArcherImplementedCondition.class)
public @interface OptionalArcherImplementation {
}

class ArcherImplementedCondition implements ExecutionCondition {
    @Override
    public ConditionEvaluationResult evaluateExecutionCondition(ExtensionContext extensionContext) {
        try {
            new Archer(new MockPlayer());
            return ConditionEvaluationResult.enabled("Archer implemented");
        } catch (UnsupportedOperationException e) {
            return ConditionEvaluationResult.disabled("Archer not implemented");
        }
    }
}
