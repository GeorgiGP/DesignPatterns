package com.factory;

import com.exceptions.FailedFigureCreationException;
import com.figures.Figure;
import com.reflection.ConstructorFinder;
import com.reflection.FigurePackageName;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StringToFigure {
    public Figure createFrom(String representation) {
        if (representation.isBlank()) {
            throw new IllegalArgumentException("Blank string to convert to figure");
        }
        try {
            Scanner scanner = new Scanner(representation);
            //extracting class name
            String className = getNameOfFileLocation(scanner.next());
            Class<?> clazz = Class.forName(className);
            validateSubclassOfFigure(clazz);
            List<BigDecimal> hold = new ArrayList<>();
            while (scanner.hasNext()) {
                BigDecimal curr = scanner.nextBigDecimal();
                hold.add(curr);
            }
            //extracted
            Constructor<?> constructor = ConstructorFinder.findRightConstructor(clazz, hold.size());
            if (constructor.isVarArgs()) {
                return (Figure) constructor.newInstance((Object) hold.toArray(BigDecimal[]::new));
            }
            return (Figure) constructor.newInstance(hold.toArray());
        } catch (Exception e) {
            throw new FailedFigureCreationException("Couldn't create figure from " + representation, e);
        }

    }

    private String toUpperCaseFirstChar(String str) {
        StringBuilder builder = new StringBuilder(str);
        char toUpperCase = builder.charAt(0);
        toUpperCase = Character.toUpperCase(toUpperCase);
        builder.setCharAt(0, toUpperCase);
        return builder.toString();
    }

    private String getNameOfFileLocation(String ofClassName) {
        return FigurePackageName.getPackageName() + "." + toUpperCaseFirstChar(ofClassName);
    }

    private void validateSubclassOfFigure(Class<?> clazz) {
        if (!Figure.class.isAssignableFrom(clazz)) {
            throw new IllegalArgumentException("Class is not a figure but in figures directory");
        }
    }
}
