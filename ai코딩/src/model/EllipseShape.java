package model;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Point;

public class EllipseShape extends DragShape {
    public EllipseShape(Point start, DrawStyle style) {
        super(start, style);
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setStroke(new BasicStroke(style.getStrokeWidth(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        if (style.isFillEnabled()) {
            g2.setColor(style.getFillColor());
            g2.fillOval(x(), y(), w(), h());
        }
        g2.setColor(style.getStrokeColor());
        g2.drawOval(x(), y(), w(), h());
    }

    public int getX() {
        return x();
    }

    public int getY() {
        return y();
    }

    public int getW() {
        return w();
    }

    public int getH() {
        return h();
    }
}
