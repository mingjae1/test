package model;

import java.awt.Color;

public class DrawStyle {
    private final Color strokeColor;
    private final Color fillColor;
    private final float strokeWidth;
    private final boolean fillEnabled;

    public DrawStyle(Color strokeColor, Color fillColor, float strokeWidth, boolean fillEnabled) {
        this.strokeColor = strokeColor;
        this.fillColor = fillColor;
        this.strokeWidth = strokeWidth;
        this.fillEnabled = fillEnabled;
    }

    public Color getStrokeColor() {
        return strokeColor;
    }

    public Color getFillColor() {
        return fillColor;
    }

    public float getStrokeWidth() {
        return strokeWidth;
    }

    public boolean isFillEnabled() {
        return fillEnabled;
    }
}
