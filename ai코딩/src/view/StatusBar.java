package view;

import model.ToolType;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StatusBar extends JPanel {
    private static final long serialVersionUID = 1L;
	private final JLabel toolLabel = new JLabel("도구: 사각형");
    private final JLabel hintLabel = new JLabel("팁: 도구를 선택하고 캔버스를 드래그하세요");

    public StatusBar() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(220, 220, 220)),
            BorderFactory.createEmptyBorder(4, 8, 4, 8)
        ));
        setBackground(new Color(248, 248, 248));
        add(toolLabel, BorderLayout.WEST);
        add(hintLabel, BorderLayout.EAST);
    }

    public void setTool(ToolType toolType) {
        String name = switch (toolType) {
            case RECTANGLE -> "사각형";
            case ELLIPSE -> "타원";
            case LINE -> "직선";
            case PENCIL -> "연필";
            case ERASER -> "지우개";
        };
        toolLabel.setText("도구: " + name);
    }
}
