import javax.swing.SwingUtilities;
import controller.EditorController;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EditorController().show());
    }
}