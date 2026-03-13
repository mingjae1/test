package handcoding;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class DrawingPanel extends JPanel {

    private static final long serialVersionUID = 1L;
	// 현재 선택된 도구 (문자열로 관리)
    String currentTool = "PENCIL";
    Color currentColor = Color.BLACK;
    int strokeWidth = 3;

    // 캔버스 이미지
    BufferedImage baseImage;

    // 드래그 시작/끝 좌표
    int startX, startY;
    int endX, endY;
    boolean isDragging = false;

    public DrawingPanel(int width, int height) {
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.WHITE);

        // 흰 배경 이미지 생성
        baseImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = (Graphics2D) baseImage.getGraphics();
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, width, height);
        g2.dispose();

        // 마우스 눌렀을 때
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                startX = e.getX();
                startY = e.getY();
                endX = e.getX();
                endY = e.getY();
                isDragging = true;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                endX = e.getX();
                endY = e.getY();
                // 연필/지우개가 아닌 경우 마우스를 뗄 때 도형 확정
                if (!currentTool.equals("PENCIL") && !currentTool.equals("ERASER")) {
                    drawShapeOnBase(startX, startY, endX, endY);
                }
                isDragging = false;
                repaint();
            }
        });

        // 마우스 드래그할 때
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                endX = e.getX();
                endY = e.getY();
                // 연필/지우개는 드래그하면서 바로 그리기
                if (currentTool.equals("PENCIL") || currentTool.equals("ERASER")) {
                    drawLineOnBase(startX, startY, endX, endY);
                    startX = e.getX();
                    startY = e.getY();
                }
                repaint();
            }
        });
    }

    // 연필/지우개: 선 그리기
    void drawLineOnBase(int x1, int y1, int x2, int y2) {
        Graphics2D g2 = (Graphics2D) baseImage.getGraphics();

        if (currentTool.equals("ERASER")) {
            g2.setColor(Color.WHITE);
            g2.setStroke(new BasicStroke(strokeWidth * 5));
        } else {
            g2.setColor(currentColor);
            g2.setStroke(new BasicStroke(strokeWidth));
        }

        g2.drawLine(x1, y1, x2, y2);
        g2.dispose();
    }

    // 사각형/원/직선: 도형 그리기
    void drawShapeOnBase(int x1, int y1, int x2, int y2) {
        Graphics2D g2 = (Graphics2D) baseImage.getGraphics();
        g2.setColor(currentColor);
        g2.setStroke(new BasicStroke(strokeWidth));

        int x = Math.min(x1, x2);
        int y = Math.min(y1, y2);
        int w = Math.abs(x2 - x1);
        int h = Math.abs(y2 - y1);

        if (currentTool.equals("RECT")) {
            g2.drawRect(x, y, w, h);
        } else if (currentTool.equals("CIRCLE")) {
            g2.drawOval(x, y, w, h);
        } else if (currentTool.equals("LINE")) {
            g2.drawLine(x1, y1, x2, y2);
        }

        g2.dispose();
    }

    // 화면 그리기 (자동 호출됨)
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // 저장된 이미지 그리기
        g.drawImage(baseImage, 0, 0, null);

        // 드래그 중 미리보기 (사각형/원/직선만 점선으로 표시)
        if (isDragging && !currentTool.equals("PENCIL") && !currentTool.equals("ERASER")) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(currentColor);

            float[] dashPattern = {5f, 5f};
            g2.setStroke(new BasicStroke(strokeWidth, BasicStroke.CAP_ROUND,
                    BasicStroke.JOIN_ROUND, 10f, dashPattern, 0f));

            int x = Math.min(startX, endX);
            int y = Math.min(startY, endY);
            int w = Math.abs(endX - startX);
            int h = Math.abs(endY - startY);

            if (currentTool.equals("RECT")) {
                g2.drawRect(x, y, w, h);
            } else if (currentTool.equals("CIRCLE")) {
                g2.drawOval(x, y, w, h);
            } else if (currentTool.equals("LINE")) {
                g2.drawLine(startX, startY, endX, endY);
            }
        }
    }

    // 캔버스 전체 지우기
    void clear() {
        Graphics2D g2 = (Graphics2D) baseImage.getGraphics();
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, baseImage.getWidth(), baseImage.getHeight());
        g2.dispose();
        repaint();
    }

    // 이미지 불러오기
    void setImage(BufferedImage img) {
        baseImage = img;
        setPreferredSize(new Dimension(img.getWidth(), img.getHeight()));
        revalidate();
        repaint();
    }
}