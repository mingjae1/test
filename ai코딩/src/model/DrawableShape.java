package model;

import java.awt.Graphics2D;
import java.awt.Point;

public interface DrawableShape {
    void updateEnd(Point endPoint);

    void draw(Graphics2D g2);
}
