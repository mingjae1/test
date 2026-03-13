package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class EditorFrame extends JFrame {
    private static final long serialVersionUID = 1L;
	private final DrawingCanvas canvas;
    private final StatusBar statusBar;

    public EditorFrame(DrawingCanvas canvas, ToolPanel toolPanel, StatusBar statusBar) {
        super("그래픽 편집기 - Paint 스타일");
        this.canvas = canvas;
        this.statusBar = statusBar;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        applySystemLookAndFeel();

        add(toolPanel, BorderLayout.NORTH);
        add(canvas, BorderLayout.CENTER);
        add(statusBar, BorderLayout.SOUTH);

        setPreferredSize(new Dimension(1024, 680));
        pack();
        setLocationRelativeTo(null);
    }

    public DrawingCanvas getCanvas() {
        return canvas;
    }

    public StatusBar getStatusBar() {
        return statusBar;
    }

    private void applySystemLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ignored) {
            // Use default look and feel if system theme is unavailable.
        }
    }
}
