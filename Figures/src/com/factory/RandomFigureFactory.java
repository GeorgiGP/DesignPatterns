package com.factory;

import com.exceptions.FailedFigureCreationException;
import com.figures.Polygon;
import org.reflections.Reflections;
import com.reflection.ConstructorFinder;
import com.reflection.FigurePackageName;

import com.figures.Figure;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class RandomFigureFactory extends FigureFactory {

    static class Holder {
        //at max should be below Double.Max / 7 because of the case with circle,
        //this variable ensures new figures with infinite many perimeters
        private static final double MAX_SUM_PERIMETERS_RANDOMIZED_FIGURE_DOUBLE = 1e6;

        private static final int SCALE = 5;
        private static final int MAX_COUNT_VARARGS_RANDOM_PERIMETERS = 10;
        private static final int MIN_COUNT_VARARGS_RANDOM_PERIMETERS = 3;
        private static final BigDecimal MAX_SUM_PERIMETERS_RANDOMIZED_RANGE =
                BigDecimal.valueOf(MAX_SUM_PERIMETERS_RANDOMIZED_FIGURE_DOUBLE);

        private static final Random RANDOM = new Random();

        public static final List<Class<? extends Figure>> FIGURE_CLASSES =
                Collections.unmodifiableList(initFigureClasses());

        private static List<Class<? extends Figure>> initFigureClasses() {
            Reflections reflections = new Reflections(FigurePackageName.getPackageName());

            Set<Class<? extends Figure>> subclasses = reflections.getSubTypesOf(Figure.class);
            subclasses.remove(Figure.class);
            return new ArrayList<>(subclasses);
        }
    }

    @Override
    public int countFigures() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    protected BigDecimal randBigDecimal() {
        return BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble())
                .setScale(Holder.SCALE * 2, RoundingMode.HALF_UP);
    }

    protected List<BigDecimal> randBigDecimalListPolygon(int count) {
        List<BigDecimal> parameters = new ArrayList<>();
        BigDecimal maxPossibleSide = Holder.MAX_SUM_PERIMETERS_RANDOMIZED_RANGE
                .divide(BigDecimal.valueOf(count), Holder.SCALE, RoundingMode.HALF_UP);
        BigDecimal sum = BigDecimal.ZERO;
        for (int i = 0; i < count - 1; i++) {
            BigDecimal current = randBigDecimal().multiply(maxPossibleSide)
                    .setScale(Holder.SCALE, RoundingMode.HALF_UP);
            parameters.add(current);
            sum = sum.add(current);
        }
        BigDecimal maxInterval = Collections.max(parameters);
        BigDecimal minInterval = maxInterval.multiply(BigDecimal.TWO).subtract(sum);
        if (minInterval.compareTo(BigDecimal.ZERO) < 0) {
            minInterval = BigDecimal.ZERO;
        }
        BigDecimal lastElement = minInterval.add(randBigDecimal().multiply(maxInterval.subtract(minInterval)))
                .setScale(Holder.SCALE, RoundingMode.HALF_UP);
        parameters.add(lastElement);
        int randomIdx = Holder.RANDOM.nextInt(parameters.size());
        Collections.swap(parameters, parameters.size() - 1, randomIdx);
        return parameters;
    }

    protected List<BigDecimal> randBigDecimalList(int count) {
        List<BigDecimal> parameters = new ArrayList<>();
        BigDecimal maxPossibleSide = Holder.MAX_SUM_PERIMETERS_RANDOMIZED_RANGE
                .divide(BigDecimal.valueOf(count), Holder.SCALE, RoundingMode.HALF_UP);
        for (int i = 0; i < count; i++) { //add random from 0 to maxPossible
            parameters.add(randBigDecimal().multiply(maxPossibleSide)
                    .setScale(Holder.SCALE, RoundingMode.HALF_UP));
        }
        return parameters;
    }

    private Figure createNonVarArgs(Class<?> randomClass, Constructor<?> constructor) {
        int countPerimeters = constructor.getParameterTypes().length;
        try {
            if (Polygon.class.isAssignableFrom(randomClass)) {
                return (Figure) constructor.newInstance(randBigDecimalListPolygon(countPerimeters).toArray());
            }
            return (Figure) constructor.newInstance(randBigDecimalList(countPerimeters).toArray());
        } catch (Exception e) {
            throw new FailedFigureCreationException("Failed to create random figure");
        }
    }

    private Figure createWithVarArgs(Class<?> randomClass, Constructor<?> constructor) {
        try {
            int countPerimeters = Holder.RANDOM
                    .nextInt(Holder.MAX_COUNT_VARARGS_RANDOM_PERIMETERS
                            - Holder.MIN_COUNT_VARARGS_RANDOM_PERIMETERS + 1) +
                    Holder.MIN_COUNT_VARARGS_RANDOM_PERIMETERS;
            if (Polygon.class.isAssignableFrom(randomClass)) {
                return (Figure) constructor.newInstance(
                        (Object) randBigDecimalListPolygon(countPerimeters).toArray(BigDecimal[]::new));
            }
            return (Figure) constructor.newInstance(
                    (Object) randBigDecimalList(countPerimeters).toArray(BigDecimal[]::new));
        } catch (Exception e) {
            throw new FailedFigureCreationException("Failed to create random figure");
        }
    }

    @Override
    public Figure create() {
        List<Class<? extends Figure>> figureClasses = Holder.FIGURE_CLASSES;

        Class<?> randomClass = figureClasses.get(Holder.RANDOM.nextInt(figureClasses.size()));
        Constructor<?> constructor = ConstructorFinder.findRightConstructor(randomClass);
        if (constructor.isVarArgs()) {
            return createWithVarArgs(randomClass, constructor);
        } else {
            return createNonVarArgs(randomClass, constructor);
        }
    }
}
