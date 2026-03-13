package view;
import model.CanvasModel;
import model.DrawableShape;
import model.EllipseShape;
import model.LineShape;
import model.RectangleShape;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class DrawingCanvas extends JPanel {
    private final CanvasModel model;
    private DrawableShape previewShape;

    private static final long serialVersionUID = 1L;

    public DrawingCanvas(CanvasModel model) {
        this.model = model;
        setBackground(new Color(250, 250, 250));
        setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
    }

    public void setPreviewShape(DrawableShape previewShape) {
        this.previewShape = previewShape;
    }

    public void clearPreview() {
        this.previewShape = null;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (DrawableShape shape : model.getShapes()) {
            shape.draw(g2);
        }

        if (previewShape != null) {
            previewShape.draw(g2);
            drawPreviewOutline(g2);
        }

        g2.dispose();
    }

    private void drawPreviewOutline(Graphics2D g2) {
        Stroke oldStroke = g2.getStroke();
        g2.setColor(new Color(80, 80, 80, 120));
        g2.setStroke(new BasicStroke(1.2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 0f, new float[] {4f, 4f}, 0f));

        if (previewShape instanceof RectangleShape rect) {
            g2.drawRect(rect.getX(), rect.getY(), rect.getW(), rect.getH());
        } else if (previewShape instanceof EllipseShape ellipse) {
            g2.drawOval(ellipse.getX(), ellipse.getY(), ellipse.getW(), ellipse.getH());
        } else if (previewShape instanceof LineShape line) {
            g2.drawLine(line.getStart().x, line.getStart().y, line.getEnd().x, line.getEnd().y);
        }

        g2.setStroke(oldStroke);
    }
}
