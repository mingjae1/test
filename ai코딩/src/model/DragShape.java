package model;

import java.awt.Point;

public abstract class DragShape implements DrawableShape {
    protected final Point start;
    protected Point end;
    protected final DrawStyle style;

    protected DragShape(Point start, DrawStyle style) {
        this.start = new Point(start);
        this.end = new Point(start);
        this.style = style;
    }

    @Override
    public void updateEnd(Point endPoint) {
        this.end = new Point(endPoint);
    }

    protected int x() {
        return Math.min(start.x, end.x);
    }

    protected int y() {
        return Math.min(start.y, end.y);
    }

    protected int w() {
        return Math.abs(end.x - start.x);
    }

    protected int h() {
        return Math.abs(end.y - start.y);
    }

    public Point getStart() {
        return new Point(start);
    }

    public Point getEnd() {
        return new Point(end);
    }
}
