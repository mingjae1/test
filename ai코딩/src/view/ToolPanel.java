package view;

import model.ToolType;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class ToolPanel extends JPanel {
    private static final long serialVersionUID = 1L;
	private static final Color[] PALETTE = {
        new Color(0, 0, 0),
        new Color(120, 120, 120),
        new Color(255, 255, 255),
        new Color(216, 0, 0),
        new Color(255, 140, 0),
        new Color(255, 210, 0),
        new Color(46, 125, 50),
        new Color(0, 121, 191),
        new Color(25, 49, 132),
        new Color(106, 27, 154),
        new Color(214, 51, 132),
        new Color(121, 85, 72)
    };

    public ToolPanel(ToolPanelListener listener) {
        setLayout(new BorderLayout(12, 8));
        setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(205, 205, 205)),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        setBackground(new Color(245, 247, 250));

        JPanel toolGroup = createToolGroup(listener);
        JPanel optionGroup = createOptionGroup(listener);
        JPanel colorGroup = createColorGroup(listener);

        JButton clearButton = new JButton("전체 지우기");
        clearButton.addActionListener(e -> listener.onClearRequested());

        JPanel leftGroup = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        leftGroup.setOpaque(false);
        leftGroup.add(toolGroup);
        leftGroup.add(optionGroup);
        leftGroup.add(colorGroup);

        JPanel rightGroup = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        rightGroup.setOpaque(false);
        rightGroup.add(clearButton);

        add(leftGroup, BorderLayout.CENTER);
        add(rightGroup, BorderLayout.EAST);
    }

    private JPanel createToolGroup(ToolPanelListener listener) {
        ButtonGroup buttonGroup = new ButtonGroup();

        JToggleButton rectButton = new JToggleButton("사각형");
        JToggleButton ellipseButton = new JToggleButton("타원");
        JToggleButton lineButton = new JToggleButton("직선");
        JToggleButton pencilButton = new JToggleButton("연필");
        JToggleButton eraserButton = new JToggleButton("지우개");

        rectButton.setSelected(true);

        rectButton.addActionListener(e -> listener.onToolSelected(ToolType.RECTANGLE));
        ellipseButton.addActionListener(e -> listener.onToolSelected(ToolType.ELLIPSE));
        lineButton.addActionListener(e -> listener.onToolSelected(ToolType.LINE));
        pencilButton.addActionListener(e -> listener.onToolSelected(ToolType.PENCIL));
        eraserButton.addActionListener(e -> listener.onToolSelected(ToolType.ERASER));

        buttonGroup.add(rectButton);
        buttonGroup.add(ellipseButton);
        buttonGroup.add(lineButton);
        buttonGroup.add(pencilButton);
        buttonGroup.add(eraserButton);

        JPanel toolGroup = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 0));
        toolGroup.setOpaque(false);
        toolGroup.setBorder(BorderFactory.createTitledBorder("도구"));
        toolGroup.add(rectButton);
        toolGroup.add(ellipseButton);
        toolGroup.add(lineButton);
        toolGroup.add(pencilButton);
        toolGroup.add(eraserButton);

        return toolGroup;
    }

    private JPanel createOptionGroup(ToolPanelListener listener) {
        JComboBox<String> thicknessBox = new JComboBox<>(new String[] {"1 px", "2 px", "4 px", "8 px", "12 px"});
        thicknessBox.setSelectedItem("2 px");
        thicknessBox.addActionListener(e -> {
            String selected = (String) thicknessBox.getSelectedItem();
            if (selected != null) {
                float width = Float.parseFloat(selected.split(" ")[0]);
                listener.onThicknessSelected(width);
            }
        });

        JCheckBox fillCheck = new JCheckBox("도형 채우기");
        fillCheck.setOpaque(false);
        fillCheck.addActionListener(e -> listener.onFillEnabledChanged(fillCheck.isSelected()));

        JPanel optionGroup = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        optionGroup.setOpaque(false);
        optionGroup.setBorder(BorderFactory.createTitledBorder("옵션"));
        optionGroup.add(new JLabel("두께:"));
        optionGroup.add(thicknessBox);
        optionGroup.add(fillCheck);
        return optionGroup;
    }

    private JPanel createColorGroup(ToolPanelListener listener) {
        JToggleButton strokeTarget = new JToggleButton("선색");
        JToggleButton fillTarget = new JToggleButton("채우기색");
        strokeTarget.setSelected(true);

        ButtonGroup colorTargetGroup = new ButtonGroup();
        colorTargetGroup.add(strokeTarget);
        colorTargetGroup.add(fillTarget);

        JPanel targetGroup = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 0));
        targetGroup.setOpaque(false);
        targetGroup.add(strokeTarget);
        targetGroup.add(fillTarget);

        JPanel paletteGrid = new JPanel(new GridLayout(2, 6, 4, 4));
        paletteGrid.setOpaque(false);

        for (Color color : PALETTE) {
            JButton colorButton = new JButton();
            colorButton.setPreferredSize(new Dimension(22, 22));
            colorButton.setBackground(color);
            colorButton.setFocusPainted(false);
            colorButton.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
            colorButton.addActionListener(e -> listener.onColorSelected(color, strokeTarget.isSelected()));
            paletteGrid.add(colorButton);
        }

        JPanel colorGroup = new JPanel(new BorderLayout(4, 4));
        colorGroup.setOpaque(false);
        colorGroup.setBorder(BorderFactory.createTitledBorder("색상 팔레트"));
        colorGroup.add(targetGroup, BorderLayout.NORTH);
        colorGroup.add(paletteGrid, BorderLayout.CENTER);
        return colorGroup;
    }
}
