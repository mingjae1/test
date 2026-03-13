package model;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Point;

public class LineShape extends DragShape {
    public LineShape(Point start, DrawStyle style) {
        super(start, style);
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(style.getStrokeColor());
        g2.setStroke(new BasicStroke(style.getStrokeWidth(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2.drawLine(start.x, start.y, end.x, end.y);
    }
}
