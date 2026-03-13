package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CanvasModel {
    private final List<DrawableShape> shapes = new ArrayList<>();
    private ToolType currentTool = ToolType.RECTANGLE;
    private Color strokeColor = Color.BLACK;
    private Color fillColor = new Color(255, 236, 179);
    private float strokeWidth = 2.0f;
    private boolean fillEnabled = false;

    public List<DrawableShape> getShapes() {
        return Collections.unmodifiableList(shapes);
    }

    public void addShape(DrawableShape shape) {
        shapes.add(shape);
    }

    public void clearShapes() {
        shapes.clear();
    }

    public ToolType getCurrentTool() {
        return currentTool;
    }

    public void setCurrentTool(ToolType currentTool) {
        this.currentTool = currentTool;
    }

    public Color getStrokeColor() {
        return strokeColor;
    }

    public void setStrokeColor(Color strokeColor) {
        this.strokeColor = strokeColor;
    }

    public Color getFillColor() {
        return fillColor;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    public float getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(float strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public boolean isFillEnabled() {
        return fillEnabled;
    }

    public void setFillEnabled(boolean fillEnabled) {
        this.fillEnabled = fillEnabled;
    }

    public DrawStyle createDrawStyle() {
        return new DrawStyle(strokeColor, fillColor, strokeWidth, fillEnabled);
    }
}
