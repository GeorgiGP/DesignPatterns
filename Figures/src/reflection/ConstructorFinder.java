package reflection;

import exceptions.FailedFigureCreationException;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.util.Arrays;

public class ConstructorFinder {
    public static Constructor<?> findRightConstructor(Class<?> clazz, int countParameters) {
        Constructor<?>[] constructors = clazz.getConstructors();
        Constructor<?> containsVarArgs = null;
        for (Constructor<?> constructor : constructors) {
            if (containsVarArgs == null && constructor.isVarArgs()) {
                containsVarArgs = constructor;
            }
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            if (parameterTypes.length == countParameters &&
                    Arrays.stream(parameterTypes).allMatch(type -> type == BigDecimal.class)) {
                return constructor;
            }
        }
        if (containsVarArgs != null) {
            return containsVarArgs;
        }
        throw new FailedFigureCreationException("No " + clazz.getCanonicalName() + " constructor found");
    }

    public static Constructor<?> findRightConstructor(Class<?> clazz) {
        Constructor<?>[] constructors = clazz.getConstructors();
        Constructor<?> containsVarArgs = null;
        for (Constructor<?> constructor : constructors) {
            if (containsVarArgs == null && constructor.isVarArgs()) {
                containsVarArgs = constructor;
            }
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            if (parameterTypes.length >= 0 &&
                    Arrays.stream(parameterTypes).allMatch(type -> type == BigDecimal.class)) {
                return constructor;
            }
        }
        if (containsVarArgs != null) {
            return containsVarArgs;
        }
        throw new IllegalArgumentException("No " + clazz.getCanonicalName() + " constructor found");
    }
}
