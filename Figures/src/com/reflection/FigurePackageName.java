package com.reflection;

import com.figures.Figure;

public class FigurePackageName {
    private FigurePackageName() { }

    public static String getPackageName() {
        return Holder.PACKAGE_NAME;
    }

    //for lazy initialisation
    private static class Holder {
        private static final String PACKAGE_NAME = initPackageName();

        private static String initPackageName() {
            String canonicalName = Figure.class.getCanonicalName();
            return canonicalName.substring(0, canonicalName.lastIndexOf('.'));
        }
    }
}
