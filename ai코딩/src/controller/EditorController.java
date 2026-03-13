package controller;

import model.CanvasModel;
import model.DrawableShape;
import model.EllipseShape;
import model.FreehandShape;
import model.LineShape;
import model.RectangleShape;
import model.ToolType;
import view.DrawingCanvas;
import view.EditorFrame;
import view.StatusBar;
import view.ToolPanel;
import view.ToolPanelListener;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EditorController implements ToolPanelListener {
    private final CanvasModel model;
    private final EditorFrame frame;
    private DrawableShape previewShape;

    public EditorController() {
        this.model = new CanvasModel();

        DrawingCanvas canvas = new DrawingCanvas(model);
        StatusBar statusBar = new StatusBar();
        ToolPanel toolPanel = new ToolPanel(this);
        this.frame = new EditorFrame(canvas, toolPanel, statusBar);

        statusBar.setTool(model.getCurrentTool());
        bindCanvasEvents();
    }

    public void show() {
        frame.setVisible(true);
    }

    public void onToolSelected(ToolType toolType) {
        model.setCurrentTool(toolType);
        frame.getStatusBar().setTool(toolType);
    }


    public void onThicknessSelected(float width) {
        model.setStrokeWidth(width);
    }


    public void onFillEnabledChanged(boolean fillEnabled) {
        model.setFillEnabled(fillEnabled);
    }


    public void onColorSelected(Color color, boolean strokeTarget) {
        if (strokeTarget) {
            model.setStrokeColor(color);
        } else {
            model.setFillColor(color);
        }
    }


    public void onClearRequested() {
        model.clearShapes();
        previewShape = null;
        frame.getCanvas().clearPreview();
        frame.getCanvas().repaint();
    }

    private void bindCanvasEvents() {
        MouseAdapter dragHandler = new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                previewShape = createShape(model.getCurrentTool(), e.getPoint());
                frame.getCanvas().setPreviewShape(previewShape);
            }


            @Override
            public void mouseDragged(MouseEvent e) {
                if (previewShape == null) {
                    return;
                }
                previewShape.updateEnd(e.getPoint());
                frame.getCanvas().repaint();
            }


            @Override
            public void mouseReleased(MouseEvent e) {
                if (previewShape == null) {
                    return;
                }
                previewShape.updateEnd(e.getPoint());
                model.addShape(previewShape);
                previewShape = null;
                frame.getCanvas().clearPreview();
                frame.getCanvas().repaint();
            }
        };

        frame.getCanvas().addMouseListener(dragHandler);
        frame.getCanvas().addMouseMotionListener(dragHandler);
    }

    private DrawableShape createShape(ToolType toolType, Point start) {
        return switch (toolType) {
            case RECTANGLE -> new RectangleShape(start, model.createDrawStyle());
            case ELLIPSE -> new EllipseShape(start, model.createDrawStyle());
            case LINE -> new LineShape(start, model.createDrawStyle());
            case PENCIL -> new FreehandShape(start, model.getStrokeColor(), model.getStrokeWidth());
            case ERASER -> new FreehandShape(start, Color.WHITE, Math.max(10.0f, model.getStrokeWidth() * 3.0f));
        };
    }
}
