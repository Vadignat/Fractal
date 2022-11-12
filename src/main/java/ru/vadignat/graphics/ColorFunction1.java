package ru.vadignat.graphics;

import java.awt.*;

public class ColorFunction1 implements Colorizer{
    @Override
    public Color getColor(float value) {
        if(value == 1) return Color.BLACK;
        var r = (float) (Math.abs(Math.sin(30 * value)));
        var g = value * (1 - value);
        var b = (float)Math.pow(value, 250);
        return new Color(r, g, b);
    }
}
