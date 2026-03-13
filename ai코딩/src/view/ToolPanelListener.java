package view;

import model.ToolType;
import java.awt.Color;

public interface ToolPanelListener {
    void onToolSelected(ToolType toolType);

    void onThicknessSelected(float width);

    void onFillEnabledChanged(boolean fillEnabled);

    void onColorSelected(Color color, boolean strokeTarget);

    void onClearRequested();
}
