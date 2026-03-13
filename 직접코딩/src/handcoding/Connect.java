package handcoding;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Connect {

    DrawingPanel drawingPanel;
    JFrame parentFrame;

    public Connect(DrawingPanel drawingPanel, JFrame parentFrame) {
        this.drawingPanel = drawingPanel;
        this.parentFrame = parentFrame;
    }

    // 도구 선택
    public void onPencil() {
        drawingPanel.currentTool = "PENCIL";
    }

    public void onEraser() {
        drawingPanel.currentTool = "ERASER";
    }

    public void onRect() {
        drawingPanel.currentTool = "RECT";
    }

    public void onCircle() {
        drawingPanel.currentTool = "CIRCLE";
    }

    public void onLine() {
        drawingPanel.currentTool = "LINE";
    }

    // 새로 만들기
    public void onNew() {
        int answer = JOptionPane.showConfirmDialog(parentFrame, "현재 그림을 지우고 새로 시작할까요?", "새로 만들기", JOptionPane.YES_NO_OPTION);
        if (answer == JOptionPane.YES_OPTION) {
            drawingPanel.clear();
        }
    }

    // 파일 열기
    public void onOpen() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new FileNameExtensionFilter("이미지 파일 (PNG, JPG)", "png", "jpg", "jpeg"));
        int result = chooser.showOpenDialog(parentFrame);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                BufferedImage img = ImageIO.read(chooser.getSelectedFile());
                drawingPanel.setImage(img);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(parentFrame, "파일을 열 수 없습니다.");
            }
        }
    }

    // 파일 저장
    public void onSave() {
        JFileChooser chooser = new JFileChooser();
        chooser.setSelectedFile(new File("drawing.png"));
        int result = chooser.showSaveDialog(parentFrame);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            try {
                ImageIO.write(drawingPanel.baseImage, "PNG", file);
                JOptionPane.showMessageDialog(parentFrame, "저장 완료!");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(parentFrame, "저장 실패.");
            }
        }
    }

    // 색상 선택
    public void onColorPick() {
        Color chosen = JColorChooser.showDialog(parentFrame, "색상 선택", drawingPanel.currentColor);
        if (chosen != null) {
            drawingPanel.currentColor = chosen;
        }
    }

    // 두께 변경
    public void onStrokeChange(int width) {
        drawingPanel.strokeWidth = width;
    }
}