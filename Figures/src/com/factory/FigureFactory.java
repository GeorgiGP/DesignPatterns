package com.factory;

import com.figures.Figure;

public abstract class FigureFactory {
    public abstract Figure create();

    //package private so only factories to have access to this because it should be called before extracting figures
    abstract int countFigures();
}
