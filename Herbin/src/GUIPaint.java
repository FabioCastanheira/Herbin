
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class GUIPaint {

    private static JFrame frame;

    public static void paintOnFrame(JComponent component, String frameName) {
        frame = new JFrame(frameName);
        WindowAdapter wa = new WindowAdapter() {

            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        };
        frame.addWindowListener(wa);
        frame.getContentPane().add(component);
        frame.pack();
        frame.setVisible(true);

    }

    public static void add(JComponent component) {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(component);
        frame.pack();
        frame.setVisible(true);
    }
    
    public static void destroy(){
        frame.dispose();
    }
}
