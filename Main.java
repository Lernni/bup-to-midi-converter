import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {
  public static void main(String[] args) {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
        | UnsupportedLookAndFeelException e) {
    }
    new Application();
  }
}