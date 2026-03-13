package model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class FreehandShape implements DrawableShape {
    private final List<Point> points = new ArrayList<>();
    private final Color color;
    private final float strokeWidth;

    public FreehandShape(Point start, Color color, float strokeWidth) {
        this.color = color;
        this.strokeWidth = strokeWidth;
        points.add(new Point(start));
    }

    @Override
    public void updateEnd(Point endPoint) {
        points.add(new Point(endPoint));
    }

    @Override
    public void draw(Graphics2D g2) {
        if (points.size() < 2) {
            return;
        }

        g2.setColor(color);
        g2.setStroke(new BasicStroke(strokeWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        for (int i = 1; i < points.size(); i++) {
            Point p1 = points.get(i - 1);
            Point p2 = points.get(i);
            g2.drawLine(p1.x, p1.y, p2.x, p2.y);
        }
    }
}
