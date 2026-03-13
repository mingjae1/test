package handcoding;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GraphicEditor extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GraphicEditor() {
        setTitle("Graphic Editor");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // ── 파일 버튼 패널 (상단 상단) ──────────────────────────
        JPanel filePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JButton openbtn = new JButton("열기");
        JButton savebtn = new JButton("저장");
        JButton newbtn = new JButton("새로 만들기");
        filePanel.add(openbtn);
        filePanel.add(new JLabel(" | "));
        filePanel.add(savebtn);
        filePanel.add(new JLabel(" | "));
        filePanel.add(newbtn);

        // ── 도구 툴바 ──────────────────────────────────────
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);

        JToggleButton drawbtn   = new JToggleButton("연필");
        JToggleButton erasebtn  = new JToggleButton("지우개");
        JToggleButton ractbtn   = new JToggleButton("사각형");
        JToggleButton crcbtn    = new JToggleButton("원");
        JToggleButton linebtn   = new JToggleButton("직선");
        JButton       colorBtn  = new JButton("색상");

        // 버튼 그룹 (하나만 선택되도록)
        ButtonGroup group = new ButtonGroup();
        group.add(drawbtn);
        group.add(erasebtn);
        group.add(ractbtn);
        group.add(crcbtn);
        group.add(linebtn);

        drawbtn.setSelected(true); // 기본 선택

        toolBar.add(drawbtn);
        toolBar.add(erasebtn);
        toolBar.addSeparator();
        toolBar.add(ractbtn);
        toolBar.add(crcbtn);
        toolBar.add(linebtn);
        toolBar.addSeparator();
        toolBar.add(colorBtn);
        toolBar.addSeparator();

        // 두께 슬라이더
        toolBar.add(new JLabel(" 두께: "));
        JSlider strokeSlider = new JSlider(1, 20, 3);
        strokeSlider.setMaximumSize(new Dimension(120, 30));
        strokeSlider.setMajorTickSpacing(5);
        strokeSlider.setPaintTicks(true);
        toolBar.add(strokeSlider);
        JLabel strokeLabel = new JLabel("3px ");
        toolBar.add(strokeLabel);

        // ── 상단 전체 패널 ──────────────────────────────────
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(filePanel, BorderLayout.NORTH);
        topPanel.add(toolBar, BorderLayout.SOUTH);
        add(topPanel, BorderLayout.NORTH);

        // ── 캔버스 (DrawingPanel) ───────────────────────────
        DrawingPanel drawingPanel = new DrawingPanel(1600, 900);
        JScrollPane scrollPane = new JScrollPane(drawingPanel);
        add(scrollPane, BorderLayout.CENTER);

        // ── Connect: 버튼 ↔ DrawingPanel 연결 ───────────────
        Connect connect = new Connect(drawingPanel, this);

        // 도구 버튼
        drawbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                connect.onPencil();
            }
        });
        erasebtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                connect.onEraser();
            }
        });
        ractbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                connect.onRect();
            }
        });
        crcbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                connect.onCircle();
            }
        });
        linebtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                connect.onLine();
            }
        });

        // 파일 버튼
        newbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                connect.onNew();
            }
        });
        openbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                connect.onOpen();
            }
        });
        savebtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                connect.onSave();
            }
        });

        // 색상 버튼
        colorBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                connect.onColorPick();
            }
        });

        // 두께 슬라이더
        strokeSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                int val = strokeSlider.getValue();
                strokeLabel.setText(val + "px ");
                connect.onStrokeChange(val);
            }
        });
    }
}